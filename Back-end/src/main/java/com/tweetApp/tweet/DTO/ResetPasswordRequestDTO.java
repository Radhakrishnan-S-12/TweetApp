package com.tweetApp.tweet.DTO;

public class ResetPasswordRequestDTO {

private String emailId;
	
	private String newpassword;
	
	private String oldpassword;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

	@Override
	public String toString() {
		return "ResetPasswordRequestDTO{" +
				"emailId='" + emailId + '\'' +
				", newpassword='" + newpassword + '\'' +
				", oldpassword='" + oldpassword + '\'' +
				'}';
	}
}
