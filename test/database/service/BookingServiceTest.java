package database.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import database.dao.BookingDataDAO;
import database.dao.GuestDataDAO;
import database.dto.BookingDataDTO;
import database.dto.GuestDataDTO;
import database.dto.NationalityDTO;
import database.dto.PaymentMethodDTO;
import service.BookingService;

@RunWith(JUnit4.class)
public class BookingServiceTest {
	
	@Mock
	private BookingDataDAO bookingDataDAO;
	
	@Mock
	private GuestDataDAO guestDataDAO;
	
	private BookingService bookingService;
	
	@Before 
	public void setUp() throws SQLException {
		MockitoAnnotations.openMocks(this);
		bookingService = new BookingService(bookingDataDAO,guestDataDAO);
	}
	
	@Test
	public void testSaveBooking() {
		BookingDataDTO bookingDataDTO=new BookingDataDTO(LocalDateTime.now(),LocalDateTime.of(2023,12,12,10,30),PaymentMethodDTO.CASH,new BigDecimal("6000"));
		bookingDataDTO.setId(123);
		Mockito.doReturn(bookingDataDTO).when(bookingDataDAO).save(any(BookingDataDTO.class));
		
		Integer id = bookingService.saveBooking(LocalDateTime.now(),LocalDateTime.of(2023,12,30,10,30),PaymentMethodDTO.CREDIT);
		assertEquals((Integer) 123, id);
	}
	
	@Test
	public void testSaveGuest() {
		GuestDataDTO guestDataDTO=new GuestDataDTO("Gabriel","Salinas",LocalDateTime.now(),NationalityDTO.ARGENTIN,"2345777",1);
		Mockito.doReturn(guestDataDTO).when(guestDataDAO).save(any(GuestDataDTO.class));
		
		bookingService.saveGuest("Gabriel","Salinas",LocalDateTime.now(),NationalityDTO.ARGENTIN,"2345777",1);

		verify(guestDataDAO).save(any(GuestDataDTO.class));
	}
	
	@Test
	public void testLoadBookingList() {
		BookingDataDTO bookingDataDTO=new BookingDataDTO(LocalDateTime.now(),LocalDateTime.of(2023,12,12,10,30),PaymentMethodDTO.CASH,new BigDecimal("6000"));
		BookingDataDTO bookingDataDTO1=new BookingDataDTO(LocalDateTime.now(),LocalDateTime.of(2024,12,12,10,30),PaymentMethodDTO.CASH,new BigDecimal("7000"));
		BookingDataDTO bookingDataDTO2=new BookingDataDTO(LocalDateTime.now(),LocalDateTime.of(2025,12,12,10,30),PaymentMethodDTO.CASH,new BigDecimal("8000"));
		
		List<BookingDataDTO>listBookingDataDTO=new ArrayList<>();
		
		listBookingDataDTO.add(bookingDataDTO);
		listBookingDataDTO.add(bookingDataDTO1);
		listBookingDataDTO.add(bookingDataDTO2);
		
		Mockito.doReturn(listBookingDataDTO).when(bookingDataDAO).searchBookingList();
		
		List<BookingDataDTO> loadBookingList = bookingService.loadBookingList();
		
		verify(bookingDataDAO).searchBookingList();
		
	}
	
	@Test
	public void testLoadBookingById() {
		BookingDataDTO bookingDataDTO=new BookingDataDTO(LocalDateTime.now(),LocalDateTime.of(2023,12,12,10,30),PaymentMethodDTO.CASH,new BigDecimal("6000"));
		bookingDataDTO.setId(1);
		BookingDataDTO bookingDataDTO1=new BookingDataDTO(LocalDateTime.now(),LocalDateTime.of(2024,12,12,10,30),PaymentMethodDTO.CASH,new BigDecimal("7000"));
		bookingDataDTO1.setId(2);
		BookingDataDTO bookingDataDTO2=new BookingDataDTO(LocalDateTime.now(),LocalDateTime.of(2025,12,12,10,30),PaymentMethodDTO.CASH,new BigDecimal("8000"));
		bookingDataDTO2.setId(3);
		
		List<BookingDataDTO>listBookingDataDTO=new ArrayList<>();
		
		listBookingDataDTO.add(bookingDataDTO);
		listBookingDataDTO.add(bookingDataDTO1);
		listBookingDataDTO.add(bookingDataDTO2);
		
		Mockito.doReturn(listBookingDataDTO.get(0)).when(bookingDataDAO).searchByIdBooking(1);
		
		BookingDataDTO loadBookingListById = bookingService.loadBookingListById(1);
		
		assertEquals(bookingDataDTO, loadBookingListById);
		
	}
	
	@Test
	public void testModifiyBooking() {

		BookingDataDTO bookingDataDTO1Modified=new BookingDataDTO(2,LocalDateTime.now(),LocalDateTime.of(2011,11,11,11,11),new BigDecimal("90000"),PaymentMethodDTO.CASH);
		Mockito.doReturn(1).when(bookingDataDAO).modify(bookingDataDTO1Modified);
		
		int modifyBooking = bookingService.modifyBooking(bookingDataDTO1Modified);
		
		verify(bookingDataDAO).modify(bookingDataDTO1Modified);
	
	}
	
	@Test
	public void testDeleteBooking() {

		Mockito.doReturn(1).when(bookingDataDAO).delete(3);
		
		bookingService.deleteBooking(3);
				
		verify(bookingDataDAO).delete(3);

	}
}

