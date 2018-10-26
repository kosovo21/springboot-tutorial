package io.kosovo21.springboot.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDetailResponse {

	private Date timestamp;
	private String message;
	private String details;

}
