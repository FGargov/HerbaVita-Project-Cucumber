Feature: Display contact information

  Background:
    Given I am on the home page

  Scenario: Verify contact information is displayed correctly
    When I should see the address displayed
    And I should see the phone number displayed
    Then I should see the email displayed