package com.blog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.blog.model.Blog;
import com.blog.model.BlogUser;
import com.blog.repository.BlogDaoImp;
import com.blog.repository.UserDao;
//@EntityScan({"com.blog.model.User"})



@Import({HiberNateConfig.class })
@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class BootThisApp {
	BCryptPasswordEncoder bpe= new BCryptPasswordEncoder();
  
	public static void main(String[] args) {
		SpringApplication.run(BootThisApp.class, args);
	}

	 
	@Bean
	public  CommandLineRunner demo(UserDao userRepo, BlogDaoImp blogDao ) {
		return (args) -> {			
			userRepo.save(new BlogUser("admin", bpe.encode("admin"), "ADMIN","Y"));
			userRepo.save(new BlogUser("abu", bpe.encode("abu"), "USER","N"));
			userRepo.save(new BlogUser("sayed", bpe.encode("sayed"), "USER","N"));
		    blogDao.saveBlog(new Blog( "Demo Blog Title", "Demo Blog Details" ,  "sayed" ,"07-03-2021","", "Y"  )); 
		    blogDao.saveBlog(new Blog( "Lorem Ipsum", lorem ,  "abu" ,"07-03-2021","", "N"  )); 
		    blogDao.saveBlog(new Blog( "Lorem Ipsum_2", lorem ,  "admin" ,"07-03-2021","", "Y"  ));
		    blogDao.saveBlog(new Blog( "Lorem Ipsum_3", lorem ,  "sayed" ,"07-03-2021","", "N"  )); 
		    blogDao.saveBlog(new Blog( sht, sh ,  "admin" ,"07-03-2021","", "Y"  )); 
		    blogDao.saveBlog(new Blog( sht, sh ,  "admin" ,"07-03-2021","", "Y"  )); 
			
		};
	}

	
String lorem="Lorem ipsum dolor sit amet,"
		+ " consectetur adipiscing elit."
		+ " Pellentesque et libero at odio mattis convallis quis ac velit."
		+ " Duis facilisis sem finibus erat scelerisque, vel ultrices lorem"
		+ " tincidunt. Ut sagittis dui et diam pretium dignissim."
		+ " Pellentesque rutrum enim sed ex tincidunt,"
		+ " quis aliquam leo blandit. Nam a maximus mauris,"
		+ " sed commodo enim. Nullam posuere justo vel quam euismod,"
		+ " at tempus ante hendrerit. Ut ullamcorper ullamcorper erat,"
		+ " sed lobortis felis pulvinar quis. Mauris ultricies auctor magna "
		+ "nec placerat. Suspendisse  venenatis euismod magna, "
		+ "quis dictum ex. Quisque id dapibus urna, in tristique turpis."
		+ " Donec ullamcorper ligula euismod nisi semper, in mollis nisl pulvinar.";	
	
String sh="Square Health is a healthcare services aggregator aiming to digitize healthcare services.\n"
		+ "Our mission is to help build a country where every patient can access their health information and participate in their own care, and every healthcare organization can serve their community quicker and more safely through cutting-edge technology with instant access to records, knowledge, and data.\n"
		+ "\n"
		+ "With the use of technology, we aim to do just that; connect and bridge caregivers and the patients so that the people can live better and healthier lives";	
String sht="Making Healthcare Digital… ";

String sh2="We don’t just make software — we empower organizations all over the country to take on the challenges of healthcare’s ever-expanding landscape. From giving providers more satisfaction in their day to fostering a safe, comfortable patient experience, we understand that the best technology is the kind that improves people’s lives.";
String sht2="We make healthcare better " ;



}
