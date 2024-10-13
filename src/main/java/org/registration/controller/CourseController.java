package org.registration.controller;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.registration.model.Course;
import org.registration.util.HibernateUtil;


public class CourseController {

    public void saveCourse(Course course) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(course);
            transaction.commit();
            System.out.println("Course saved successfully.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            System.err.println("Failed to save the course.");
        }
    }
}
