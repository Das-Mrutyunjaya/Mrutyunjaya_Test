Feature: Login to OrangeHRM Practice website

  @practice
  Scenario Outline: User login to to the OrangeHRM application
    Given User open the "<browser>" browser
    Then User put his "<username>" and "<password>"
    Then user click on login button
    Then user validate the page-title "<title>"
    Examples:
      | browser | username | password |title|
      | chrome  | Admin    | admin123 |OrangeHRM|
      | edge    | Admin    | admin123 |OrangeHRM|
