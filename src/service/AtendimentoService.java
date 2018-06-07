package service;

import dao.AtendimentoDAO;
import enums.StatusFuncionario;
import model.Atendimento;
import model.Funcionario;

public class AtendimentoService {
	AtendimentoDAO dao = new AtendimentoDAO();

	public int newChatBot(Atendimento atendimento) {
		return dao.newChatBot(atendimento);
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
		Funcionario funcionario = null;
		FuncionarioService funcionarioService = new FuncionarioService();
		while(funcionario == null) {
			funcionario = funcionarioService.findByStatus(StatusFuncionario.DISPONIVEL);
		}
		funcionarioService.statusIndisponivel(funcionario);
		atendimento.setFuncionario(funcionario);
		dao.newChat(atendimento);
	}

	public Atendimento newAtendimento(Funcionario funcionario) {
		Atendimento atendimento = null;
		while(atendimento == null) {
			atendimento = dao.newAtendimento(funcionario);
		}
		return atendimento;
	}
}
