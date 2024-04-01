Feature: Program

    @AddNewProgram1
   	Scenario Outline: Check if Admin able to create a program with valid endpoint and request body with Authorization
    Given Admin creates POST request by reading from Excel "<rowNumber>" with auth
    When Admin sends HTTPS Request 
    Then Admin receives "<expectedStatusCode>" Created Status with response body
    Examples:
    | rowNumber | expectedStatusCode |
    | 0 | 201 |
    | 1 | 400 |
    | 2 | 400 |
    | 3 | 400 |
    | 4 | 400 |
    | 5 | 400 |
    | 6 | 400 |
    | 7 | 201 |
    | 8 | 400 |
    | 9 | 400 |
    | 10 | 400 |
    | 11 | 400 |
    | 11 | 400 |
    
    @AddNewProgram3
    Scenario: Check if Admin able to create a program with invalid endpoint
    Given Admin creates POST Request for the LMS with request body with auth
    When Admin sends HTTPS Request and request Body with invalid endpoint
    Then Admin receives 404 Status
    
    @AddNewProgram4
    Scenario: Check if Admin able to create a program with already existing program name
   	Given Admin creates POST Request for the LMS with request body with existing program name with auth
    When Admin sends HTTPS Request
    Then Admin receives 400 Status
   
    @AddNewProgram5
    Scenario: Check if Admin able to create a program with invalid method
    Given Admin creates POST Request for the LMS with request body with auth
    When Admin sends HTTPS Request and request Body with endpoint with invalid method
    Then Admin receives 405 Status
   
    @AddNewProgram6
    Scenario: Check if Admin able to create a program with invalid request body
    Given Admin creates POST Request for the LMS with invalid request body with auth
    When Admin sends HTTPS Request
    Then Admin receives 400 Status
		
		@GetAll1
		Scenario: Check if Admin able to retrieve all programs with valid Endpoint
		Given Admin creates GET Request for the LMS API with auth
		When Admin sends HTTPS Request with endpoint
		Then Admin receives 200 Status
		
		@GetAll2
		Scenario: Check if Admin able to retrieve all programs with invalid Endpoint
		Given Admin creates GET Request for the LMS API with auth
		When Admin sends HTTPS get Request with invalid endpoint
		Then Admin receives 404 Status
	
		@GetAll3
		Scenario: Check if Admin able to retrieve all programs with invalid Method
		Given Admin creates GET Request for the LMS API with auth
		When Admin sends HTTPS Request with invalid method
		Then Admin receives 405 Status
		
		@GetProgramID1
		Scenario: Check if Admin able to retrieve a program with valid program ID
		Given Admin creates GET Request for the LMS API with auth
		When Admin sends GET Request for the LMS API for valid program ID
		Then Admin receives 200 Status

		@GetProgramID2
		Scenario: Check if Admin able to retrieve a program with invalid program ID
		Given Admin creates GET Request for the LMS API with auth
		When Admin sends GET Request for the LMS API for invalid program ID
		Then Admin receives 404 Status
		
		@GetProgramID3
		Scenario: Check if Admin able to retrieve a program with invalid baseURL
		Given Admin creates GET Request for the LMS API with auth
		When Admin sends GET Request for the LMS API with invalid baseURL
		Then Admin receives 404 Status

		@GetAllProgramsWithAdmins1
		Scenario: Check if Admin able to retrieve all programs with valid Endpoint
		Given Admin creates GET Request for the LMS API with auth
		When Admin sends GET Request with valid endpoint for all users
		Then Admin receives 200 Status
		
		@GetAllProgramsWithAdmins2
		Scenario: Check if Admin able to retrieve all programs with invalid Endpoint
		Given Admin creates GET Request for the LMS API with auth
		When Admin sends GET Request with invalid endpoint for all users
		Then Admin receives 404 Status
		
		@GetAllProgramsWithAdmins3
		Scenario: Check if Admin able to retrieve all programs with invalid Method
		Given Admin creates GET Request for the LMS API with auth
		When Admin sends GET Request with invalid method for all users
		Then Admin receives 405 Status

		@AddNewProgram2
  	Scenario: Check if Admin able to create a program with valid endpoint and request body without Authorization
    Given Admin creates POST Request for the LMS with request body with noauth
    When Admin sends HTTPS Request
    Then Admin receives 401 Status
    
    @GetAll4
		Scenario: Check if Admin able to retrieve all programs without Authorization
		Given Admin creates GET Request for the LMS API with noauth
		When Admin sends HTTPS Request with endpoint
		Then Admin receives 401 Status
		
		@GetProgramID4
		Scenario: Check if Admin able to retrieve a program without Authorization
		Given Admin creates GET Request for the LMS API with noauth
		When Admin sends HTTPS Request with endpoint
		Then Admin receives 401 Status
		
		@GetAllProgramsWithAdmins4
		Scenario: Check if Admin able to retrieve all programs without Authorization
		Given Admin creates GET Request for the LMS API with noauth
		When Admin sends HTTPS Request with endpoint
		Then Admin receives 401 Status
		
		#############################################   UPDATE WITH AUTH	##########################################
	
    @UpdateProgramByID1
		Scenario Outline: Check if Admin able to update a program with valid programID endpoint with Authorization
		Given Admin creates PUT Request by reading from Excel "<rowNumber>" with auth
    When Admin sends PUT Request with ID 
		Then Admin receives "<expectedStatusCode>" updated Status
		Examples:
    | rowNumber | expectedStatusCode |
		| 0 | 200 |
    | 1 | 400 |
    | 2 | 200 |
    | 3 | 400 |
    | 4 | 400 |
    | 5 | 400 |
    | 6 | 400 |
    | 7 | 400 |
    

		
		@UpdateProgramByID2
		Scenario: Check if Admin able to update a program with invalid programID with Authorization
		Given Admin creates PUT Request with auth
		When Admin sends PUT Request with invalid program ID
		Then Admin receives 404 Status
		
		@UpdateProgramByID3
		Scenario: Check if Admin able to update a program with invalid request body with Authorization
		Given Admin creates PUT Request with invalid request body with auth
    When Admin sends PUT Request with ID 
		Then Admin receives 400 Status
		

		
		@UpdateProgramByID5
		Scenario: Check if Admin able to update a program with invalid baseURL
		Given Admin creates PUT Request with auth
		When Admin sends PUT Request with invalid endpoint
		Then Admin receives 404 Status
		
		@UpdateProgramByID6
		Scenario: Check if Admin able to update a program with invalid method
		Given Admin creates PUT Request with auth
		When Admin sends PUT Request with invalid method
		Then Admin receives 405 Status
		
		@UpdateProgramByName1
		Scenario Outline: Check if Admin able to update a program with Valid program Name and request body
		Given Admin creates PUT Request by reading from Excel "<rowNumber>" with auth
		When Admin sends PUT Request with Name
		Then Admin receives "<expectedStatusCode>" updated Status
		Examples:
    | rowNumber | expectedStatusCode |
    | 0 | 200 |
    | 1 | 400 |
    | 2 | 200 |
    | 3 | 400 |
    | 4 | 400 |
    | 5 | 400 |
    | 6 | 400 |
    | 7 | 400 |
    
    @UpdateProgramByName2
		Scenario: Check if Admin able to update a program with invalid program Name and request body
		Given Admin creates PUT Request with auth
		When Admin sends PUT Request with invalid program name
		Then Admin receives 404 Status
		
		###noauth###
		@UpdateProgramByID7
		Scenario: Check if Admin able to update a program without Authorization
		Given Admin creates PUT Request for the LMS with noauth
    When Admin sends PUT Request with ID 
		Then Admin receives 401 Status
		
		@UpdateProgramByName7
		Scenario: Check if Admin able to update a program without Authorization
		Given Admin creates PUT Request for the LMS with noauth 
    When Admin sends PUT Request with Name 
		Then Admin receives 401 Status
	
		