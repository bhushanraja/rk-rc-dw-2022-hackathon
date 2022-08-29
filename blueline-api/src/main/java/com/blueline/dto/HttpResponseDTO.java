package com.blueline.dto;

import lombok.Data;

@Data
public class HttpResponseDTO {
	private String successMessage;
	private int statusCode;
}
