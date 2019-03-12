package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
										.addAnnotatedClass(Student.class)
										.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			System.out.println("Creating a new Student");
			Student tempStudent = new Student("Derin", "Gbadebo", "derin@noreply.com");
			session.beginTransaction();
			session.save(tempStudent);
			System.out.println("Saved the student");
			session.getTransaction().commit();
			System.out.println("All completed!");
		} finally {
			factory.close();
		}
	}

}
