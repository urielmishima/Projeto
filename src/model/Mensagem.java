package model;

import enums.RemetenteMensagem;
import enums.StatusMensagem;

public class Mensagem {
	
	private int id;
	private String mensagem;
	private int remetente;
	private int status;
	
	public Mensagem() {
	}
	
	public Mensagem(int id, String mensagem, RemetenteMensagem remetente, StatusMensagem status) {
		this.id = id;
		this.mensagem = mensagem;
		this.remetente = remetente.getCod();
		this.status = status.getCod();
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

	public StatusMensagem getStatus() {
		return StatusMensagem.toEnum(status);
	}

	public void setStatus(StatusMensagem status) {
		this.status = status.getCod();
	}
	
	
	
}
