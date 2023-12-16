package org.tester;


import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.client.config.RequestConfig;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.utils.jsonreader;
import pojo.petStore.petStore;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ApiTester extends BaseTester {
    public static String api_endpoint;
    public static String api_Uri;
    public static String api_DeleteEndpoint;
    public static Consumer<String> testRunnerLogger;
    public static Response response;

    private void log2TestRunner(String msg) {
        testRunnerLogger.accept(msg);
    }

    public ApiTester() {
        try {
            api_endpoint = jsonreader.getValueFromEnvParams("api/endpoint");
            api_DeleteEndpoint = jsonreader.getValueFromEnvParams("api/deleteEndpoint");
            api_Uri = jsonreader.getValueFromEnvParams("api/url");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void userSetupPetstoreOrder(Map<String, String> datalist) {
        petStore petstore_request = new petStore();
        try {
            // Set connection timeout
            RestAssured.config = RestAssured.config().httpClient(
                    RestAssured.config().getHttpClientConfig().setParam("http.connection.timeout", 5000)
            );

            // Set socket timeout
            RestAssured.config = RestAssured.config().httpClient(
                    RestAssured.config().getHttpClientConfig().setParam("http.socket.timeout", 5000)
            );
            petstore_request.setId(Integer.parseInt(datalist.get("id")));
            petstore_request.setPetId(Integer.parseInt(datalist.get("petId")));
            petstore_request.setComplete(Boolean.parseBoolean(datalist.get("complete")));
            petstore_request.setQuantity(Integer.parseInt(datalist.get("quantity")));
            petstore_request.setShipDate(datalist.get("shipDate"));
            petstore_request.setStatus(datalist.get("status"));
            RestAssured.baseURI = api_Uri;
            RequestSpecification httpRequest = RestAssured.given();
            httpRequest.header("Content-Type", "application/json");
            httpRequest.body(petstore_request);
            httpRequest.config(RestAssured.config);
            response = httpRequest.post(api_endpoint);
            log2TestRunner("Status Code for this API Call is : " + response.getStatusCode());
            log2TestRunner("Response Body is: " + response.getBody().prettyPrint());


        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("user Setup Pet store Order\n\t\t"+e.getMessage());
        }
    }

    public void validatethePetStoreOrderDetail(Map<String, String> datalist) {
        try {
            log2TestRunner("Expected ID :" + datalist.get("id") + " Actual Id:" + response.getBody().jsonPath().get("id"));
            Assert.assertEquals("ID Mismatch", datalist.get("id"), response.getBody().jsonPath().get("id").toString());
            log2TestRunner("Expected Pet ID :" + datalist.get("petId") + " Actual Id:" + response.getBody().jsonPath().get("petId"));
            Assert.assertEquals("Pet ID Mismatch", datalist.get("petId"), response.getBody().jsonPath().get("petId").toString());
        } catch (Exception e) {
            e.fillInStackTrace();
            Assert.fail("validate the Pet Store Order Detail failed\n"+e.getMessage());
        }
    }

    public void exam() {
        try {
//            driver.get(getValueFromEnvParams("data_UI/url2"));
//            driver.findElement(By.id("search-input")).sendKeys("port");
//            driver.findElement(By.id("search-button")).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
            List<WebElement> res = driver.findElements(By.cssSelector("#search-results"));
            WebElement res1 = driver.findElement(By.cssSelector("#search-results"));

//            Thread.sleep(2000);
            WebElement z = res1.findElement(By.id(""));
            List<WebElement> zx = res1.findElements(By.id(""));
            Assert.assertEquals("Port Royal", res.get(2).getText().trim());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("exam method failed\n" + e.getMessage());
        }

    }

    public void userDeleteTheOrder(Map<String, String> datalist) {
        try {
            RestAssured.config = RestAssured.config().httpClient(
                    RestAssured.config().getHttpClientConfig().setParam("http.connection.timeout", 5000)
            );

            // Set socket timeout
            RestAssured.config = RestAssured.config().httpClient(
                    RestAssured.config().getHttpClientConfig().setParam("http.socket.timeout", 5000)
            );
            RestAssured.baseURI = api_Uri;
            RequestSpecification httpRequest = RestAssured.given();
            httpRequest.config(RestAssured.config);
            response = httpRequest.delete(api_DeleteEndpoint);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("user Delete The Order method failed\n\t\t"+e.getMessage());
        }
    }
}
