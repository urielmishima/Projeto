package model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import enums.StatusAtendimento;

public class Atendimento implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private Date dtInicio;
	private Date dtFim;
	private int interacoes;
	private int status;
	private Cliente cliente;
	private Funcionario funcionario;
	private String duvida;
	private List<Resposta> respostas = new ArrayList<Resposta>();
	
	public Atendimento() {
	}	
	public Atendimento(int id, Date dtInicio, Date dtFim, int interacoes, StatusAtendimento status, Cliente cliente,
			Funcionario funcionario, String duvida) {
		super();
		this.id = id;
		this.dtInicio = dtInicio;
		this.dtFim = dtFim;
		this.interacoes = interacoes;
		this.status = status.getCod();
		this.cliente = cliente;
		this.funcionario = funcionario;
		this.duvida = duvida;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDtInicio() {
		return dtInicio;
	}
	public void setDtInicio(Date dtInicio) {
		this.dtInicio = dtInicio;
	}
	public Date getDtFim() {
		return dtFim;
	}
	public void setDtFim(Date dtFim) {
		this.dtFim = dtFim;
	}
	public int getInteracoes() {
		return interacoes;
	}
	public void setInteracoes(int interacoes) {
		this.interacoes = interacoes;
	}
	public StatusAtendimento getStatus() {
		return StatusAtendimento.toEnum(status);
	}
	public void setStatus(StatusAtendimento status) {
		this.status = status.getCod();
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public String getDuvida() {
		return duvida;
	}
	public void setDuvida(String duvida) {
		this.duvida = duvida;
	}
	public List<Resposta> getRespostas() {
		return respostas;
	}
	public void setRespostas(List<Resposta> respostas) {
		this.respostas = respostas;
	}	
}
