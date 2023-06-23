package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import database.ConnectionManager;
import database.dto.UserDataDTO;

public class UserDataDAO {
	
	private Connection con;
	
	private synchronized Connection getConnection() {
		if (con == null) {
			con = new ConnectionManager().getConnection();
		} else {
			try {
				if (con.isClosed()) {
					con = new ConnectionManager().getConnection();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				con = new ConnectionManager().getConnection();
			}
		}
		return con;
	}
	
	public List<UserDataDTO> getUserByLogin(String login){
		
		List<UserDataDTO> userPassword=new ArrayList<>();
		Connection con= getConnection();
		
		try {
			String querySelect="SELECT id, login, password FROM user_data WHERE login=?";
			final PreparedStatement statement=con.prepareStatement(querySelect);
			
			try(statement){
				statement.setString(1,login);
				statement.execute();
				final ResultSet resultSet=statement.getResultSet();
					
					try(resultSet){
						while(resultSet.next()) {
							UserDataDTO fila=new UserDataDTO(
								resultSet.getInt("id"),
								resultSet.getString("login"),
								resultSet.getString("password"));
							userPassword.add(fila);
						}
					}
				}
				return userPassword;
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}
		}
}
	
	
	


