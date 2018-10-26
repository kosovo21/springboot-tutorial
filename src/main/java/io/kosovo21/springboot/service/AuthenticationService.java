package io.kosovo21.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.kosovo21.springboot.config.security.JwtTokenUtil;
import io.kosovo21.springboot.dto.CredentialRequest;
import io.kosovo21.springboot.dto.CredentialResponse;
import io.kosovo21.springboot.entity.RoleEntity;
import io.kosovo21.springboot.entity.UserEntity;
import io.kosovo21.springboot.repository.RoleRepository;
import io.kosovo21.springboot.repository.UserRepository;

@Service
public class AuthenticationService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	public CredentialResponse login(CredentialRequest request) throws UsernameNotFoundException {
		Optional<UserEntity> userOpt = userRepository.findById(request.getUsername());
		if (!userOpt.isPresent()) {
			throw new UsernameNotFoundException("Invalid username or password");
		}
		try {
			final Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (AuthenticationException e) {
			throw new UsernameNotFoundException("Invalid username or password");
		}
		final String token = jwtTokenUtil.generateToken(request.getUsername());
		return new CredentialResponse(token);
	}

	public UserEntity save(UserEntity user) {
		return userRepository.save(user);
	}

	public RoleEntity save(RoleEntity role) {
		return roleRepository.save(role);
	}

}
