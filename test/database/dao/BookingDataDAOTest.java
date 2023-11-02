package database.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import model.BookingData;
import model.PaymentMethod;


@RunWith(JUnit4.class)
public class BookingDataDAOTest {

	private BookingDataDAO bookingDataDAO = new BookingDataDAO();

	@Test
	public void testBookingCrudTest() throws SQLException {
		LocalDateTime entryDate=LocalDateTime.now().withNano(0);
		
		BookingData booking=new BookingData(entryDate,LocalDateTime.of(2023,12,12,10,30),new BigDecimal("6000.0"),PaymentMethod.CASH);
		
		BookingData bookingSaved = bookingDataDAO.save(booking);
		Assert.assertNotNull(bookingSaved);
		Assert.assertNotNull(bookingSaved.getId());
		
		BookingData bookingSearched = (bookingDataDAO.searchByAnyRequestBooking(Integer.toString(bookingSaved.getId()))).get(0);
		Assert.assertNotNull(bookingSearched);
		assertEqualsBookingData(bookingSaved,bookingSearched);
		
		BookingData bookingToModify=new BookingData(bookingSearched.getId(),LocalDateTime.of(2023,12,10,10,30), LocalDateTime.of(2024,01,01,10,30), new BigDecimal("30000.0"), PaymentMethod.CREDIT);
		
		int bookingModified = bookingDataDAO.modify(bookingToModify);
		Assert.assertNotNull(bookingSearched);
		Assert.assertTrue(1 == bookingModified);
		
		BookingData bookingSearchedModified = (bookingDataDAO.searchByAnyRequestBooking(Integer.toString(bookingToModify.getId()))).get(0);
		Assert.assertNotNull(bookingSearchedModified);
		assertEqualsBookingData(bookingToModify,bookingSearchedModified);
		
		int deletedRecords = bookingDataDAO.delete(bookingToModify.getId());
		Assert.assertTrue(1 == deletedRecords);
		
		List<BookingData> bookingDeleted = (bookingDataDAO.searchByAnyRequestBooking(Integer.toString(bookingToModify.getId())));
		Assert.assertNotNull(bookingSearchedModified);
		Assert.assertNotNull(bookingDeleted);
		Assert.assertEquals(new ArrayList<>(),bookingDeleted);
		
	}
	@Test
	public void testSearchAnyRequestBookingList() {
		LocalDateTime entryDate=LocalDateTime.now().withNano(0);
		BookingData booking=new BookingData(entryDate,LocalDateTime.of(2023,12,12,11,30),new BigDecimal("6000.0"),PaymentMethod.CASH);
		BookingData booking2=new BookingData(entryDate,LocalDateTime.of(2023,10,12,8,30),new BigDecimal("7000.0"),PaymentMethod.DEBIT);
		BookingData booking3=new BookingData(entryDate,LocalDateTime.of(2023,8,12,07,30),new BigDecimal("8000.0"),PaymentMethod.CASH);
		BookingData booking4=new BookingData(entryDate,LocalDateTime.of(2023,8,12,07,30),new BigDecimal("8000.0"),PaymentMethod.CASH);
		
		BookingData bookingSaved = bookingDataDAO.save(booking);
		BookingData bookingSaved1 = bookingDataDAO.save(booking2);
		BookingData bookingSaved2 = bookingDataDAO.save(booking3);
		BookingData bookingSaved3 = bookingDataDAO.save(booking4);
		
		List<BookingData> searchBookingList = bookingDataDAO.searchByAnyRequestBooking("CASH");
		assertEqualsBookingData(bookingSaved,searchBookingList.get(0));
		assertEqualsBookingData(bookingSaved2,searchBookingList.get(1));
		assertEqualsBookingData(bookingSaved3,searchBookingList.get(2));
		Assert.assertEquals(3,searchBookingList.size());
		
		BookingData bookingToModify=new BookingData(searchBookingList.get(0).getId(),LocalDateTime.of(2023,11,10,10,30), LocalDateTime.of(2024,01,02,10,30), new BigDecimal("30000.0"), PaymentMethod.DEBIT);
		int bookingModified = bookingDataDAO.modify(bookingToModify);
		Assert.assertNotNull(searchBookingList.get(0));
		Assert.assertTrue(1 == bookingModified);
		
		List<BookingData> searchBookingListModified = bookingDataDAO.searchByAnyRequestBooking("CASH");
		assertEqualsBookingData(bookingSaved2,searchBookingListModified.get(0));
		assertEqualsBookingData(bookingSaved3,searchBookingListModified.get(1));
		Assert.assertEquals(2,searchBookingListModified.size());
		
		int deletedRecord1 = bookingDataDAO.delete(booking.getId());
		Assert.assertTrue(1 == deletedRecord1);
		int deletedRecord2 = bookingDataDAO.delete(booking2.getId());
		Assert.assertTrue(1 == deletedRecord2);
		int deletedRecord3 = bookingDataDAO.delete(booking3.getId());
		Assert.assertTrue(1 == deletedRecord3);
		int deletedRecord4 = bookingDataDAO.delete(booking4.getId());
		Assert.assertTrue(1 == deletedRecord4);
		
	}
	
