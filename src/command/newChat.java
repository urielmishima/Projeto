package command;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import enums.StatusAtendimento;
import model.Atendimento;
import service.AtendimentoService;

public class NewChat implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pIdCliente = request.getParameter("idCliente");
		int id = Integer.parseInt(pIdCliente);
				
		Atendimento atendimento = new Atendimento();
		atendimento.setId(id);
		atendimento.setDtFim(new Date(System.currentTimeMillis()));
		atendimento.setStatus(StatusAtendimento.FUNCIONARIO);
		AtendimentoService atendimentoService = new AtendimentoService();
		atendimentoService.newChat(atendimento);
		
		
	}

}
