package org.tester;


import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pojo.books.Books;
import pojo.petStore.petStore;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static org.stepDefinitions.apiStepDefinition.scenario;
import static org.utils.jsonreader.getValueFromEnvParams;

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
        // Set request timeout in millisecond
        RestAssured.config = RestAssured.config().httpClient(
                RestAssured.config().getHttpClientConfig().setParam("http.connection.timeout", 5000)
        );
        // Set socket timeout in millisecond
        RestAssured.config = RestAssured.config().httpClient(
                RestAssured.config().getHttpClientConfig().setParam("http.socket.timeout", 5000)
        );

    }

    public void ApiDetailSetUp() throws IOException {
        if (scenario.getName().contains("pets")) {
            api_endpoint = getValueFromEnvParams("petStore_Api/endpoint");
            api_DeleteEndpoint = getValueFromEnvParams("petStore_Api/deleteEndpoint");
            api_Uri = getValueFromEnvParams("petStore_Api/url");
        } else {
            api_endpoint = getValueFromEnvParams("bookStore_Api/endpoint");
            api_DeleteEndpoint = getValueFromEnvParams("bookStore_Api/deleteEndpoint");
            api_Uri = getValueFromEnvParams("bookStore_Api/url");
        }
    }

    public void userSetupPetstoreOrder(Map<String, String> datalist) {
        petStore petstore_request = new petStore();
        try {
            response = null;
            ApiDetailSetUp();
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
            Assert.fail("user Setup Pet store Order\n\t\t" + e.getMessage());
        }
    }

    public void validatethePetStoreOrderDetail(Map<String, String> datalist) {
        try {
            log2TestRunner("Expected ID :" + datalist.get("id") + " Actual Id:" + response.getBody().jsonPath().get("id"));
            Assert.assertEquals("ID Mismatch", datalist.get("id"), response.getBody().jsonPath().get("id").toString());
            log2TestRunner("Expected Pet ID :" + datalist.get("petId") + " Actual Id:" + response.getBody().jsonPath().get("petId"));
            Assert.assertEquals("Pet ID Mismatch", datalist.get("petId"), response.getBody().jsonPath().get("petId").toString());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("validate the Pet Store Order Detail failed\n" + e.getMessage());
        }
    }

    public void exam() {
        try {
            driver.get(getValueFromEnvParams("data_UI/url2"));
            driver.findElement(By.id("search-input")).sendKeys("port");
            driver.findElement(By.id("search-button")).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
            List<WebElement> res = driver.findElements(By.cssSelector("#search-results"));
            Assert.assertEquals("Port Royal", res.get(0).getText().trim());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("exam method failed\n" + e.getMessage());
        }

    }

    public void userDeleteTheOrder(Map<String, String> datalist) {
        try {
            ApiDetailSetUp();
            RestAssured.baseURI = api_Uri;
            RequestSpecification httpRequest = RestAssured.given();
            httpRequest.config(RestAssured.config);
            response = httpRequest.delete(api_DeleteEndpoint);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("user Delete The Order method failed\n\t\t" + e.getMessage());
        }
    }

    public void userCreateTheBookRequestBodyUsingBelowData(Map<String, String> datalist) {
        try {
            ApiDetailSetUp();
            Books book = new Books();
            book.setId(Integer.parseInt(datalist.get("id")));
            book.setDescription(datalist.get("description"));
            book.setExcerpt(datalist.get("excerpt"));
            book.setTitle(datalist.get("title"));
            book.setPageCount(Integer.parseInt(datalist.get("pageCount")));
            book.setPublishDate(datalist.get("publishDate"));

            RestAssured.baseURI = api_Uri;
            RequestSpecification httprequest = RestAssured.given();
            httprequest.config(RestAssured.config);
            httprequest.body(book);
            httprequest.header("Content-Type", "application/json");
            httprequest.header("accept", "*/*");
            response = httprequest.post(api_endpoint);
            log2TestRunner("Book creation Response: " + response.getBody().prettyPrint());
            Assert.assertEquals("Book creation Status Mismatch", 200, response.getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("user Create The Book Request Body method failed\n\t\t" + e.getMessage());
        }
    }

    public void userValidateTheBookCreationDetailWithBelowData(Map<String, String> datalist) {
        try {
            log2TestRunner("Book Expected ID: " + datalist.get("id") + " Actual ID: " + response.getBody().jsonPath().get("id"));
            log2TestRunner("Book Expected Description: " + datalist.get("description") + " Actual Description: " + response.getBody().jsonPath().get("description"));
            log2TestRunner("Book Expected Title: " + datalist.get("title") + " Actual Title: " + response.getBody().jsonPath().get("title"));
            log2TestRunner("Book Expected Excerpt: " + datalist.get("excerpt") + " Actual Excerpt: " + response.getBody().jsonPath().get("excerpt"));
            log2TestRunner("Book Expected PublishDate: " + datalist.get("publishDate") + " Actual PublishDate: " + response.getBody().jsonPath().get("publishDate"));
            log2TestRunner("Book Expected PageCount: " + datalist.get("pageCount") + " Actual PageCount: " + response.getBody().jsonPath().get("pageCount"));

            Assert.assertEquals("Book Mismatch ID: ", datalist.get("id"), response.getBody().jsonPath().get("id").toString());
            Assert.assertEquals("Book Mismatch Description: ", datalist.get("description"), response.getBody().jsonPath().get("description"));
            Assert.assertEquals("Book Mismatch Title: ", datalist.get("title"), response.getBody().jsonPath().get("title"));
            Assert.assertEquals("Book Mismatch Excerpt: ", datalist.get("excerpt"), response.getBody().jsonPath().get("excerpt"));
            Assert.assertEquals("Book Mismatch PublishDate: ", datalist.get("publishDate"), response.getBody().jsonPath().get("publishDate"));
            Assert.assertEquals("Book Mismatch PageCount: ", datalist.get("pageCount"), response.getBody().jsonPath().get("pageCount").toString());


        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("user Validate The Book Creation Method failed\n\t\t" + e.getMessage());
        }
    }

    public void userUpdateTheBookWithBelowDetails(Map<String, String> datalist) {
        try {
            ApiDetailSetUp();
            Books book = new Books();
            book.setId(Integer.parseInt(datalist.get("new_Id")));
            book.setDescription(datalist.get("description"));
            book.setExcerpt(datalist.get("excerpt"));
            book.setTitle(datalist.get("title"));
            book.setPageCount(Integer.parseInt(datalist.get("pageCount")));
            book.setPublishDate(datalist.get("publishDate"));

            RestAssured.baseURI = api_Uri;
            RequestSpecification httprequest = RestAssured.given();
            httprequest.config(RestAssured.config);
            httprequest.body(book);
            httprequest.header("Content-Type", "application/json");
            httprequest.header("accept", "*/*");
            response = httprequest.put(api_endpoint + "/" + datalist.get("id"));
            log2TestRunner("Book Update Response: " + response.getBody().prettyPrint());
            Assert.assertEquals("Book Update Status Mismatch", 200, response.getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("user Modify The Book Request Body method failed\n\t\t" + e.getMessage());
        }
    }

    public void userGetTheBook(String bookId, String Method) {
        try {
            ApiDetailSetUp();
            RestAssured.baseURI = api_Uri;
            if (Method.equalsIgnoreCase("get")) {
                response = RestAssured.get(api_endpoint + "/" + bookId);
            } else {
                response = RestAssured.delete(api_endpoint + "/" + bookId);
            }
            log2TestRunner("Book " + Method + " Response Code: " + response.getStatusCode());
            log2TestRunner("Book " + Method + " Response: " + response.getBody().prettyPrint());
            Assert.assertEquals("Book " + Method + " Status Mismatch", 200, response.getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("user Get The Book method failed\n\t\t" + e.getMessage());
        }
    }
}
