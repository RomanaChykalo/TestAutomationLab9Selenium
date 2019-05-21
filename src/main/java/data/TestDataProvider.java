package data;

import org.testng.annotations.DataProvider;
import utils.CSVParser;

import java.util.Iterator;

public class TestDataProvider {
    @DataProvider(name = "users", parallel = true)
    public Object[][] users() {
        return new Object[][]{
                {"tt6549624", "Qwerty123!"},
                {"tt6549625", "Qwerty123!"},
                {"tt6549626", "Qwerty123!"},
                {"tt6549627", "Qwerty123!"},
                {"tt6549629", "Qwerty123!"}
        };
    }

    @DataProvider(name = "recipientsMails", parallel = true)
    public Iterator<Object[]> recipientsTest() {
        return CSVParser.parseCSV("src/test/resources/testEmails.csv").iterator();
    }

}
