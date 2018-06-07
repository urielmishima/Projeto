package service;

import dao.FuncionarioDAO;
import enums.StatusFuncionario;
import model.Funcionario;

public class FuncionarioService {
	FuncionarioDAO dao = new FuncionarioDAO();

	public Funcionario findByStatus(StatusFuncionario status) {
		return dao.findByStatus(status);
	}

	public Funcionario logar(Funcionario funcionario) {
		return dao.logar(funcionario);
	}

	public void statusIndisponivel(Funcionario funcionario) {
		dao.statusIndisponivel(funcionario);		
	}
	
	
}
