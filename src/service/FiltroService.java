package service;

import dao.FiltroDAO;
import model.Filtro;

public class FiltroService {
	FiltroDAO dao = new FiltroDAO();

	public Filtro diario() {
		return dao.diario();
	}
	public Filtro semanal() {
		return dao.semanal();
	}
	public Filtro mensal() {
		return dao.mensal();
	}
	
}
