Feature: Main page
  Scenario: switch gender on male
    Given main page
    When switch gender on "Мужское"
    Then current gender is "Мужское"

  Scenario Outline: switch gender
    Given main page
    When switch gender on "<gender>"
    Then current gender is "<gender>"
    Examples:
      | gender |
      | Мужское|
      | Женское|
      | Детское|

  Scenario: switch language on english
      Given main page
      When switch language on "English"
      Then current language is "EN"

  Scenario: put bonus card on cart and verify price
    Given main page
    * I click bonus cards
    * I choose 5000
    When I click put to cart
    Then I will see 5000 on cart

  Scenario Outline: put bonus card on cart and verify price
    Given main page
    * I click bonus cards
    * I choose <price>
    When I click put to cart
    Then I will see <price> on cart
    Examples:
      |price|
      |3000 |
      |5000 |

  Scenario: adding in favorites
    Given main page
    * I expand tab "Обувь" and choose category "Сапоги"
    * I open detail product № 1
    When adding to favorites
    * I go to favorites
    Then I will see product in favorites