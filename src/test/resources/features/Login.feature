# Login Feature - BDD Scenarios
# Author: Subash A | QA Automation Engineer

Feature: User Login Functionality
  As a user of the SauceDemo application
  I want to be able to login with valid credentials
  So that I can access the products page

  Background:
    Given I am on the login page

  @smoke @regression
  Scenario: Successful login with valid credentials
    When I enter username "standard_user"
    And I enter password "secret_sauce"
    And I click the login button
    Then I should be redirected to the products page
    And the page title should be "Products"

  @regression
  Scenario: Failed login with invalid credentials
    When I enter username "invalid_user"
    And I enter password "wrong_password"
    And I click the login button
    Then I should see an error message containing "Username and password do not match"

  @regression
  Scenario: Failed login with empty credentials
    When I click the login button
    Then I should see an error message containing "Username is required"

  @regression
  Scenario Outline: Data driven login tests
    When I enter username "<username>"
    And I enter password "<password>"
    And I click the login button
    Then the login result should be "<result>"

    Examples:
      | username        | password      | result  |
      | standard_user   | secret_sauce  | success |
      | locked_out_user | secret_sauce  | failure |
      | invalid_user    | wrong_pass    | failure |
