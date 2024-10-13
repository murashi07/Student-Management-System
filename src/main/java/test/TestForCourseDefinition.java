package test;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.registration.controller.CourseDefinitionController;
import org.registration.model.CourseDefinition;
import org.registration.util.HibernateUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestForCourseDefinition {

    private CourseDefinitionController courseDefinitionController;
    private Session session;

    @Before
    public void setUp() throws Exception {
        courseDefinitionController = new CourseDefinitionController();
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @After
    public void tearDown() throws Exception {
        if (session != null) {
            session.close();
        }
    }

    @Test
    public void testSaveCourseDefinition() {
        // Create new CourseDefinition instances
        CourseDefinition courseDefinition1 = new CourseDefinition();
        courseDefinition1.setCode("CSOC212");
        courseDefinition1.setName("Software Security");
        courseDefinition1.setDescription("Study of securing software applications.");

        CourseDefinition courseDefinition2 = new CourseDefinition();
        courseDefinition2.setCode("SENG101");
        courseDefinition2.setName("Software Testing");
        courseDefinition2.setDescription("Principles and practices of software testing.");

        CourseDefinition courseDefinition3 = new CourseDefinition();
        courseDefinition3.setCode("NETADMIN");
        courseDefinition3.setName("Network Administration");
        courseDefinition3.setDescription("Management of network systems.");

        CourseDefinition courseDefinition4 = new CourseDefinition();
        courseDefinition4.setCode("INFOSEC");
        courseDefinition4.setName("Information Security");
        courseDefinition4.setDescription("Protection of information systems.");

        CourseDefinition courseDefinition5 = new CourseDefinition();
        courseDefinition5.setCode("PENTEST");
        courseDefinition5.setName("Penetration Testing");
        courseDefinition5.setDescription("Ethical hacking and penetration testing techniques.");

        // Save CourseDefinitions
        courseDefinitionController.saveCourseDefinition(courseDefinition1);
        courseDefinitionController.saveCourseDefinition(courseDefinition2);
        courseDefinitionController.saveCourseDefinition(courseDefinition3);
        courseDefinitionController.saveCourseDefinition(courseDefinition4);
        courseDefinitionController.saveCourseDefinition(courseDefinition5);

        // Fetching and asserting one of the CourseDefinitions
        CourseDefinition savedCourseDefinition = session.get(CourseDefinition.class, "CSOC212");
        assertNotNull(savedCourseDefinition);
        assertEquals("CSOC212", savedCourseDefinition.getCode());
        assertEquals("Software Security", savedCourseDefinition.getName());
    }
}
