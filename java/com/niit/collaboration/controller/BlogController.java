package com.niit.collaboration.controller;

import java.util.List;

import javax.persistence.PostUpdate;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaboration.DAO.BlogDAO;
import com.niit.collaboration.model.Blog;
import com.niit.collaboration.model.User;

@RestController
public class BlogController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	BlogDAO blogDAO;

	@Autowired
	Blog blog;

	@Autowired
	private HttpSession session;

	@GetMapping("/blogs")
	public ResponseEntity<List<Blog>> getBlogs() {
		logger.debug("calling method getBlog");
		List blogs = blogDAO.list();
		if (blogs == null) {
			blog = new Blog();
			blog.setErrorCodes("404");
			blog.setErrorMessage("No Blogs are available");
			return new ResponseEntity<List<Blog>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Blog>>(blogs, HttpStatus.NO_CONTENT);
	}

	@GetMapping("/blog/{id}")
	public ResponseEntity<Blog> getBlog(@PathVariable("id") int id) {
		logger.debug("calling method getBlog with this id " + id);
		Blog blog = (Blog) blogDAO.get(id);
		if (blog == null) {
			return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}

	@PostMapping(value = "/blog")
	public ResponseEntity<Blog> createBlog(@RequestBody Blog blog) {
		logger.debug("calling method createBlog with this id " + blog.getId());
		User loggedInUserID = (User) session.getAttribute("loggedInUserID");
		logger.debug("Blog is created by the user " + loggedInUserID);
		blog.setUserID(loggedInUserID.getId());
		blog.setStatus('N');

		blogDAO.save(blog);

		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}

	@DeleteMapping("/blog/{id}")
	public ResponseEntity<Blog> deleteBlog(@PathVariable int id) {
		logger.debug("calling method createBlog with this id " + id);
		if (blogDAO.get(id) != null) {
			blogDAO.delete(id);
			logger.debug("->->blog deleted successfully");
			return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
		} else {
			logger.debug("blog does not exist with id " + id);
			blogDAO.delete(id);
			return new ResponseEntity<Blog>(HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/blog/{id}", method = RequestMethod.POST)
	public ResponseEntity<Blog> editBlog(@PathVariable int id) {
		logger.debug("calling method editBlog with this id " + id);
		Blog blog = (Blog) blogDAO.get(id);
		blogDAO.update(blog);
		logger.debug("->->blog updated successfully");
		return new ResponseEntity<Blog>(HttpStatus.OK);

	}

}
