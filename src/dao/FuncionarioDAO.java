package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import enums.TipoFuncionario;
import model.Funcionario;

public class FuncionarioDAO {
	
	public Funcionario findById(int idFuncionario) {
		Funcionario funcionario = new Funcionario();
		funcionario.setId(idFuncionario);
		String sqlSelect = "SELECT id, senha, tipo FROM Funcionario WHERE Funcionario.id = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, funcionario.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					funcionario.setId(rs.getInt("id"));
					funcionario.setSenha(rs.getString("senha"));
					funcionario.setTipo(TipoFuncionario.toEnum(rs.getInt("tipo")));
				} else {
					funcionario.setId(-1);
					funcionario.setSenha(null);
					funcionario.setTipo(null);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return funcionario;
	}

}
