package br.com.caelum.tarefas.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.caelum.tarefas.bean.Tarefa;
import br.com.caelum.tarefas.jdbc.ConnectionFactory;

@Repository
public class JdbcTarefaDao implements TarefaDao {
	// a conexão com o banco de dados
	private Connection connection;
	
	@Autowired
	public JdbcTarefaDao(DataSource dataSource) {
		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void adicionar(Tarefa tarefa) {
		String sql = "insert into tarefas "
				+ "(descricao, finalizado, dataFinalizacao)"
				+ " values (?,?,?)";

		try {
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);
			// seta os valores
			stmt.setString(1, tarefa.getDescricao());
			stmt.setBoolean(2, tarefa.isFinalizado());
			stmt.setDate(3, new Date(tarefa.getDataFinalizacao().getTimeInMillis()));

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public List<Tarefa> getLista() {
		try {
			List<Tarefa> tarefas = new ArrayList<Tarefa>();
			PreparedStatement stmt = this.connection
					.prepareStatement("select * from tarefas");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// criando o objeto Tarefa
				Tarefa tarefa = new Tarefa();
				tarefa.setId(rs.getLong("id"));
				tarefa.setDescricao(rs.getString("descricao"));
				tarefa.setFinalizado(rs.getBoolean("finalizado"));

				// montando a data através do Calendar
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataFinalizacao"));
				tarefa.setDataFinalizacao(data);

				// adicionando o objeto à lista
				tarefas.add(tarefa);
			}
			rs.close();
			stmt.close();
			return tarefas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public Tarefa buscarPorId(Long id) {
		try {
			PreparedStatement stmt = this.connection
					.prepareStatement("select * from tarefas where id = ?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// criando o objeto Tarefa
				Tarefa tarefa = new Tarefa();
				tarefa.setId(rs.getLong("id"));
				tarefa.setDescricao(rs.getString("descricao"));
				tarefa.setFinalizado(rs.getBoolean("finalizado"));

				// montando a data através do Calendar
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataFinalizacao"));
				
				tarefa.setDataFinalizacao(data);

				return tarefa;
			}

			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void alterar(Tarefa tarefa) {
		String sql = "update tarefas set descricao=?, finalizado=?,"
				+ "dataFinalizacao=? where id=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, tarefa.getDescricao());
			stmt.setBoolean(2, tarefa.isFinalizado());
			stmt.setDate(3, new Date(tarefa.getDataFinalizacao()
					.getTimeInMillis()));
			stmt.setLong(4, tarefa.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void remover(Tarefa tarefa) {
		try {
			PreparedStatement stmt = connection
					.prepareStatement("delete from tarefas where id=?");
			stmt.setLong(1, tarefa.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void finalizar(Long id){
		String sql = "update tarefas set finalizado=?, dataFinalizacao=? where id=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setBoolean(1, true);
			stmt.setDate(2, new Date(Calendar.getInstance().getTimeInMillis()));
			stmt.setLong(3, id);
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
