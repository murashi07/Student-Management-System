package test;


import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.registration.controller.AcademicUnitController;
import org.registration.model.AcademicUnit;
import org.registration.model.EAcademicUnit;
import org.registration.util.HibernateUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestForAcademicUnit {

    private AcademicUnitController academicUnitController;
    private Session session;

    @Before
    public void setUp() throws Exception {
        // Initialize the AcademicUnitController
        academicUnitController = new AcademicUnitController();
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
    public void testSaveAcademicUnit() {
        // Create new AcademicUnit instances for departments
        AcademicUnit softwareEngineering = new AcademicUnit();
        softwareEngineering.setName("Software Engineering");
        softwareEngineering.setType(EAcademicUnit.DEPARTMENT); // Use DEPARTMENT as per your enum

        AcademicUnit networkingAdministration = new AcademicUnit();
        networkingAdministration.setName("Networking Administration");
        networkingAdministration.setType(EAcademicUnit.DEPARTMENT); // Use DEPARTMENT as per your enum

        AcademicUnit informationManagement = new AcademicUnit();
        informationManagement.setName("Information Management");
        informationManagement.setType(EAcademicUnit.DEPARTMENT); // Use DEPARTMENT as per your enum

        // Save the academic units using the AcademicUnitController
        academicUnitController.saveAcademicUnit(softwareEngineering);
        academicUnitController.saveAcademicUnit(networkingAdministration);
        academicUnitController.saveAcademicUnit(informationManagement);

        // Fetch the academic units from the database to check if they were saved correctly
        AcademicUnit savedSoftwareEngineering = session.get(AcademicUnit.class, softwareEngineering.getId());
        AcademicUnit savedNetworkingAdministration = session.get(AcademicUnit.class, networkingAdministration.getId());
        AcademicUnit savedInformationManagement = session.get(AcademicUnit.class, informationManagement.getId());

        // Assert that the saved units are not null, meaning they were saved
        assertNotNull(savedSoftwareEngineering);
        assertNotNull(savedNetworkingAdministration);
        assertNotNull(savedInformationManagement);

        // Assert the names of the saved academic units
        assertEquals("Software Engineering", savedSoftwareEngineering.getName());
        assertEquals("Networking Administration", savedNetworkingAdministration.getName());
        assertEquals("Information Management", savedInformationManagement.getName());

        // Assert the type of the saved academic units
        assertEquals(EAcademicUnit.DEPARTMENT, savedSoftwareEngineering.getType());
        assertEquals(EAcademicUnit.DEPARTMENT, savedNetworkingAdministration.getType());
        assertEquals(EAcademicUnit.DEPARTMENT, savedInformationManagement.getType());
    }
}
