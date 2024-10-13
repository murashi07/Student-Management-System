package test;


import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.registration.controller.CourseController;
import org.registration.model.Course;
import org.registration.model.CourseDefinition;
import org.registration.util.HibernateUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestForCourse {

    private CourseController courseController;
    private Session session;

    @Before
    public void setUp() throws Exception {
        courseController = new CourseController();
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @After
    public void tearDown() throws Exception {
        if (session != null) {
            session.close();
        }
    }

    @Test
    public void testSaveCourses() {
        // Array of course definitions to save
        String[][] coursesToSave = {
            {"CSOC212", "Software Security"},
            {"SENG101", "Software Testing"},
            {"NETADMIN", "Network Administration"},
            {"INFOSEC", "Information Security"},
            {"PEN_TEST", "Penetration Testing"}
        };

        // Loop through the array to save each course
        for (String[] courseData : coursesToSave) {
            String courseCode = courseData[0];
            String courseName = courseData[1];

            // Assume CourseDefinition has already been saved
            CourseDefinition courseDefinition = session.get(CourseDefinition.class, courseCode);
            assertNotNull(courseDefinition); // Ensure that the CourseDefinition exists

            // Create a new Course instance
            Course course = new Course();
            course.setCode(courseCode); // Set the course code
            course.setName(courseName); // Set the course name
            course.setDefinition(courseDefinition); // Associate with CourseDefinition

            // Save the course
            courseController.saveCourse(course);

            // Fetching and asserting the saved Course
            Course savedCourse = session.get(Course.class, courseCode);
            assertNotNull(savedCourse);
            assertEquals(courseCode, savedCourse.getCode());
            assertEquals(courseName, savedCourse.getName());
            assertNotNull(savedCourse.getDefinition());
            assertEquals(courseCode, savedCourse.getDefinition().getCode());
        }
    }
}
