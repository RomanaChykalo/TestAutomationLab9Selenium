package pages;

import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected DriverFactory factory;

    public BasePage() {
        PageFactory.initElements(factory.getInstance().getDriver(), this);
    }
}