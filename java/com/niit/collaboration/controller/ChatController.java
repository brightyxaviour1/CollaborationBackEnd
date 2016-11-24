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

import com.niit.collaboration.DAO.ChatDAO;
import com.niit.collaboration.model.Blog;
import com.niit.collaboration.model.Chat;
import com.niit.collaboration.model.Friend;
import com.niit.collaboration.model.User;

@RestController
public class ChatController {

	private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

	@Autowired
	ChatDAO chatDAO;

	@Autowired
	Chat chat;

	@RequestMapping(value = "/myChats", method = RequestMethod.GET)
	public ResponseEntity<List<Chat>> getMyChats(HttpSession session) {
		logger.debug("calling method getMyChats");
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		List<Chat> myChats = chatDAO.get(loggedInUser.getId());
		return new ResponseEntity<List<Chat>>(myChats, HttpStatus.OK);
	}

	@RequestMapping(value = "/createChat/{friendID}", method = RequestMethod.POST)
	public ResponseEntity<Chat> createChat(@PathVariable("friendID") String friendID, HttpSession session) {
		logger.debug("calling method createChat");
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		chat.setUserID(loggedInUser.getId());
		chat.setFriendID(friendID);
		chat.setMessage("You can chat here");
		// chat.setDateTime(dateTime);
		chatDAO.save(chat);
		return new ResponseEntity<Chat>(chat, HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteChat/{friendID}", method = RequestMethod.POST)
	public ResponseEntity<Chat> deleteChat(@PathVariable("friendID") String friendID, HttpSession session) {
		logger.debug("calling method createChat");
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		chat.setUserID(loggedInUser.getId());
		chat.setFriendID(friendID);
		chatDAO.delete(chat.getId());
		;
		return new ResponseEntity<Chat>(chat, HttpStatus.OK);
	}

	@RequestMapping(value = "/getCthat/{friendID}", method = RequestMethod.GET)
	public ResponseEntity<Chat> getChat(@PathVariable("friendID") String friendID, HttpSession session) {
		logger.debug("calling method getChat with this friendID " + friendID);
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		// chat.setUserID(loggedInUser.getId());
		Chat chat = chatDAO.get(loggedInUser.getId(), friendID);
		if (chat == null) {
			return new ResponseEntity<Chat>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Chat>(chat, HttpStatus.OK);
	}

	@RequestMapping(value = "/updateChat/{friendID}", method = RequestMethod.POST)
	public ResponseEntity<Chat> updateChat(@PathVariable("friendID") String friendID, HttpSession session) {
		logger.debug("calling method updateChat");
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		chat.setUserID(loggedInUser.getId());
		chat.setFriendID(friendID);
		chatDAO.update(chat);
		return new ResponseEntity<Chat>(chat, HttpStatus.OK);
	}

}
