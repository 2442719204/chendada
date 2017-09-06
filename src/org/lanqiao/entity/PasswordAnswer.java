package org.lanqiao.entity;

public class PasswordAnswer {
	private String answerid;
	private String aquestion;
	private String answer;
	private String userid;
	private String email;
	public PasswordAnswer(String answerid, String aquestion, String answer,
			String userid, String email) {
		super();
		this.answerid = answerid;
		this.aquestion = aquestion;
		this.answer = answer;
		this.userid = userid;
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAnswerid() {
		return answerid;
	}
	public void setAnswerid(String answerid) {
		this.answerid = answerid;
	}
	public String getAquestion() {
		return aquestion;
	}
	public void setAquestion(String aquestion) {
		this.aquestion = aquestion;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
}	
