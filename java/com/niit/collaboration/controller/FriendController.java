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

import com.niit.collaboration.DAO.FriendDAO;
import com.niit.collaboration.model.Friend;
import com.niit.collaboration.model.User;

@RestController
public class FriendController {

	private static final Logger logger = LoggerFactory.getLogger(FriendController.class);

	@Autowired
	FriendDAO friendDAO;

	@Autowired
	Friend friend;

	@RequestMapping(value = "/myFriends", method = RequestMethod.GET)
	public ResponseEntity<List<Friend>> getMyFriends(HttpSession session) {
		logger.debug("calling method getMyFriends");
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		List<Friend> myFriends = friendDAO.getAllFriends(loggedInUser.getId());
		return new ResponseEntity<List<Friend>>(myFriends, HttpStatus.OK);
	}

	@RequestMapping(value = "/addFriend/{friendID}", method = RequestMethod.GET)
	public ResponseEntity<Friend> sendFriendRequest(@PathVariable("friendID") String friendID, HttpSession session) {
		logger.debug("calling method sendFriendRequest");
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		friend.setUserID(loggedInUser.getId());
		friend.setFriendID(friendID);
		friend.setStatus("N"); // N-New R-Rejected A-Accepted
		friendDAO.save(friend);
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
	}

	@RequestMapping(value = "/unFriend/{friendID}", method = RequestMethod.GET)
	public ResponseEntity<Friend> unFriend(@PathVariable("friendID") String friendID, HttpSession session) {
		logger.debug("calling method unFriend");
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		friend.setUserID(loggedInUser.getId());
		friend.setFriendID(friendID);
		friend.setStatus("U"); // N-New R-Rejected A-Accepted U-Unfriend
		friendDAO.update(friend);
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
	}

	@RequestMapping(value = "/rejectFriend/{friendID}", method = RequestMethod.GET)
	public ResponseEntity<Friend> rejectFriend(@PathVariable("friendID") String friendID, HttpSession session) {
		logger.debug("calling method rejectFriend");
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		friend.setUserID(loggedInUser.getId());
		friend.setFriendID(friendID);
		friend.setStatus("R"); // N-New R-Rejected A-Accepted U-Unfriend
		friendDAO.update(friend);
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
	}

	@RequestMapping(value = "/acceptFriend/{friendID}", method = RequestMethod.GET)
	public ResponseEntity<Friend> acceptFriend(@PathVariable("friendID") String friendID, HttpSession session) {
		logger.debug("calling method acceptFriend");
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		friend.setUserID(loggedInUser.getId());
		friend.setFriendID(friendID);
		friend.setStatus("A"); // N-New R-Rejected A-Accepted U-Unfriend
		friendDAO.update(friend);
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
	}

	@RequestMapping(value = "/getMyFriendRequests", method = RequestMethod.GET)
	public ResponseEntity<Friend> getMyFriendRequests(HttpSession session) {
		logger.debug("calling method getMyFriendRequests");
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		friendDAO.getMyFriendNewFriendRequest(loggedInUser.getId());
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
	}

	@RequestMapping(value = "/myFriends/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Friend>> getMyFriends(@PathVariable("id") String id) {
		logger.debug("calling method getMyFriends");
		List<Friend> myFriends = friendDAO.getAllFriends(id);
		return new ResponseEntity<List<Friend>>(myFriends, HttpStatus.OK);
	}

}
