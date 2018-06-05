package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import enums.StatusAtendimento;
import model.Atendimento;

public class AtendimentoDAO {
	public int insert(Atendimento atendimento) {
		String sqlInsert = "INSERT INTO atendimento(dtInicio, dtFim, interacoes, status, cliente_id, funcionario_id, duvida) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {			
			if(atendimento.getDtInicio() != null) stm.setDate(1, atendimento.getDtInicio());
			else stm.setNull(6, java.sql.Types.DATE);
			
			if(atendimento.getDtFim() != null) stm.setDate(2, atendimento.getDtFim());
			else stm.setNull(6, java.sql.Types.DATE);
			
			stm.setInt(3, atendimento.getInteracoes());
			stm.setInt(4, atendimento.getStatus().getCod());
			stm.setInt(5, atendimento.getCliente().getId());
			
			if (atendimento.getFuncionario() != null) stm.setInt(6, atendimento.getFuncionario().getId());
			else stm.setNull(6, java.sql.Types.INTEGER);
			
			if (atendimento.getDuvida() != null) stm.setString(7, atendimento.getDuvida());
			else stm.setNull(6, java.sql.Types.VARCHAR);
			
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

	public Atendimento findById(int idAtendimento) {
		Atendimento atendimento = new Atendimento();
		atendimento.setId(idAtendimento);
		String sqlSelect = "SELECT id, dtInicio, dtFim, interacoes, status, cliente_id, funcionario_id, duvida FROM atendimento WHERE atendimento.id = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, atendimento.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					atendimento.setId(rs.getInt("id"));
					atendimento.setDtInicio((rs.getDate("dtInicio")));
					atendimento.setDtFim(rs.getDate("dtFim"));
					atendimento.setInteracoes(rs.getInt("interacoes"));
					atendimento.setStatus(StatusAtendimento.toEnum(rs.getInt("status")));
					ClienteDAO clienteDao = new ClienteDAO();
					atendimento.setCliente(clienteDao.findById(rs.getInt("cliente_id")));
					FuncionarioDAO funcionarioDao = new FuncionarioDAO();
					atendimento.setFuncionario(funcionarioDao.findById(rs.getInt("funcionario_id")));
					atendimento.setDuvida(rs.getString("duvida"));

				} else {
					atendimento.setId(-1);
					atendimento.setDtInicio(null);
					atendimento.setDtFim(null);
					atendimento.setInteracoes(-1);
					atendimento.setStatus(null);
					atendimento.setCliente(null);
					atendimento.setFuncionario(null);
					atendimento.setDuvida(null);
					atendimento.setRespostas(null);
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
