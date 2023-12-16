package org.tester;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.utils.jsonreader;
import pojo.petStore.petStore;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ApiTester extends BaseTester {
    public static String api_endpoint;

    public ApiTester() {
        try {
            api_endpoint = jsonreader.getValueFromEnvParams("api/endpoint");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void userSetupPetstoreOrder(Map<String, String> datalist) {
        petStore petstore_request = new petStore();
        try {
            petstore_request.setId(Integer.parseInt(datalist.get("id")));
            petstore_request.setPetId(Integer.parseInt(datalist.get("petId")));
            petstore_request.setComplete(Boolean.parseBoolean(datalist.get("complete")));
            petstore_request.setQuantity(Integer.parseInt(datalist.get("quantity")));
            petstore_request.setShipDate(datalist.get("shipDate"));
            petstore_request.setStatus(datalist.get("status"));
            RestAssured.baseURI = "https://petstore.swagger.io";


            RequestSpecification httpRequest = RestAssured.given();
            httpRequest.header("Content-Type", "application/json");
            httpRequest.body(petstore_request);
            Response response = httpRequest.post(api_endpoint);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody().jsonPath().get("id").toString());
            System.out.println(response.getBody());

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("user Setup Pet store Order");
        }
    }

    public void validatethePetStoreOrderDetail(Map<String, String> datalist) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("validate the Pet Store Order Detail failed");
        }
    }

    public void exam() throws InterruptedException {
        driver.get("https://codility-frontend-prod.s3.amazonaws.com/media/task_static/qa_search/6f03f4361b080eeb747193aadd94cd2b/static/attachments/reference_page.html");
        driver.findElement(By.id("search-input")).sendKeys("port");
        driver.findElement(By.id("search-button")).click();
        List<WebElement> res = driver.findElements(By.cssSelector("#search-results"));
        Thread.sleep(5000);
        Assert.assertEquals("Port Royal", res.get(0).getText().trim());
    }
}
