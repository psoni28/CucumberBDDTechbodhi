package steps;

import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import pages.HomePage;

public class HomeStepsWithFactory extends BaseSteps {

    HomePage homePage = new HomePage(driver);

    @Then("User should login successfully with factory")
    public void validateLoginSuccessful() {
        String actualtext = homePage.getActualProductText();
        Assert.assertEquals("Products", actualtext);
    }
}
