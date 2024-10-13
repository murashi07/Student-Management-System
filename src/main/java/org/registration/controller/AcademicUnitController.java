package org.registration.controller;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.registration.model.AcademicUnit;

public class AcademicUnitController {

    private ConnectionController connectionController;

    public AcademicUnitController() {
        this.connectionController = new ConnectionController();
    }

    /**
     * Saves an AcademicUnit entity to the database.
     *
     * @param academicUnit The AcademicUnit object to be saved.
     */
    public void saveAcademicUnit(AcademicUnit academicUnit) {
        Session session = null;
        Transaction transaction = null;

        try {
            // Get a session and begin transaction
            session = connectionController.getConnection();
            transaction = session.beginTransaction();

            // Save the academic unit entity
            session.save(academicUnit);

            // Commit the transaction
            transaction.commit();

            System.out.println("Academic Unit saved successfully: " + academicUnit.getName());
        } catch (Exception e) {
            // Rollback the transaction in case of any error
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error saving academic unit: " + e.getMessage());
        } finally {
            // Close the session
            connectionController.closeConnection(session);
        }
    }
}
