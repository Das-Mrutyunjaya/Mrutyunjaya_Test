package org.stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.tester.ApiTester;

public class apiStepDefinition {
    ApiTester apiTester = new ApiTester();

    @Given("User set up the request body using below data")
    public void userSetUpTheRequestBodyUsingBelowData(DataTable dataTable) {
        apiTester.userSetupPetstoreOrder(dataTable.asMaps().get(0));

    }

    @Then("User validate the order deatil with below data")
    public void userValidateTheOrderDeatilWithBelowData(DataTable dataTable) {
        apiTester.validatethePetStoreOrderDetail(dataTable.asMaps().get(0));
    }

    @And("user check the inventory")
    public void userCheckTheInventory() throws InterruptedException {
        apiTester.exam();
    }
}
