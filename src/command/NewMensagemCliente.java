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

public class NewMensagemCliente implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mensagemEnviada = request.getParameter("mensagemEnviada");
		
		HttpSession session = request.getSession();
		Gson gson = new Gson();	
		
		Atendimento atendimento = gson.fromJson((String) session.getAttribute("atendimento"), Atendimento.class);
		
		Mensagem mensagem = new Mensagem(0, mensagemEnviada, RemetenteMensagem.CLIENTE, atendimento);
		MensagemService mensagemService = new MensagemService();
		mensagemService.newMensagem(mensagem);
	}

}
