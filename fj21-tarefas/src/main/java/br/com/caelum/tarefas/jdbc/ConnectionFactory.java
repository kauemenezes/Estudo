package br.com.caelum.tarefas.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public Connection getConnection() {
		// try {
		// return DriverManager.getConnection("jdbc:mysql://localhost/fj21",
		// "root", "");
		// } catch (SQLException e) {
		//
		// }
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			System.out.println("erro ao carregar driver");
		}
		
		String url = "jdbc:mysql://localhost/fj21";
		String user = "root";
		String password = "";
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			System.out.println(e.getMessage());
//		}

		try {
			return DriverManager.getConnection(url, user, password);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
