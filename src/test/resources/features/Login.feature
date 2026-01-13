Feature: Login functionality

  Scenario Outline: Login using multiple credentials
    Given User is on the login page
    When User enters username "<username>" and password "<password>"
    And User clicks on Login button
    Then User should see error message "<errorMessage>"

    Examples:
      | username  | password  | errorMessage                                                              |
      | wronguser | wrongpass | Epic sadface: Username and password do not match any user in this service |

  Scenario Outline: Login using multiple credentials
    Given User is on the login page
    When User enters username "<username>" and password "<password>"
    And User clicks on Login button
    Then User should see "<result>"

    Examples:
      | username      | password     | result    |
      | standard_user | secret_sauce | Dashboard |