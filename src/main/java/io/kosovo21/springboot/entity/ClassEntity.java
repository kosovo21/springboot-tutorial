package io.kosovo21.springboot.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.kosovo21.springboot.enums.Major;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "class")
public class ClassEntity extends BaseIdEntity {

	@NotNull
	private Integer grade;

	@NotNull
	private Major major;

}
