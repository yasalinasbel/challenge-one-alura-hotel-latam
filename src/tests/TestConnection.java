package tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {
	
	public static void main(String[] args) throws SQLException {
		
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost/hotel?useTimeZone=true&serverTimeZone=UTC",
				"root",
				"Yineth103010");
		con.close();
		
		System.out.println("Cerrando la conexión");
		

		
		
	}

}
