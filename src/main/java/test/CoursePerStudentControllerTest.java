package test;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.registration.config.HibernateUtil;
import org.registration.controller.CoursePerStudentController;
import org.registration.model.Course;


import java.util.List;

import static org.junit.Assert.assertNotNull;

public class CoursePerStudentControllerTest {

    private CoursePerStudentController coursePerStudentController;
    private Session session;

    @Before
    public void setUp() throws Exception {
        coursePerStudentController = new CoursePerStudentController();
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @After
    public void tearDown() throws Exception {
        if (session != null) {
            session.close();
        }
    }

    @Test
    public void testGetCoursesByStudent() {
        String studentRegNo = "25407";  // Registration number of BYIRINGIRO Christian

        // Fetch the courses for the specified student
        List<Course> courses = coursePerStudentController.getCoursesByStudent(studentRegNo);

        assertNotNull("Course list should not be null", courses);
        
        // Optionally check that the list is not empty
        if (!courses.isEmpty()) {
            System.out.println("Courses for student with registration number " + studentRegNo + ":");
            for (Course course : courses) {
                System.out.println("Course Code: " + course.getCode() + ", Course Name: " + course.getName());
            }
        } else {
            System.out.println("No courses found for student with registration number " + studentRegNo);
        }
        
        // Verify that the courses match expected results (if you know what courses to expect)
        // Uncomment the following line if you know the expected course names or codes
        // assertEquals("Expected course count mismatch", expectedCourseCount, courses.size());
    }
}
