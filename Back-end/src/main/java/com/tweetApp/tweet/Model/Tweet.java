package com.tweetApp.tweet.Model;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@DynamoDBTable(tableName = "Tweet")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tweet {

	@DynamoDBHashKey(attributeName = "tweetID")
	private int id;

	@DynamoDBAttribute
	private String tweetDescription;

	@DynamoDBAttribute
	private String tweetTag;

	@DynamoDBAttribute
	private String Date;

	@DynamoDBAttribute
	private String email;

	@DynamoDBAttribute
	private char recordActive;

	@DynamoDBAttribute
	private List<Reply> reply;

	@Override
	public String toString() {
		return "Tweet [id=" + id + ", tweetDescription=" + tweetDescription + ", tweetTag=" + tweetTag + ", Date="
				+ Date + ", email=" + email + ", recordActive=" + recordActive + ", reply=" + reply + "]";
	}

}
