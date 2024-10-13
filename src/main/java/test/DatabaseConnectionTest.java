package test;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.registration.controller.ConnectionController;

import static org.junit.Assert.assertNotNull;

public class DatabaseConnectionTest {

    private ConnectionController connectionController;
    private Session session;

    @Before
    public void setUp() throws Exception {
        // Initialize ConnectionController before each test
        connectionController = new ConnectionController();
    }

    @After
    public void tearDown() throws Exception {
        // Close session after each test
        if (session != null && session.isOpen()) {
            connectionController.closeConnection(session);
        }
    }

    @Test
    public void testDatabaseConnection() {
        // Test establishing a database connection
        session = connectionController.getConnection();
        assertNotNull("The session should not be null", session);  // Assert that the session is not null
        System.out.println("Database connection established and tested successfully.");
    }
}
