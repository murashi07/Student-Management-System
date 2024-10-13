package org.registration.controller;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.registration.model.Student;
import org.registration.util.HibernateUtil;

import java.util.List;


public class StudentQueryController {

    /**
     * Retrieves a list of students for a specific department (academicUnitName) and semester (semesterName).
     * @param semesterName The name of the semester as a String.
     * @param academicUnitName The name of the department (academic unit).
     * @return List of students.
     */
    public List<Student> getStudentsBySemesterAndDepartment(String semesterName, String academicUnitName) {
        Transaction transaction = null;
        List<Student> students = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Start the transaction
            transaction = session.beginTransaction();

            // Query to get the students by semester name and department name
            String hql = "SELECT sr.student FROM StudentRegistration sr " +
                         "JOIN sr.semester s " +
                         "JOIN sr.student.academicUnit a " +
                         "WHERE s.name = :semesterName " +
                         "AND a.name = :academicUnitName";

            Query<Student> query = session.createQuery(hql, Student.class);
            query.setParameter("semesterName", semesterName);
            query.setParameter("academicUnitName", academicUnitName);

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
