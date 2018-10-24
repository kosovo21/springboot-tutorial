package io.kosovo21.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentDto {

	private long id;
	private String name;

	private Integer grade;
	private String major;

}
