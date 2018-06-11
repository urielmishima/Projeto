package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import enums.StatusAtendimento;
import model.Atendimento;
import model.Funcionario;

public class AtendimentoDAO {
	public int newChatBot(Atendimento atendimento) {
		String sqlInsert = "INSERT INTO atendimento(interacoes, status, idCliente) VALUES (?, ?, ?)";
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

	public void newPergunta(Date date, int id, String duvida) {
		String sqlUpdate = "UPDATE atendimento SET dtInicio=?, duvida=? WHERE id=?";
	      try (Connection conn = ConnectionFactory.obtemConexao();
	      			PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
	         stm.setDate(1, date);
	         stm.setString(2, duvida);
	         stm.setInt(3, id);
	         stm.execute();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	}

	public void newChat(Atendimento atendimento, Funcionario funcionario) {
		String sqlUpdate = "UPDATE atendimento SET dtFim=?, status=?, idFuncionario=? WHERE id=?";
	      try (Connection conn = ConnectionFactory.obtemConexao();
	      			PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
	         stm.setDate(1, atendimento.getDtFim());
	         stm.setInt(2, atendimento.getStatus().getCod());
	         stm.setInt(3, funcionario.getId());
	         stm.setInt(4, atendimento.getId());
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
					atendimento.setInteracoes(rs.getInt("interacoes"));
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

	public void endChat(Atendimento atendimento) {
		String sqlUpdate = "UPDATE atendimento SET status=? WHERE id=?";
	      try (Connection conn = ConnectionFactory.obtemConexao();
	      			PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
	         stm.setInt(1, atendimento.getStatus().getCod());
	         stm.setInt(2, atendimento.getId());
	         stm.execute();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }	
		
	}

	public void endChatBot(int idCliente, Date date, int contador) {
		String sqlUpdate = "UPDATE atendimento SET dtFim=?, interacoes=?, status=? WHERE id=?";
	      try (Connection conn = ConnectionFactory.obtemConexao();
	      			PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
	         stm.setDate(1, date);
	         stm.setInt(2, contador);
	         stm.setInt(3, StatusAtendimento.RESOLVIDO_BOT.getCod());
	         stm.setInt(4, idCliente);
	         stm.execute();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }	
		
	}

	public void incrementar(int idCliente, int contador) {
		String sqlUpdate = "UPDATE atendimento SET interacoes=? WHERE id=?";
	      try (Connection conn = ConnectionFactory.obtemConexao();
	      			PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
	         stm.setInt(1, contador);
	         stm.setInt(2, idCliente);
	         stm.execute();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }	
		
	}
}
