Feature: User Login

  Background:
    Given I am on the home page

    @SmokeTest
    Scenario: Successful login with valid credentials

      Given I am on the login page
      When Enter the username "fero"
      And Enter the password "f&JY3HNfLigRmLXQFEN&YCe4"
      And Click on the login button
      Then I should be logged in successfully
      And I should see the user dashboard


    @RegressionTest
    Scenario: Unsuccessful login with invalid credentials

      Given I am on the login page
      When Enter the username "fero2"
      And Enter the password "fero123"
      And Click on the login button
      But Should see an error message saying "ERROR : The username or password you entered is incorrect. Lost your password ?"
