package io.ozon.springboot.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDetailDto {

	private Date timestamp;
	private String message;
	private String details;

}
