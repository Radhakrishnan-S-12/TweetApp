package com.tweetApp.tweet.Service;

import com.tweetApp.tweet.DTO.RegisterDTO;
import com.tweetApp.tweet.DTO.ResetPasswordRequestDTO;
import com.tweetApp.tweet.DTO.ResetPasswordResponseDTO;
import com.tweetApp.tweet.DTO.UserDTO;
import com.tweetApp.tweet.DTO.UserTweetsDTO;

import java.util.List;

import com.tweetApp.tweet.DTO.LoginRequestDTO;
import com.tweetApp.tweet.DTO.LoginResponseDTO;

public interface UserService {

	RegisterDTO register(RegisterDTO registerDTO);
	
	LoginResponseDTO signUp(LoginRequestDTO loginRequestDTO);
	
	ResetPasswordResponseDTO resetpassword(ResetPasswordRequestDTO resetRequest);

	List<UserDTO> getAllUsers();

	List<UserTweetsDTO> getUsersTweet(String email);

}
