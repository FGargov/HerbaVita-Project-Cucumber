Feature: Product Details Page

    Background:
        Given I am on the home page

    @RegressionTest
    Scenario: View detailed information about a product from the category

      When  I click on "Шейкове" navigation menu item
      Then  Then I should see products listed under the "Шейкове" category
      And I click on the first product in the list
      Then I should see the product details page
      And The product title should be displayed correctly



