package com.tweetApp.tweet.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.tweetApp.tweet.DTO.LoginRequestDTO;
import com.tweetApp.tweet.DTO.LoginResponseDTO;
import com.tweetApp.tweet.DTO.RegisterDTO;
import com.tweetApp.tweet.DTO.ReplyDTO;
import com.tweetApp.tweet.DTO.ResetPasswordRequestDTO;
import com.tweetApp.tweet.DTO.ResetPasswordResponseDTO;
import com.tweetApp.tweet.DTO.UserDTO;
import com.tweetApp.tweet.DTO.UserTweetsDTO;
import com.tweetApp.tweet.Model.Register;
import com.tweetApp.tweet.Model.Reply;
import com.tweetApp.tweet.Model.Tweet;
import com.tweetApp.tweet.Repository.RegisterRepository;
import com.tweetApp.tweet.Repository.ReplyRepository;
import com.tweetApp.tweet.Repository.TweetRepository;
import com.tweetApp.tweet.ServiceImpl.UserServiceImpl;

public class UserServiceImplTest {

	@Mock
	private RegisterRepository registerRepo;

	@Mock
	private TweetRepository tweetRepo;

	@Mock
	private ReplyRepository replyRepo;

	@InjectMocks
	private UserServiceImpl userServiceMock = new UserServiceImpl();

	@Test
	public void registerPositiveTest() throws Exception {
		RegisterDTO registerDTO = new RegisterDTO();
		registerDTO.setId(1);
		registerDTO.setEmail("fse@gmail.com");
		registerDTO.setFirstName("admin");
		registerDTO.setLastName("admin");
		registerDTO.setPassword("admin");
		registerDTO.setGender("male");

		when(registerRepo.findByemail("fse@gmail.com")).thenReturn(null);

		when(registerRepo.findById(1)).thenReturn(null);
		RegisterDTO actualresp = userServiceMock.register(registerDTO);

		assertEquals(registerDTO, actualresp);
	}

	@Test
	public void registerNegativeTest() throws Exception {

		RegisterDTO expectedres = new RegisterDTO();
		expectedres.setErrorMessage("Entered E-mail/Login Id Already Exists");
		expectedres.setId(1);
		expectedres.setEmail("fse@gmail.com");
		RegisterDTO registerDTO = new RegisterDTO();
		registerDTO.setId(1);
		registerDTO.setEmail("fse@gmail.com");

		Register resgister = new Register();
		resgister.setEmail("fse@gmail.com");
		resgister.setId(1);

		when(registerRepo.findByemail("fse@gmail.com")).thenReturn(Optional.of(resgister));

		when(registerRepo.findById(1)).thenReturn(resgister);

		RegisterDTO actualresp = userServiceMock.register(registerDTO);

		assertEquals(expectedres, actualresp);

	}

	@Test
	public void signUpPostiveTest() throws Exception {
		LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
		loginRequestDTO.setEmail("fse@gmail.com");
		loginRequestDTO.setPassword("pass");

		LoginResponseDTO expected = new LoginResponseDTO();
		expected.setStatus(true);
		expected.setEmail("fse@gmail.com");
		Register register = new Register();
		register.setEmail("fse@gmail.com");
		register.setPassword("pass");

		when(registerRepo.findByemail("fse@gmail.com")).thenReturn(Optional.of(register));

		LoginResponseDTO actual = userServiceMock.signUp(loginRequestDTO);
		assertEquals(expected, actual);
	}

	@Test
	public void signUpElseTest() throws Exception {
		LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
		loginRequestDTO.setEmail("fse@gmail.com");
		loginRequestDTO.setPassword("passs");

		LoginResponseDTO expected = new LoginResponseDTO();
		expected.setStatus(false);
		expected.setErrorMessage("Incorrect UserName/Password");
		Register register = new Register();
		register.setEmail("fse@gmail.com");
		register.setPassword("pass");

		when(registerRepo.findByemail("fse@gmail.com")).thenReturn(Optional.of(register));

		LoginResponseDTO actual = userServiceMock.signUp(loginRequestDTO);
		assertEquals(expected, actual);
	}

	@Test
	public void signUpNullTest() throws Exception {
		LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
		loginRequestDTO.setEmail("fse@gmail.com");
		loginRequestDTO.setPassword("passs");

		LoginResponseDTO expected = new LoginResponseDTO();
		expected.setStatus(false);
		expected.setErrorMessage("Incorrect UserName/Password");

		when(registerRepo.findByemail("fse@gmail.com")).thenReturn(null);

		LoginResponseDTO actual = userServiceMock.signUp(loginRequestDTO);
		assertEquals(expected, actual);
	}

