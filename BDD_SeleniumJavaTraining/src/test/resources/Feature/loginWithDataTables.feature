Feature: Login feature test

  Background:user login prerequisite
    Given User is redirect to login

  @Sanity
  Scenario: Validate incorrect user login
    When  User enter credentials from data table
      | user1 | pass1 |
    And User click on login button
    Then User validate error message on login page

  @Sanity
  Scenario: Validate incorrect user login2
    When  User enter credentials from data table with map
      | username | password |
      | user1    | pass1    |

    And User click on login button
    Then User validate error message on login page









