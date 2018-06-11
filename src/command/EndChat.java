package command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import enums.StatusAtendimento;
import model.Atendimento;
import service.AtendimentoService;

public class EndChat implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idCliente = request.getParameter("idCliente");
		
		int id = Integer.parseInt(idCliente);
		
		Atendimento atendimento = new Atendimento();
		atendimento.setId(id);
		atendimento.setStatus(StatusAtendimento.RESOLVIDO_FUNCIONARIO);
		AtendimentoService atendimentoService = new AtendimentoService();
		atendimentoService.endChat(atendimento);
	}

}
