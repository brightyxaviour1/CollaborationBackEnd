package com.niit.collaboration.DAO;

import java.util.List;

import com.niit.collaboration.model.Event;

public interface EventDAO {

	public List<Event> list();

	public List<Event> get(Long id);

	public boolean save(Event event);

	public boolean update(Event event);

	public void delete(Long id);

	public List<Event> get(String userID);

}
