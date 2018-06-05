package enums;

public enum TipoFuncionario {
	ATENDENTE(1, "Atendente"),
	ADMINISTRADOR(2, "Administrador");

	private int cod;
	private String descricao;

	private TipoFuncionario(int cod, String descricao) {
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

	public static TipoFuncionario toEnum(Integer id) {
		if (id == null) {
			return null;
		}
		for (TipoFuncionario x : TipoFuncionario.values()) {
			if (id.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido " + id);
	}

}
