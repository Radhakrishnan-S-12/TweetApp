package com.tweetApp.tweet.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.tweetApp.tweet.Model.Register;

@Repository
public class RegisterRepository {

	@Autowired
	private DynamoDBMapper dbMapper;

	public Optional<Register> findByemail(String email) {
		List<Register> registers = findAll();
		if (!CollectionUtils.isEmpty(registers)) {
			Optional<Register> registerDetails = registers.stream()
					.filter(register -> email.equals(register.getEmail())).findAny();
			return registerDetails;
		} else {
			return Optional.empty();
		}
	}

	public void save(Register register) {
		dbMapper.save(register);
	}

	public List<Register> findAll() {
		return dbMapper.scan(Register.class, new DynamoDBScanExpression());
	}

	public Register findById(int id) {
		return dbMapper.load(Register.class, id);
	}

}
