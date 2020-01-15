package com.beta.test.daoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import com.beta.test.dao.UserDao;
import com.beta.test.entity.User;

public class UserDaoImpl implements UserDao {

	public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(User.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration
                .buildSessionFactory(builder.build());
        return sessionFactory;
    }
	
	@Autowired
	public void registerUser(User user) {
		
		
		Session session = getSessionFactory().openSession();
		
		try {

			session.beginTransaction();

			System.out.println("inserting record...........");
			session.save(user);

			session.getTransaction().commit();

		} catch (Exception e) {
		} finally {
			session.close();
		}
		
	}

}
