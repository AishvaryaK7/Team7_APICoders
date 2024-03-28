
@tag
Feature: Program Module
  
  @Background: UserLogin with Valid Credentials
  Scenario: Check if Admin able to generate token with valid credential
    Given Admin creates request with valid credentials
    When Admin calls Post Https method  with valid endpoint    
    Then Admin receives 201 created with auto generated token

  @tag1
  Scenario: Title of your scenario
    Given I want to write a step with precondition
    And some other precondition
    When I complete action
    And some other action
    And yet another action
    Then I validate the outcomes
    And check more outcomes

  @tag2
  Scenario Outline: Title of your scenario outline
    Given I want to write a step with <name>
    When I check for the <value> in step
    Then I verify the <status> in step

    Examples: 
      | name  | value | status  |
      | name1 |     5 | success |
      | name2 |     7 | Fail    |
      
 @tagNo_Authorization
