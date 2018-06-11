package model;

public class Relatorio {
	private Filtro diario;
	private Filtro semanal;
	private Filtro mensal;
	
	public Relatorio() {
	}
	public Relatorio(Filtro diario, Filtro semanal, Filtro mensal) {
		this.diario = diario;
		this.semanal = semanal;
		this.mensal = mensal;
	}
	
	public Filtro getDiario() {
		return diario;
	}
	public void setDiario(Filtro diario) {
		this.diario = diario;
	}
	public Filtro getSemanal() {
		return semanal;
	}
	public void setSemanal(Filtro semanal) {
		this.semanal = semanal;
	}
	public Filtro getMensal() {
		return mensal;
	}
	public void setMensal(Filtro mensal) {
		this.mensal = mensal;
	}	
}
