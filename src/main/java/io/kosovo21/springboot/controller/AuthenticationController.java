package io.kosovo21.springboot.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.kosovo21.springboot.config.security.JwtTokenUtil;
import io.kosovo21.springboot.controller.base.BaseResponseEntityExceptionHandler;
import io.kosovo21.springboot.dto.CredentialRequest;
import io.kosovo21.springboot.dto.CredentialResponse;
import io.kosovo21.springboot.entity.RoleEntity;
import io.kosovo21.springboot.entity.UserEntity;
import io.kosovo21.springboot.service.AuthenticationService;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController extends BaseResponseEntityExceptionHandler {

	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody CredentialRequest request) {
		final CredentialResponse response = authenticationService.login(request);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/role")
	public ResponseEntity<?> save(@Valid @RequestBody RoleEntity role) {
		authenticationService.save(role);
		return new ResponseEntity<>(role, HttpStatus.OK);
	}

	@PostMapping("/user")
	public ResponseEntity<?> save(@Valid @RequestBody UserEntity user) {
		if (!user.getPassword().isEmpty()) {
			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		}
		authenticationService.save(user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

}
