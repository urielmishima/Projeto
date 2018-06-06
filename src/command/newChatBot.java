package command;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.Atendimento;
import model.Resposta;
import service.AtendimentoService;
import service.RespostaService;

public class NewChatBot implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String duvida = request.getParameter("duvida");
		
		HttpSession session = request.getSession();
		Gson gson = new Gson();	
		
		Atendimento atendimento = gson.fromJson((String) session.getAttribute("atendimento"), Atendimento.class);
		atendimento.setDtInicio(new Date(System.currentTimeMillis()));
		atendimento.setDuvida(duvida);
		AtendimentoService atendimentoService = new AtendimentoService();
		atendimentoService.newPergunta(atendimento);
		session.setAttribute("atendimento",  gson.toJson(atendimento));
		
		RespostaService respostaService = new RespostaService();
		List<Resposta> respostas = respostaService.findByDuvida(duvida);
		session.setAttribute("respostas",  gson.toJson(respostas));
	}

}
	