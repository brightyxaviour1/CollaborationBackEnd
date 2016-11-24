package com.niit.collaboration.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration.model.Friend;
import com.niit.collaboration.model.User;

@Repository("friendDAO")
public class FriendDAOImpl implements FriendDAO {

	@Autowired
	Friend friend;

	@Autowired
	private SessionFactory sessionFactory;

	public FriendDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<Friend> list() {
		@SuppressWarnings("unchecked")
		List<Friend> list = (List<Friend>) sessionFactory.getCurrentSession().createCriteria(Friend.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return list;
	}

	@Transactional
	public boolean save(Friend friend) {
		try {
			//friend.setId(getMaxId() + 1);
			sessionFactory.getCurrentSession().save(friend);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean update(Friend friend) {
		try {
			sessionFactory.getCurrentSession().update(friend);
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}

	}

	@Transactional
	public void delete(String userID, String friendID) {
		Friend friend = new Friend();
		friend.setFriendID(friendID);
		friend.setUserID(userID);
		sessionFactory.getCurrentSession().delete(friend);

	}

	
	public String getMaxId() {
		String hql = "select max(id) from Friend";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		String maxID = (String) query.uniqueResult();
		return maxID;
	}

	@Transactional
	public List<Friend> getMyFriendNewFriendRequest(String userID) {
		String hql = "from Friend where userID = '" + userID + "' and status = 'N'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Friend> list = (List<Friend>) query.list();
		return list;
	}

	@Transactional
	public List<Friend> get(String userID, String friendID) {
		System.out.println("FriendDAO get Operation  invoked");

		String hql = "from User where userID = " + "'" + userID + "'" + " and friendID " + "'" + friendID + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		System.out.println("Query:\t" + query);

		@SuppressWarnings("unchecked")
		List<Friend> list = (List<Friend>) query.list();
		System.out.println(list.toString());

		if (list != null && !list.isEmpty()) {
			return list();
		}

		return null;
	}

	@Transactional
	public List<Friend> getAllFriends(String userID) {
		System.out.println("FriendDAO get Operation  invoked");

		String hql = "from User where userID = " + "'" + userID + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		System.out.println("Query:\t" + query);

		@SuppressWarnings("unchecked")
		List<Friend> list = (List<Friend>) query.list();
		System.out.println(list.toString());

		if (list != null && !list.isEmpty()) {
			return list();
		}

		return null;
	}
/*
	@Transactional
	public List<Friend> getAllMyFriends(String id, String status) {
		System.out.println("FriendDAO get Operation  invoked");

		String hql = "from User where id=" + "'" + id + "'" + " and status " + "'" + status + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		System.out.println("Query:\t" + query);

		@SuppressWarnings("unchecked")
		List<Friend> list = (List<Friend>) query.list();
		System.out.println(list.toString());

		if (list != null && !list.isEmpty()) {
			return list();
		}

		return null;
	}
*/
	
	
	@Transactional
	public void setOnline(String userID){
		
	}

	public void setOffline(String userID) {
		// TODO Auto-generated method stub
		
	}
}