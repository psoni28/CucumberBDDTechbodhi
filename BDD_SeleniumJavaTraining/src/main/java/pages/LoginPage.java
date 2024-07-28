package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "user-name")
    WebElement username;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(xpath = "//*[@id='login-button']")
    WebElement loginButton;

    @FindBy(xpath = "//*[@class='error-button']")
    WebElement errorMsg;

    public void userLogin(String user, String pass) {
        username.sendKeys(user);
        password.sendKeys(pass);
    }

    public void userClickLoginButton() {
        loginButton.click();
    }

    public void userRedirectToUrl(String url) {
        driver.get(url);
    }

    public boolean isErrorDisplayed(
    ) {
        return errorMsg.isDisplayed();
    }

}
