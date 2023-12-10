package org.stepDefinitions;

import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.tester.LoginTester;

public class loginStepdefinition {
    LoginTester loginTester = new LoginTester();
    private static Scenario scenario;

    @BeforeStep
    public void beforestep(Scenario lscenario) {
        scenario = lscenario;
    }
    public loginStepdefinition() {
        LoginTester.testRunnerLogger = loginStepdefinition::cukelogger;
    }
    private static void cukelogger(String msg) {
        scenario.log(msg);
    }

    @Then("User put his {string} and {string}")
    public void userPutusernameAndpassword(String username, String password) {
        loginTester.logindetailinsert(username, password);
    }

    @Given("User open the {string} browser")
    public void userOpenTheBrowser(String browsername) {
        loginTester.openbrowser(browsername);
    }

    @Then("user click on login button")
    public void userClickOnLoginButton() {
        loginTester.click();
    }
}
