package service;

import java.sql.Date;

import dao.AtendimentoDAO;
import enums.StatusFuncionario;
import model.Atendimento;
import model.Funcionario;

public class AtendimentoService {
	AtendimentoDAO dao = new AtendimentoDAO();

	public int newChatBot(Atendimento atendimento) {
		return dao.newChatBot(atendimento);
	}

	public void newPergunta(Date date, int id, String duvida) {
		dao.newPergunta(date, id, duvida);
	}

	public void newChat(Atendimento atendimento) {
		Funcionario funcionario = null;
		FuncionarioService funcionarioService = new FuncionarioService();
		while(funcionario == null) {
			funcionario = funcionarioService.findByStatus(StatusFuncionario.DISPONIVEL);
		}
		funcionario.setStatus(StatusFuncionario.INDISPONIVEL);
		funcionarioService.alterStatus(funcionario);
		atendimento.setFuncionario(funcionario);
		dao.newChat(atendimento, funcionario);
	}

	public Atendimento newAtendimento(Funcionario funcionario) {
		Atendimento atendimento = null;
		while(atendimento == null) {
			atendimento = dao.newAtendimento(funcionario);
		}
		return atendimento;
	}

	public void endChat(Atendimento atendimento) {
		dao.endChat(atendimento);
		
	}

	public void endChatBot(int idCliente, Date date, int contador) {
		dao.endChatBot(idCliente, date, contador+1);
		
	}

	public void incrementar(int idCliente, int contador) {
		dao.incrementar(idCliente, contador+1);
		
	}
}
