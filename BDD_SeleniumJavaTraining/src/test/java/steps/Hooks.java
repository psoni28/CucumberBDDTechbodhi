package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {
    static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    @Before
    public void beforeScenario() {
        driver = new ChromeDriver();
        System.out.println("-------------This will run before the Scenario----------------");
    }

    @After
    public void afterScenario() {
        driver.quit();
        System.out.println("-------------This will run after the Scenario------------------");
    }
}

