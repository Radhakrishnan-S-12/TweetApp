package com.tweetApp.tweet.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.tweetApp.tweet.properties.DynamoDbProperties;

@Configuration
public class DynamoDBConfiguration {

	@Autowired
	private DynamoDbProperties dynamoDbProperties;

	@Bean
	public DynamoDBMapper dynamoDBMapper() {
		return new DynamoDBMapper(amazonDynamoDBDetails());
	}

	private AmazonDynamoDB amazonDynamoDBDetails() {
		return AmazonDynamoDBAsyncClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
						dynamoDbProperties.getServiceEndpoint(), dynamoDbProperties.getSigningRegion()))
				.withCredentials(new AWSStaticCredentialsProvider(
						new BasicAWSCredentials(dynamoDbProperties.getAccessKey(), dynamoDbProperties.getSecretKey())))
				.build();
	}

}
