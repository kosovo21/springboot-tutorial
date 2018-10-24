package io.kosovo21.springboot.dao;

import org.springframework.data.repository.CrudRepository;

import io.kosovo21.springboot.entity.ClassEntity;

public interface ClassDao extends CrudRepository<ClassEntity, Long>{

}
