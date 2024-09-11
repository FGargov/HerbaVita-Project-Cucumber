Feature: Home Page
  Background:
    Given I am on the home page

  @SmokeTest
  Scenario: Load the home page
    Then I should be on the correct home page URL

  @SmokeTest
  Scenario: Display the site logo correctly
    Then I should see the site logo displayed correctly

  @Skip
  @SmokeTest @RegressionTest
  Scenario: Navigation menu functionality
    When I click on each navigation menu item
    Then I should be directed to the correct page

  @Skip
  @RegressionTest
  Scenario: Display promotional banners
    Then I should see promotional banners displayed on the home page