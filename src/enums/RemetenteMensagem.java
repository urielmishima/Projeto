package enums;

public enum RemetenteMensagem {
	CLIENTE(1, "Cliente"), 
	FUNCIONARIO(2, "Funcionario");

	private int cod;
	private String descricao;

	private RemetenteMensagem(int cod, String descricao) {
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

	public static RemetenteMensagem toEnum(Integer id) {
		if (id == null) {
			return null;
		}
		for (RemetenteMensagem x : RemetenteMensagem.values()) {
			if (id.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inv�lido " + id);
	}

}
