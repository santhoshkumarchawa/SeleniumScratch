package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {
    private static WebDriver driver;

    public static WebDriver getDriver() throws MalformedURLException {
        if (driver == null) {
            String browser = ConfigReader.getProperty("browser");
            String gridUrl = ConfigReader.getProperty("grid.url");

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(browser);

            driver = new RemoteWebDriver(new URL(gridUrl), capabilities);
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
