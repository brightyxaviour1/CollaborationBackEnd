package com.niit.collaboration.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration.model.Blog;

@Repository("blogDAO")
public class BlogDAOImpl implements BlogDAO {
	
	@Autowired
	Blog blog;
	
	@Autowired
	private SessionFactory sessionFactory;

	public BlogDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<Blog> list() {
		@SuppressWarnings("unchecked")
		List<Blog> list = (List<Blog>) sessionFactory.getCurrentSession().createCriteria(Blog.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return list;
	}

	@Transactional
	public boolean save(Blog blog) {
		try {
			sessionFactory.getCurrentSession().save(blog);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean update(Blog blog) {
		try {
			sessionFactory.getCurrentSession().update(blog);
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}

	}

	@Transactional
	public void delete(int id) {
		Blog blog = new Blog();
		blog.setId(id);
		sessionFactory.getCurrentSession().delete(blog);

	}

	@Transactional
	public List<Blog> get(int id) {
		System.out.println("BlogDAO get Operation  invoked");

		String hql = "from User where id=" + "'" + id + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		System.out.println("Query:\t" + query);

		@SuppressWarnings("unchecked")
		List<Blog> list = (List<Blog>) query.list();
		System.out.println(list.toString());

		if (list != null && !list.isEmpty()) {
			return list();
		}

		return null;
	}

}