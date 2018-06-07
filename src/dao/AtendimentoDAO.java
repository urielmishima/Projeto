package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import enums.StatusAtendimento;
import model.Atendimento;
import model.Funcionario;

public class AtendimentoDAO {
	public int newChatBot(Atendimento atendimento) {
		String sqlInsert = "INSERT INTO atendimento(interacoes, status, idCliente,) VALUES (?, ?, ?)";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {			
			stm.setInt(1, atendimento.getInteracoes());
			stm.setInt(2, atendimento.getStatus().getCod());
			stm.setInt(3, atendimento.getCliente().getId());			
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery); ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					atendimento.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return atendimento.getId();
	}

	public void newPergunta(Atendimento atendimento) {
		String sqlUpdate = "UPDATE atendimento SET dtInicio=?, duvida=? WHERE id=?";
	      try (Connection conn = ConnectionFactory.obtemConexao();
	      			PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
	         stm.setDate(1, atendimento.getDtInicio());
	         stm.setString(2, atendimento.getDuvida());
	         stm.setInt(3, atendimento.getId());
	         stm.execute();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	}

	public void endChatBot(Atendimento atendimento) {
		String sqlUpdate = "UPDATE atendimento SET dtFim=?, interacoes=? status=? WHERE id=?";
	      try (Connection conn = ConnectionFactory.obtemConexao();
	      			PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
	         stm.setDate(1, atendimento.getDtFim());
	         stm.setInt(2, atendimento.getInteracoes());
	         stm.setInt(3, atendimento.getStatus().getCod());
	         stm.setInt(4, atendimento.getId());
	         stm.execute();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }		
	}

	public void newChat(Atendimento atendimento) {
		String sqlUpdate = "UPDATE atendimento SET dtFim=?, interacoes=? status=? idFuncionario=? WHERE id=?";
	      try (Connection conn = ConnectionFactory.obtemConexao();
	      			PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
	         stm.setDate(1, atendimento.getDtFim());
	         stm.setInt(2, atendimento.getInteracoes());
	         stm.setInt(3, atendimento.getStatus().getCod());
	         stm.setInt(4, atendimento.getFuncionario().getId());
	         stm.setInt(5, atendimento.getId());
	         stm.execute();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }	
		
	}

	public Atendimento newAtendimento(Funcionario funcionario) {
		Atendimento atendimento = new Atendimento();
		String sqlSelect = "SELECT id, dtInicio, dtFim, interacoes, status, duvida FROM atendimento WHERE Atendimento.idFuncionario = ? and Atendimento.status = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, funcionario.getId());
			stm.setInt(2, StatusAtendimento.FUNCIONARIO.getCod());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					atendimento.setId(rs.getInt("id"));
					atendimento.setDtInicio(rs.getDate("dtInicio"));
					atendimento.setDtFim(rs.getDate("dtFim"));
					atendimento.setInteracoes(rs.getInt("iteracoes"));
					atendimento.setStatus(StatusAtendimento.toEnum(rs.getInt("status")));
					atendimento.setDuvida(rs.getString("duvida"));
				} else {
					return null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return atendimento;
	}
}
