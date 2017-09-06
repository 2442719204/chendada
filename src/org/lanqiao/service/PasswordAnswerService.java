package org.lanqiao.service;

import org.lanqiao.entity.PasswordAnswer;

public interface PasswordAnswerService {
	public  void insertPasswordAnswer(PasswordAnswer passwordAnswer);
	public void updatePasswordAnswer(PasswordAnswer passwordAnswer);
	public PasswordAnswer searchPasswordAnswer(String userid);
	public void removePasswordAnswer(String userid);
}
