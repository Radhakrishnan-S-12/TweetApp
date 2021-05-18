package com.tweetApp.tweet.Model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@DynamoDBTable(tableName = "Register")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Register {

	@DynamoDBHashKey(attributeName = "registationID")
	private int id;

	@DynamoDBAttribute
	private String firstName;

	@DynamoDBAttribute
	private String lastName;

	@DynamoDBAttribute
	private String gender;

	@DynamoDBAttribute
	private String email;

	@DynamoDBAttribute
	private String password;

	@Override
	public String toString() {
		return "Register [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", email=" + email + ", password=" + password + "]";
	}

}
