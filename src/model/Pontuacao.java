package model;

import java.io.Serializable;

public class Pontuacao implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int idPalavraChave;
	private int idResposta;
	private int pontuacao;
	
	public Pontuacao() {
	}
	
	public Pontuacao(int idPalavraChave, int idResposta, int pontuacao) {
		this.idPalavraChave = idPalavraChave;
		this.idResposta = idResposta;
		this.pontuacao = pontuacao;
	}
	
	public int getIdPalavraChave() {
		return idPalavraChave;
	}

	public void setIdPalavraChave(int idPalavraChave) {
		this.idPalavraChave = idPalavraChave;
	}

	public int getIdResposta() {
		return idResposta;
	}

	public void setIdResposta(int idResposta) {
		this.idResposta = idResposta;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}	
}
