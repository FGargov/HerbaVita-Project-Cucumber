Feature: Shopping Cart
  As a user,
  I want to be able to add products to my shopping cart
  So that I can purchase them later

  Background:
    Given I am on the home page

  @SmokeTest
  Scenario Outline: Add products to shopping cart
    When I search for <Name> in the search field and click on the search button
    Then I have added <Name> to my shopping cart

    Examples:
      | Name |
      | ШЕЙК ФОРМУЛА 1 – 550ГР. КАФЕ ЛАТЕ |


  @RegressionTest
  Scenario Outline: Update the quantity of a product in my shopping cart

    When I search for <Name> in the search field and click on the search button
    Then I have added <Name> to my shopping cart
    And I change the quantity of <Name> to <Quantity>
    And I should see the updated quantity of <Name> in my shopping cart and it should be <Quantity>

    Examples:
      | Name | Quantity |
      | ШЕЙК ФОРМУЛА 1 – 550ГР. КАФЕ ЛАТЕ | 3 |


    @RegressionTest
    Scenario Outline: Remove a product from the shopping cart
    When I search for <Name> in the search field and click on the search button
    Then I have added <Name> to my shopping cart
    And I remove <Name> from my shopping cart
    And My shopping cart is empty

    Examples:
      | Name |
      | ШЕЙК ФОРМУЛА 1 – 550ГР. КАФЕ ЛАТЕ |
