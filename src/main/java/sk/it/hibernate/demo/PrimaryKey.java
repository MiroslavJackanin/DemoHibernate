package sk.it.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import sk.it.hibernate.entity.Student;

public class PrimaryKey {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try{
            System.out.println("Creating a new students...");
            Student student1 = new Student("Adolf", "Hortler", "hortler@mail.com");
            Student student2 = new Student("Peter", "Modry", "peter@mail.com");
            Student student3 = new Student("Ondrej", "Silny", "ondrej@mail.com");

            session.beginTransaction();

            System.out.println("Saving students...");
            session.save(student1);
            session.save(student2);
            session.save(student3);
            session.getTransaction().commit();

            System.out.println("Done!");
        }finally {
            sessionFactory.close();
        }
    }
}
