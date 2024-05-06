package com.hotel.service;

import java.io.Serializable;

public class NoBookingDetailsFound extends Exception implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String errorCode;
	String errorMessage;

	public NoBookingDetailsFound(String errorMsg) {
		super(errorMsg);
	}

	public NoBookingDetailsFound(String errorCode, String errorMsg) {

		this.errorCode = errorCode;
		this.errorMessage = errorMsg;
	}

}
