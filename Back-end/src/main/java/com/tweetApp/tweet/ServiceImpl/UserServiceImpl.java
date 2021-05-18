package com.tweetApp.tweet.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
import com.tweetApp.tweet.Service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private RegisterRepository registerRepo;

	@Autowired
	private TweetRepository tweetRepo;

	@Autowired
	ReplyRepository replyRepo;

	@Override
	public RegisterDTO register(RegisterDTO registerDTO) {
		log.info("Inside UserserviceIMpl  register method");
		if (checkUniqueEmail(registerDTO).getErrorMessage() != null) {
			log.warn("Email already exits ");
			return registerDTO;
		}
		Register register = convertDTOToEntity(registerDTO);
		registerRepo.save(register);
		registerDTO.setSucessMessage("Registration Successful");

		return registerDTO;
	}

	private RegisterDTO checkUniqueEmail(RegisterDTO registerDTO) {
		log.info("Inside UserserviceIMpl checkUniqueEmail");
		if (!registerDTO.getEmail().isEmpty() && registerDTO.getEmail() != null) {
			Optional<Register> register = registerRepo.findByemail(registerDTO.getEmail());
			if (register.isPresent() && register.get().getEmail() != null) {
				registerDTO.setErrorMessage("Entered E-mail/Login Id Already Exists");
			}
			Register loginIDCheck = registerRepo.findById(registerDTO.getId());
			if (Objects.nonNull(loginIDCheck)) {
				log.error("Inside UserServiceImpl checkUnqueEmail Email aready exits");
				registerDTO.setErrorMessage("Entered Login Id Already Exists");
			}

		}
		return registerDTO;
	}

	private Register convertDTOToEntity(RegisterDTO registerDTO) {
		Register register = new Register();
		register.setId(registerDTO.getId());
		register.setFirstName(registerDTO.getFirstName());
		register.setLastName(registerDTO.getLastName());
		register.setGender(registerDTO.getGender());
		register.setEmail(registerDTO.getEmail());
		register.setPassword(registerDTO.getPassword());

		return register;
	}

	@Override
	public LoginResponseDTO signUp(LoginRequestDTO loginRequestDTO) {
		LoginResponseDTO loginResponseDTO = new LoginResponseDTO();

		String userName = loginRequestDTO.getEmail();
		log.info("Inside UserserviceIMpl signUp Method");
		Optional<Register> register = registerRepo.findByemail(userName);
		if (register.isPresent()) {
			if (!register.get().getPassword().equals(loginRequestDTO.getPassword())) {
				loginResponseDTO.setStatus(false);
				loginResponseDTO.setErrorMessage("Incorrect UserName/Password");

				return loginResponseDTO;

			}

			loginResponseDTO.setStatus(true);

			loginResponseDTO.setEmail(register.get().getEmail());

			return loginResponseDTO;
		} else {
			loginResponseDTO.setStatus(false);
			loginResponseDTO.setErrorMessage("Incorrect UserName/Password");

		}

		return loginResponseDTO;
	}

	@Override
	public ResetPasswordResponseDTO resetpassword(ResetPasswordRequestDTO resetRequest) {
		Optional<Register> registerOptional = Optional.empty();
		log.info("Inside UserserviceIMpl resetpassword method");
		ResetPasswordResponseDTO resetPasswordResponseDTO = new ResetPasswordResponseDTO();

		registerOptional = registerRepo.findByemail(resetRequest.getEmailId());
		Register register = (registerOptional.isPresent()) ? registerOptional.get() : new Register();
		if (register.getPassword() != null && register.getPassword().equals(resetRequest.getOldpassword())) {
			register.setPassword(resetRequest.getNewpassword());
			registerRepo.save(register);
			resetPasswordResponseDTO.setSuccessMessage("Password Updated Successfully");
		} else {
			log.error("Inside UserserviceIMpl resetpassword method Old Password is Incorrect");
			resetPasswordResponseDTO.setErrorMessage("Entered Old Password is Incorrect");
		}

		return resetPasswordResponseDTO;
	}

	@Override
	public List<UserDTO> getAllUsers() {
		// TODO Auto-generated method stub
		log.info("Inside UserserviceIMpl getAllUsers method");
		List<UserDTO> userDTOList = new ArrayList<>();
		List<Register> register = registerRepo.findAll();
		register.parallelStream().forEach(user -> {
			UserDTO userDTO = new UserDTO();
			userDTO.setId(user.getId());
			userDTO.setEmail(user.getEmail());
			userDTO.setFirstName(user.getFirstName());
			userDTO.setGender(user.getGender());
			userDTOList.add(userDTO);
		});

		return userDTOList;
	}

	@Override
	public List<UserTweetsDTO> getUsersTweet(String email) {
		log.info("Inside UserserviceIMpl getUsersTweet method");
		List<UserTweetsDTO> userTweetsDTOList = new ArrayList<>();
		List<Tweet> tweetList = tweetRepo.findByEmail(email);
		tweetList.stream().forEach(tweet -> {
			UserTweetsDTO userTweetsDTO = new UserTweetsDTO();
			userTweetsDTO.setTweetId(tweet.getId());
			userTweetsDTO.setTweetDesc(tweet.getTweetDescription());
			userTweetsDTO.setDate(tweet.getDate());
			List<Reply> replyList = replyRepo.findByTweetId(tweet.getId());
			List<ReplyDTO> replyDTOList = new ArrayList<>();
			if (!CollectionUtils.isEmpty(replyList)) {
				replyList.stream().forEach(reply -> {
					ReplyDTO replyDTO = new ReplyDTO();
					replyDTO.setEmail(reply.getEmail());
					replyDTO.setReplyDesc(reply.getReplyDesc());
					replyDTO.setTweetId(reply.getTweetId());
					replyDTO.setDate(reply.getDate());
					replyDTOList.add(replyDTO);
				});
			}
			userTweetsDTO.setReplyDTOList(replyDTOList);
			userTweetsDTOList.add(userTweetsDTO);
		});

		return userTweetsDTOList;
	}

}
