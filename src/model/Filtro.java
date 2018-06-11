package model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Filtro {
	private int qtdAtendimentos;
	private Time dura��o;
	private int intera�oes;
	private int primeira;
	private int segunda;
	private int terceira;
	private int encaminhados ;
	private List<Atendimento> atendimentos = new ArrayList<Atendimento>();
	
	public Filtro() {
	}
	
	public Filtro(int qtdAtendimentos, Time dura��o, int intera�oes, int primeira, int segunda, int terceira,
			int encaminhados) {
		this.qtdAtendimentos = qtdAtendimentos;
		this.dura��o = dura��o;
		this.intera�oes = intera�oes;
		this.primeira = primeira;
		this.segunda = segunda;
		this.terceira = terceira;
		this.encaminhados = encaminhados;
	}

	public int getQtdAtendimentos() {
		return qtdAtendimentos;
	}
	public void setQtdAtendimentos(int qtdAtendimentos) {
		this.qtdAtendimentos = qtdAtendimentos;
	}
	public Time getDura��o() {
		return dura��o;
	}
	public void setDura��o(Time dura��o) {
		this.dura��o = dura��o;
	}
	public int getIntera�oes() {
		return intera�oes;
	}
	public void setIntera�oes(int intera�oes) {
		this.intera�oes = intera�oes;
	}
	public int getPrimeira() {
		return primeira;
	}
	public void setPrimeira(int primeira) {
		this.primeira = primeira;
	}
	public int getSegunda() {
		return segunda;
	}
	public void setSegunda(int segunda) {
		this.segunda = segunda;
	}
	public int getTerceira() {
		return terceira;
	}
	public void setTerceira(int terceira) {
		this.terceira = terceira;
	}
	public int getEncaminhados() {
		return encaminhados;
	}
	public void setEncaminhados(int encaminhados) {
		this.encaminhados = encaminhados;
	}
	public List<Atendimento> getAtendimentos() {
		return atendimentos;
	}
	public void setAtendimentos(List<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}	
}
