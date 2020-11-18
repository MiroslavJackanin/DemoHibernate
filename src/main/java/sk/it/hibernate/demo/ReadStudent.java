package sk.it.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import sk.it.hibernate.entity.Student;

public class ReadStudent {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try{
            System.out.println("Creating a new student...");
            Student student = new Student("Jozef", "Stary", "jozef@mail.com");
            session.beginTransaction();
            System.out.println("Saving student...");
            session.save(student);
            session.getTransaction().commit();
            System.out.println("Generated ID: " + student.getId());

            System.out.println("Retrieving student...");
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            System.out.println("Getting student with ID: " + student.getId());
            student = session.get(Student.class, student.getId());
            System.out.println("Retrieve complete: " + student);
            session.getTransaction().commit();

            System.out.println("Done!");
        }finally {
            sessionFactory.close();
        }
    }
}
