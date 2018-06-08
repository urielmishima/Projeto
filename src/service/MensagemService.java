package service;

import dao.MensagemDAO;
import model.Mensagem;

public class MensagemService {
	MensagemDAO dao = new MensagemDAO();
	
	public void newMensagem(Mensagem mensagem) {
		dao.newMensagem(mensagem);
	}

	public Mensagem searchMensagem(Mensagem mensagem) {
		return dao.searchMensagem(mensagem);
	}
}
