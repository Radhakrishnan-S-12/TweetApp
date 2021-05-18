package com.tweetApp.tweet.DTO;

import java.util.List;

public class TweetResponseDTO {
	
	private String tweetDesc;
	
	private String tweetBy;
	
	private int tweetId;
	
	private String date;
	
	private List<ReplyDTO> replyDTOList;

	public String getTweetDesc() {
		return tweetDesc;
	}

	public void setTweetDesc(String tweetDesc) {
		this.tweetDesc = tweetDesc;
	}

	public String getTweetBy() {
		return tweetBy;
	}

	public void setTweetBy(String tweetBy) {
		this.tweetBy = tweetBy;
	}

	public int getTweetId() {
		return tweetId;
	}

	public void setTweetId(int tweetId) {
		this.tweetId = tweetId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((replyDTOList == null) ? 0 : replyDTOList.hashCode());
		result = prime * result + ((tweetBy == null) ? 0 : tweetBy.hashCode());
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
		TweetResponseDTO other = (TweetResponseDTO) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (replyDTOList == null) {
			if (other.replyDTOList != null)
				return false;
		} else if (!replyDTOList.equals(other.replyDTOList))
			return false;
		if (tweetBy == null) {
			if (other.tweetBy != null)
				return false;
		} else if (!tweetBy.equals(other.tweetBy))
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
