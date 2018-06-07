package enums;

public enum StatusMensagem {
	ENVIADA(1, "Enviada"), 
	RECEBIDA(2, "Recebida");

	private int cod;
	private String descricao;

	private StatusMensagem(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static StatusMensagem toEnum(Integer id) {
		if (id == null) {
			return null;
		}
		for (StatusMensagem x : StatusMensagem.values()) {
			if (id.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido " + id);
	}

}
