package org.lanqiao.service.impl;

import org.lanqiao.dao.PasswordAnswerDao;
import org.lanqiao.dao.impl.PasswordAnswerImpl;
import org.lanqiao.entity.PasswordAnswer;
import org.lanqiao.service.PasswordAnswerService;

public class PasswordAnswerServiceImpl implements PasswordAnswerService{
	PasswordAnswerDao passwordAnswerDao = null;
	public PasswordAnswerServiceImpl() {
		passwordAnswerDao = new PasswordAnswerImpl();
	}
	@Override
	public void insertPasswordAnswer(PasswordAnswer passwordAnswer) {
		passwordAnswerDao.insertPasswordAnswer(passwordAnswer);
		
	}
	@Override
	public void updatePasswordAnswer(PasswordAnswer passwordAnswer) {
		passwordAnswerDao.updatePasswordAnswer(passwordAnswer);
		
	}
	@Override
	public PasswordAnswer searchPasswordAnswer(String userid) {
		return passwordAnswerDao.searchPasswordAnswer(userid);
	}
	@Override
	public void removePasswordAnswer(String userid) {
		passwordAnswerDao.removePasswordAnswer(userid);
	}
	
}
