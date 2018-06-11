package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import enums.RemetenteMensagem;
import model.Mensagem;

public class MensagemDAO {

	public void newMensagem(Mensagem mensagem) {
		String sqlInsert = "INSERT INTO mensagem(mensagem, remetente, idAtendimento) VALUES (?, ?, ?)";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, mensagem.getMensagem());
			stm.setInt(2, mensagem.getRemetente().getCod());
			stm.setInt(3, mensagem.getAtendimento().getId());
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Mensagem searchMensagem(Mensagem mensagem) {
		Mensagem aux = new Mensagem();
		String sqlSelect = "SELECT id, mensagem, remetente FROM mensagem WHERE Mensagem.idAtendimento = ? and Mensagem.remetente = ? and Mensagem.id > ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, mensagem.getAtendimento().getId());
			stm.setInt(2, mensagem.getRemetente().getCod());
			stm.setInt(3, mensagem.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					aux.setId(rs.getInt("id"));
					aux.setMensagem(rs.getString("mensagem"));
					aux.setRemetente(RemetenteMensagem.toEnum(rs.getInt("remetente")));
				} else {
					return null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return aux;
		
	}

}
