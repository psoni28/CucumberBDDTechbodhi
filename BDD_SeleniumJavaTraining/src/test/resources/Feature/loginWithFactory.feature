Feature: Login feature test

  Scenario: Validate user is able to login with factory
    Given User is redirect to login with factory
    When User enter username and password with factory
    When User click on login button with factory
    Then User should login successfully with factory


  Scenario: Validate incorrect user login
    Given User is redirect to login with factory
    When User enter "sdsd" and "addasas" credentials with factory
    And User click on login button with factory
    Then User validate error message on login page with factory




