package com.tweetApp.tweet.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.tweetApp.tweet.Model.Reply;
import com.tweetApp.tweet.Model.Tweet;

@Repository
public class ReplyRepository {

	@Autowired
	private DynamoDBMapper dbMapper;

	public List<Reply> findByTweetId(int tweetId) {
		Tweet tweet = dbMapper.load(Tweet.class, tweetId);
		return tweet.getReply();
	}

	public void save(Reply reply) {
		dbMapper.save(reply);
	}
}
