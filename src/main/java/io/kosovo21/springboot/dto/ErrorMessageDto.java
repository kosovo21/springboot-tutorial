package io.kosovo21.springboot.dto;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessageDto {
	private List<String> errors;

	public ErrorMessageDto(String error) {
		this(Collections.singletonList(error));
	}

	public ErrorMessageDto(String... errors) {
		this(Arrays.asList(errors));
	}

}
