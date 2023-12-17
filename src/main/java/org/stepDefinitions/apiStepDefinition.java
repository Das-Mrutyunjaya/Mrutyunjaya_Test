package org.stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.tester.ApiTester;

public class apiStepDefinition {
    ApiTester apiTester = new ApiTester();
    public static Scenario scenario;
    public apiStepDefinition() {
        ApiTester.testRunnerLogger = apiStepDefinition::cukelogger;
    }
    @BeforeStep
    public void beforestep(Scenario lscenario) {
        scenario = lscenario;
    }

    private static void cukelogger(String msg) {
        scenario.log(msg);
    }

    @Given("User set up the request body using below data")
    public void userSetUpTheRequestBodyUsingBelowData(DataTable dataTable) {
        apiTester.userSetupPetstoreOrder(dataTable.asMaps().get(0));

    }

    @Then("User validate the order detail with below data")
    public void userValidateTheOrderDeatilWithBelowData(DataTable dataTable) {
        apiTester.validatethePetStoreOrderDetail(dataTable.asMaps().get(0));
    }

    @And("user check the inventory")
    public void userCheckTheInventory() {
        apiTester.exam();
    }

    @Then("User Delete the Order detail with below data")
    public void userDeleteTheOrderDetailWithBelowData(DataTable dataTable) {
        apiTester.userDeleteTheOrder(dataTable.asMaps().get(0));
    }

    @Given("User Create the book request body using below data")
    public void userCreateTheBookRequestBodyUsingBelowData(DataTable datatable) {
        apiTester.userCreateTheBookRequestBodyUsingBelowData(datatable.asMaps().get(0));
    }

    @And("User validate the Book creation detail with below data")
    public void userValidateTheBookCreationDetailWithBelowData(DataTable dataTable) {
        apiTester.userValidateTheBookCreationDetailWithBelowData(dataTable.asMaps().get(0));
    }

    @Then("user update the Book with below details")
    public void userUpdateTheBookWithBelowDetails(DataTable dataTable) {
        apiTester.userUpdateTheBookWithBelowDetails(dataTable.asMaps().get(0));
    }

    @Then("User {string} the book using {string}")
    public void userGetTheBookUsing(String method,String bookId) {
        apiTester.userGetTheBook(bookId,method);
    }
}
