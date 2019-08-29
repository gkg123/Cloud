package org.oracle.note.dao;

import org.oracle.note.entity.User;

public interface UserDao {
	public User findByName(String name);
	public void register(User user);
	public String findBypwd(User user);
	public void updatePassword(User user);
}
