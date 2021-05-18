package com.tweetApp.tweet.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.tweetApp.tweet.DTO.ReplyDTO;
import com.tweetApp.tweet.DTO.TweetRequestDTO;
import com.tweetApp.tweet.DTO.TweetResponseDTO;
import com.tweetApp.tweet.Model.Register;
import com.tweetApp.tweet.Model.Reply;
import com.tweetApp.tweet.Model.Tweet;
import com.tweetApp.tweet.Repository.RegisterRepository;
import com.tweetApp.tweet.Repository.ReplyRepository;
import com.tweetApp.tweet.Repository.TweetRepository;
import com.tweetApp.tweet.ServiceImpl.TweetServiceImpl;

public class TweetServiceImplTest {

	@Mock
	private RegisterRepository registerrepo;

	@Mock
	private TweetRepository tweetRepo;

	@Mock
	private ReplyRepository replyRepo;

	@InjectMocks
	private TweetServiceImpl twitterServiceMock = new TweetServiceImpl();

	@Test
	public void postTweetPositiveTest() throws Exception {

		TweetRequestDTO tweetRequest = new TweetRequestDTO();
		tweetRequest.setEmailId("fse@gmail.com");
		tweetRequest.setTweetDesc("Desc");
		tweetRequest.setTweetTag("tag");

		Register register = new Register();
		register.setEmail("fse@gmail.com");
		Optional<Register> registerOptional = Optional.of(register);
		String expected = "Success";

		when(tweetRepo.findByRecordActive('Y')).thenReturn(null);

		when(registerrepo.findByemail("fse@gmail.com")).thenReturn(registerOptional);

		String actualresp = twitterServiceMock.postTweet(tweetRequest);

		assertEquals(expected, actualresp);

	}

	@Test
	public void postTweetElseTest() throws Exception {

		TweetRequestDTO tweetRequest = new TweetRequestDTO();
		tweetRequest.setEmailId("fse@gmail.com");
		tweetRequest.setTweetDesc("Desc");
		tweetRequest.setTweetTag("tag");

		Register register = new Register();
		register.setEmail("fse@gmail.com");

		List<Tweet> tweetList = new ArrayList<>();

		Tweet tweet = new Tweet();
		tweet.setEmail("fse@gmail.com");
		tweet.setId(1);
		tweet.setTweetDescription("Desc");
		tweetList.add(tweet);

		String expected = "Success";

		when(tweetRepo.findByRecordActive('Y')).thenReturn(tweetList);

		when(registerrepo.findByemail("fse@gmail.com")).thenReturn(Optional.of(register));

		String actualresp = twitterServiceMock.postTweet(tweetRequest);

		assertEquals(expected, actualresp);

	}

	@Test
	public void getAllTweetsPostiveTest() throws Exception {

		List<Tweet> tweetList = new ArrayList<>();

		Tweet tweet = new Tweet();
		tweet.setEmail("fse@gmail.com");
		tweet.setId(1);
		tweet.setTweetDescription("Desc");
		tweet.setDate("");
		tweetList.add(tweet);

		List<Reply> replyList = new ArrayList<>();

		Reply reply = new Reply();
		reply.setEmail("fse@gmail.com");
		reply.setReplyDesc("replyDesc");
		reply.setTweetId(1);
		reply.setDate("");

		replyList.add(reply);

		List<TweetResponseDTO> expectedList = new ArrayList<>();
		TweetResponseDTO expected = new TweetResponseDTO();
		expected.setDate("");
		expected.setTweetBy("fse@gmail.com");
		expected.setTweetDesc("Desc");
		expected.setTweetId(1);

		List<ReplyDTO> replyDTOList = new ArrayList<>();
		ReplyDTO replyDTO = new ReplyDTO();
		replyDTO.setEmail("fse@gmail.com");
		replyDTO.setReplyDesc("replyDesc");
		replyDTO.setTweetId(1);
		replyDTO.setDate("");
		replyDTOList.add(replyDTO);

		expected.setReplyDTOList(replyDTOList);

		expectedList.add(expected);

		when(tweetRepo.findByRecordActive('Y')).thenReturn(tweetList);

		when(replyRepo.findByTweetId(1)).thenReturn(replyList);

		List<TweetResponseDTO> actualresp = twitterServiceMock.getAllTweets();

		assertEquals(expectedList, actualresp);
	}

	@Test
	public void postReplyPositiveTest() throws Exception {

		ReplyDTO replyDTO = new ReplyDTO();
		replyDTO.setEmail("fse@gmail.com");
		replyDTO.setReplyDesc("replyDesc");
		replyDTO.setTweetId(1);

		String actualresp = twitterServiceMock.postReply(replyDTO);

		assertEquals("Success", actualresp);

	}

	@Test
	public void deleteTweetPositiveTest() throws Exception {

		doNothing().when(tweetRepo).deleteById(1);
		String actualresp = twitterServiceMock.deleteTweet(1);

		assertEquals("success", actualresp);

	}
}
