package org.registration.controller;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.registration.config.HibernateUtil;
import org.registration.model.Student;

import java.util.List;

public class StudentsPerCourseSemesterController {

    /**
     * Retrieves a list of students for a specific course and semester.
     * @param courseCode The code of the course (e.g., "CSOC212").
     * @param semesterName The name of the semester (e.g., "Spring 2024").
     * @return List of students registered in the course and semester.
     */
    public List<Student> getStudentsByCourseAndSemester(String courseCode, String semesterName) {
        Transaction transaction = null;
        List<Student> students = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Start the transaction
            transaction = session.beginTransaction();

            // Adjusted HQL query
            String hql = "SELECT sc.student FROM StudentCourse sc " +
                         "JOIN sc.studentRegistration sr " +
                         "JOIN sr.semester sem " +
                         "WHERE sc.course.code = :courseCode " +
                         "AND sem.name = :semesterName";

            Query<Student> query = session.createQuery(hql, Student.class);
            query.setParameter("courseCode", courseCode);
            query.setParameter("semesterName", semesterName);

            // Execute the query and get the list of students
            students = query.getResultList();

            // Commit the transaction
            transaction.commit();
            System.out.println("List of students retrieved successfully.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            System.err.println("Failed to retrieve students.");
        }

        return students;
    }
}
