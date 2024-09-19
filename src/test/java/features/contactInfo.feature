Feature: Display contact information

  Background:
    Given I am on the home page

  Scenario: Verify contact information is displayed correctly
    When I should see the address as "София, Младост 1"
    And I should see the phone number as "0887 684 167"
    Then I should see the email as "sales@herba-vita.eu"
