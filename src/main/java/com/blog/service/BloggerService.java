package com.blog.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.model.BlogUser;
import com.blog.repository.BloggerDao;

@Service
public class BloggerService {
	
	BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
	@Autowired
	BloggerDao bloggerDao;

	public List<BlogUser> getAllBlogger() {
		return bloggerDao.getBloggerList();
	}

	public BlogUser findBloggerById(long id) {
		BlogUser user = bloggerDao.findBloggerById(id);
		return user;
	}

	public void updateBlogUser(BlogUser user, String status) {
		if (status.equals("Y")) {
			user.setIsactive("N");
		} else if (status.equals("N")) {
			user.setIsactive("Y");
		}
		bloggerDao.updateBlogger(user);
	}

	public void deleteBloggerById(long id) {
		bloggerDao.deleteById(id);

	}

	public void saveUser(BlogUser blogger) {
		Date date = new Date();
//		SimpleDateFormat formatter = new SimpleDateFormat("dd/yyyy");
//		String created = formatter.format(date);
		blogger.setPassword(bpe.encode(blogger.getPassword()));
        bloggerDao.saveBloguser(blogger);
		
	}
}
