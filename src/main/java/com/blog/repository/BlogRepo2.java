package com.blog.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.blog.model.Blog;

@Repository
public interface BlogRepo2 extends CrudRepository<Blog, Integer> {

	
	List<Blog> findByIsactive(String isActive);
	List<Blog> findByUsername(String username);
}
