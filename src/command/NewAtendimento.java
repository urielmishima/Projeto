package command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import enums.StatusFuncionario;
import model.Atendimento;
import model.Cliente;
import model.Funcionario;
import service.AtendimentoService;
import service.ClienteService;
import service.FuncionarioService;

public class NewAtendimento implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pIdFuncionario = request.getParameter("idFuncionario");
		
		int id = Integer.parseInt(pIdFuncionario);
		
		Gson gson = new Gson();
		
		Funcionario funcionario = new Funcionario();
		funcionario.setId(id);
		funcionario.setStatus(StatusFuncionario.DISPONIVEL);
		FuncionarioService funcionarioService = new FuncionarioService();
		funcionarioService.alterStatus(funcionario);
		Atendimento atendimento = new Atendimento();
		AtendimentoService atendimentoService = new AtendimentoService();
		atendimento = atendimentoService.newAtendimento(funcionario);
		funcionario.setStatus(StatusFuncionario.INDISPONIVEL);
		Cliente cliente = new Cliente();
		ClienteService clienteService = new ClienteService();
		cliente = clienteService.findById(atendimento.getId());
		
		response.getWriter().print(gson.toJson(cliente));
	}

}
