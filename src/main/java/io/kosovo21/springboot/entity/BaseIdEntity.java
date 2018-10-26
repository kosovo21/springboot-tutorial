package io.kosovo21.springboot.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseIdEntity extends BaseAuditEntity {

	@Id
	@GeneratedValue
	private Long id;

}
