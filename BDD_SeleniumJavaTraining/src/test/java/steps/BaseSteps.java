package steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseSteps {


    WebDriver driver;

    BaseSteps() {
        this.driver = Hooks.getDriver();
    }
}
