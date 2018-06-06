package service;

import java.util.ArrayList;
import java.util.List;

import dao.RespostaDAO;
import model.Resposta;

public class RespostaService {
	RespostaDAO dao = new RespostaDAO();

	public List<Resposta> findByDuvida(String duvida) {
		String[] palavrasChave = duvida.split(" ");
		List<Resposta> respostas = new ArrayList<Resposta>();
		for (String palavraChave : palavrasChave) {
			if(respostas.size() < 3) {			
				List<Resposta> preRespostas = dao.findByPalavraChave(palavraChave);
				for (Resposta preResposta : preRespostas) {
					if(respostas.size() < 3) {
						respostas.add(preResposta);
					}
					else
						return respostas;
				}				
			}
			else
				return respostas;
		}
		return respostas;
	}

}
