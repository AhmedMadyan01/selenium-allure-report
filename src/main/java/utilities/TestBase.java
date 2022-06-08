package utilities;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

public class TestBase extends AbstractTestNGCucumberTests {
    private static WebDriver webDriver;

    @BeforeMethod
    public void setUpDriver() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
    }

    public static WebDriver getDriver() {
        assert webDriver != null;
        return webDriver;
    }

    @AfterMethod
    public void tearDownDriver() {
        webDriver.quit();
    }

    @BeforeSuite
    public void deleteOutDatedAllureReport() throws IOException {
        CLI.executeCommandLine("cd allure-results");
        CLI.executeCommandLine("DEL /S /Q *.json");
//        utilities.CMD.executeCommandLine("rmdir /Q /S allure-results");
//        utilities.CMD.executeCommandLine("mkdir allure-results");
//        utilities.CMD.executeCommandLine("copy environment.properties allure-results");
    }

    @AfterSuite
    public void generateAllureReport() throws IOException {
        CLI.executeCommandLine("allure serve allure-results");
    }
}