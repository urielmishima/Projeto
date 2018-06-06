package service;

import dao.AtendimentoDAO;
import model.Atendimento;

public class AtendimentoService {
	AtendimentoDAO dao = new AtendimentoDAO();

	public int newAtendimento(Atendimento atendimento) {
		return dao.newAtendimento(atendimento);
	}

	public void newPergunta(Atendimento atendimento) {
		dao.newPergunta(atendimento);

	}

	public void endChatBot(Atendimento atendimento) {
		PontuacaoService pontuacaoService = new PontuacaoService();
		for (int i = 0; i < atendimento.getRespostas().size(); i++) {
			if (i + 1 != atendimento.getRespostas().size()) {
				pontuacaoService.hate(atendimento.getRespostas().get(i).getPontuacao());
			} else {
				pontuacaoService.like(atendimento.getRespostas().get(i).getPontuacao());
			}
		}
		atendimento.setInteracoes(atendimento.getRespostas().size());
		dao.endChatBot(atendimento);
	}

	public void newChat(Atendimento atendimento) {
		PontuacaoService pontuacaoService = new PontuacaoService();
		for (int i = 0; i < atendimento.getRespostas().size(); i++) {
			pontuacaoService.hate(atendimento.getRespostas().get(i).getPontuacao());			
		}
		atendimento.setInteracoes(3);
		dao.endChatBot(atendimento);
		***
	}
}
