package com.niit.collaboration.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration.model.Job;
import com.niit.collaboration.model.JobApplication;
import com.niit.collaboration.model.User;

@Repository("jobDAO")
public class JobDAOImpl implements JobDAO {

	@Autowired
	Job job;

	@Autowired
	JobDAO jobDAO;

	@Autowired
	JobApplication jobApplication;

	@Autowired
	private SessionFactory sessionFactory;

	public JobDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// to post a job
	@Transactional
	public boolean save(Job job) {
		try {
			sessionFactory.getCurrentSession().save(job);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Transactional
	public boolean update(Job job) {
		try {
			sessionFactory.getCurrentSession().update(job);

		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
		return true;

	}

	@Transactional
	public void delete(Long id) {
		Job job = new Job();
		job.setId(id);
		sessionFactory.getCurrentSession().delete(job);

	}

	@Transactional
	public Job get(String jobID) {
		System.out.println("JobDAO get Operation  invoked");

		String hql = "from User where jobID=" + "'" + jobID + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		System.out.println("Query:\t" + query);

		@SuppressWarnings("unchecked")
		List<Job> list = (List<Job>) query.list();
		System.out.println(list.toString());

		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	@Transactional
	public List<Job> getAllOpenedJobs() {
		String hql = "from Job where status= 'V'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Transactional
	public List<Job> getAppliedJobs() {
		String hql = "from Job where status= 'A'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Transactional
	public boolean save(JobApplication jobApplication) {
		try {
			sessionFactory.getCurrentSession().save(jobApplication);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Transactional
	public boolean update(JobApplication jobApplication) {
		try {
			sessionFactory.getCurrentSession().update(jobApplication);

		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Transactional
	public JobApplication getJobApplication(String userID, String jobID) {
		String hql = "from JobApplication where userID=" + "'" + userID + "' and jobID=" + "'" + jobID + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (JobApplication) query.list();
	}

	@Transactional
	public JobApplication getJobApplicationOfUser(String userID) {
		String hql = "from JobApplication where userID=" + "'" + userID + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (JobApplication) query.list();
	}

	@Transactional
	public JobApplication getJobApplication(String jobID) {
		String hql = "from JobApplication where jobID=" + "'" + jobID + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (JobApplication) query.list();
	}
}