	@Test
	public void resetpasswordPositiveTest() throws Exception {

		ResetPasswordRequestDTO resetRequest = new ResetPasswordRequestDTO();
		resetRequest.setOldpassword("old");
		resetRequest.setNewpassword("new");
		resetRequest.setEmailId("fse@gmail.com");

		ResetPasswordResponseDTO expected = new ResetPasswordResponseDTO();
		expected.setSuccessMessage("Password Updated Successfully");

		Register register = new Register();
		register.setEmail("fse@gmail.com");
		register.setPassword("old");

		when(registerRepo.findByemail("fse@gmail.com")).thenReturn(Optional.of(register));

		ResetPasswordResponseDTO actual = userServiceMock.resetpassword(resetRequest);
		assertEquals(expected, actual);
	}

	@Test
	public void resetpasswordElseTest() throws Exception {

		ResetPasswordRequestDTO resetRequest = new ResetPasswordRequestDTO();
		resetRequest.setOldpassword("new");
		resetRequest.setNewpassword("new");
		resetRequest.setEmailId("fse@gmail.com");

		ResetPasswordResponseDTO expected = new ResetPasswordResponseDTO();
		expected.setErrorMessage("Entered Old Password is Incorrect");

		Register register = new Register();
		register.setEmail("fse@gmail.com");
		register.setPassword("old");

		when(registerRepo.findByemail("fse@gmail.com")).thenReturn(Optional.of(register));

		ResetPasswordResponseDTO actual = userServiceMock.resetpassword(resetRequest);
		assertEquals(expected, actual);
	}

	@Test
	public void resetpasswordNullTest() throws Exception {

		ResetPasswordRequestDTO resetRequest = new ResetPasswordRequestDTO();
		resetRequest.setOldpassword("new");
		resetRequest.setNewpassword("new");
		resetRequest.setEmailId("fse@gmail.com");

		ResetPasswordResponseDTO expected = new ResetPasswordResponseDTO();
		expected.setErrorMessage("Entered Old Password is Incorrect");

		Register register = new Register();
		register.setEmail("fse@gmail.com");
		register.setPassword("old");

		when(registerRepo.findByemail("fse@gmail.com")).thenReturn(Optional.empty());

		ResetPasswordResponseDTO actual = userServiceMock.resetpassword(resetRequest);
		assertEquals(expected, actual);
	}

	@Test
	public void getAllUsersPositiveTest() throws Exception {

		List<Register> registerList = new ArrayList<>();
		Register register = new Register();
		register.setEmail("fse@gmail.com");
		register.setId(1);
		register.setFirstName("fname");
		register.setGender("male");

		registerList.add(register);

		List<UserDTO> expectedList = new ArrayList<>();

		UserDTO userDTO = new UserDTO();
		userDTO.setEmail("fse@gmail.com");
		userDTO.setId(1);
		userDTO.setFirstName("fname");
		userDTO.setGender("male");

		expectedList.add(userDTO);

		when(registerRepo.findAll()).thenReturn(registerList);

		List<UserDTO> actual = userServiceMock.getAllUsers();
		assertEquals(expectedList, actual);

	}

	@Test
	public void getUsersTweetPositiveTest() throws Exception {

		List<UserTweetsDTO> expectedList = new ArrayList<>();

		UserTweetsDTO expecetd = new UserTweetsDTO();
		expecetd.setTweetId(1);
		expecetd.setTweetDesc("tweetDesc");
		List<ReplyDTO> replyDTOList = new ArrayList<>();
		ReplyDTO replyDTO = new ReplyDTO();
		replyDTO.setEmail("fse@gmail.com");
		replyDTO.setReplyDesc("replyDesc");
		replyDTO.setTweetId(1);
		replyDTOList.add(replyDTO);

		expecetd.setReplyDTOList(replyDTOList);

		expectedList.add(expecetd);

		List<Tweet> tweetList = new ArrayList<>();

		Tweet tweet = new Tweet();
		tweet.setEmail("fse@gmail.com");
		tweet.setId(1);
		tweet.setTweetDescription("tweetDesc");

		tweetList.add(tweet);

		when(tweetRepo.findByEmail("fse@gmail.com")).thenReturn(tweetList);

		List<Reply> replyList = new ArrayList<>();
		Reply reply = new Reply();
		reply.setEmail("fse@gmail.com");
		reply.setReplyDesc("replyDesc");
		reply.setTweetId(1);
		replyList.add(reply);

		when(replyRepo.findByTweetId(1)).thenReturn(replyList);

		List<UserTweetsDTO> actual = userServiceMock.getUsersTweet("fse@gmail.com");
		assertEquals(expectedList, actual);

	}

}
