package test;


import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.registration.controller.StudentQueryController;
import org.registration.model.Student;
import org.registration.util.HibernateUtil;


import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class StudentQueryControllerTest {

    private StudentQueryController studentQueryController;
    private Session session;

    @Before
    public void setUp() throws Exception {
        studentQueryController = new StudentQueryController();
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @After
    public void tearDown() throws Exception {
        if (session != null) {
            session.close();
        }
    }

    @Test
    public void testGetStudentsBySemesterAndDepartment() {
        String semesterName = "Spring 2024";  // Actual semester name
        String academicUnitName = "Software Engineering";  // Actual academic unit name

        // Assuming the semester and academic unit retrieval logic has been adjusted accordingly
        List<Student> students = studentQueryController.getStudentsBySemesterAndDepartment(semesterName, academicUnitName);

        assertNotNull("Student list should not be null", students);
        assertFalse("Student list should not be empty", students.isEmpty());

        for (Student student : students) {
            System.out.println("Student ID: " + student.getRegNo() + ", Name: " + student.getFirstName());
        }
    }
}
