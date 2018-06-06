package command;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import enums.StatusAtendimento;
import model.Atendimento;
import service.AtendimentoService;

public class newChat implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Gson gson = new Gson();	
		
		Atendimento atendimento = gson.fromJson((String) session.getAttribute("atendimento"), Atendimento.class);
		atendimento.setDtFim(new Date(System.currentTimeMillis()));
		atendimento.setStatus(StatusAtendimento.FUNCIONARIO);
		AtendimentoService atendimentoService = new AtendimentoService();
		atendimentoService.newChat(atendimento);
		***
	}

}
