package model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Filtro {
	private int qtdAtendimentos;
	private Time duração;
	private int interaçoes;
	private int primeira;
	private int segunda;
	private int terceira;
	private int encaminhados ;
	private List<Atendimento> atendimentos = new ArrayList<Atendimento>();
	
	public Filtro() {
	}
	
	public Filtro(int qtdAtendimentos, Time duração, int interaçoes, int primeira, int segunda, int terceira,
			int encaminhados) {
		this.qtdAtendimentos = qtdAtendimentos;
		this.duração = duração;
		this.interaçoes = interaçoes;
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
	public Time getDuração() {
		return duração;
	}
	public void setDuração(Time duração) {
		this.duração = duração;
	}
	public int getInteraçoes() {
		return interaçoes;
	}
	public void setInteraçoes(int interaçoes) {
		this.interaçoes = interaçoes;
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
