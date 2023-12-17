package org.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import static org.tester.BaseTester.driver;


public class driverUtility {
    static WebElement webElement;
    static List<WebElement> webElements;

    public static WebElement getElement(WebDriver driver, String page, String locatorName) {

        String path = "src/main/resources/object_repository/" + page + ".json";
        Map locator_map = jsonreader.elementLocatorReader(path, locatorName);
        String Method = (String) locator_map.get("Method");
        String Locator = (String) locator_map.get("Locator");
        return webElement = driver.findElement(byMethodSelection(Method, Locator));
    }

    public static List<WebElement> getElements(WebDriver driver, String page, String locatorName) {

        String path = "src/main/resources/object_repository/" + page + ".json";
        Map locator_map = jsonreader.elementLocatorReader(path, locatorName);
        String Method = (String) locator_map.get("Method");
        String Locator = (String) locator_map.get("Locator");
        return webElements = driver.findElements(byMethodSelection(Method, Locator));
    }

    public static WebElement getElement(WebElement webelement, String page, String locatorName) {

        String path = "src/main/resources/object_repository/" + page + ".json";
        Map locator_map = jsonreader.elementLocatorReader(path, locatorName);
        String Method = (String) locator_map.get("Method");
        String Locator = (String) locator_map.get("Locator");
        return webElement = webelement.findElement(byMethodSelection(Method, Locator));
    }

    public static List<WebElement> getElements(WebElement webelement, String page, String locatorName) {

        String path = "src/main/resources/object_repository/" + page + ".json";
        Map locator_map = jsonreader.elementLocatorReader(path, locatorName);
        String Method = (String) locator_map.get("Method");
        String Locator = (String) locator_map.get("Locator");
        return webElements = webelement.findElements(byMethodSelection(Method, Locator));
    }

    public static By byMethodSelection(String Method, String Locator) {
        return switch (Method) {
            case "css" -> By.cssSelector(Locator);
            case "id" -> By.id(Locator);
            case "name" -> By.name(Locator);
            case "class" -> By.className(Locator);
            case "xpath" -> By.xpath(Locator);
            case "tag" -> By.tagName(Locator);
            case "linkText" -> By.linkText(Locator);
            case "partialLinkText" -> By.partialLinkText(Locator);
            default -> null;
        };
    }


}
