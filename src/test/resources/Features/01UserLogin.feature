
Feature: User Login to LMS API

  @UserLogin_Generate_Token
  Scenario: Check if Admin able to generate token with valid credential
    Given Admin creates request with valid credentials
    When Admin calls Post Https method  with valid endpoint    
    Then Admin receives 200 created with auto generated token

  @UserLogin_Invalid_Endpoint
  Scenario: Check if Admin able to generate token with invalid endpoint
  	Given Admin creates request with valid credentials
    When Admin calls Post Https method  with invalid endpoint    
    Then Admin receives 401 unauthorized

	@UserLogin_Invalid_Credentails
	Scenario: Check if Admin able to generate token with invalid credentials
		Given Admin creates request with invalid credentials
    When Admin calls Post Https method  with valid endpoint    
    Then Admin receives 400 Bad request