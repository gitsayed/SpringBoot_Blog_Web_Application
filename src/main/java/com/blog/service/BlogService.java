 package com.blog.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.blog.dto.BlogDto;
import com.blog.model.Blog;
import com.blog.model.Comment;
import com.blog.repository.BlogDaoImp;
import com.blog.repository.CommentDaoImp;



@Service
public class BlogService {

	@Autowired
	BlogDaoImp blogDaoImp;
	@Autowired 
    CommentDaoImp commentDaoImp;

	public List<Blog> getActiveBlogkList()  {
		List<Blog> list_a = blogDaoImp.getActiveBlogList();
		List<Blog> list_b = null;
		DateFormat ds = new SimpleDateFormat("DD-MM-yyyy");
		try {
		if (list_a != null) {
			list_b = new ArrayList<>();
			for (int i = 0; i < list_a.size(); i++) {
				Blog ab = list_a.get(i);
				Date d1 = new SimpleDateFormat("yyyy-MM-DD").parse(ab.getCreateat());
				ab.setCreateat(ds.format(d1));
				list_b.add(ab);
			}
		}} catch(Exception e) {
			
		}
		return list_a;
	}
	public List<Blog> getBlogkList()  {
		List<Blog> list_a = blogDaoImp.getBlogList();
		List<Blog> list_b = null;
		DateFormat ds = new SimpleDateFormat("DD-MM-yyyy");
		try {
		if (list_a != null) {
			list_b = new ArrayList<>();
			for (int i = 0; i < list_a.size(); i++) {
				Blog ab = list_a.get(i);
				Date d1 = new SimpleDateFormat("yyyy-MM-DD").parse(ab.getCreateat());
				ab.setCreateat(ds.format(d1));
				list_b.add(ab);
			}
		}} catch(Exception e) {
			
		}
		return list_a;
	}

	



	public void updateBlog(Blog blog, String status ) {
		if(status.equals("Y")) {
			blog.setIsactive("N");
		}else if(status.equals("N")) {
			blog.setIsactive("Y");
		}
	blogDaoImp.updateBlog(blog);

	}

	public Blog findBlogById(Integer id) {
	
		return blogDaoImp.findBlogById(id);
		 
	}

	public void deleteById(Integer id) {
		blogDaoImp.deleteById(id);
		
	}

	public BlogDto bookDetailsById(Integer id) {
	 BlogDto blogDto=new BlogDto();
	 List<Comment> clist=commentDaoImp.getCommentsByBookId(id);
	
	 blogDto.setBlog(this.findBlogById(id));
	 blogDto.setCommentList(clist);
	
		return blogDto;
	}

	public void saveComment(Comment comment) {
		commentDaoImp.saveComment(comment);
		
	}

	

}
