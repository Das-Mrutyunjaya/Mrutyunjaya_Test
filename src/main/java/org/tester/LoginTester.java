package org.tester;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Clock;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class LoginTester extends BaseTester {
    public static Consumer<String> testRunnerLogger;

    private void log2TestRunner(String msg) {
        testRunnerLogger.accept(msg);
    }

    public void logindetailinsert(String username, String password) {
        try {
            log2TestRunner("user put his user name and password correctly");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("login detail insert method failed");
        }
    }

    public void click() {
        try {
            log2TestRunner("login click method failed");
            Assert.fail("login click method failed");
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("login click method failed");
        }
    }

    public void openbrowser(String browsername) {
        try {

            switch (browsername) {
                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("-private");
//                    firefoxOptions.addArguments("-headless");

                    driver = new FirefoxDriver(firefoxOptions);
                    driver.manage().window().maximize();
                    break;
                case "edge":
                    EdgeDriverService edge_service = new EdgeDriverService.Builder().withSilent(true).build();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("start-maximized", "InPrivate", "version", "log-level=3");
                    driver = new EdgeDriver(edge_service, edgeOptions);
                    break;
                default:
                    ChromeDriverService service = new ChromeDriverService.Builder().withSilent(true).build();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("start-maximized", "incognito", "version", "log-level=3");
                    driver = new ChromeDriver(service, options);
                    break;
            }
//            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

//            WebDriverWait await = new WebDriverWait(driver, Duration.ofMillis(10000));
//            await.until(ExpectedConditions.visibilityOfElementLocated(By.id("")));
//
//
//            WebDriverWait wait = (WebDriverWait) new FluentWait(driver);
//            wait.ignoring(NoSuchElementException.class);
//            wait.pollingEvery(Duration.ofMillis(5000));
//            wait.withTimeout(Duration.ofMillis(5000));
//
//            Actions act = new Actions(driver);
//            WebElement x = null,y = null;
//            act.clickAndHold(x).moveToElement(y).release().build().perform();
//
//
//
//            driver.get("https://flipkart.com/");
            log2TestRunner("Browser opened Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("open broswer method failed");
        }
    }
}
