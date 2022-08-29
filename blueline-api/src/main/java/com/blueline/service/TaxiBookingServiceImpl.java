package com.blueline.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.blueline.dto.FareEstimationDTO;
import com.blueline.dto.TaxiBookingDTO;
import com.blueline.entity.FareEstimationEntity;
import com.blueline.entity.TaxiBookingEntity;
import com.blueline.exception.TaxiServiceException;
import com.blueline.repository.FareRepository;
import com.blueline.repository.TaxiBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("bookingService")
@Transactional
public class TaxiBookingServiceImpl implements TaxiBookingService {
	@Autowired
	TaxiBookingRepository bookingRepository;
	
	@Autowired
	FareRepository fareRepository;

	@Override
	public Integer bookTaxi(TaxiBookingDTO bookingDTO) throws TaxiServiceException {
		Float fare = fareRepository.findFareBySourceAndDestination(bookingDTO.getSource(), bookingDTO.getDestination());
		if (fare == null) {
			throw new TaxiServiceException("BookingService.AREA_INVALID");
		}
		bookingDTO.setFare(fare);
		bookingDTO.setStatus('B');
		TaxiBookingEntity bookingEntity = new TaxiBookingEntity();
		bookingEntity.setDestination(bookingDTO.getDestination());
		bookingEntity.setSource(bookingDTO.getSource());
		bookingEntity.setFare(bookingDTO.getFare());
		bookingEntity.setTravelDate(bookingDTO.getTravelDate());
		bookingEntity.setUserMobile(bookingDTO.getUserMobile());
		bookingEntity.setStatus(bookingDTO.getStatus());
		return bookingRepository.save(bookingEntity).getBookingId();
	}

	@Override
	public List<TaxiBookingDTO> getBookingDetails(Long mobileNo) throws TaxiServiceException {
		List<TaxiBookingDTO> taxiBookingEntities = new ArrayList<>();
		List<TaxiBookingEntity> taxiBookings = bookingRepository.findByUserMobile(mobileNo);
		if (taxiBookings.isEmpty())
			throw new TaxiServiceException("BookingService.BOOKINGS_NOT_FOUND");
		taxiBookings.forEach( taxiBooking -> {
			TaxiBookingDTO taxiBookingDTO = new TaxiBookingDTO();
			taxiBookingDTO.setBookingId(taxiBooking.getBookingId());
			taxiBookingDTO.setDestination(taxiBooking.getDestination());
			taxiBookingDTO.setSource(taxiBooking.getSource());
			taxiBookingDTO.setFare(taxiBooking.getFare());
			taxiBookingDTO.setStatus(taxiBooking.getStatus());
			taxiBookingDTO.setTravelDate(taxiBooking.getTravelDate());
			taxiBookingDTO.setUserMobile(taxiBooking.getUserMobile());
			taxiBookingEntities.add(taxiBookingDTO);
		});
		
		return taxiBookingEntities;
	}

	@Override
	public Integer cancelBooking(Integer bookingId) throws TaxiServiceException {
		Optional<TaxiBookingEntity> optional = bookingRepository.findById(bookingId);
		if(!optional.isPresent()) {
			throw new TaxiServiceException("BookingService.BOOKINGS_NOT_FOUND");
		}
		else {
			bookingRepository.deleteById(optional.get().getBookingId());
			return optional.get().getBookingId();
		}
	}

	@Override
	public List<FareEstimationDTO> getFares() throws TaxiServiceException {
		List<FareEstimationDTO> fareEstimationDTOs = new ArrayList<>();
		List<FareEstimationEntity> fares = fareRepository.findFares();
		if (fares.isEmpty())
			throw new TaxiServiceException("BookingService.FARES_NOT_FOUND");
		fares.forEach( fare -> {
			FareEstimationDTO fareEstimationDTO = new FareEstimationDTO();
			fareEstimationDTO.setFareId(fare.getFareId());
			fareEstimationDTO.setSource(fare.getSource());
			fareEstimationDTO.setDestination(fare.getDestination());
			fareEstimationDTO.setFare(fare.getFare());
			fareEstimationDTOs.add(fareEstimationDTO);
		});
		
		return fareEstimationDTOs;
	}
	@Override
	public Integer updateRide(Integer bookingId, TaxiBookingDTO taxiBookingDTO) throws TaxiServiceException {
		Optional<TaxiBookingEntity> optional = bookingRepository.findById(bookingId);
		if(!optional.isPresent()) {
			throw new TaxiServiceException("BookingService.BOOKINGS_NOT_FOUND");
		}
		else {
			TaxiBookingEntity bookingEntity= optional.get();
			bookingEntity.setSource(taxiBookingDTO.getSource());
			bookingEntity.setDestination(taxiBookingDTO.getDestination());
			Float fare = fareRepository.findFareBySourceAndDestination(taxiBookingDTO.getSource(), taxiBookingDTO.getDestination());
			if (fare == null) {
				throw new TaxiServiceException("BookingService.AREA_INVALID");
			}
			bookingEntity.setFare(fare);
			bookingEntity.setTravelDate(taxiBookingDTO.getTravelDate());
			return bookingRepository.save(bookingEntity).getBookingId();
		}
	}
}
