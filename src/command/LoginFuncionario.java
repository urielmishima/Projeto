package command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import enums.StatusFuncionario;
import enums.TipoFuncionario;
import model.Funcionario;
import service.FuncionarioService;

public class LoginFuncionario implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pId = request.getParameter("id");
		String senha = request.getParameter("senha");
		
		int id = Integer.parseInt(pId);
		
		Gson gson = new Gson();	
		
		Funcionario funcionario = new Funcionario(id, senha, TipoFuncionario.ATENDENTE, StatusFuncionario.INDISPONIVEL);
		FuncionarioService funcionarioService = new FuncionarioService();
		funcionario = funcionarioService.logar(funcionario);
		if(funcionario != null) {
			response.getWriter().print(gson.toJson(funcionario));
		}
	}

}
