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

public class NewMensagemFuncionario implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mensagemEnviada = request.getParameter("mensagemEnviada");
		String idCliente = request.getParameter("idCliente");
		
		int id = Integer.parseInt(idCliente);
		
		Gson gson = new Gson();	
		
		Atendimento atendimento = new Atendimento();
		atendimento.setId(id);
		
		Mensagem mensagem = new Mensagem(0, mensagemEnviada, RemetenteMensagem.FUNCIONARIO, atendimento);
		MensagemService mensagemService = new MensagemService();
		mensagemService.newMensagem(mensagem);
	}

}
