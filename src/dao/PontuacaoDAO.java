package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

	public Pontuacao select(Pontuacao pontuacao) {
		Pontuacao retorno = new Pontuacao();
		String sqlSelect = "SELECT idPalavraChave, idResposta, pontuacao FROM pontuacao WHERE pontuacao.idPalavraChave = ? and pontuacao.idResposta = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, pontuacao.getIdPalavraChave());
			stm.setInt(2, pontuacao.getIdResposta());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					retorno.setIdPalavraChave(rs.getInt("idPalavraChave"));
					retorno.setIdResposta(rs.getInt("idResposta"));
					retorno.setPontuacao(rs.getInt("pontuacao"));
				} else {
					retorno.setIdPalavraChave(-1);
					retorno.setIdResposta(-1);
					retorno.setPontuacao(-1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return retorno;
	}
}
