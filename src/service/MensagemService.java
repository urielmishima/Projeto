package service;

import dao.MensagemDAO;
import model.Mensagem;

public class MensagemService {
	MensagemDAO dao = new MensagemDAO();
	
	public void newMensagem(Mensagem mensagem) {
		dao.newMensagem(mensagem);
	}

	public Mensagem searchMensagem(Mensagem mensagem) {
		Mensagem aux = new Mensagem();
		do {
		aux = dao.searchMensagem(mensagem);
		} while(aux == null);
		return aux;
	}
}
