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

import com.niit.collaboration.DAO.EventDAO;
import com.niit.collaboration.model.*;
import com.niit.collaboration.model.User;

@RestController
public class EventController {

	private static final Logger logger = LoggerFactory.getLogger(EventController.class);

	@Autowired
	EventDAO eventDAO;

	@Autowired
	Event event;
	
	@Autowired
	User user;

	@RequestMapping(value = "/myEvents", method = RequestMethod.GET)
	public ResponseEntity<List<Event>> getMyEvents(HttpSession session) {
		logger.debug("calling method getMyEvents");
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		List<Event> myEvents = eventDAO.get(loggedInUser.getId());
		return new ResponseEntity<List<Event>>(myEvents, HttpStatus.OK);
	}

	@RequestMapping(value = "/createEvent", method = RequestMethod.POST)
	public ResponseEntity<Event> createEvent(@PathVariable("friendID") String friendID, HttpSession session) {
		logger.debug("calling method createEvent");
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		event.setUserID(loggedInUser.getId());
		event.getName();
		event.getDescription();
		event.getVenue();
		// event.setDateTime(dateTime);
		eventDAO.save(event);
		return new ResponseEntity<Event>(event, HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteEvent/{id}", method = RequestMethod.POST)
	public ResponseEntity<Event> deleteEvent(@PathVariable("id") Long id) {
		logger.debug("calling method createEvent");
		//User loggedInUser = (User) session.getAttribute("loggedInUser");
		//event.setUserID(loggedInUser.getId());
		//event.setId(id);
		eventDAO.delete(id);
		return new ResponseEntity<Event>( HttpStatus.OK);
	}

	@RequestMapping(value = "/getEvent", method = RequestMethod.GET)
	public ResponseEntity<Event> getEvent( HttpSession session) {
		logger.debug("calling method getEvent");
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		// event.setUserID(loggedInUser.getId());
		Event event = (Event) eventDAO.get(loggedInUser.getId());
		if (event == null) {
			return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Event>(event, HttpStatus.OK);
	}

	@RequestMapping(value = "/updateEvent/{id}", method = RequestMethod.POST)
	public ResponseEntity<Event> updateEvent(@PathVariable("id") Long id, HttpSession session) {
		logger.debug("calling method updateEvent");
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		event.setUserID(loggedInUser.getId());
		event.setId(id);
		event.getName();
		event.getDescription();
		event.getVenue();
		eventDAO.update(event);
		return new ResponseEntity<Event>(event, HttpStatus.OK);
	}

}
