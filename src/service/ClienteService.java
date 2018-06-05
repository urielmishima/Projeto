package service;

import dao.ClienteDAO;
import model.Cliente;

public class ClienteService {
	ClienteDAO dao = new ClienteDAO();
	
	public Cliente entrar(Cliente cliente) {
		return dao.findById(dao.insert(cliente));
	}
}
