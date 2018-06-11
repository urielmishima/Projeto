package command;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Resposta;
import service.AtendimentoService;
import service.RespostaService;

public class NewChatBot implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String duvida = request.getParameter("duvida");
		String idCliente = request.getParameter("idCliente");
		
		int id = Integer.parseInt(idCliente);
						
		AtendimentoService atendimentoService = new AtendimentoService();
		atendimentoService.newPergunta(new Date(System.currentTimeMillis()), id, duvida);
		
		RespostaService respostaService = new RespostaService();
		List<Resposta> respostas = respostaService.findByDuvida(duvida);
		
		Gson gson = new Gson();	
		response.getWriter().print(gson.toJson(respostas));
	}

}
	