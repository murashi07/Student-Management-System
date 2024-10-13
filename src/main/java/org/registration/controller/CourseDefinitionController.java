package org.registration.controller;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.registration.model.CourseDefinition;
import org.registration.util.HibernateUtil;


public class CourseDefinitionController {

    public void saveCourseDefinition(CourseDefinition courseDefinition) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(courseDefinition);
            transaction.commit();
            System.out.println("CourseDefinition saved successfully.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            System.err.println("Failed to save the CourseDefinition.");
        }
    }
}
