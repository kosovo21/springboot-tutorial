package io.kosovo21.springboot.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseAuditEntity {

	private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date modifiedDate;

}
