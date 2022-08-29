package com.blueline.entity;

import lombok.Data;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="taxi_booking")
@Data
public class TaxiBookingEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer	bookingId;
	private String source;
	private String destination;
	private Float fare;
	private LocalDate travelDate;
	private Long userMobile;
	private Character status;
}
