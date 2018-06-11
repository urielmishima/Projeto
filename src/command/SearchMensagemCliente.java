package command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import enums.RemetenteMensagem;
import model.Atendimento;
import model.Mensagem;
import service.MensagemService;

public class SearchMensagemCliente implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pIdCliente = request.getParameter("idCliente");
		String pLastIdMensagem = request.getParameter("lastIdMensagem");
		
		int idCliente = Integer.parseInt(pIdCliente);
		int lastIdMensagem = Integer.parseInt(pLastIdMensagem);

		Gson gson = new Gson();	
		
		Mensagem mensagem = new Mensagem();
		mensagem.setId(lastIdMensagem);
		MensagemService mensagemService = new MensagemService();
		
		Atendimento atendimento = new Atendimento();
		atendimento.setId(idCliente);
		mensagem.setAtendimento(atendimento);
		mensagem.setRemetente(RemetenteMensagem.CLIENTE);
		
		mensagem = mensagemService.searchMensagem(mensagem);	
		
		response.getWriter().print(gson.toJson(mensagem));
	}
}
