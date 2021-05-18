package com.tweetApp.tweet.ServiceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.tweetApp.tweet.DTO.ReplyDTO;
import com.tweetApp.tweet.DTO.TweetRequestDTO;
import com.tweetApp.tweet.DTO.TweetResponseDTO;
import com.tweetApp.tweet.Model.Register;
import com.tweetApp.tweet.Model.Reply;
import com.tweetApp.tweet.Model.Tweet;
import com.tweetApp.tweet.Repository.RegisterRepository;
import com.tweetApp.tweet.Repository.ReplyRepository;
import com.tweetApp.tweet.Repository.TweetRepository;
import com.tweetApp.tweet.Service.TweetService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TweetServiceImpl implements TweetService {

	@Autowired
	TweetRepository tweetRepo;

	@Autowired
	RegisterRepository registerRepo;

	@Autowired
	ReplyRepository replyRepo;

	@Autowired
	TweetService tweetService;

	@Override
	public String postTweet(TweetRequestDTO tweetRequest) {
		log.info("Inside TweetServiceImpl postTweet method");
		Tweet tweets = convertDTOToEntity(tweetRequest);
		tweetRepo.save(tweets);
		String msg = null;
		if (tweets != null) {
			msg = "Success";
			return msg;
		} else {
			msg = "Internal Server Error Occured";
		}
		return msg;
	}

	private Tweet convertDTOToEntity(TweetRequestDTO tweetRequest) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		Tweet tweets = new Tweet();

		List<Tweet> tweetList = tweetRepo.findByRecordActive('Y');
		if (tweetList == null) {
			tweets.setId(1);
		} else {
			int count = tweetList.size();
			tweets.setId(count + 1);
		}

		tweets.setTweetDescription(tweetRequest.getTweetDesc());
		tweets.setTweetTag(tweetRequest.getTweetTag());
		tweets.setDate(dtf.format(now));
		Optional<Register> register = registerRepo.findByemail(tweetRequest.getEmailId());
		if (register.isPresent()) {
			tweets.setEmail(register.get().getEmail());
			tweets.setRecordActive('Y');
			return tweets;
		} else {
			return new Tweet();
		}
	}

	@Override
	public List<TweetResponseDTO> getAllTweets() {
		// TODO Auto-generated method stub
		log.info("Inside TweetServiceImpl getAllTweets method");
		List<TweetResponseDTO> tweetResponseDTOList = new ArrayList<>();
		List<Tweet> tweetList = tweetRepo.findByRecordActive('Y');
		tweetList.stream().forEach(tweet -> {
			TweetResponseDTO tweetResponseDTO = new TweetResponseDTO();
			tweetResponseDTO.setTweetDesc(tweet.getTweetDescription());
			tweetResponseDTO.setTweetBy(tweet.getEmail());
			tweetResponseDTO.setTweetId(tweet.getId());
			tweetResponseDTO.setDate(tweet.getDate());
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
			tweetResponseDTO.setReplyDTOList(replyDTOList);
			tweetResponseDTOList.add(tweetResponseDTO);
		});

		return tweetResponseDTOList;
	}

	@Override
	public String postReply(ReplyDTO replyDTO) {
		log.info("Inside TweetServiceImpl postReply method");
		Reply reply = converttDTOToReplyEntity(replyDTO);
		int tweetID = reply.getTweetId();
		Tweet tweet = tweetRepo.findByTweetID(tweetID);
		tweet.setReply(Arrays.asList(reply));
		Tweet replyTweet = tweetRepo.save(tweet);
		String msg = null;
		if (Objects.nonNull(replyTweet)) {
			msg = "Success";
			return msg;
		} else {
			msg = "Internal Server Error Occured";
		}
		return msg;
	}

	public Reply converttDTOToReplyEntity(ReplyDTO replyDTO) {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		Reply reply = new Reply();
		reply.setEmail(replyDTO.getEmail());
		reply.setTweetId(replyDTO.getTweetId());
		reply.setReplyDesc(replyDTO.getReplyDesc());
		reply.setDate(dtf.format(now));

		return reply;
	}

	@Override
	public String deleteTweet(int tweetId) {
		log.info("Inside TweetServiceImpl deleteTweet method");
		tweetRepo.deleteById(tweetId);
		return "success";
	}
}
