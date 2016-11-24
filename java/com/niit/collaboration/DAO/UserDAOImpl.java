package com.niit.collaboration.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration.model.User;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {

	@Autowired
	User user;

	@Autowired
	private SessionFactory sessionFactory;

	public UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public List<User> list() {
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) sessionFactory.getCurrentSession().createCriteria(User.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return list;
	}

	@Transactional
	public User get(String id) {
		System.out.println("UserDAO    isValidUser Db Operation  invoked");

		String hql = "from User where id='" + id + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		System.out.println("Query\t" + query);

		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) query.list();
		System.out.println(list.toString());

		if (list != null && !list.isEmpty()) {
			return user;
		}

		return null;
	}

	@Transactional
	public boolean save(User user) {
		try {
			sessionFactory.getCurrentSession().save(user);
			user.setRole("User");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean update(User user) {
		try {
			sessionFactory.getCurrentSession().update(user);
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}

	}

	@Transactional
	public void delete(String id) {
		User user = new User();
		user.setId(id);
		sessionFactory.getCurrentSession().delete(user);

	}

	@Transactional
	public boolean isValidUser(String id, String password) {

		String hql = "from UserDetails where id= '" + id + "' and " + " password ='" + password + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) query.list();

		if (list != null && !list.isEmpty()) {

			return true;
		}

		return false;
	}

	public User authenticate(String id, String password) {

		String hql = "from User where id= '" + id + "' and " + " password ='" + password + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) query.list();

		if (list != null && !list.isEmpty()) {

			return list.get(0);
		}

		return null;

	}

}