package model;

import java.io.Serializable;

import enums.StatusFuncionario;
import enums.TipoFuncionario;

public class Funcionario implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String senha;
	private int tipo;
	private int status;

	public Funcionario() {
	}

	public Funcionario(int id, String senha, TipoFuncionario tipo, StatusFuncionario status) {
		super();
		this.id = id;
		this.senha = senha;
		this.tipo = tipo.getCod();
		this.status = status.getCod();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public TipoFuncionario getTipo() {
		return TipoFuncionario.toEnum(tipo);
	}

	public void setTipo(TipoFuncionario tipo) {
		this.tipo = tipo.getCod();
	}

	public StatusFuncionario getStatus() {
		return StatusFuncionario.toEnum(status);
	}

	public void setStatus(StatusFuncionario status) {
		this.status = status.getCod();
	}

}
