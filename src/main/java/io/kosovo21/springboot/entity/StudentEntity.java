package io.kosovo21.springboot.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;


@Data
@Entity
public class StudentEntity {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@Size(min = 3, message = "Name minimum 3 character")
	private String name;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "class_id")
	private ClassEntity currentClass;

}
