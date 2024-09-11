Feature: Home Page
  Background:
    Given I am on the home page

  @SmokeTest
  Scenario: Load the home page
    Then I should be on the correct home page URL

  @SmokeTest
  Scenario: Display the site logo correctly
    Then I should see the site logo displayed correctly

  @SmokeTest @RegressionTest
  Scenario: Navigation menu functionality
    When I click on first menu item and I should be directed to the correct page
    Then I click on second menu item and I should be directed to the correct page

  @RegressionTest
  Scenario: Display promotional banners
    When I should see promotional banners displayed on the home page
    Then I click on the promotional banner and I should be directed to the promotional page