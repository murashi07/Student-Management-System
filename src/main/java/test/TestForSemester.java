package test;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.registration.controller.SemesterController;
import org.registration.model.Semester;
import org.registration.util.HibernateUtil;

import static org.junit.Assert.assertEquals;


import java.time.LocalDate;

import static org.junit.Assert.assertNotNull;

public class TestForSemester {

    private SemesterController semesterController;
    private Session session;

    @Before
    public void setUp() throws Exception {
        // Initialize the SemesterController
        semesterController = new SemesterController();
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
    public void testSaveSemester() {
        // Create a new Semester instance
        Semester semester = new Semester();
        semester.setId("SEM2024");  // Example ID
        semester.setName("Spring 2024");
        semester.setStartDate(LocalDate.of(2024, 1, 1));
        semester.setEndDate(LocalDate.of(2024, 6, 30));

        // Save the semester using the SemesterController
        semesterController.saveSemester(semester);

        // Fetch the semester from the database to check if it was saved correctly
        Semester savedSemester = session.get(Semester.class, "SEM2024");

        // Assert that the savedSemester is not null, meaning it was saved
        assertNotNull(savedSemester);

        // Assert the semester ID
        assertEquals("SEM2024", savedSemester.getId());

        // Assert the semester name
        assertEquals("Spring 2024", savedSemester.getName());

        // Assert the start and end dates
        assertEquals(LocalDate.of(2024, 1, 1), savedSemester.getStartDate());
        assertEquals(LocalDate.of(2024, 6, 30), savedSemester.getEndDate());
    }
}
