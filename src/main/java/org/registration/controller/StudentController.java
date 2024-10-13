package org.registration.controller;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.registration.model.Student;

public class StudentController {

    private ConnectionController connectionController;

    public StudentController() {
        this.connectionController = new ConnectionController();
    }

    /**
     * Saves a Student entity to the database.
     *
     * @param student The Student object to be saved.
     */
    public void saveStudent(Student student) {
        Session session = null;
        Transaction transaction = null;

        try {
            // Get a session and begin transaction
            session = connectionController.getConnection();
            transaction = session.beginTransaction();

            // Save the student entity
            session.save(student);

            // Commit the transaction
            transaction.commit();

            System.out.println("Student saved successfully: " + student.getRegNo());
        } catch (Exception e) {
            // Rollback the transaction in case of any error
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error saving student: " + e.getMessage());
        } finally {
            // Close the session
            connectionController.closeConnection(session);
        }
    }
}
