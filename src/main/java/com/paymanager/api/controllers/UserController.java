package com.paymanager.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paymanager.api.domain.user.UserEntity;
import com.paymanager.api.dtos.UserDTO;
import com.paymanager.api.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<UserEntity> createUser(@RequestBody UserDTO user) {
		UserEntity newUser = userService.createUser(user);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<UserEntity>> getAllUsers() {
		List<UserEntity> users = this.userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

}
