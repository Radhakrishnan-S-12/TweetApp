package com.tweetApp.tweet.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tweetApp.tweet.DTO.RegisterDTO;
import com.tweetApp.tweet.Service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

import com.tweetApp.tweet.DTO.UserDTO;
import com.tweetApp.tweet.DTO.UserTweetsDTO;
import com.tweetApp.tweet.DTO.ResetPasswordRequestDTO;
import com.tweetApp.tweet.DTO.ResetPasswordResponseDTO;
import com.tweetApp.tweet.DTO.LoginRequestDTO;
import com.tweetApp.tweet.DTO.LoginResponseDTO;

@Slf4j
@Api(tags = { "TwittersUserManagement" })
@RestController
public class UserController {

	@Autowired
	UserService userService;

	@ApiOperation(value = "User Register", response = RegisterDTO.class, notes = "This API used to Register user.It receives emailId,loginId,firstName, lastName,"
			+ "and return registered user details ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful operation"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Unauthorized access"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@PostMapping("/register")
	public ResponseEntity<RegisterDTO> register(@RequestBody RegisterDTO registerDTO) {
		log.info("Inside UserController register method");
		registerDTO = userService.register(registerDTO);
		return ResponseEntity.ok().body(registerDTO);
	}

	@ApiOperation(value = "User Login", response = LoginResponseDTO.class, notes = "This API used to Login the user.It receives emailId,password"
			+ "and return logged in user details ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful operation"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Unauthorized access"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> signUp(@RequestBody LoginRequestDTO loginRequest) {
		System.out.println("IN call login user");
		LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
		loginResponseDTO = userService.signUp(loginRequest);
		log.info("Inside UserController signUp method");
		return ResponseEntity.ok().body(loginResponseDTO);
	}

	@ApiOperation(value = "Reset Password", response = ResetPasswordResponseDTO.class, notes = "This API used to reset the password of loggedin user.It receives new Password and old Password"
			+ "and reset password details ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful operation"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Unauthorized access"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@PutMapping("/reset")
	public ResponseEntity<ResetPasswordResponseDTO> resetpassword(@RequestBody ResetPasswordRequestDTO resetRequest) {
		ResetPasswordResponseDTO resetPasswordResponse = new ResetPasswordResponseDTO();
		resetPasswordResponse = userService.resetpassword(resetRequest);
		log.info("Inside UserController resetpassword method");
		return ResponseEntity.ok().body(resetPasswordResponse);
	}

	@ApiOperation(value = "Get all user", response = UserDTO.class, notes = "This API used to get all users account."
			+ "and return all loggedin users account details ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful operation"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Unauthorized access"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@GetMapping("/getallUsers")
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		log.info("Inside UserController getAllUsers method");
		return ResponseEntity.ok().body(userService.getAllUsers());
	}

	@ApiOperation(value = "Get user tweet details", response = UserTweetsDTO.class, notes = "This API used to get loggedin user tweet details."
			+ "and return all loggedin users tweet details ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful operation"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Unauthorized access"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@GetMapping("/UsersTweet")
	public ResponseEntity<List<UserTweetsDTO>> getUsersTweet(@RequestParam("email") String email) {
		log.info("Inside UserController getAllUsers getUsersTweet");
		return ResponseEntity.ok().body(userService.getUsersTweet(email));
	}
}
