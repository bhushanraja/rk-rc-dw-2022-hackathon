package com.blueline.controller;

import java.util.List;

import javax.validation.Valid;

import com.blueline.dto.FareEstimationDTO;
import com.blueline.dto.TaxiBookingDTO;
import com.blueline.exception.TaxiServiceException;
import com.blueline.service.TaxiBookingService;
import com.blueline.dto.HttpResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@Validated
@RestController
@RequestMapping(value = "/bookings")
@Slf4j
public class TaxiBookingController {
	
	@Autowired
	private TaxiBookingService bookingService;

	@Autowired
	private Environment environment;

	@PostMapping(value = "/book-taxi")
	public ResponseEntity<HttpResponseDTO> bookTaxi(@Valid @RequestBody TaxiBookingDTO taxiBookingDTO) throws TaxiServiceException {
		log.info("Controller call for bookTaxi with taxiBookingDTO: {}", taxiBookingDTO);
		Integer bookingId = bookingService.bookTaxi(taxiBookingDTO);
		HttpResponseDTO httpResponseDTo = new HttpResponseDTO();
		httpResponseDTo.setSuccessMessage(environment.getProperty("API.BOOKING_SUCCESSFUL")+bookingId);
		httpResponseDTo.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<HttpResponseDTO>(httpResponseDTo, HttpStatus.CREATED);
	}

	@GetMapping(value = "/{mobileNo}")
	public ResponseEntity<List<TaxiBookingDTO>> getBookingDetails(@PathVariable Long mobileNo) throws TaxiServiceException {
		log.info("Controller call for getBookingDetails with mobileNo: {}", mobileNo);
		return new ResponseEntity<List<TaxiBookingDTO>>(bookingService.getBookingDetails(mobileNo), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{bookingId}")
	public ResponseEntity<HttpResponseDTO> cancelBooking(@PathVariable Integer bookingId) throws TaxiServiceException {
		log.info("Controller call for cancelBooking with bookingId: {}", bookingId);
		Integer cancelId = bookingService.cancelBooking(bookingId);
		HttpResponseDTO httpResponseDTo = new HttpResponseDTO();
		httpResponseDTo.setSuccessMessage(environment.getProperty("API.BOOKING_CANCELLED")+cancelId);
		httpResponseDTo.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<HttpResponseDTO>(httpResponseDTo, HttpStatus.OK);
	}
	
	@GetMapping(value = "/fares")
	public ResponseEntity<List<FareEstimationDTO>> getFareDetails() throws TaxiServiceException {
		log.info("Controller call for getFareDetails");
		return new ResponseEntity<List<FareEstimationDTO>>(bookingService.getFares(), HttpStatus.OK);
	}
	
	@PutMapping(value = "/{bookingId}")
	public ResponseEntity<HttpResponseDTO> updateRide(@PathVariable Integer bookingId, @Valid @RequestBody TaxiBookingDTO taxiBookingDTO) throws TaxiServiceException {
		log.info("Controller call for updateRide with bookingId: {} and taxiBookingDTO: {}", bookingId, taxiBookingDTO);
		Integer updateBookingId = bookingService.updateRide(bookingId, taxiBookingDTO);
		HttpResponseDTO httpResponseDTo = new HttpResponseDTO();
		httpResponseDTo.setSuccessMessage(environment.getProperty("API.UPDATE_SUCCESSFUL")+updateBookingId);
		httpResponseDTo.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<HttpResponseDTO>(httpResponseDTo, HttpStatus.OK);
	}
}
