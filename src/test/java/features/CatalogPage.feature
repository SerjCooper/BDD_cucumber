Feature: catalog page
  Scenario: adding product to cart
    Given catalog page
    * I open detail product № 3
    When adding to cart
    Then I will see notification

  Scenario: verify searching
    Given main page
    When I search "Джинсы"
    Then I will see products "Джинсы"

  Scenario: adding apple product in favorite
    Given main page
    When I expand tab "Apple" and choose category "iPhone"
    * I open detail product № 1
    * adding to favorites
    * I go to favorites
    Then I will see product in favorites

  Scenario: searching male watches
    Given main page
    * switch gender on "Мужское"
    When I expand tab "Часы" and choose category "Часы"
    Then I will see products "Часы"