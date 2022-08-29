package com.blueline.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="taxi_fare")
@Data
public class FareEstimationEntity {
	@Id
	private Integer fareId;
	private String source;
	private String destination;
	private Float fare;
}
