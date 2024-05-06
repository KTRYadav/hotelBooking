package com.hotel.hotelBooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin("/")
@ComponentScan(basePackages = "com.hotel")
public class HotelBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelBookingApplication.class, args);
	}

}
