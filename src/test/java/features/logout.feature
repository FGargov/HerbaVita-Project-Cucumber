Feature: User Logout

  Background:
    Given I am on the home page
    And Click on login icon and redirect to login page
    When Enter the user "fero"
    And Enter the long password "f&JY3HNfLigRmLXQFEN&YCe4"
    Then Finally click on the login button

  Scenario: Successfully logout from the application
    When I click on the logout button
    Then I should be redirected to the home page
    And I should no longer see the user profile button
