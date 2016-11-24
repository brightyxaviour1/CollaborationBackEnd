package com.niit.collaboration.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration.model.Event;
import com.niit.collaboration.model.User;

@Repository("eventDAO")
public class EventDAOImpl implements EventDAO {

	@Autowired
	Event event;

	@Autowired
	private SessionFactory sessionFactory;

	public EventDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<Event> list() {
		@SuppressWarnings("unchecked")
		List<Event> list = (List<Event>) sessionFactory.getCurrentSession().createCriteria(Event.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return list;
	}

	@Transactional
	public boolean save(Event event) {
		try {
			sessionFactory.getCurrentSession().save(event);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean update(Event event) {
		try {
			sessionFactory.getCurrentSession().update(event);
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}

	}

	@Transactional
	public void delete(Long id) {
		Event event = new Event();
		event.setId(id);
		sessionFactory.getCurrentSession().delete(event);

	}

	@Transactional
	public List<Event> get(Long id) {
		System.out.println("EventDAO get Operation  invoked");

		String hql = "from User where id=" + "'" + id + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		System.out.println("Query:\t" + query);

		@SuppressWarnings("unchecked")
		List<Event> list = (List<Event>) query.list();
		System.out.println(list.toString());

		if (list != null && !list.isEmpty()) {
			return list();
		}

		return null;
	}

	public List<Event> get(String userID) {
		System.out.println("EventDAO get Operation  invoked");

		String hql = "from User where userID=" + "'" + userID + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		System.out.println("Query:\t" + query);

		@SuppressWarnings("unchecked")
		List<Event> list = (List<Event>) query.list();
		System.out.println(list.toString());

		if (list != null && !list.isEmpty()) {
			return list();
		}

		return null;
	}

}