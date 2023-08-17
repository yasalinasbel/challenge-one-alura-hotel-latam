package database.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import database.dto.BookingDataDTO;
import database.dto.PaymentMethodDTO;


@RunWith(JUnit4.class)
public class BookingDataDAOTest {

	private BookingDataDAO bookingDataDAO = new BookingDataDAO();

	@Test
	public void testSaveBooking() throws SQLException {
		LocalDateTime entryDate=LocalDateTime.now().withNano(0);
		
		BookingDataDTO booking=new BookingDataDTO(entryDate,LocalDateTime.of(2023,12,12,10,30),PaymentMethodDTO.CASH,new BigDecimal("6000.0"));
		
		BookingDataDTO bookingSaved = bookingDataDAO.save(booking);
		Assert.assertNotNull(bookingSaved);
		Assert.assertNotNull(bookingSaved.getId());
		
		BookingDataDTO bookingSearched = bookingDataDAO.searchByIdBooking(bookingSaved.getId());
		Assert.assertNotNull(bookingSearched);
		Assert.assertEquals(bookingSaved.getId(), bookingSearched.getId());
		Assert.assertEquals(bookingSaved.getEntryDate(), bookingSearched.getEntryDate());
		Assert.assertEquals(bookingSaved.getDepartureDate(), bookingSearched.getDepartureDate());
		Assert.assertEquals(bookingSaved.getPrice(), bookingSearched.getPrice());
		Assert.assertEquals(bookingSaved.getPaymentMethod(), bookingSearched.getPaymentMethod());
		
		BookingDataDTO bookingToModify=new BookingDataDTO(bookingSearched.getId(),LocalDateTime.of(2023,12,10,10,30), LocalDateTime.of(2024,01,01,10,30), new BigDecimal("30000"), PaymentMethodDTO.CREDIT);
		
		int bookingModified = bookingDataDAO.modify(bookingToModify);
		Assert.assertNotNull(bookingSearched);
		Assert.assertTrue(1 == bookingModified);
		
		int deletedRecords = bookingDataDAO.delete(bookingToModify.getId());
		Assert.assertTrue(1 == deletedRecords);
	}

	@Test
	public void testSearchBookingList() {
		
		LocalDateTime entryDate=LocalDateTime.now().withNano(0);
		BookingDataDTO booking=new BookingDataDTO(entryDate,LocalDateTime.of(2023,12,12,11,30),PaymentMethodDTO.CASH,new BigDecimal("6000.0"));
		BookingDataDTO booking2=new BookingDataDTO(entryDate,LocalDateTime.of(2023,10,12,8,30),PaymentMethodDTO.CASH,new BigDecimal("7000.0"));
		BookingDataDTO booking3=new BookingDataDTO(entryDate,LocalDateTime.of(2023,8,12,07,30),PaymentMethodDTO.CASH,new BigDecimal("8000.0"));
		
		BookingDataDTO bookingSaved = bookingDataDAO.save(booking);
		BookingDataDTO bookingSaved1 = bookingDataDAO.save(booking2);
		BookingDataDTO bookingSaved2 = bookingDataDAO.save(booking3);
		
		List<BookingDataDTO> searchBookingList = bookingDataDAO.searchBookingList();
		for (BookingDataDTO elemento:searchBookingList) {
			System.out.println(elemento);
		}
		
		int deletedRecords = bookingDataDAO.delete(bookingSaved.getId());	
		int deletedRecords2 = bookingDataDAO.delete(bookingSaved1.getId());
		int deletedRecords3 = bookingDataDAO.delete(bookingSaved2.getId());
		Assert.assertTrue(1 == deletedRecords3);
		Assert.assertTrue(1 == deletedRecords2);
		Assert.assertTrue(1 == deletedRecords);
	}	
}