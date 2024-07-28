package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    By productText = By.xpath("//*[text()='Products']");

    public String getActualProductText() {
        String actualProductText = driver.findElement(productText).getText().trim();
        return actualProductText;
    }
}
