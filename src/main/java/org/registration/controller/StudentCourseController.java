package org.registration.controller;


import org.hibernate.Session;
import org.registration.model.Course;
import org.registration.model.Student;
import org.registration.model.StudentCourse;
import org.registration.util.HibernateUtil;

import java.math.BigDecimal;


public class StudentCourseController {

    public void registerStudentInCourse(Student student, Course course, Integer credits) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();

            // Create a new StudentCourse instance
            StudentCourse studentCourse = new StudentCourse();
            studentCourse.setStudent(student);
            studentCourse.setCourse(course);
            studentCourse.setCredits(credits);
            studentCourse.setResults(BigDecimal.ZERO); // Initialize results as zero or whatever your logic is

            // Save the StudentCourse record
            session.save(studentCourse);

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace(); // Log the exception
        } finally {
            session.close();
        }
    }
}
