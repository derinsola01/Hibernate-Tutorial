package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();
		
		try {
			System.out.println("Creating 4 new Students");
			Student tempStudent1 = new Student("Ade", "Orokin", "ade@noreply.com");
			Student tempStudent2 = new Student("Bukunmi", "Oyedeji", "bukunmi@noreply.com");
			Student tempStudent3 = new Student("Chiamaka", "Obianyor", "chiamaka@noreply.com");
			Student tempStudent4 = new Student("Femi", "Eluyemi", "femi@noreply.com");
			
			session.beginTransaction();
			
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);
			session.save(tempStudent4);
			
			System.out.println("Saved the students");
			
			session.getTransaction().commit();
			
			System.out.println("All completed!");
		} finally {
			factory.close();
		}
	}

}
