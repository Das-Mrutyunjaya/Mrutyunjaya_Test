Feature: Book & PetStore CRUD operation Practice API

  @practice
  Scenario Outline: User CRUD Operation on Books
    Given User Create the book request body using below data
      | id   | title              | description | pageCount | excerpt                      | publishDate          |
      | <id> | Tha last of summer | 2*2=4       | 112       | Amet rebum sadipscing et sit | 2023-10-28T11:15:00Z |
    And User validate the Book creation detail with below data
      | id   | title              | description | pageCount | excerpt                      | publishDate          |
      | <id> | Tha last of summer | 2*2=4       | 112       | Amet rebum sadipscing et sit | 2023-10-28T11:15:00Z |
    Then user update the Book with below details
      | new_Id   | id   | title                      | description     | pageCount | excerpt                              | publishDate          |
      | <new_Id> | <id> | Tha last of summer Updated | 2*2=4   Updated | 112       | Amet rebum sadipscing et sit Updated | 2024-12-28T11:15:00Z |
    Then User "get" the book using "<id>"
    Then User "delete" the book using "<id>"
    Examples:
      | id | new_Id |
      | 2  | 3      |
      | 5  | 7      |


  @practice1
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


