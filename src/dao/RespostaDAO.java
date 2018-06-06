package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Resposta;

public class RespostaDAO {

	public List<Resposta> findByPalavraChave(String palavraChave) {
		List<Resposta> respostas = new ArrayList<Resposta>();
		String sqlSelect = "SELECT Resposta.id, Resposta.resposta, Pontuacao.idPalavraChave, Pontuacao.idResposta, Pontuacao.pontuacao FROM Resposta JOIN Pontuacao ON Pontuacao.idResposta = Resposta.id JOIN PalavraChave	ON PalavraChave.id = Pontuacao.idPalavraChave WHERE PalavraChave.PalavraChave = ? ORDER BY Pontuacao.pontuacao DESC LIMIT 0,3";

		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setString(1, palavraChave);
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					Resposta resposta = new Resposta();
					resposta.setId(rs.getInt("Resposta.id"));
					resposta.setResposta(rs.getString("Resposta.resposta"));
					resposta.getPontuacao().setIdPalavraChave(rs.getInt("Pontuacao.idPalavraChave"));
					resposta.getPontuacao().setIdResposta(rs.getInt("Pontuacao.idResposta"));
					resposta.getPontuacao().setPontuacao(rs.getInt("Pontuacao.pontuacao"));
					respostas.add(resposta);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return respostas;
	}
}
