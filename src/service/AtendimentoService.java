package service;

import dao.AtendimentoDAO;
import model.Atendimento;

public class AtendimentoService {
	AtendimentoDAO dao = new AtendimentoDAO();

	public Atendimento newAtendimento(Atendimento atendimento) {
		return dao.findById(dao.insert(atendimento));
	}
}
