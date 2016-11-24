package com.niit.collaboration.DAO;

import java.util.List;

import com.niit.collaboration.model.ChatForum;
import com.niit.collaboration.model.ChatForumComment;

public interface ChatForumDAO {

	public List<ChatForum> list();

	public ChatForum getChatForum(Long id);
	
	public ChatForum get(String userID);

	public ChatForum get(String userID, String friendID);

	public boolean save(ChatForum chatForum);

	public boolean update(ChatForum chatForum);

	public void deleteChatForum(Long id);

	// For chatForumComment

	public ChatForumComment getChatForumComment(Long id);

	public boolean save(ChatForumComment chatForumComment);

	public boolean update(ChatForumComment chatForumComment);

	public void deleteChatForumComment(Long id);

}
