Feature: Checkout Process
  As a user,
  I want to be able to add products to my shopping cart
  So that I can purchase them

  Background:
    Given I am on the home page

    @SmokeTest @RegressionTest
    Scenario Outline: Proceed to checkout
      When I find and select <Name> using the search
      Then <Name> is added to my cart
      And I click on the order button
      Then I should be taken to the checkout page
      Examples:
        | Name |
        | ШЕЙК ФОРМУЛА 1 – 550ГР. КАФЕ ЛАТЕ |

  @RegressionTest
  Scenario Outline: Complete purchase with correct billing information
    Given I find and select <Name> using the search field in home page
    And <Name> is added to my cart and click on the order button
    And I am on the checkout page
    When I enter the following shipping information and verify that the shipping information is correct
      | <firstName> |
      | <lastName> |
      | <address> |
      | <city> |
      | <postalCode> |
      | <phone> |
      | <email> |
    And I click on the "ПОРЪЧВАНЕ" button
    And I should see a confirmation page with the order details
    And А confirmation message saying "<confirmationMessage>" should be displayed

    Examples:
      | Name                         | firstName | lastName | address       | city  | postalCode | phone       | email              | confirmationMessage                         |
      | ШЕЙК ФОРМУЛА 1 – 550ГР. КАФЕ ЛАТЕ | Ivan     | Ivanov   | 15 Pirin St. | Sofia | 1618       | +35988712345 | fgargov1@gmail.com | Благодарности. Вашата поръчка беше получена. |

