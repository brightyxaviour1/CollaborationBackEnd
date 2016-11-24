package com.niit.collaboration.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.collaboration.DAO.*;
import com.niit.collaboration.DAO.JobDAOImpl;
import com.niit.collaboration.DAO.UserDAO;
import com.niit.collaboration.DAO.UserDAOImpl;
import com.niit.collaboration.model.Blog;
import com.niit.collaboration.model.Chat;
import com.niit.collaboration.model.ChatForum;
import com.niit.collaboration.model.ChatForumComment;
import com.niit.collaboration.model.Friend;
import com.niit.collaboration.model.Job;
import com.niit.collaboration.model.JobApplication;
import com.niit.collaboration.model.User;

import antlr.debug.Event;

@Configuration
@ComponentScan("com.niit.*")
@EnableTransactionManagement
public class ApplicationContextConfig {

	@Bean(name = "dataSource")
	public DataSource getOracleDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");

		dataSource.setUsername("Collaboration");// COLB_DB
		dataSource.setPassword("root");         // root

		Properties connectionProperties = new Properties();
		connectionProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		connectionProperties.setProperty("hibernate.show_sql", "true");
		connectionProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		connectionProperties.setProperty("hibernate.format_sql", "true");
		connectionProperties.setProperty("hibernate.jdbc.use_get_generated_keys", "true");
		// connectionProperties.setProperty("hibernate.default_schema","COLLABORATION_DB");
		dataSource.setConnectionProperties(connectionProperties);

		return dataSource;
	}

	/*
	 * @Bean(name = "dataSource") public DataSource getDataSource() {
	 * BasicDataSource dataSource = new BasicDataSource();
	 * dataSource.setDriverClassName("org.h2.Driver");
	 * dataSource.setUrl("jdbc:h2:tcp://localhost/~/test");
	 * dataSource.setUserDetailsname("sa"); dataSource.setPassword(""); return
	 * dataSource; }
	 */

	/*
	 * @Bean(name = "dataSource") public DataSource getMySQLDataSource() {
	 * BasicDataSource dataSource = new BasicDataSource();
	 * dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	 * dataSource.setUrl("jdbc:mysql://localhost:3036/niitdb");
	 * dataSource.setUsername("root"); dataSource.setPassword("root");
	 * 
	 * return dataSource; }
	 */

	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
		properties.put("hibernate.hbm2ddl.auto", "update");

		System.out.println("*******HibernateProperties initialized****");
		return properties;
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(getHibernateProperties());
		// sessionBuilder.addAnnotatedClasses(User.class);

		sessionBuilder.addAnnotatedClasses(Blog.class);
		sessionBuilder.addAnnotatedClasses(Chat.class);
		sessionBuilder.addAnnotatedClasses(Event.class);
		sessionBuilder.addAnnotatedClasses(Friend.class);
		sessionBuilder.addAnnotatedClasses(Job.class);
		sessionBuilder.addAnnotatedClasses(JobApplication.class);

		sessionBuilder.addAnnotatedClasses(ChatForum.class);
		sessionBuilder.addAnnotatedClasses(ChatForumComment.class);
		sessionBuilder.addAnnotatedClasses(User.class);

		System.out.println("Session factory is retrieved***");
		return sessionBuilder.buildSessionFactory();
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

		System.out.println("transactionManager is retreived***");
		return transactionManager;
	}

	@Autowired
	@Bean(name = "userDAO")
	public UserDAO getuserDao(SessionFactory sessionFactory) {
		// System.out.println("*******userDAO Object Retrieved***");
		return new UserDAOImpl(sessionFactory);
	}

	@Autowired
	@Bean(name = "jobDAO")
	public JobDAO getjobDao(SessionFactory sessionFactory) {
		// System.out.println("*******UserDetailsDao Object Retrieved***");
		return new JobDAOImpl(sessionFactory);
	}

	@Autowired
	@Bean(name = "friendDAO")
	public FriendDAO getfriendDao(SessionFactory sessionFactory) {
		// System.out.println("*******friendDAO Object Retrieved***");
		return new FriendDAOImpl(sessionFactory);
	}

	@Autowired
	@Bean(name = "eventDAO")
	public EventDAO geteventDao(SessionFactory sessionFactory) {
		// System.out.println("*******eventDAO Object Retrieved***");
		return new EventDAOImpl(sessionFactory);
	}

	@Autowired
	@Bean(name = "chatForumDAO")
	public ChatForumDAO getchatForumDao(SessionFactory sessionFactory) {
		// System.out.println("*******chatForumDAO Object Retrieved***");
		return new ChatForumDAOImpl(sessionFactory);
	}

	@Autowired
	@Bean(name = "chatDAO")
	public ChatDAO getchatDao(SessionFactory sessionFactory) {
		// System.out.println("*******chatDAO Object Retrieved***");
		return new ChatDAOImpl(sessionFactory);
	}

	@Autowired
	@Bean(name = "blogDAO")
	public BlogDAO getblogDao(SessionFactory sessionFactory) {
		// System.out.println("*******BlogDAO Object Retrieved***");
		return new BlogDAOImpl(sessionFactory);
	}
}
