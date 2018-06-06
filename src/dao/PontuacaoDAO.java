package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import model.Pontuacao;

public class PontuacaoDAO {

	public void update(Pontuacao pontuacao) {
		String sqlUpdate = "UPDATE pontuacao SET pontuacao=? WHERE idPalavraChave=? and idResposta=?";
	      try (Connection conn = ConnectionFactory.obtemConexao();
	      			PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
	         stm.setInt(1, pontuacao.getPontuacao());
	         stm.setInt(2, pontuacao.getIdPalavraChave());
	         stm.setInt(3, pontuacao.getIdResposta());
	         stm.execute();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }		
	}
}
