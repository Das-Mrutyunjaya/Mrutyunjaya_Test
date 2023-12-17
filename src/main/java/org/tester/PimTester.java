package org.tester;

import org.junit.Assert;
import org.openqa.selenium.WebElement;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static org.utils.driverUtility.*;

public class PimTester extends BaseTester {
    public static Consumer<String> testRunnerLogger;
    public static String page = "hrmPIM";

    private void log2TestRunner(String msg) {
        testRunnerLogger.accept(msg);
    }

    public void userClickOnModule(String module) {
        try {
            List<WebElement> openSideItems = getElements(driver, page, "SideNavItems");
            for (WebElement sideElement : openSideItems) {

                if (sideElement.getText().equalsIgnoreCase(module)) {
                    log2TestRunner("Clicked On " + sideElement.getText());
                    sideElement.click();
                    break;
                }
            }
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("user Click On Module Method failed");
        }
    }

    public void userPutEmployeeDetailsAndAddThEnewEmployee(Map<String, String> datalist) {
        try {
            List<WebElement> employeeDetails = getElements(driver, page, "EmployeeDetailForm");
            List<WebElement> statusButtons = new ArrayList<>();
            if (employeeDetails.size() == 5 && datalist.get("Create Login Details").equalsIgnoreCase("enable")) {
                getElement(driver, page, "EmployeeCreateLoginSwitch").click();
                statusButtons = getElements(driver, page, "EmployeeStatusRadioButton");
            }
            employeeDetails = getElements(driver, page, "EmployeeDetailForm");
            for (int i = 1; i < employeeDetails.size(); i++) {
                WebElement employeeDetail = employeeDetails.get(i);
                employeeDetail.clear();
                if (employeeDetail.getAttribute("placeholder").equalsIgnoreCase("first name")) {
                    employeeDetail.sendKeys(datalist.get("First Name"));
                } else if (employeeDetail.getAttribute("placeholder").equalsIgnoreCase("middle name")) {
                    employeeDetail.sendKeys(datalist.get("Middle Name"));
                } else if (employeeDetail.getAttribute("placeholder").equalsIgnoreCase("last name")) {
                    employeeDetail.sendKeys(datalist.get("Last Name"));
                } else if (i == 4) {
                    employeeDetail.sendKeys(datalist.get("Employee Id"));
                } else if (i == 5) {
                    employeeDetail.sendKeys(datalist.get("Username"));
                } else if (i == 6) {
                    employeeDetail.sendKeys(datalist.get("Password"));
                } else if (i == 7) {
                    employeeDetail.sendKeys(datalist.get("Password"));
                }
            }

            if (statusButtons.size() == 2 && datalist.get("Status").equalsIgnoreCase("enabled")) {
                statusButtons.get(0).click();
            } else if (statusButtons.size() == 2 && datalist.get("Status").equalsIgnoreCase("disabled")) {
                statusButtons.get(1).click();
            }

            getElement(driver, page, "EmployeeSaveButton").submit();
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("user Put Employee Details And Add ThE new Employee method failed\n\t\t\t" + e.getMessage());
        }
    }

    public void userClickOnTopBarMenu(String topbarmenu) {
        try {
            List<WebElement> topBarItems = getElements(driver, page, "TopBarItems");
            for (WebElement topBarItem : topBarItems) {
                if (topBarItem.getText().equalsIgnoreCase(topbarmenu)) {
                    topBarItem.click();
                    break;
                }
            }
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("user Click On Top Bar Menu Method Failed\n\t\t\t" + e.getMessage());
        }
    }

    public void userSearchForCreatedEmployee(Map<String, String> datalist) {
        try {
            getElement(driver, page, "SearchByEmployeeName").sendKeys(datalist.get("Employee Name"));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("user Search For Created Employee method failed\n\t\t\t" + e.getMessage());
        }
    }

    public void userClickOnButton(String button) {
        try {
            if (button.equalsIgnoreCase("search") || button.equalsIgnoreCase("save"))
                getElement(driver, page, "EmployeeSearchButton").click();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("user Click On Button\n\t\t\t" + e.getMessage());
        }
    }

    public void userFindsTheSearchedEmployeeInRecordTable(Map<String, String> datalist) {
        try {
            List<WebElement> tableRows = getElements(driver, page, "SearchTableRows");
            for (WebElement tableRow : tableRows) {
                List<WebElement> columns = getElements(tableRow, page, "SearchTableColumn");
                String name = columns.get(2).getText().trim() + " " + columns.get(3).getText().trim();
                if (name.equalsIgnoreCase(datalist.get("Employee Name"))) {
                    getElement(columns.get(0), page, "SearchTableColumnCheckBox").click();
                    getElement(columns.get(8), page, "SearchTableColumnTrashBox").click();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("user Finds The Searched Employee In Record Table method failed\n\t\t\t" + e.getMessage());
        }
    }

    public void userClickOnCheckboxAndTrashButton() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("user Click On Checkbox And Trash Button method failed\n\t\t\t" + e.getMessage());
        }
    }

    public void userclickOnPopUpConfirmation(String button) {
        try {
            getElement(driver, page, "PopUpDeleteYesConfirmation").click();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("user click On PopUp Confirmation method failed\n\t\t\t" + e.getMessage());
        }
    }

    public void userVerifyTheSuccessMessage(Map<String, String> datalist) {
        try {
            Assert.assertEquals("Success Title Mismatch", "Success", getElement(driver, page, "SuccessToastTitle").getText());
            Assert.assertEquals("Success Message Mismatch", datalist.get("Message"), getElement(driver, page, "SuccessToastMessage").getText());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("user Verify The Success Message method failed\n\t\t\t" + e.getMessage());
        }
    }
}
