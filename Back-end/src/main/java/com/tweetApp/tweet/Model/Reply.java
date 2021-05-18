package com.tweetApp.tweet.Model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@DynamoDBDocument
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reply {

	@DynamoDBAttribute
	private String email;

	@DynamoDBAttribute
	private int tweetId;

	@DynamoDBAttribute
	private String replyDesc;

	@DynamoDBAttribute
	private String date;

	@Override
	public String toString() {
		return "Reply [email=" + email + ", tweetId=" + tweetId + ", replyDesc=" + replyDesc + ", date=" + date + "]";
	}

}
