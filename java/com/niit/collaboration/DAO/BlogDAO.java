package com.niit.collaboration.DAO;

import java.util.List;

import com.niit.collaboration.model.Blog;

public interface BlogDAO {

	public List<Blog> list();

	// public boolean get(String id, String password);

	public List<Blog> get(int id);

	public boolean save(Blog blog);

	public boolean update(Blog blog);

	public void delete(int id);

	// public boolean isValidBlog(int id, String name);

}
