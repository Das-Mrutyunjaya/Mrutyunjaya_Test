package org.stepDefinitions;


import io.cucumber.core.gherkin.Step;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.plugin.event.PickleStepTestStep;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tester.BaseTester;


public class cucuHooks extends BaseTester {

    private static final Logger LOG = LoggerFactory.getLogger(cucuHooks.class);
    private static int pixcount = 0;

    @AfterStep
    public void afterStep(Scenario scenario) {
        System.out.println("<=========================== End of the Step of : " + scenario.getLine() + " ===========================>");
        try {
            if (driver != null) {
                final byte[] source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(source, "image/png", "Cucumber7x" + pixcount);
                pixcount++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("After scenario failed: " + scenario.getName());
        }
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        System.out.println("<=========================== start of the scenario: " + scenario.getName() + " ===========================>");
    }

    @After
    public void afterScenario(Scenario scenario) {
        System.out.println("<=========================== End of the scenario: " + scenario.getName() + " ===========================>");
        try {
            if (scenario.isFailed() && driver != null) {
                final byte[] source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(source, "image/png", "Cucumber7x" + pixcount);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("After scenario failed: " + scenario.getName());
        } finally {
            if (driver != null) {
                driver.quit();
                driver = null;
            }
        }
    }
}
