Feature: Product Search

  Background:
    Given I am on the home page

    @SmokeTest
    Scenario Outline: Search for existing product and verify results

      When I search for <Name> in the search field
      Then I should see suggestions related to <Name>
      And The number of search suggestions should be correct for <Name>

      Examples:
        | Name | ExpectedCount |
        | хербал алое концентрат | 2  |
        | протеинови блокчета  | 1  |


    @RegressionTest
    Scenario Outline: Search for a non-existing product

      When I search for <Name> in the search field
      Then I should see a message saying "noProductsFound"

      Examples:
      | Name |
      | мултивитамин |
      | ментов чай |


    @RegressionTest
    Scenario Outline: Display relevant suggestions while type in the search box

      When I search for <Name> in the search field
      Then The suggestions should contain the correct number of items for <Name>
      And I should see the "VIEW ALL RESULTS" button

      Examples:
        | Name | ExpectedCount |
        | чай  | 3 |
        | алое | 6 |


    @RegressionTest
    Scenario Outline: Click on "View all results" button and view the search results page
      When I search for <Name> in the search field
      When I click on the "View all results" button
      Then I should be directed to the result page for <Name> product

      Examples:
        | Name |
        | алое  |
