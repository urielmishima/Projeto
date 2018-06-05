package command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import enums.StatusAtendimento;
import model.Atendimento;
import model.Cliente;
import service.AtendimentoService;
import service.ClienteService;

public class entrarCliente implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		
		Cliente cliente = new Cliente(0, nome, email);
		ClienteService clienteService = new ClienteService();	
		cliente = clienteService.entrar(cliente);
		
		Atendimento atendimento = new Atendimento(0, null, null, 0, StatusAtendimento.BOT, cliente, null, null);
		AtendimentoService atendimentoService = new AtendimentoService();
		atendimento = atendimentoService.newAtendimento(atendimento);
		
		Gson gson = new Gson();	
		String json = gson.toJson(atendimento);
		
		HttpSession session = request.getSession();
		session.setAttribute("atendimento", json);
		response.sendRedirect("chatbot.jsp");
	}

}
