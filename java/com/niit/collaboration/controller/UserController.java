package com.niit.collaboration.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaboration.DAO.UserDAO;
import com.niit.collaboration.model.User;

@RestController
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserDAO userDAO;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		logger.debug("->->calling method listAllUsers");
		List<User> user = userDAO.list();
		if (user.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
			// return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
		} else
			return new ResponseEntity<List<User>>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public ResponseEntity<User> createUser(@RequestBody User user) {
		logger.debug("->->calling method createUser");
		if (userDAO.get(user.getId()) == null) {
			userDAO.save(user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			logger.debug("->->user already exists with id" + user.getId());
			user.setErrorMessage("user already exist with id " + user.getId());
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User user) {
		logger.debug("->->calling method updateUser");
		if (userDAO.get(id) == null) {
			logger.debug("->->user does not exist with id " + user.getId());
			user = new User();
			user.setErrorMessage("user does not exist with id " + user.getId());
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		userDAO.update(user);
		logger.debug("->->user updated successfully");
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") String id) {
		logger.debug("->->calling method deleteUser");
		User user = userDAO.get(id);
		if (user == null) {
			logger.debug("user does not exist with id " + id);
			user = new User();
			user.setErrorMessage("user does not exist");
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		} else {
			userDAO.delete(id);
			logger.debug("->->user deleted successfully");
			return new ResponseEntity<User>(HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("id") String id) {
		logger.debug("->->calling method getUser");
		logger.debug("->->id->->" + id);
		User user = userDAO.get(id);
		if (user == null) {
			logger.debug("user does not exist with id " + id);
			user = new User();
			user.setErrorMessage("user does not exist");
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		} else {
			logger.debug("user exist with id " + id);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/user/authenticate", method = RequestMethod.POST)
	public ResponseEntity<User> authenticate(@RequestBody User user, HttpSession session) {
		logger.debug("->->calling method authenticate");
		user = userDAO.authenticate(user.getId(), user.getPassword());
		if (user == null) {
			user = new User();
			user.setErrorMessage("Invalid credentials. Please enter valid credentials");
		} else {
			logger.debug("->->user exist with given credentials");
			session.setAttribute("LoggedInUser", user);
			session.setAttribute("LoggedInUserID", user.getId());
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}
