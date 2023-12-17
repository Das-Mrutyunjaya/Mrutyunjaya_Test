Feature: Login to OrangeHRM Practice website


  Background: : User login to to the OrangeHRM application
    Given User open the "chrome" browser
    Then User put his "Admin" and "admin123"
    Then user click on login button
    Then user validate the page-title "OrangeHRM"

  @practice
  Scenario: User Adding Employee to the database
    Given User click on "PIM" Module
    And user click "Add Employee" on Top Bar Menu
    And User put below employee details and add the new employee
      | First Name | Middle Name | Last Name | Employee Id | Create Login Details | Status  | Username | Password   |
      | P          | Mrutyunjaya | Das       | 030         | Enable               | Enabled | daspmr   | Aditya@038 |
    And user verify the success message
      | Message            |
      | Successfully Saved |
    Then user click "Employee List" on Top Bar Menu
    And User Search for created employee
      | Employee Name     |
      | P Mrutyunjaya Das |
    And user click on "Search" button
    Then User Finds the searched employee in record table & click on checkbox and Trash button
      | Employee Name     |
      | P Mrutyunjaya Das |
    Then user click on "Yes, Delete"
    And user verify the success message
      | Message              |
      | Successfully Deleted |

