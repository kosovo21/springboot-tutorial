package io.kosovo21.springboot.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import io.kosovo21.springboot.entity.RoleEntity;
import io.kosovo21.springboot.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, String> {

	@Query("select user.role from UserEntity user where user.username = ?1")
	public RoleEntity findRoleByUsername(String username);

}
