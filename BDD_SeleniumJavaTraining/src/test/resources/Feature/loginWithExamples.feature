Feature: Login feature test

  Scenario Outline: Validate incorrect user login
    Given User is redirect to login
    When User enter "<username>" and "<password>" credentials
    And User click on login button
    Then User validate error message on login page
    Examples:
      | username | password |
      | user1    | pass1    |
      | user2    | pass2    |
      | user3    | pass3    |
      | user4    | pass4    |








