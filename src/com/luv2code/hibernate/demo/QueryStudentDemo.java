package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.entity.Student;


public class QueryStudentDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
										.addAnnotatedClass(Student.class)
										.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			int studentId = 1;
			session.beginTransaction();
			
			Student myStudent = session.get(Student.class, studentId);
			
			System.out.println("got my student: " + myStudent);
			
			myStudent.setFirstName("Abisola");
			System.out.println("Updated my student record to: " + myStudent);
			
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			System.out.println("Print all Students from dB");
			displayStudents(theStudents);
			
			theStudents = session.createQuery("from Student stu where stu.lastName='Eluyemi'").getResultList();
			
			System.out.println("\n\nPrint specific Student from dB\n");
			displayStudents(theStudents);
			
			theStudents = session.createQuery("from Student stu where stu.lastName='Eluyemi'"
					+ "OR stu.firstName='Chiamaka'").getResultList();
			
			System.out.println("\n\nPrint OR statement for Student from dB\n");
			displayStudents(theStudents);
			
			theStudents = session.createQuery("from Student stu where stu.email like '%noreply.com'").getResultList();
			
			System.out.println("\n\nPrint Like statement for Student from dB\n");
			displayStudents(theStudents);
			
			System.out.println("Update all records by changing the firstName");
			session.createQuery("update Student set firstName='Aderinsola'").executeUpdate();
			
			System.out.println("Delete first record");
			session.delete(myStudent);
			
			System.out.println("Delete second record");
			session.createQuery("delete from Student where lastName='Orokin'").executeUpdate();

			session.getTransaction().commit();

		} finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student std : theStudents )
			System.out.println(std);
	}

}
