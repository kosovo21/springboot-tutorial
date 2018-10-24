package io.ozon.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.ozon.springboot.entity.ClassEntity;
import io.ozon.springboot.service.SchoolService;

@RestController
@RequestMapping("/class")
public class ClassController {

	@Autowired
	private SchoolService schoolService;

	@PostMapping
	public ResponseEntity<?> save(@RequestBody ClassEntity clasz) {
		schoolService.save(clasz);
		return new ResponseEntity<>(clasz, HttpStatus.OK);
	}

}
