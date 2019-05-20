import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import util.readers.JsonReader;
import util.DriverManager;
import java.io.FileNotFoundException;

public class FunctionalTest {

    private JsonReader jsonReader = new JsonReader();
    private static Logger LOG = Logger.getLogger(FunctionalTest.class.getName());

    @BeforeMethod
    public void setURL() {
        DriverManager.getWebDriver().get("https://www.gmail.com");
        LOG.info("Web application launched");
        Assert.assertEquals(DriverManager.getWebDriver().getTitle() , "Gmail" );
    }

    @AfterMethod
    public void driverTearDown() {
        DriverManager.closeDriver();
        LOG.info("Browser closed");
    }

    @DataProvider(parallel = true)
    public Object[] user() throws FileNotFoundException {
        return jsonReader.getData();
    }
}
