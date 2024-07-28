package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.LoginPage;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class LoginStepsWithFactory extends BaseSteps {

    LoginPage loginPage = new LoginPage(driver);
    HomePage homePage = new HomePage(driver);


    @Given("User is redirect to login with factory")
    public void userRedirectToLoginPage() {
        loginPage.userRedirectToUrl("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @When("User enter username and password with factory")
    public void userEnterCredentials() {
        loginPage.userLogin("standard_user", "secret_sauce");
    }

    @When("User click on login button with factory")
    public void userClickButton() {
        loginPage.userClickLoginButton();
    }


    @When("User enter {string} and {string} credentials with factory")
    public void userEnterCredetials(String username, String password) {
        loginPage.userLogin(username, password);
    }

    @Then("User validate error message on login page with factory")
    public void validateErrorMessage() {
        boolean actual =loginPage.isErrorDisplayed();
        Assert.assertTrue(actual);
    }

}
