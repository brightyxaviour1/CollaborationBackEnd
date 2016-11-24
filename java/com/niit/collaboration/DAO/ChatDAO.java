package com.niit.collaboration.DAO;

import java.util.List;

import com.niit.collaboration.model.Chat;

public interface ChatDAO {

	public List<Chat> list();

	public List<Chat> get(int id);

	public Chat get(String userID, String friendID);

	public boolean save(Chat chat);

	public boolean update(Chat chat);

	public void delete(int id);

	public List<Chat> get(String userID);

	

}
