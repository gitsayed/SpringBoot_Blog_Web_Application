package com.blog.repository;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.blog.model.BlogUser;

@Repository
@Transactional
public class UserDao2 {
	@Autowired
	SessionFactory sf;
	
	
	public BlogUser findByUsernameAndIsactive(String username) {
    String sql = "from User where user_id=" + username +" and isactive="+"'Y'" ;
	BlogUser  blogUser = (BlogUser) sf.getCurrentSession().createQuery(sql).uniqueResult();
		return blogUser;
	};
	
}
