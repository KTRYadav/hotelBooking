package com.hotel.hotelBooking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestBody;

import com.hotel.model.BookingRequest;
import com.hotel.service.NoBookingDetailsFound;
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HotelBookingApplicationControllerTest {

	@LocalServerPort
	private int port;
	
	TestRestTemplate restTemplate=new TestRestTemplate();
	HttpHeaders headers=new HttpHeaders();
	
	@Test
	public void getBookingDetails() throws NoBookingDetailsFound {	
	HttpEntity<String> entity=new HttpEntity<>(null,headers);
	ResponseEntity<Object> response=restTemplate.exchange(createUrlWithPort("/hotel/findAllBookings/1235"),HttpMethod.GET,entity,Object.class);
	Assertions.assertEquals(response.getStatusCodeValue(), 500);
	}
	
	@Test
	public void getBookingDetailsByDate() throws NoBookingDetailsFound {	
	HttpEntity<String> entity=new HttpEntity<>(null,headers);
	ResponseEntity<Integer> response=restTemplate.exchange(createUrlWithPort("/hotel/findAllBookingsByDate?bookingDate=04-05-2024"),HttpMethod.GET,entity,Integer.class);
	Assertions.assertNotNull(response);
	Assertions.assertEquals(response.getBody(), 10);
	}
	
	@Test
	public void createBookingDetails() throws Exception {	
		HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
             String  filePath="file:src/test/resources/booking_request.json";
        String requestBody=new String(Files.readAllBytes(ResourceUtils.getFile(filePath).toPath()));
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(createUrlWithPort("/hotel/createBooking"), request, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	private String createUrlWithPort(String uri) {		
		return "http://localhost:"+port+uri;
	}
}
