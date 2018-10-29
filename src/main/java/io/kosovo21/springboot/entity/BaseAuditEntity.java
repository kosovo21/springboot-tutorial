package io.kosovo21.springboot.entity;

import java.util.Date;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseAuditEntity {

	@CreatedBy
	private String createdBy;
	
	@CreatedDate
	private Date createdDate;
	
	@LastModifiedBy
	private String modifiedBy;
	
	@LastModifiedDate
	private Date modifiedDate;

}
