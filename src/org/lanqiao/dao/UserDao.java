package org.lanqiao.dao;

import java.util.List;

import org.lanqiao.entity.User;

public interface UserDao {
	public void insert(User user);
	public User getUserByLoginId(String loginid);
	public void updateUser(User user);
	public List<User> userlist();
	public void removeUser(String uid);
	public List<User> getUserBySearch(String loginid);
	public User getUserByUserId(String userid);
	
}
