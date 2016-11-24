package com.niit.collaboration.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaboration.DAO.ChatForumDAO;
import com.niit.collaboration.model.Blog;
import com.niit.collaboration.model.ChatForum;
import com.niit.collaboration.model.Friend;
import com.niit.collaboration.model.User;

@RestController
public class ChatForumController {

	private static final Logger logger = LoggerFactory.getLogger(ChatForumController.class);

	@Autowired
	ChatForumDAO chatForumDAO;

	@Autowired
	ChatForum chatForum;

	@RequestMapping(value = "/myChatForums", method = RequestMethod.GET)
	public ResponseEntity<List<ChatForum>> getMyChatForums(HttpSession session) {
		logger.debug("calling method getMyChatForums");
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		List<ChatForum> myChatForums = (List<ChatForum>) chatForumDAO.get(loggedInUser.getId());
		return new ResponseEntity<List<ChatForum>>(myChatForums, HttpStatus.OK);
	}

	@RequestMapping(value = "/createChatForum", method = RequestMethod.POST)
	public ResponseEntity<ChatForum> createChatForum(HttpSession session) {
		logger.debug("calling method createChatForum");
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		chatForum.setUserID(loggedInUser.getId());
		chatForum.getCreatedDate();
		chatForum.setMessage("You can chatForum here");
		// chatForum.setDateTime(dateTime);
		chatForumDAO.save(chatForum);
		return new ResponseEntity<ChatForum>(chatForum, HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteChatForum/{friendID}", method = RequestMethod.POST)
	public ResponseEntity<ChatForum> deleteChatForum(HttpSession session) {
		logger.debug("calling method createChatForum");
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		chatForum.setUserID(loggedInUser.getId());
		chatForumDAO.deleteChatForum(chatForum.getId());
		return new ResponseEntity<ChatForum>(chatForum, HttpStatus.OK);
	}

	@RequestMapping(value = "/getCthatForum", method = RequestMethod.GET)
	public ResponseEntity<ChatForum> getChatForum(HttpSession session) {
		logger.debug("calling method getChatForum ");
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		// chatForum.setUserID(loggedInUser.getId());
		ChatForum chatForum = chatForumDAO.get(loggedInUser.getId());
		if (chatForum == null) {
			return new ResponseEntity<ChatForum>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ChatForum>(chatForum, HttpStatus.OK);
	}

	@RequestMapping(value = "/updateChatForum", method = RequestMethod.POST)
	public ResponseEntity<ChatForum> updateChatForum(HttpSession session) {
		logger.debug("calling method updateChatForum");
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		chatForum.setUserID(loggedInUser.getId());
		chatForum.getMessage();
		chatForumDAO.update(chatForum);
		return new ResponseEntity<ChatForum>(chatForum, HttpStatus.OK);
	}

}
