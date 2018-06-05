package enums;

public enum StatusFuncionario {
	DISPONIVEL(1, "Disponivel"), 
	INDISPONIVEL(2, "Indisponivel");

	private int cod;
	private String descricao;

	private StatusFuncionario(int cod, String descricao) {
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

	public static StatusFuncionario toEnum(Integer id) {
		if (id == null) {
			return null;
		}
		for (StatusFuncionario x : StatusFuncionario.values()) {
			if (id.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido " + id);
	}

}
