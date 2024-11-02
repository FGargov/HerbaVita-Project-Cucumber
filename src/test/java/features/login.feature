Feature: User Login

  Background:
    Given I am on the home page

    @SmokeTest
    Scenario: Successful login with valid credentials

      Given I am on the login page
      When Enter the username "validUsername"
      And Enter the password "validPassword"
      And Click on the login button
      Then I should be logged in successfully
      And I should see the user dashboard


  @RegressionTest
  Scenario: Unsuccessful login with invalid credentials

    Given I am on the login page
    When Enter the username "invalidUsername"
    And Enter the password "invalidPassword"
    And Click on the login button
    But Should see an error message saying "loginError"
