package service;

import model.Filtro;
import model.Relatorio;

public class RelatorioService {
	FiltroService filtro = new FiltroService();
	
	private Relatorio builder(){
		Relatorio relatorio = new Relatorio();
		Filtro diario = filtro.diario();
		Filtro semanal = filtro.semanal();
		Filtro mensal = filtro.mensal();
		
		return relatorio;
	}
}
