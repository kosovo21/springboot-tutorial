package io.kosovo21.springboot.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Setter;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Setter
@Entity
@Table(name = "user")
public class UserEntity extends BaseAuditEntity {

	@Id
	private String username;

	@NonNull
	private String password;

	@Email
	@NotNull
	private String email;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private RoleEntity role;
}
