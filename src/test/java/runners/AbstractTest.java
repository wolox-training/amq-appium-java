package runners;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import utils.MobileManagement;

public abstract class AbstractTest {

    @BeforeClass
    public static void init() {
        MobileManagement.startServer();
    }

    @AfterClass
    public static void finish() {
        MobileManagement.stopServer();
    }
}
