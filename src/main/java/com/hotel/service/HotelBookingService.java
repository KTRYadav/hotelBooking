package com.hotel.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.hotel.model.BookingRequest;
import com.hotel.model.Room;

@Component
public class HotelBookingService {
	

	Map<String, List<Room>> bookingDataMap = new LinkedHashMap<>();
	@Value("${room.total}")
	private int totalAvailableRooms;
	
	public synchronized String createBookingDetails(List<BookingRequest> bookingRequestList) {
		if (CollectionUtils.isEmpty(bookingRequestList)) {
			return "Input Request can not be Empty";
		}
		List<Room> roomsList=new ArrayList<>();
		if(totalAvailableRooms>0) {			 
			String bookingNumber = UUID.randomUUID().toString();
			bookingRequestList.stream().forEach(obj -> {
				Room room=new Room();
				room.setBookingNumber(bookingNumber);
				room.setRoomNumber(obj.getRoomNumber());
				room.setBookingDate(obj.getBookingDate());
				room.setGuestName(obj.getGuestName());
				roomsList.add(room);
				totalAvailableRooms--;
			});
			bookingDataMap.put(bookingNumber, roomsList);
			return bookingNumber;
		}
			
		return "Sorry Rooms are Not Available ,please try again after some Time";			
		 
	}


	public List<Room> getBookingDetailsByBookingId(String bookingId) throws NoBookingDetailsFound {
		List<Room> resultList=bookingDataMap.get(bookingId);
		 if(CollectionUtils.isEmpty(resultList)) {
			 throw new NoBookingDetailsFound("Booking Details Not found with BookingID "+bookingId);
		 }
		return resultList;
	}
	
	public int getBookingDetailsByDate(LocalDate date){
	 List<Room> matchingRoomList=new ArrayList<>();
		if (date != null) {
			bookingDataMap.entrySet().forEach(obj -> obj.getValue().forEach(room -> {
				if (date.isAfter(formatDate(room.getBookingDate()))) {
					matchingRoomList.add(room);
				}
			}));
		}
			return (totalAvailableRooms +matchingRoomList.size());

	}
	
	public LocalDate formatDate(Date date) {
		LocalDate localDate = null ;
		if(date!=null) {
			Instant instant = date.toInstant();	        
			 localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
		}
        return localDate;
		
	}

}
