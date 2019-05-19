package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class WebDriverManager {

	private WebDriverManager() {
	}

	public static WebDriver driver = null;

	static {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
	}

	public static WebDriver getDriver() {
		if (driver == null) {
			driver = setUp();
		}
		return driver;
	}

	public static WebDriver setUp() {
		WebDriver chromeDriver = new ChromeDriver();
		chromeDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		chromeDriver.get("https://mail.google.com/");
		return chromeDriver;
	}

	public static void quit(){
		driver.quit();
	}

}
