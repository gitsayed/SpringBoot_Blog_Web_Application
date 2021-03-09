package com.blog;

import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class HiberNateConfig {

	private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/abc";
	private static final String DATABASE_DRIVER_CLASS_NAME = "org.postgresql.Driver";
	private static final String DATABASE_USERNAME = "sayed";
	private static final String DATABASE_PASSWORD = "sayed";

//    @Autowired
//    UserDao2 userDao2;

	@Bean(name = "entityManagerFactory")
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] { "com.blog.model" });
		sessionFactory.setHibernateProperties(hibernateProperties());
		
		return sessionFactory;

	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(DATABASE_DRIVER_CLASS_NAME);
		dataSource.setUrl(DATABASE_URL);
		dataSource.setUsername(DATABASE_USERNAME);
		dataSource.setPassword(DATABASE_PASSWORD);
		dataSource.setSchema("sayed");
		return dataSource;
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		properties.put("hibernate.show_sql", true);
		properties.put("hibernate.format_sql", true);
		properties.put("hibernate.hbm2ddl.auto", "create");
		return properties;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sf) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sf);
		return txManager;
	}

//    @Bean( name="sessionFactoryConfig")
//    public LocalSessionFactoryBean sessionFactoryConfig() {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource());
//        sessionFactory.setPackagesToScan(
//                new String[] { "org.crud.com.model" });
//
//        sessionFactory.setHibernateProperties(hibernateProperties());
//
//        return sessionFactory;
//    }

//    @Bean
//    public void testBean() {
//    	
//      userDao2.saveUserToTable(new User(
//    		      		  "admin",
//    		      		   "admin",
//    		      		    "admin"    		  
//    		  ));
	//private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/abc";
	//org.hibernate.dialect.PostgreSQLDialect"
//    }

}
