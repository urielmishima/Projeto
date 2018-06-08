package command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import enums.StatusFuncionario;
import model.Atendimento;
import model.Funcionario;
import service.AtendimentoService;
import service.FuncionarioService;

public class NewAtendimento implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Gson gson = new Gson();
		
		Funcionario funcionario = gson.fromJson((String) session.getAttribute("funcionario"), Funcionario.class);
		funcionario.setStatus(StatusFuncionario.DISPONIVEL);
		FuncionarioService funcionarioService = new FuncionarioService();
		funcionarioService.alterStatus(funcionario);
		Atendimento atendimento = new Atendimento();
		AtendimentoService atendimentoService = new AtendimentoService();
		atendimento = atendimentoService.newAtendimento(funcionario);
		funcionario.setStatus(StatusFuncionario.INDISPONIVEL);
		
		session.setAttribute("atendimento",  gson.toJson(atendimento));	
		session.setAttribute("funcionario",  gson.toJson(funcionario));	

	}

}
