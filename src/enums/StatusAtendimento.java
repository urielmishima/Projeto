package enums;

public enum StatusAtendimento {
	BOT(1, "Em atendimento com o BOT"), 
	FUNCIONARIO(2, "Em atendimento com o Funcionario"), 
	RESOLVIDO_BOT(3,"Resolvido pelo BOT"), 
	RESOLVIDO_FUNCIONARIO(4, "Resolvido pelo Funcionario");

	private int cod;
	private String descricao;

	private StatusAtendimento(int cod, String descricao) {
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

	public static StatusAtendimento toEnum(Integer id) {
		if (id == null) {
			return null;
		}
		for (StatusAtendimento x : StatusAtendimento.values()) {
			if (id.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido " + id);
	}

}
