package org.lanqiao.service.impl;

import java.util.List;

import org.lanqiao.dao.UserDao;
import org.lanqiao.dao.impl.UserDaoImpl;
import org.lanqiao.entity.User;
import org.lanqiao.service.UserService;

public class UserserviceImpl implements UserService{
	
	UserDao userDao = null;
	public UserserviceImpl() {
		userDao = new UserDaoImpl();
	}
	@Override
	public void insetUser(User user) {
		userDao.insert(user);
		
	}
	@Override
	public User getUserByLoginId(String loginid) {
		
		return userDao.getUserByLoginId(loginid);
	}
	@Override
	public User login(String loginid, String password) {
		User user = userDao.getUserByLoginId(loginid);
		if(user==null){
			return null;
		}else{
			if(user.getUpassword().equals(password)){
				return user;
			}
		}
		return null;
	}
	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
		
	}
	@Override
	public List<User> getUserList() {
		// TODO Auto-generated method stub
		return userDao.userlist();
	}
	@Override
	public void removeUser(String uid) {
		userDao.removeUser(uid);
		
	}
	@Override
	public List<User> getUserBySearch(String loginid) {
		// TODO Auto-generated method stub
		return userDao.getUserBySearch(loginid);
	}
	@Override
	public User getUserByUserId(String userid) {
		// TODO Auto-generated method stub
		return userDao.getUserByUserId(userid);
	}

}
