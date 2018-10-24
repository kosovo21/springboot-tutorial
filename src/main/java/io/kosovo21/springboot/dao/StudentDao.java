package io.kosovo21.springboot.dao;

import org.springframework.data.repository.CrudRepository;

import io.kosovo21.springboot.entity.StudentEntity;

public interface StudentDao extends CrudRepository<StudentEntity, Long>{

}
