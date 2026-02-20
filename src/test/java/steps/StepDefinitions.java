package steps;

import io.qameta.allure.Step;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Attachment;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StepDefinitions {

    private WebDriver driver;

    @Step("Launch Chrome browser with version 122.0.6261.111")
    @Description("Initializes WebDriver and launches Chrome browser")
    @Severity(SeverityLevel.CRITICAL)
    @Given("I Launch Browser")
    public void i_launch_browser_given() throws MalformedURLException {
        System.out.println("Launching browser...");
        DesiredCapabilities caps=new DesiredCapabilities();
        caps.setBrowserName("chrome");
        //WebDriverManager.chromedriver().driverVersion("145.0.7632.76").setup();
        driver = new RemoteWebDriver(new URL("http://13.235.104.41:4444/"), caps);
    }

    @Step("Navigate to Google homepage")
    @Description("Opens https://www.google.com and maximizes the browser window")
    @Severity(SeverityLevel.NORMAL)
    @Then("I Navigate to google")
    public void i_navigate_to_google() throws InterruptedException {
        System.out.println("Navigating to Google...");
        driver.get("https://www.google.com");
        driver.findElement(By.id("APjFqb")).sendKeys("NAVEENA");
        driver.manage().window().maximize();
        Thread.sleep(3000);
        System.out.println("Google Homepage is Displayed Successfully");

        // Capture screenshot before quitting
        attachScreenshot(driver);

        driver.quit();
    }

    @Attachment(value = "Screenshot of Google Homepage", type = "image/png")
    public byte[] attachScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

}
