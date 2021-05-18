package com.tweetApp.tweet.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@ConfigurationProperties(value = "dynamo")
@Data
@EnableConfigurationProperties
@Component
public class DynamoDbProperties {

	private String accessKey;
	private String secretKey;
	private String signingRegion;
	private String serviceEndpoint;
	
}
