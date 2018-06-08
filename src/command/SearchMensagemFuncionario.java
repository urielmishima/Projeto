package command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import enums.RemetenteMensagem;
import model.Atendimento;
import model.Mensagem;
import service.MensagemService;

public class SearchMensagemFuncionario implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Gson gson = new Gson();	
		
		Mensagem mensagem = gson.fromJson((String) session.getAttribute("mensagem"), Mensagem.class);
		MensagemService mensagemService = new MensagemService();
		
		if(mensagem == null) {
			Atendimento atendimento = gson.fromJson((String) session.getAttribute("atendimento"), Atendimento.class);
			mensagem = new Mensagem(0, null, RemetenteMensagem.FUNCIONARIO, atendimento);
		}
		
		mensagem = mensagemService.searchMensagem(mensagem);
		session.setAttribute("mensagemRecebida",  gson.toJson(mensagem));
		

	}

}
