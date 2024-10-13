package test;


import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.registration.config.HibernateUtil;
import org.registration.controller.StudentCourseController;
import org.registration.model.Course;
import org.registration.model.Student;
import org.registration.model.StudentCourse;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestForStudentCourse {

    private StudentCourseController studentCourseController;
    private Session session;

    @Before
    public void setUp() throws Exception {
        studentCourseController = new StudentCourseController();
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @After
    public void tearDown() throws Exception {
        if (session != null) {
            session.close();
        }
    }

    @Test
    public void testRegisterStudentInCourse() {
        // Assuming you have methods to get the Student and Course instances
        Student student1 = session.get(Student.class, 25407); // BYIRINGIRO Christian
        Student student2 = session.get(Student.class, 25447); // UWERA Gentille

        Course course1 = session.get(Course.class, "CSOC212"); // Software Security
        Course course2 = session.get(Course.class, "CSOC213"); // Software Testing (make sure to set this code in your Course entity)

        // Registering students in their respective courses
        studentCourseController.registerStudentInCourse(student1, course1, 4);
        studentCourseController.registerStudentInCourse(student2, course2, 4);

        // Verifying registration
        StudentCourse registeredCourse1 = session.createQuery(
                "FROM StudentCourse sc WHERE sc.student = :student AND sc.course = :course", StudentCourse.class)
                .setParameter("student", student1)
                .setParameter("course", course1)
                .uniqueResult();

        StudentCourse registeredCourse2 = session.createQuery(
                "FROM StudentCourse sc WHERE sc.student = :student AND sc.course = :course", StudentCourse.class)
                .setParameter("student", student2)
                .setParameter("course", course2)
                .uniqueResult();

        assertNotNull(registeredCourse1);
        assertEquals(Integer.valueOf(4), registeredCourse1.getCredits());
        assertNotNull(registeredCourse2);
        assertEquals(Integer.valueOf(4), registeredCourse2.getCredits());
    }
}
