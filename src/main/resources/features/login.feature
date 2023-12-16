Feature: Login to Practice website

  @practice
  Scenario Outline: User login to to the application
    Given User open the "<browser>" browser
    Then User put his "<username>" and "<password>"
    Then user click on login button
    Examples:
      | browser | username | password |
      | chrome  | Admin    | admin123 |
      | edge    | Admin    | admin123 |

  @practice
  Scenario Outline: User Order pets and validate the order details from a petStore
    Given User Delete the Order detail with below data
      | id   |
      | <id> |
    Then User set up the request body using below data
      | id   | petId | quantity | status | complete | shipDate             |
      | <id> | 98765 | 2        | placed | true     | 2023-10-28T11:15:00Z |
    And User validate the order detail with below data
      | id   | petId | quantity | status | complete | shipDate             |
      | <id> | 98765 | 2        | placed | true     | 2023-10-28T11:15:00Z |

    Examples:
      | id |
      | 2  |
      | 3  |
      | 4  |


