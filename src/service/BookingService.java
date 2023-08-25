package service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import config.Setting;
import database.dao.BookingDataDAO;
import database.dao.GuestDataDAO;
import database.dto.BookingDataDTO;
import database.dto.GuestDataDTO;
import database.dto.NationalityDTO;
import database.dto.PaymentMethodDTO;

public class BookingService {
	
	private final BookingDataDAO bookingDataDAO;
	private final GuestDataDAO guestDataDAO;
	private final Setting setting;
	
	public BookingService() {
		this.bookingDataDAO=new BookingDataDAO();
		this.guestDataDAO = new GuestDataDAO();
		this.setting = new Setting();
	}
	
	public BookingService(BookingDataDAO bookingDataDAO,GuestDataDAO guestDataDAO) {
		this.bookingDataDAO=bookingDataDAO;
		this.guestDataDAO = guestDataDAO;
		this.setting = new Setting();
	}

	public Integer saveBooking(LocalDateTime entryDate, LocalDateTime departureDate, PaymentMethodDTO methodPayment)  {
		if (entryDate == null) {
			throw new IllegalArgumentException("Entry date was null");
		}
		if (departureDate == null) {
			throw new IllegalArgumentException("Departure date was null");
		}
		if (methodPayment == null) {
			throw new IllegalArgumentException("Method payment was null");
		}		
		if (departureDate.toLocalDate().isBefore(entryDate.toLocalDate())){
			throw new IllegalArgumentException("Entry date can't be before departure date");
		}
		if (departureDate.toLocalDate().isEqual(entryDate.toLocalDate())){
			throw new IllegalArgumentException("Entry date can't be equal to departure date");
		}
		if (entryDate.isBefore(LocalDateTime.now())) {
			throw new IllegalArgumentException("Entry date can't be before today");
		}
		
		Duration duration=Duration.between(entryDate, departureDate);
		BigDecimal days=new BigDecimal(duration.toDays());
		
		BookingDataDTO bookingDataDTO=new BookingDataDTO(entryDate,departureDate,methodPayment,bookingPrice(days));
		BookingDataDTO saveBooking = bookingDataDAO.save(bookingDataDTO);
		return saveBooking.getId();
	}

	private  BigDecimal bookingPrice(BigDecimal days) {
		final BigDecimal pricePerNight=new BigDecimal(setting.getProperty("price_per_night"));
		return  pricePerNight.multiply(days);
	}
	
	public void saveGuest(String name, String lastName,LocalDateTime birthDate, NationalityDTO nationality, String phoneNumber, Integer idBooking) {
		GuestDataDTO guestDataDTO=new GuestDataDTO(name,lastName,birthDate,nationality,phoneNumber,idBooking);	
		guestDataDAO.save(guestDataDTO);
	}

	public List<BookingDataDTO> loadBookingList() {
		return bookingDataDAO.searchBookingList();
	}
	
	public BookingDataDTO loadBookingListById(int idSearch) {
		return bookingDataDAO.searchByIdBooking(idSearch);
	}

	public int modifyBooking (BookingDataDTO bookingDataDTO) {
		return bookingDataDAO.modify(bookingDataDTO);
	}
	
	public int deleteBooking (int id){
		return bookingDataDAO.delete(id);
	}
}
