package com.niit.collaboration.DAO;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.niit.collaboration.model.Job;
import com.niit.collaboration.model.JobApplication;

public interface JobDAO {

	public List<Job> getAllOpenedJobs();

	public List<Job> getAppliedJobs();

	public Job get(String jobID);

	public boolean save(Job job);

	public boolean save(JobApplication jobApplication);

	public boolean update(Job job);

	public boolean update(JobApplication jobApplication);

	public JobApplication getJobApplication(String userID, String jobID);

	public JobApplication getJobApplicationOfUser(String userID);
	
	public JobApplication getJobApplication(String jobID);

	public void delete(Long id);

}
