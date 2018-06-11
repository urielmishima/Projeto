package command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Pontuacao;
import service.AtendimentoService;
import service.PontuacaoService;

public class NegativarResposta implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pIdCliente = request.getParameter("idCliente");
		String pIdPalavraChave = request.getParameter("idPalavraChave");
		String pIdResposta = request.getParameter("idResposta");
		String pContador = request.getParameter("contador");
		
		int idCliente = Integer.parseInt(pIdCliente);
		int IdResposta = Integer.parseInt(pIdResposta);
		int IdPalavraChave = Integer.parseInt(pIdPalavraChave);
		int contador = Integer.parseInt(pContador);
		
		Pontuacao pontuacao = new Pontuacao(IdPalavraChave, IdResposta, 0);
		PontuacaoService pontuacaoService = new PontuacaoService();
		pontuacaoService.hate(pontuacao);
		
		AtendimentoService atendimentoService = new AtendimentoService();
		atendimentoService.incrementar(idCliente, contador);
	}

}
