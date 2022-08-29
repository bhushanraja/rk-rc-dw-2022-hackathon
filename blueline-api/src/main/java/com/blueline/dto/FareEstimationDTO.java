package com.blueline.dto;

import lombok.Data;

@Data
public class FareEstimationDTO {
	private Integer fareId;
	private String source;
	private String destination;
	private Float fare;
}
