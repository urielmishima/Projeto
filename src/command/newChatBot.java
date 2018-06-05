package command;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.Atendimento;

public class newChatBot implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String duvida = request.getParameter("duvida");
		
		HttpSession session = request.getSession();
		Gson gson = new Gson();	
		
		Atendimento atendimento = gson.fromJson((String) session.getAttribute("atendimento"), Atendimento.class);
		atendimento.setDtInicio(new Date(System.currentTimeMillis()));
		atendimento.setDuvida(duvida);
		
		
	}

}
	