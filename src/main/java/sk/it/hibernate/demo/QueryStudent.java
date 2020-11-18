package sk.it.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import sk.it.hibernate.entity.Student;

import java.util.List;

public class QueryStudent {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try{
            session.beginTransaction();
            List<Student> students;

            System.out.println("\nAll students: ");
            students = session.createQuery("from Student").getResultList();
            printQuery(students);

            System.out.println("\nQuery - lastName = Silny: ");
            students = session.createQuery("from Student s where s.lastName='Silny'").getResultList();
            printQuery(students);

            System.out.println("\nQuery - lastName = Silny OR Jackanin: ");
            students = session.createQuery("from Student s where s.lastName='Silny' OR s.lastName='Jackanin'").getResultList();
            printQuery(students);

            System.out.println("\nQuery - email = %@mail.com: ");
            students = session.createQuery("from Student s where s.email LIKE '%@mail.com'").getResultList();
            printQuery(students);

            System.out.println("\nQuery - email = %@gmail.com: ");
            students = session.createQuery("from Student s where s.email LIKE '%@gmail.com'").getResultList();
            printQuery(students);

            session.getTransaction().commit();
            System.out.println("Done!");
        }finally {
            sessionFactory.close();
        }
    }

    private static void printQuery(List<Student> students) {
        for (Student student : students){
            System.out.println(student);
        }
    }
}
