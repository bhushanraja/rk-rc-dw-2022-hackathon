package com.blueline.dto;

import lombok.Data;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

@Data
public class TaxiBookingDTO {
	private Integer	bookingId;
	@NotNull(message = "{booking.source.absent}")
	private String source;
	@NotNull(message = "{booking.destination.absent}")
	private String destination;
	private Float fare;
	@NotNull(message = "{booking.travelDate.absent}")
	@Future(message = "{booking.travelDate.invalid}")
	private LocalDate travelDate;
	@NotNull(message = "{booking.userMobile.absent}")
	private Long userMobile;
	private Character status;
}
