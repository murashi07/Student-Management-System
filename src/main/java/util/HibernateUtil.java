package util;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import org.registration.model.*;

public class HibernateUtil {
    private static SessionFactory sessionFactory = null;

    public static SessionFactory getSession() {
        if(sessionFactory == null) {
            Configuration configuration = new Configuration();

            Properties property = new Properties();

            property.put(Environment.URL, "jdbc:postgresql://localhost:5432/universitydb");
            property.put(Environment.USER, "postgres");
            property.put(Environment.PASS, "yvan");
            property.put(Environment.SHOW_SQL, true);
            property.put(Environment.HBM2DDL_AUTO, "create");

            configuration.addProperties(property);

            configuration.addAnnotatedClass(Student.class);
            configuration.addAnnotatedClass(Course.class);
            configuration.addAnnotatedClass(AcademicUnit.class);
            configuration.addAnnotatedClass(CourseDefinition.class);
            configuration.addAnnotatedClass(EAcademicUnit.class);
            configuration.addAnnotatedClass(EQualification.class);
            configuration.addAnnotatedClass(Semester.class);
            configuration.addAnnotatedClass(StudentCourse.class);
            configuration.addAnnotatedClass(StudentRegistration.class);
            configuration.addAnnotatedClass(Teacher.class);

            sessionFactory = configuration.buildSessionFactory();
            return sessionFactory;
        }else {
            return sessionFactory;
        }

    }
}