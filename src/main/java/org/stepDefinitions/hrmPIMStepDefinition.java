package org.stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.tester.PimTester;

public class hrmPIMStepDefinition {
    private static Scenario scenario;

    @BeforeStep
    public void beforestep(Scenario lscenario) {
        scenario = lscenario;
    }

    PimTester pimTester = new PimTester();

    public hrmPIMStepDefinition() {
        PimTester.testRunnerLogger = hrmPIMStepDefinition::cukelogger;
    }

    private static void cukelogger(String msg) {
        scenario.log(msg);
    }

    @Given("User click on {string} Module")
    public void userClickOnModule(String module) {
        pimTester.userClickOnModule(module);
    }

    @Then("User put below employee details and add the new employee")
    public void userPutBelowEmployeeDetailsAndAddThEnewEmployee(DataTable datatable) {
        pimTester.userPutEmployeeDetailsAndAddThEnewEmployee(datatable.asMaps().get(0));
    }

    @Then("user click {string} on Top Bar Menu")
    public void userClickOnTopBarMenu(String topbarmenu) {
        pimTester.userClickOnTopBarMenu(topbarmenu);
    }

    @And("User Search for created employee")
    public void userSearchForCreatedEmployee(DataTable dataTable) {
        pimTester.userSearchForCreatedEmployee(dataTable.asMaps().get(0));
    }

    @And("user click on {string} button")
    public void userClickOnButton(String button) {
        pimTester.userClickOnButton(button);
    }

    @Then("User Finds the searched employee in record table & click on checkbox and Trash button")
    public void userFindsTheSearchedEmployeeInRecordTable(DataTable dataTable) {
        pimTester.userFindsTheSearchedEmployeeInRecordTable(dataTable.asMaps().get(0));
    }

    @And("user click on checkbox and Trash button")
    public void userClickOnCheckboxAndTrashButton() {
        pimTester.userClickOnCheckboxAndTrashButton();
    }

    @Then("user click on {string}")
    public void userClickOn(String button) {
        pimTester.userclickOnPopUpConfirmation(button);
    }

    @And("user verify the success message")
    public void userVerifyTheSuccessMessage(DataTable dataTable) {
        pimTester.userVerifyTheSuccessMessage(dataTable.asMaps().get(0));
    }
}
