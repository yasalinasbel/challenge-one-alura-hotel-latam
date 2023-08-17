package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import database.dto.GuestDataDTO;
import database.dto.NationalityDTO;

public class GuestDataDAO extends MainDAO {
	
	private static final String SAVE_IN_GUEST="INSERT INTO guest(name,lastname,birthdate,nationality,telephone,id_booking)"+
			"VALUES(?,?,?,?,?,?)";
	private static final String SELECT_GUEST_TABLE = "SELECT id, name, lastname, birthdate,nationality,telephone,id_booking  FROM guest";
	
	public GuestDataDTO save(GuestDataDTO guestData) {
		Connection con= super.getConnection();	
		
		try {
			final PreparedStatement statement = con.prepareStatement(SAVE_IN_GUEST, Statement.RETURN_GENERATED_KEYS);
			try (statement){
				saveRecordGuest(guestData, statement);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return guestData;	
	}
	private void saveRecordGuest(GuestDataDTO guestData, PreparedStatement statement) throws SQLException {
		String name = guestData.getName();
		String lastName = guestData.getLastName();
		LocalDateTime birthDate=guestData.getBirthDate();
		Timestamp brithDateTimestamp=Timestamp.valueOf(birthDate);
		String nationality = guestData.getNationality().getName();
		String phoneNumber=guestData.getPhoneNumber();
		Integer idBooking=guestData.getIdBooking();
		
		statement.setString(1,name);
		statement.setString(2, lastName);
		statement.setTimestamp(3, brithDateTimestamp);
		statement.setString(4,nationality);
		statement.setString(5,phoneNumber);
		statement.setInt(6, idBooking);
		
		statement.execute();
	}
	
	public List<GuestDataDTO> selectGuestList(){
		List<GuestDataDTO> guestdataList=new ArrayList<>();
		Connection con= super.getConnection();
		GuestDataDTO guestDataDTO=null;

		try {
			final PreparedStatement statement=con.prepareStatement(SELECT_GUEST_TABLE);
			
			try(statement){
				statement.execute();
				final ResultSet resultSet=statement.getResultSet();
				
				try(resultSet){
					while(resultSet.next()) {
						int id = resultSet.getInt("id");
						
						String name=resultSet.getString("name");
						
						String lastName=resultSet.getString("lastname");
						
						LocalDateTime birthDate = resultSet.getTimestamp("birthdate").toLocalDateTime();
						
						String nationalityString=resultSet.getString("nationality");
						NationalityDTO nationality=NationalityDTO.valueOf(nationalityString);
						
						String phoneNumber=resultSet.getString("telephone");
						
						Integer idBooking = resultSet.getInt("id_booking");
						
						
						GuestDataDTO guestRow=new GuestDataDTO(
								id,
								name,
								lastName,
								birthDate,
								nationality,
								phoneNumber,
								idBooking);
						guestdataList.add(guestRow);				
					}
				}
			}
			return guestdataList;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
