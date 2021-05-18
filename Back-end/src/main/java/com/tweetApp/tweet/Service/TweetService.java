package com.tweetApp.tweet.Service;

import java.util.List;

import com.tweetApp.tweet.DTO.ReplyDTO;
import com.tweetApp.tweet.DTO.TweetRequestDTO;
import com.tweetApp.tweet.DTO.TweetResponseDTO;

public interface TweetService {
	
	String postTweet(TweetRequestDTO tweerRequest);
	
	List<TweetResponseDTO> getAllTweets();

	String postReply(ReplyDTO replyDTO);

	String deleteTweet(int tweetId);

}
