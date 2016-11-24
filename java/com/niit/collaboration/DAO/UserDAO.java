package com.niit.collaboration.DAO;

import java.util.List;

import com.niit.collaboration.model.User;

public interface UserDAO {

	public List<User> list();

	public User get(String id);

	//public User get(String id);

	public boolean save(User userDetails);

	public boolean update(User userDetails);

	public void delete(String id);

	public boolean isValidUser(String id, String password);

	public User authenticate(String id, String password);

}
