package command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.Funcionario;
import service.FuncionarioService;

public class LoginFuncionario implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pId = request.getParameter("id");
		String senha = request.getParameter("senha");
		
		int id = Integer.parseInt(pId);
		
		HttpSession session = request.getSession();
		Gson gson = new Gson();	
		
		Funcionario funcionario = new Funcionario(id, senha, null, null);
		FuncionarioService funcionarioService = new FuncionarioService();
		funcionario = funcionarioService.logar(funcionario);
		if(funcionario != null) {
			session.setAttribute("funcionario",  gson.toJson(funcionario));	
		}
		else
			session.setAttribute("funcionario",  null);

	}

}
