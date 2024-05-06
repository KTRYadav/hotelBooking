package com.hotel.model;

import java.util.Date;

import lombok.NonNull;

public class BookingRequest {
	@NonNull
	private String roomNumber;
	@NonNull
	private String guestName;
	@NonNull
	private Date bookingDate;
	
	

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

}
