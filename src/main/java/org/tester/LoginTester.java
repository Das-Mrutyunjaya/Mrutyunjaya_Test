package org.tester;

import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


import java.util.function.Consumer;

import static org.utils.driverUtility.getElement;
import static org.utils.jsonreader.getValueFromEnvParams;

public class LoginTester extends BaseTester {
    public static Consumer<String> testRunnerLogger;
    public static String page = "hrmLogin";

    private void log2TestRunner(String msg) {
        testRunnerLogger.accept(msg);
    }

    public void logindetailinsert(String username, String password) {
        try {
            driver.get(getValueFromEnvParams("data_UI/url"));
            getElement(driver, page, "Username").sendKeys(username);
            getElement(driver, page, "Password").sendKeys(password);
            log2TestRunner("user put his " + username + " and " + password + " correctly");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("login detail insert method failed");
        }
    }

    public void click() {
        try {
            getElement(driver, page, "Login_Button").click();
            log2TestRunner("User click on login button");
            Thread.sleep(2000);
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
            log2TestRunner("Browser opened Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("open broswer method failed");
        }
    }

    public void pageValidate(String title) {
        try{
            log2TestRunner("PageTitle Expect: "+title+" Actual: "+driver.getTitle());
            Assert.assertEquals("PAge title mismatch",title,driver.getTitle());
        }catch (Exception e){
            e.printStackTrace();
            Assert.fail();
        }
    }
}
