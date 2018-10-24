package io.kosovo21.springboot.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class ClassEntity {

	@Id
	@GeneratedValue
	private Long id;
	
	private Integer grade;
	private String major;
	
}
