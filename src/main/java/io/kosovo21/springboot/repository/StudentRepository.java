package io.kosovo21.springboot.repository;

import org.springframework.data.repository.CrudRepository;

import io.kosovo21.springboot.entity.StudentEntity;

public interface StudentRepository extends CrudRepository<StudentEntity, Long>{

}