	@Test
	public void testSearchBookingList() {
		LocalDateTime entryDate=LocalDateTime.now().withNano(0);
		BookingData booking=new BookingData(entryDate,LocalDateTime.of(2023,12,12,11,30),new BigDecimal("6000.0"),PaymentMethod.CASH);
		BookingData booking2=new BookingData(entryDate,LocalDateTime.of(2023,10,12,8,30),new BigDecimal("7000.0"),PaymentMethod.CASH);
		BookingData booking3=new BookingData(entryDate,LocalDateTime.of(2023,8,12,07,30),new BigDecimal("8000.0"),PaymentMethod.CASH);
		
		BookingData bookingSaved = bookingDataDAO.save(booking);
		BookingData bookingSaved1 = bookingDataDAO.save(booking2);
		BookingData bookingSaved2 = bookingDataDAO.save(booking3);
		
		List<BookingData> searchBookingList = bookingDataDAO.searchBookingList();
		assertEqualsBookingData(bookingSaved,searchBookingList.get(0));
		assertEqualsBookingData(bookingSaved1,searchBookingList.get(1));
		assertEqualsBookingData(bookingSaved2,searchBookingList.get(2));
		
		int deletedRecords = bookingDataDAO.delete(bookingSaved.getId());	
		int deletedRecords2 = bookingDataDAO.delete(bookingSaved1.getId());
		int deletedRecords3 = bookingDataDAO.delete(bookingSaved2.getId());
		Assert.assertTrue(1 == deletedRecords3);
		Assert.assertTrue(1 == deletedRecords2);
		Assert.assertTrue(1 == deletedRecords);
	}	

	@Test
	public void testGetBookingAttributes() {
		try {
			BookingData booking=new BookingData(null,null,new BigDecimal("6000.0"),null);
			
			bookingDataDAO.save(booking);
			Assert.fail("This test should have failed");
		}catch(RuntimeException e) {
			Assert.assertNotNull(e);
			String error="java.sql.SQLIntegrityConstraintViolationException: Column 'entry_date' cannot be null";
			Assert.assertEquals(error,e.getMessage());

		}
	}

	private void assertEqualsBookingData(BookingData bookingToBeCompared, BookingData bookingWhoComparesTo) {
		Assert.assertEquals(bookingToBeCompared.getId(), bookingWhoComparesTo.getId());
		Assert.assertEquals(bookingToBeCompared.getEntryDate(), bookingWhoComparesTo.getEntryDate());
		Assert.assertEquals(bookingToBeCompared.getDepartureDate(), bookingWhoComparesTo.getDepartureDate());
		Assert.assertEquals(bookingToBeCompared.getPrice(), bookingWhoComparesTo.getPrice());
		Assert.assertEquals(bookingToBeCompared.getPaymentMethod(), bookingWhoComparesTo.getPaymentMethod());
	}

	
	
}