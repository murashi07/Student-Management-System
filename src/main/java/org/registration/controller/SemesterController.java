package org.registration.controller;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.registration.model.Semester;

public class SemesterController {

    private ConnectionController connectionController;

    public SemesterController() {
        this.connectionController = new ConnectionController();
    }

    /**
     * Saves a Semester entity to the database.
     *
     * @param semester The Semester object to be saved.
     */
    public void saveSemester(Semester semester) {
        Session session = null;
        Transaction transaction = null;

        try {
            // Get a session and begin transaction
            session = connectionController.getConnection();
            transaction = session.beginTransaction();

            // Save the semester entity
            session.save(semester);

            // Commit the transaction
            transaction.commit();

            System.out.println("Semester saved successfully: " + semester.getName());
        } catch (Exception e) {
            // Rollback the transaction in case of any error
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error saving semester: " + e.getMessage());
        } finally {
            // Close the session
            connectionController.closeConnection(session);
        }
    }
}
