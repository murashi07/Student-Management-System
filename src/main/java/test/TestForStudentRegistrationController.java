package test;


import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.registration.controller.StudentRegistrationController;
import org.registration.model.Semester;
import org.registration.model.Student;
import org.registration.util.HibernateUtil;


import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class TestForStudentRegistrationController {

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
    public void testGetStudentsBySemester() {
        // Retrieve the existing semester object from the database
        Semester semester = session.get(Semester.class, "SEM2024"); // Assuming the semester ID is "SEM2024"

        // Ensure the semester exists
        assertNotNull("Semester should not be null", semester);

        // Fetch students registered in the specified semester
        List<Student> students = studentRegistrationController.getStudentsBySemester(semester);

        // Assert that we have a list of students
        assertNotNull(students);
        assertFalse("Student list should not be empty", students.isEmpty());

        // Print out the students for visibility in test output
        for (Student student : students) {
            System.out.println("Student ID: " + student.getRegNo() + ", Name: " + student.getFirstName());
        }
    }
}
