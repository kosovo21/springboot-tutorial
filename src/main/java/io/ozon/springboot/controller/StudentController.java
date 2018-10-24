package io.ozon.springboot.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.ozon.springboot.dto.StudentDto;
import io.ozon.springboot.entity.StudentEntity;
import io.ozon.springboot.service.SchoolService;
import io.ozon.springboot.util.FilterParser;
import io.ozon.springboot.util.SearchCriteria;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/student")
public class StudentController extends CustomizedResponseEntityExceptionHandler{

	@Autowired
	private SchoolService schoolService;

	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody StudentEntity student) {
		schoolService.save(student);
		return new ResponseEntity<>(student, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<StudentEntity> getOne(@PathVariable Long id) {
		Optional<StudentEntity> student = schoolService.findById(id);
		if (!student.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<StudentEntity>(student.get(), HttpStatus.OK);
	}

	@ApiOperation(value = "Search Student", notes = "get page of student based on search criteria, example = currentClass_major:IPA")
	@GetMapping("/filter")
	public ResponseEntity<?> getOne(@RequestParam(required = true) String search,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
		List<SearchCriteria> params = FilterParser.paramParse(search);
		Page<StudentDto> students = schoolService.search(params, page, size);
		return new ResponseEntity<Page<StudentDto>>(students, HttpStatus.OK);
	}

}
