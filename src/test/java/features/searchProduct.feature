 Feature: Search and Place the order for Products

   Scenario Outline: Search Experience for product search in both home and offers page

     Given User is on Green Card Landing page
     When User searched with shortname <Name> and expected actual name of product
     Then User searched for <Name> shortname in offers page to check if the product exist with same name
     And Validate product name in Offers Page matches with Landing Page

    Examples:
     | Name |
     | Tom  |
     | Beet |