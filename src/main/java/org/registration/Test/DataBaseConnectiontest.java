package org.registration.Test;

import org.junit.Test;
import org.registration.connection.Connection;

import static org.junit.Assert.assertNotNull;

public class DataBaseConnectiontest {

    @Test
    public void test(){
        Connection connection = new Connection();
        assertNotNull(connection.establishConnection());
    }
}
