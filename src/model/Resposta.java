package model;

import java.io.Serializable;

public class Resposta implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String resposta;
	private Pontuacao pontuacao;
	
	public Resposta() {
	}

	public Resposta(int id, String resposta, Pontuacao pontuacao) {
		super();
		this.id = id;
		this.resposta = resposta;
		this.pontuacao = pontuacao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public Pontuacao getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(Pontuacao pontuacao) {
		this.pontuacao = pontuacao;
	}
}
