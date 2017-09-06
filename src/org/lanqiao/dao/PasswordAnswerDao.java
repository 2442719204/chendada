package org.lanqiao.dao;

import org.lanqiao.entity.PasswordAnswer;

public interface PasswordAnswerDao {
	public void insertPasswordAnswer(PasswordAnswer passwordAnswer);
	public void updatePasswordAnswer(PasswordAnswer passwordAnswer);
	public PasswordAnswer searchPasswordAnswer(String userid);
	public void removePasswordAnswer(String userid);
}
