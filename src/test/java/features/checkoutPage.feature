 Feature: Place the order for Products

   Scenario Outline: Search Experience for product search in both home and offers page

     Given User is on Green Card Landing page
     When User searched with shortname <Name> and expected actual name of product
     And Added "3" items of the selected product card
     Then User proceeds to Checkout and validate the <Name> items in checkout page
     And Verify user has ability to enter promo code and place order

    Examples:
     | Name |
     | Tom  |
