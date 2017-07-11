package com.registration.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

//provide sessionFactory: provides session
public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	
	// before creating any object
	static {
		try{				
			Configuration configuraiton = new Configuration();
			configuraiton.configure("hibernate.cfg.xml");
			ServiceRegistry serviceRegistry = 
					new StandardServiceRegistryBuilder()
                    .applySettings(configuraiton.getProperties()).build();
        
			sessionFactory = configuraiton.buildSessionFactory(serviceRegistry);
			
		}catch (Throwable t){
	}
	}
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;		
	}
	
}	