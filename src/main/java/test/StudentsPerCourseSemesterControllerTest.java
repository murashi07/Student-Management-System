package test;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.registration.controller.StudentsPerCourseSemesterController;
import org.registration.model.Student;
import org.registration.util.HibernateUtil;


import java.util.List;

import static org.junit.Assert.assertNotNull;

public class StudentsPerCourseSemesterControllerTest {

    private StudentsPerCourseSemesterController studentsPerCourseSemesterController;
    private Session session;

    @Before
    public void setUp() throws Exception {
        studentsPerCourseSemesterController = new StudentsPerCourseSemesterController();
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @After
    public void tearDown() throws Exception {
        if (session != null) {
            session.close();
        }
    }

    @Test
    public void testGetStudentsByCourseAndSemester() {
        String courseCode = "CSOC212";  // Course code for Software Security
        String semesterName = "Spring 2024";  // Semester name

        // Fetch the students for the specified course and semester
        List<Student> students = studentsPerCourseSemesterController.getStudentsByCourseAndSemester(courseCode, semesterName);

        assertNotNull("Student list should not be null", students);
        
        // Optionally check that the list is not empty
        if (!students.isEmpty()) {
            System.out.println("Students enrolled in course " + courseCode + " during " + semesterName + ":");
            for (Student student : students) {
                System.out.println("Student ID: " + student.getRegNo() + ", Name: " + student.getFirstName());
            }
        } else {
            System.out.println("No students found for course " + courseCode + " during " + semesterName);
        }
        
        // Optionally assert specific conditions, like expected student count
        // assertEquals("Expected student count mismatch", expectedStudentCount, students.size());
    }
}
