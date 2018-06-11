package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import enums.StatusFuncionario;
import enums.TipoFuncionario;
import model.Funcionario;

public class FuncionarioDAO {
	
	public Funcionario findByStatus(StatusFuncionario status) {
		Funcionario funcionario = new Funcionario();
		funcionario.setStatus(status);
		String sqlSelect = "SELECT id, tipo FROM Funcionario WHERE Funcionario.status = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, funcionario.getStatus().getCod());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					funcionario.setId(rs.getInt("id"));
					funcionario.setTipo(TipoFuncionario.toEnum(rs.getInt("tipo")));
				} else {
					funcionario = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return funcionario;
	}

	public Funcionario logar(Funcionario funcionario) {
		Funcionario retorno = new Funcionario();
		String sqlSelect = "SELECT id, tipo, status FROM Funcionario WHERE Funcionario.id = ? and Funcionario.senha = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, funcionario.getId());
			stm.setString(2, funcionario.getSenha());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					retorno.setId(rs.getInt("id"));
					retorno.setTipo(TipoFuncionario.toEnum(rs.getInt("tipo")));
					retorno.setStatus(StatusFuncionario.toEnum(rs.getInt("status")));
				} else {
					return null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return retorno;
	}

	public void alterStatus(Funcionario funcionario) {
		String sqlUpdate = "UPDATE funcionario SET status=? WHERE id=?";
	      try (Connection conn = ConnectionFactory.obtemConexao();
	      			PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
	         stm.setInt(1, funcionario.getStatus().getCod());
	         stm.setInt(2, funcionario.getId());
	         stm.execute();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }	
	}
}
