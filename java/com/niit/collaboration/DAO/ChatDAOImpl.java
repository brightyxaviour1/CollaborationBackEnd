package com.niit.collaboration.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration.model.Chat;
import com.niit.collaboration.model.User;

@Repository("chatDAO")
public class ChatDAOImpl implements ChatDAO {

	@Autowired
	Chat chat;

	@Autowired
	private SessionFactory sessionFactory;

	public ChatDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<Chat> list() {
		@SuppressWarnings("unchecked")
		List<Chat> list = (List<Chat>) sessionFactory.getCurrentSession().createCriteria(Chat.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return list;
	}

	@Transactional
	public boolean save(Chat chat) {
		try {
			sessionFactory.getCurrentSession().save(chat);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean update(Chat chat) {
		try {
			sessionFactory.getCurrentSession().update(chat);
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}

	}

	@Transactional
	public void delete(int id) {
		Chat chat = new Chat();
		chat.setId(id);  
		sessionFactory.getCurrentSession().delete(chat);

	}

	@Transactional
	public List<Chat> get(int id) {
		System.out.println("ChatDAO get Operation  invoked");

		String hql = "from User where id=" + "'" + id + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		System.out.println("Query:\t" + query);

		@SuppressWarnings("unchecked")
		List<Chat> list = (List<Chat>) query.list();
		System.out.println(list.toString());

		if (list != null && !list.isEmpty()) {
			return list;
		}

		return null;
	}

	@Transactional
	public Chat get(String userID, String friendID) {
		System.out.println("ChatDAO get Operation  invoked");

		String hql = "from User where userID = " + "'" + userID + "' and friendID=" + "'" + friendID + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		System.out.println("Query:\t" + query);

		@SuppressWarnings("unchecked")
		List<Chat> list = (List<Chat>) query.list();
		System.out.println(list.toString());

		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	public List<Chat> get(String userID) {
		System.out.println("ChatDAO get Operation  invoked");

		String hql = "from User where userID=" + "'" + userID + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		System.out.println("Query:\t" + query);

		@SuppressWarnings("unchecked")
		List<Chat> list = (List<Chat>) query.list();
		System.out.println(list.toString());

		if (list != null && !list.isEmpty()) {
			return list;
		}

		return null;
	}

}