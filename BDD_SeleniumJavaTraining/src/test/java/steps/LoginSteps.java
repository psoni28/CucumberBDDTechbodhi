package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class LoginSteps {

    WebDriver driver = new ChromeDriver();


    @Given("User is redirect to login")
    public void userRedirectToLoginPage() {
        driver.get("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @When("User enter username and password")
    public void userEnterCredentials() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
    }

    @When("User click on login button")
    public void userClickButton() {
        driver.findElement(By.id("login-button")).click();
    }

    @When("User enter credentials from data table")
    public void enterCredentialsFromDataTable(DataTable credentials) {
        List<List<String>> data = credentials.asLists(String.class);
        String username = data.get(0).get(0);
        String password = data.get(0).get(1);
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
    }


    @When("User enter credentials from data table with map")
    public void enterCredentialsFromDataTableWithMap(DataTable credentials) {
        List<Map<String, String>> data = credentials.asMaps(String.class, String.class);
        String user = data.get(0).get("username");
        String pass = data.get(0).get("password");
        driver.findElement(By.id("user-name")).sendKeys(user);
        driver.findElement(By.id("password")).sendKeys(pass);
    }


    @When("User enter {string} and {string} credentials")
    public void userEnterCredetials(String username, String password) {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @Then("User validate error message on login page")
    public void validateErrorMessage() {
        boolean actual = driver.findElement(By.className("error-button")).isDisplayed();
        Assert.assertTrue(actual);
        driver.quit();
    }

    @Then("User should login successfully")
    public void validateLoginSuccessful() {
        String actualtext = driver.findElement(By.xpath("//*[text()='Products']")).getText().trim();
        Assert.assertEquals("Products", actualtext);
    }


}
