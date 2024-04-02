Feature: User Logout LMS API

	@UserLogout_Successful
	Scenario: Check if Admin able to logout 
		Given Admin creates request 
    When Admin calls Get Https method with valid endpoint    
    Then Admin receives 200 ok  and response with "Logout successful"
    
  @UserLogout_Invalid_EndPoint
	Scenario: Check if Admin able to logout  with invalid endpoint 
		Given Admin creates request 
    When Admin calls Get Https method withinvalid endpoint    
    Then Admin receives 404 Not found
    
  
  @UserLogout_With_NoAuthorizationSet
	Scenario: Check if Admin able to logout 
		Given Admin creates request with no authorization
    When Admin calls Get Https method with valid endpoint    
    Then Admin receives 401 Unauthorized Logout