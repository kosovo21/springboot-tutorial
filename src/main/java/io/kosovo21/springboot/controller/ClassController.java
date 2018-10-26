package io.kosovo21.springboot.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.kosovo21.springboot.controller.base.BaseResponseEntityExceptionHandler;
import io.kosovo21.springboot.entity.ClassEntity;
import io.kosovo21.springboot.service.SchoolService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Class API")
@RestController
@RequestMapping("/class")
public class ClassController extends BaseResponseEntityExceptionHandler {

	@Autowired
	private SchoolService schoolService;

	@ApiOperation(value = "Save Class")
	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody ClassEntity clasz) {
		schoolService.save(clasz);
		return new ResponseEntity<>(clasz, HttpStatus.OK);
	}

}
