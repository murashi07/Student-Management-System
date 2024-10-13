package org.registration.controller;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.registration.model.Course;
import org.registration.util.HibernateUtil;


import java.util.List;

public class CoursePerStudentController {

    /**
     * Retrieves a list of courses for a specific student identified by their registration number.
     * @param studentRegNo The registration number of the student.
     * @return List of courses the student is taking.
     */
    public List<Course> getCoursesByStudent(String studentRegNo) {
        Transaction transaction = null;
        List<Course> courses = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Start the transaction
            transaction = session.beginTransaction();

            // Query to get the courses by student registration number
            String hql = "SELECT c FROM Course c JOIN StudentCourse sc ON c.code = sc.course.code WHERE sc.student.regNo = :studentRegNo";
            Query<Course> query = session.createQuery(hql, Course.class);
            query.setParameter("studentRegNo", studentRegNo); // Use getRegNo() here

            // Execute the query and get the list of courses
            courses = query.getResultList();

            // Commit the transaction
            transaction.commit();
            System.out.println("List of courses retrieved successfully.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            System.err.println("Failed to retrieve courses.");
        }

        return courses;
    }
}
