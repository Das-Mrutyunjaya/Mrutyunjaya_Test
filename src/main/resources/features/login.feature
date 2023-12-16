Feature: Login to Practice website

  @juju
  Scenario Outline: User login to to the application
    Given User open the "<browser>" browser
    Then User put his "<username>" and "<password>"
    Then user click on login button
    Examples:
      | browser | username | password |
      | chrome  | Admin    | admin123 |
      | edge    | Admin    | admin123 |


  Scenario Outline: User Order pets and validate the order details from a petStore
#    Given User set up the request body using below data
#      | id   | petId | quantity | status | complete | shipDate             |
#      | <id> | 98765 | 2        | placed | true     | 2023-10-28T11:15:00Z |
#    Then User validate the order deatil with below data
#      | id   | petId | quantity | status | complete | shipDate             |
#      | <id> | 98765 | 2        | placed | true     | 2023-10-28T11:15:00Z |
    Given User open the "chrome" browser
    And user check the inventory
    Examples:
      | id | username | password |
      | 9  | juju     | pwd      |
#      | 8  | juju     | pwd      |
#      | 7  | juju     | pwd      |
