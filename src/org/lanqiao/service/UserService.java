package org.lanqiao.service;

import java.util.List;

import org.lanqiao.entity.User;

public interface UserService {
	public void insetUser(User user);
	public User getUserByLoginId(String loginid);
	public User login(String loginid, String password);
	public void updateUser(User user);
	public List<User> getUserList();
	public void removeUser(String uid);
	public List<User> getUserBySearch(String loginid);
	public User getUserByUserId(String userid);
}
