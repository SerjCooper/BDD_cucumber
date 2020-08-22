Feature: contactsPage
  Scenario: verify visibility map
    Given contacts page
    When I will see map
    Then I will see marker

  Scenario: send TSUM with watsapp
    Given contacts page
    When I click write to TSUM
    Then I will see message send to "tsum.ru"