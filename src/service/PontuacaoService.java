package service;

import dao.PontuacaoDAO;
import model.Pontuacao;

public class PontuacaoService {
	PontuacaoDAO dao = new PontuacaoDAO();
	
	public void like(Pontuacao pontuacao) {
		pontuacao.setPontuacao(pontuacao.getPontuacao()+1);
		dao.update(pontuacao);
	}
	
	public void hate(Pontuacao pontuacao) {
		pontuacao = dao.select(pontuacao);
		pontuacao.setPontuacao(pontuacao.getPontuacao()-1);
		dao.update(pontuacao);
	}

	public void endChatBot(Pontuacao pontuacao) {
		pontuacao = dao.select(pontuacao);
		like(pontuacao);	
	}


}
