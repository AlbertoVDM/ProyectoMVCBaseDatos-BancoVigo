package modelo;
import java.sql.*;
import vista.*;
import modelo.*;
import controlador.*;
import java.sql.*;

public class ConexionBD {
	
	private Connection con;

	
	public ConexionBD(String host, int puerto, String usuario, String password) {
		this.con=conectar(host,puerto,usuario,password);
	
	}
	
	private Connection conectar(String host, int puerto, String usuario, String password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String URL="jdbc:mysql://"+host+":"+puerto+"/";
			Connection con=DriverManager.getConnection(URL,usuario,password);
			return con;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public Connection getCon() {
		return this.con;
	}
}
