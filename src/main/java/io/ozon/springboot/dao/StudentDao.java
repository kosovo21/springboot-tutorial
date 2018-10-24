package io.ozon.springboot.dao;

import org.springframework.data.repository.CrudRepository;

import io.ozon.springboot.entity.StudentEntity;

public interface StudentDao extends CrudRepository<StudentEntity, Long>{

}
