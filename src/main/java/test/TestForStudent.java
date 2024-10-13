package test;


import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.registration.config.HibernateUtil;
import org.registration.controller.StudentController;
import org.registration.model.AcademicUnit;
import org.registration.model.Student;


import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestForStudent {

    private StudentController studentController;
    private Session session;

    @Before
    public void setUp() throws Exception {
        // Initialize the StudentController
        studentController = new StudentController();
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
    public void testSaveStudent() {
        // Create a new Student instance
        Student student = new Student();
        student.setRegNo("25409");
        student.setFirstName("DUSHIME Brother");
        student.setDateOfBirth(LocalDate.of(2001, 01, 02));

        // Create a mock AcademicUnit (can be an existing one from the database)
        AcademicUnit academicUnit = new AcademicUnit();
        academicUnit.setName("Software Engineering");

        // Associate the student with the academic unit
        student.setAcademicUnit(academicUnit);

        // Save the academic unit to the session before saving the student
        session.beginTransaction();
        session.save(academicUnit);
        session.getTransaction().commit();

        // Save the student using the StudentController
        studentController.saveStudent(student);

        // Fetch the student from the database to check if it was saved correctly
        Student savedStudent = session.get(Student.class, "25409");

        // Assert that the savedStudent is not null, meaning it was saved
        assertNotNull(savedStudent);

        // Assert the student registration number
        assertEquals("25409", savedStudent.getRegNo());

        // Assert the student first name
        assertEquals("DUSHIME Brother", savedStudent.getFirstName());

        // Assert the student date of birth
        assertEquals(LocalDate.of(2001, 01, 02), savedStudent.getDateOfBirth());

        // Assert that the academic unit was correctly associated
        assertNotNull(savedStudent.getAcademicUnit());
        assertEquals("Software Engineering", savedStudent.getAcademicUnit().getName());

        // Additional check: ensure the academic unit is the same one that was saved
        assertEquals(academicUnit.getId(), savedStudent.getAcademicUnit().getId());
    }
}
