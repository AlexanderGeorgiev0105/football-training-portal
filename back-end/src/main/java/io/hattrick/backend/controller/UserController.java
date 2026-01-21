package io.hattrick.backend.controller;

import io.hattrick.backend.payload.request.*;
import io.hattrick.backend.payload.response.*;
import io.hattrick.backend.service.UserService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/register")
	public ResponseEntity<RegisterUserResponse> register(@RequestBody RegisterUserRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(this.userService.register(request));
	}

	@PostMapping("/login")
	public ResponseEntity<LoginUserResponse> login(@RequestBody LoginUserRequest request) {
		return ResponseEntity.status(HttpStatus.OK)
			.body(this.userService.login(request));
	}
}
