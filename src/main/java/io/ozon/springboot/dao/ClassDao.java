package io.ozon.springboot.dao;

import org.springframework.data.repository.CrudRepository;

import io.ozon.springboot.entity.ClassEntity;

public interface ClassDao extends CrudRepository<ClassEntity, Long>{

}
