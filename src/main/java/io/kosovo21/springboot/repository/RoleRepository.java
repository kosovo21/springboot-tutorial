package io.kosovo21.springboot.repository;

import org.springframework.data.repository.CrudRepository;

import io.kosovo21.springboot.entity.RoleEntity;

public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

}
