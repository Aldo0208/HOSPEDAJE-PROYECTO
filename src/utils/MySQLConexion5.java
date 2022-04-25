package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConexion5 {
	public static Connection getConexion() {
		Connection con = null;
		try {
			// 1. carga el driver o conector
			Class.forName("com.mysql.jdbc.Driver"); // 5.7
			
			// 2.establece conexión con la bd
			String url = "jdbc:mysql://localhost:3306/CiberHospedaje";  // 5.7
			String usr = "root";
			String psw = "mysql";
			con = DriverManager.getConnection(url, usr, psw);
		} catch (ClassNotFoundException ex) {
			System.out.println("Error >> Driver no Instalado!!");
		} catch (SQLException ex) {
			System.out.println("Error >> de conexión con la BD");
		}
		return con;
	}

}
