package test;


import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.registration.config.HibernateUtil;
import org.registration.controller.StudentRegistrationController;
import org.registration.model.Semester;
import org.registration.model.Student;
import org.registration.model.StudentRegistration;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestForStudentRegistration {

    private StudentRegistrationController studentRegistrationController;
    private Session session;

    @Before
    public void setUp() throws Exception {
        // Initialize the StudentRegistrationController
        studentRegistrationController = new StudentRegistrationController();
        // Open a session before the test
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @After
    public void tearDown() throws Exception {
        // Close the session after the test
        if (session != null) {
            session.close();
        }
    }

    @Test
    public void testSaveStudentRegistration() {
        // Fetch the existing student with regNo "25409"
        Student existingStudent = session.get(Student.class, "25409");
        assertNotNull("Student with regNo 25409 should exist", existingStudent);

        // Fetch the existing semester with id "SEM2024"
        Semester existingSemester = session.get(Semester.class, "SEM2024");
        assertNotNull("Semester with id SEM2024 should exist", existingSemester);

        // Create a new StudentRegistration instance
        StudentRegistration studentRegistration = new StudentRegistration();
        studentRegistration.setStudentId("25409");  // Using the existing student's regNo
        studentRegistration.setRegistrationDate(LocalDate.now()); // Set the registration date to now
        
        // Set the existing student and semester in the StudentRegistration
        studentRegistration.setStudent(existingStudent); 
        studentRegistration.setSemester(existingSemester); 

        // Save the student registration using the StudentRegistrationController
        studentRegistrationController.saveStudentRegistration(studentRegistration);

        // Fetch the student registration from the database to check if it was saved correctly
        StudentRegistration savedRegistration = session.get(StudentRegistration.class, "25409");

        // Assert that the savedRegistration is not null, meaning it was saved
        assertNotNull(savedRegistration);

        // Assert the student ID
        assertEquals("25409", savedRegistration.getStudentId());

        // Assert the registration date
        assertEquals(LocalDate.now().getDayOfMonth(), savedRegistration.getRegistrationDate().getDayOfMonth());
        assertEquals(LocalDate.now().getMonth(), savedRegistration.getRegistrationDate().getMonth());
        assertEquals(LocalDate.now().getYear(), savedRegistration.getRegistrationDate().getYear());

        // Assert the associated semester ID
        assertEquals("SEM2024", savedRegistration.getSemester().getId());
    }
}
