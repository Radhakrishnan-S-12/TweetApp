package com.tweetApp.tweet.DTO;

import java.util.List;

public class UserTweetsDTO {
	
	private int tweetId;
	
	private String tweetDesc;
	
	private String Date;
	
	private List<ReplyDTO> replyDTOList;

	public int getTweetId() {
		return tweetId;
	}

	public void setTweetId(int tweetId) {
		this.tweetId = tweetId;
	}

	public String getTweetDesc() {
		return tweetDesc;
	}

	public void setTweetDesc(String tweetDesc) {
		this.tweetDesc = tweetDesc;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public List<ReplyDTO> getReplyDTOList() {
		return replyDTOList;
	}

	public void setReplyDTOList(List<ReplyDTO> replyDTOList) {
		this.replyDTOList = replyDTOList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Date == null) ? 0 : Date.hashCode());
		result = prime * result + ((replyDTOList == null) ? 0 : replyDTOList.hashCode());
		result = prime * result + ((tweetDesc == null) ? 0 : tweetDesc.hashCode());
		result = prime * result + tweetId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserTweetsDTO other = (UserTweetsDTO) obj;
		if (Date == null) {
			if (other.Date != null)
				return false;
		} else if (!Date.equals(other.Date))
			return false;
		if (replyDTOList == null) {
			if (other.replyDTOList != null)
				return false;
		} else if (!replyDTOList.equals(other.replyDTOList))
			return false;
		if (tweetDesc == null) {
			if (other.tweetDesc != null)
				return false;
		} else if (!tweetDesc.equals(other.tweetDesc))
			return false;
		if (tweetId != other.tweetId)
			return false;
		return true;
	}
	
	
	

}
