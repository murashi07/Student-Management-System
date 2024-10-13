package org.registration.controller;


import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.registration.model.Semester;
import org.registration.model.Student;
import org.registration.model.StudentRegistration;

import java.util.List;

public class StudentRegistrationController {

    private ConnectionController connectionController;

    public StudentRegistrationController() {
        this.connectionController = new ConnectionController();
    }

    /**
     * Saves a StudentRegistration entity to the database.
     *
     * @param studentRegistration The StudentRegistration object to be saved.
     */
    public void saveStudentRegistration(StudentRegistration studentRegistration) {
        Session session = null;
        Transaction transaction = null;

        try {
            // Get a session and begin transaction
            session = connectionController.getConnection();
            transaction = session.beginTransaction();

            // Check if the student exists
            Student student = session.get(Student.class, studentRegistration.getStudent().getRegNo());
            if (student == null) {
                System.err.println("Error: Student with registration number " + studentRegistration.getStudent().getRegNo() + " does not exist.");
                return; // Exit the method
            }

            // Check if the semester exists
            Semester semester = session.get(Semester.class, studentRegistration.getSemester().getId());
            if (semester == null) {
                System.err.println("Error: Semester with ID " + studentRegistration.getSemester().getId() + " does not exist.");
                return; // Exit the method
            }

            // Save the student registration entity
            session.save(studentRegistration);

            // Commit the transaction
            transaction.commit();
            System.out.println("Student registration saved successfully for student: " + student.getFirstName() + " in semester: " + semester.getName());

        } catch (Exception e) {
            // Rollback the transaction in case of any error
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error saving student registration: " + e.getMessage());
        } finally {
            // Close the session
            connectionController.closeConnection(session);
        }
    }

    /**
     * Fetches a list of students registered in the specified semester.
     *
     * @param semester The Semester object.
     * @return A list of students registered in the semester.
     */
    public List<Student> getStudentsBySemester(Semester semester) {
        Session session = null;
        Transaction transaction = null;
        List<Student> students = null;

        try {
            // Get a session and begin transaction
            session = connectionController.getConnection();
            transaction = session.beginTransaction();

            // Create a query to fetch student registrations based on semester
            Query<StudentRegistration> query = session.createQuery(
                "FROM StudentRegistration sr WHERE sr.semester = :semester", StudentRegistration.class);
            query.setParameter("semester", semester);

            // Execute the query and get the results
            List<StudentRegistration> registrations = query.getResultList();

            // Extract the students from the registrations
            students = registrations.stream()
                .map(StudentRegistration::getStudent)
                .toList();

            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            // Rollback the transaction in case of any error
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error fetching students for semester " + semester.getId() + ": " + e.getMessage());
        } finally {
            // Close the session
            connectionController.closeConnection(session);
        }

        return students;
    }

}
