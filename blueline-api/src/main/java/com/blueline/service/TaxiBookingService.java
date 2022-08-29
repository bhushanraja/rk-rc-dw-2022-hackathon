package com.blueline.service;

import java.util.List;

import com.blueline.dto.FareEstimationDTO;
import com.blueline.dto.TaxiBookingDTO;
import com.blueline.exception.TaxiServiceException;

public interface TaxiBookingService {
		public Integer bookTaxi(TaxiBookingDTO booking) throws TaxiServiceException;
		public List<TaxiBookingDTO> getBookingDetails(Long mobileNo) throws TaxiServiceException;
		public Integer cancelBooking(Integer bookingId) throws TaxiServiceException;
		public List<FareEstimationDTO> getFares() throws TaxiServiceException;
		public Integer updateRide(Integer bookingId, TaxiBookingDTO taxiBookingDTO) throws TaxiServiceException;
}
