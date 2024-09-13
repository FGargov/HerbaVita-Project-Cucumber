Feature: Product Search

  Background:
    Given I am on the home page
  @Pending
    @SmokeTest
    Scenario Outline: Search for existing product and verify results

      When I search for <Name> in the search field
      Then I should see <Name> in the search results
      And The number of search results should be <ExpectedCount>

      Examples:
        | Name | ExpectedCount |
        | хербал алое концентрат | 2  |
        | протеинов бар          | 3  |

  @Pending
    @RegressionTest
    Scenario Outline: Search for a non-existing product

      When I search for <Name> in the search field
      Then I should see a message saying "Не бяха намерени продукти, отговарящи на критериите Ви."

      Examples:
      | Name |
      | мултивитамин |
      | ментов чай |

  @Pending
    @RegressionTest
    Scenario Outline: Display relevant suggestions while type in the search box

      When I start typing <Name> in the search field
      Then I should see suggestions related to <Name>
      And The suggestions should contain at least <ExpectedCount> items
      And I should see the "View all results" button

      Examples:
        | Name | ExpectedCount |
        | чай  | 3 |
        | алое | 2 |