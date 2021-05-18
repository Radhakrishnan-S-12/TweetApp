package com.tweetApp.tweet.Repository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.tweetApp.tweet.Model.Tweet;

@Repository
public class TweetRepository {

	@Autowired
	private DynamoDBMapper dbMapper;

	public Tweet save(Tweet tweet) {
		dbMapper.save(tweet);
		return findByTweetID(tweet.getId());
	}

	public List<Tweet> findByRecordActive(char recordActive) {
		String activeStatus = Character.toString(recordActive);
		List<Tweet> allTweets = findAll();
		List<Tweet> tweets = allTweets.stream()
				.filter(tweet -> activeStatus.equals(Character.toString(tweet.getRecordActive())))
				.collect(Collectors.toList());
		return CollectionUtils.isEmpty(tweets) ? Collections.emptyList() : tweets;
	}

	public List<Tweet> findByEmail(String email) {
		List<Tweet> allTweets = findAll();
		List<Tweet> tweets = allTweets.stream().filter(tweet -> email.equals(tweet.getEmail()))
				.collect(Collectors.toList());
		return CollectionUtils.isEmpty(tweets) ? Collections.emptyList() : tweets;
	}

	public Tweet findByTweetID(int tweetId) {
		return dbMapper.load(Tweet.class, tweetId);
	}

	private List<Tweet> findAll() {
		return dbMapper.scan(Tweet.class, new DynamoDBScanExpression());
	}

	public void deleteById(int id) {
		Tweet tweet = dbMapper.load(Tweet.class, id);
		dbMapper.delete(tweet);
	}
}
