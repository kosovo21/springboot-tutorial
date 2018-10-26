package io.kosovo21.springboot.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "student")
public class StudentEntity extends BaseIdEntity {

	@NotNull
	@Size(min = 3, message = "Name minimum 3 character")
	private String name;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "class_id")
	private ClassEntity currentClass;

}
