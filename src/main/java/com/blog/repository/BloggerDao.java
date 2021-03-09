package com.blog.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.blog.model.Blog;
import com.blog.model.BlogUser;

@Repository
@Transactional
public class BloggerDao {

	@Autowired
	SessionFactory sessionFactory;

	public BlogUser findBloggerById(long id) {
		BlogUser user = null;
		try {
			String sql = "from BlogUser where user_id=" + id;
			Session session = sessionFactory.getCurrentSession();
			Query q = session.createQuery(sql, BlogUser.class);
			System.out.println(q.list().get(0).toString());
			user = (BlogUser) q.list().get(0);
		} catch (Exception e) {
			System.out.println(e);
		}
		return user;
	}

	public void deleteById(long id) {
		String sql = "delete from BlogUser where user_id=" + id;
		int i = sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
		System.out.println("delete print : " + i);
	}

	public void updateBlogger(BlogUser user) {
		try {
			sessionFactory.getCurrentSession().merge(user);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("update_blogger_error :" + e);
		}

	}

	public List<BlogUser> getBloggerList() {
		String sql = "from BlogUser order by 1 ";
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery(sql, BlogUser.class);
		System.out.println(q.list().get(0).toString());
		return (List<BlogUser>) q.list();
	}

	public void saveBloguser(BlogUser blogger) {
		try {
			sessionFactory.getCurrentSession().merge(blogger);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("update_blogger_error :" + e);
		}

	}
}
