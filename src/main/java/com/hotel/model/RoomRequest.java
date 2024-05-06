package com.hotel.model;

import java.util.Date;

import lombok.NonNull;

public class RoomRequest {
	@NonNull
	private int roomNumber;	
	@NonNull
	private String guestName;
	@NonNull
	private Date bookingDate;
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
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
