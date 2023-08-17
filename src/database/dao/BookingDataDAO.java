package database.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import database.dto.BookingDataDTO;
import database.dto.PaymentMethodDTO;

public class BookingDataDAO extends MainDAO {
	
	private static final String SAVE_IN_BOOKING="INSERT INTO booking(entry_date,departure_date,method_payment,price)"+
			"VALUES(?,?,?,?)";
	private static final String SELECT_BOOKING_TABLE = "SELECT id, entry_date, departure_date, price, method_payment FROM booking";
	
	private static final String SELECT_BOOKING_TABLE_BY_ID="SELECT id, entry_date, departure_date, price, method_payment FROM booking WHERE id=?";
	
	private static final String DELETE_BOOKING_BY_ID="DELETE FROM booking WHERE id=?";
	
	private static final String MODIFY_BOOKING="UPDATE booking SET entry_date=?,departure_date=?,price=?,method_payment=? WHERE id=?";
	
	public BookingDataDTO save(BookingDataDTO bookingData) {
		Connection con= super.getConnection();
		
		try {
			final PreparedStatement statement = con.prepareStatement(SAVE_IN_BOOKING, Statement.RETURN_GENERATED_KEYS);
			try (statement){
				Integer id = saveRecord(bookingData, statement);
				bookingData.setId(id);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return bookingData;	
	}

	Integer saveRecord(BookingDataDTO bookingData, PreparedStatement statement) throws SQLException {
		LocalDateTime entryDatebyUser=bookingData.getEntryDate();
		Timestamp entryTimestampUser=Timestamp.valueOf(entryDatebyUser);
		
		LocalDateTime departureDatebyUser=bookingData.getDepartureDate();
		Timestamp departureTimestampUser=Timestamp.valueOf(departureDatebyUser);
		
		String methodPaymentUser = bookingData.getPaymentMethod().getName();
		BigDecimal priceByBookingService=bookingData.getPrice();
		
		statement.setTimestamp(1,entryTimestampUser);
		statement.setTimestamp(2, departureTimestampUser);
		statement.setString(3, methodPaymentUser);
		statement.setBigDecimal(4, priceByBookingService);
		
		statement.execute();
		 
		final ResultSet resultSet=statement.getGeneratedKeys();
		try(resultSet){
			if(resultSet.next()) {
				return resultSet.getInt(1);
			}
		}
		return null;
	}
	
	public List<BookingDataDTO> searchBookingList(){
		List<BookingDataDTO> bookingdataList=new ArrayList<>();
		Connection con= super.getConnection();

		try {
			final PreparedStatement statement=con.prepareStatement(SELECT_BOOKING_TABLE);
			
			try(statement){
				statement.execute();
				final ResultSet resultSet=statement.getResultSet();
				bookingdataList = getBookingAttributes(resultSet);
			}	
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return bookingdataList;
	}
	
	public BookingDataDTO searchByIdBooking(int idSearch) {
		Connection con= super.getConnection();
		BookingDataDTO bookingdata=null;
		
		try {
			final PreparedStatement statement=con.prepareStatement(SELECT_BOOKING_TABLE_BY_ID);
			
			try(statement){
				statement.setInt(1, idSearch);
				statement.execute();
				final ResultSet resultSet=statement.getResultSet();
				List<BookingDataDTO> bookingdataList = getBookingAttributes(resultSet);
				if (!bookingdataList.isEmpty()) {
					bookingdata = bookingdataList.get(0);
				}
			}	
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return bookingdata;
	}
					
	public List<BookingDataDTO> getBookingAttributes(ResultSet resultSet) {					
		List<BookingDataDTO> bookingdataList=new ArrayList<>();
		
		try(resultSet){
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				
				LocalDateTime entryDate = resultSet.getTimestamp("entry_date").toLocalDateTime();
				
				LocalDateTime departureDate = resultSet.getTimestamp("departure_date").toLocalDateTime();
				
				BigDecimal price = resultSet.getBigDecimal("price");
				
				String databasePaymentMethod=resultSet.getString("method_payment");
				
				PaymentMethodDTO paymentMethod=PaymentMethodDTO.valueOf(databasePaymentMethod);
				
				BookingDataDTO bookingRow=new BookingDataDTO(
						id,
						entryDate,
						departureDate,
						price,
						paymentMethod);
				bookingdataList.add(bookingRow);
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return bookingdataList;	
	}
	
	public int delete(int id) {
		Connection con= super.getConnection();
		try {
			final PreparedStatement statement=con.prepareStatement(DELETE_BOOKING_BY_ID);
			
			try(statement){
				statement.setInt(1, id);
				return statement.executeUpdate();
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int modify(BookingDataDTO bookingDataDTO) {
		Connection con= super.getConnection();
		try {
			final PreparedStatement statement=con.prepareStatement(MODIFY_BOOKING);
			
			try(statement){
				
				Timestamp entryDateTimestamp=Timestamp.valueOf(bookingDataDTO.getEntryDate());
				Timestamp departureDateTimestamp=Timestamp.valueOf(bookingDataDTO.getDepartureDate());
				BigDecimal price=bookingDataDTO.getPrice();
				String paymentMethodData=bookingDataDTO.getPaymentMethod().getName();
				int id=bookingDataDTO.getId();
				
				statement.setTimestamp(1, entryDateTimestamp);
				statement.setTimestamp(2, departureDateTimestamp);
				statement.setBigDecimal(3, price);
				statement.setString(4, paymentMethodData);
				statement.setInt(5, id);
				statement.execute();
				
				int updateCount=statement.getUpdateCount();
				
				return updateCount;	
			}
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}