package io.ozon.springboot.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class StudentEntity {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	@ManyToOne
	@JoinColumn(name = "class_id")
	private ClassEntity currentClass;

}
