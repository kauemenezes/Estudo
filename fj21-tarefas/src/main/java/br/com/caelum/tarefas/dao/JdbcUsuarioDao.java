package br.com.caelum.tarefas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.caelum.tarefas.bean.Usuario;
import br.com.caelum.tarefas.jdbc.ConnectionFactory;

public class JdbcUsuarioDao {
	// a conexão com o banco de dados
		private Connection connection;

		public JdbcUsuarioDao() {
			this.connection = new ConnectionFactory().getConnection();
		}
		
		public boolean existeUsuario(Usuario usuario) {
			String sql = "SELECT * FROM usuarios u "
					+ "WHERE u.login = ?" 
					+ "AND u.senha = ?"; 
			
			try {
				// prepared statement para inserção
				PreparedStatement stmt = this.connection.prepareStatement(sql);
			    stmt.setString(1, usuario.getLogin());
			    stmt.setString(2, usuario.getSenha());
				ResultSet result = stmt.executeQuery();
				
				if(result.next()){
					result.close();
					stmt.close();
					return true;
				}
				result.close();
				stmt.close();
				return false;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
}
