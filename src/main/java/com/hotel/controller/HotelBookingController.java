package com.hotel.controller;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.model.BookingRequest;
import com.hotel.model.Room;
import com.hotel.service.HotelBookingService;
import com.hotel.service.NoBookingDetailsFound;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping("/hotel")
public class HotelBookingController  {
	
	@Autowired
	HotelBookingService bookingService;
	
	@GetMapping("/findAllBookings/{bookingId}")
	public List<Room> getBookingDetails(@PathVariable String bookingId) throws NoBookingDetailsFound {				
		return bookingService.getBookingDetailsByBookingId(bookingId);
	}
	
	@GetMapping("/findAllBookingsByDate")
	public int  getBookingDetailsByDate(@RequestParam(name="bookingDate" ,required = false) String bookingDate) {
		int numberofRoomsAvailable = 0;
		if(StringUtils.isNotBlank(bookingDate)) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(bookingDate, formatter);
        numberofRoomsAvailable= bookingService.getBookingDetailsByDate(localDate);
		}
		return numberofRoomsAvailable;
	}
	@PostMapping("/createBooking")
	public String createBookingDetails(@RequestBody List<BookingRequest> bookingRequestList) {		
		return bookingService.createBookingDetails(bookingRequestList);
	}
	

}
