package model;

import enums.RemetenteMensagem;

public class Mensagem {
	
	private int id;
	private String mensagem;
	private int remetente;
	private Atendimento atendimento;
	
	public Mensagem() {
	}	
	
	public Mensagem(int id, String mensagem, RemetenteMensagem remetente, Atendimento atendimento) {
		this.id = id;
		this.mensagem = mensagem;
		this.remetente = remetente.getCod();
		this.atendimento = atendimento;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public RemetenteMensagem getRemetente() {
		return RemetenteMensagem.toEnum(remetente);
	}

	public void setRemetente(RemetenteMensagem remetente) {
		this.remetente = remetente.getCod();
	}
	
	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}
	
	
}
