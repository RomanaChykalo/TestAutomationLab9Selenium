package data;

import org.testng.annotations.DataProvider;

public class DataProvidersSet {
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

    @DataProvider(name = "recipients", parallel = true)
    public Object[][] recipients() {
        return new Object[][]{
                {"tt6549624@gmail.com", "Subject 1", "This is test mail #1"},
                {"tt6549625@gmail.com", "Subject 1", "This is test mail #1"},
                {"tt6549626@gmail.com", "Subject 1", "This is test mail #1"},
                {"tt6549627@gmail.com", "Subject 1", "This is test mail #1"},
                {"tt6549629@gmail.com", "Subject 1", "This is test mail #1"},

                {"tt6549624@gmail.com", "Subject 2", "This is test mail #2"},
                {"tt6549625@gmail.com", "Subject 2", "This is test mail #2"},
                {"tt6549626@gmail.com", "Subject 2", "This is test mail #2"},
                {"tt6549627@gmail.com", "Subject 2", "This is test mail #2"},
                {"tt6549629@gmail.com", "Subject 2", "This is test mail #2"},

                {"tt6549624@gmail.com", "Subject 3", "This is test mail #3"},
                {"tt6549625@gmail.com", "Subject 3", "This is test mail #3"},
                {"tt6549626@gmail.com", "Subject 3", "This is test mail #3"},
                {"tt6549627@gmail.com", "Subject 3", "This is test mail #3"},
                {"tt6549629@gmail.com", "Subject 3", "This is test mail #3"}
        };
    }

    @DataProvider(name = "recipientsTest", parallel = true)
    public Object[][] recipientsTest() {
        return new Object[][]{
                {"tt6549624@gmail.com", "Subject 1", "This is test mail #1"}
        };
    }
}
