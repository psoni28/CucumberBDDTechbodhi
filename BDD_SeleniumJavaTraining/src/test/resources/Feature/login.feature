Feature: Login feature test

  Scenario: Validate user is able to login
    Given User is redirect to login
    When User enter username and password
    When User click on login button
    Then User should login successfully


  Scenario: Validate incorrect user login
    Given User is redirect to login
    When User enter "sdsd" and "addasas" credentials
    And User click on login button
    Then User validate error message on login page



