Feature: program delete requests
  #############################################   DELETE WITH AUTH	##########################################

		@DeleteProgramByName1
		Scenario: Check if Admin able to delete a program with valid programName
		Given Admin creates DELETE Request with auth
		When Admin sends DELETE request with program name
	  Then Admin receives 200 deleted Status
		
		@DeleteProgramByID1
		Scenario: Check if Admin able to delete a program with valid ID
		Given Admin creates DELETE Request with auth
		When Admin sends DELETE request with program ID
	  Then Admin receives 200 deleted Status
	  
	  
	  
	  ####invalid&noauth###
	  
		@DeleteProgramByName2
		Scenario: Check if Admin able to delete a program with invalid program Name
		Given Admin creates DELETE Request with auth
		When Admin sends DELETE request with invalid program name
	  Then Admin receives 404 deleted Status
	  
	  
	  @DeleteProgramByID2
		Scenario: Check if Admin able to delete a program invalid program ID
		Given Admin creates DELETE Request with auth
		When Admin sends DELETE request with invalid program ID
	  Then Admin receives 404 Status
	  
	  @DeleteProgramByName3
		Scenario: Check if Admin able to delete a program without Authorization
		Given Admin creates DELETE Request with noauth
		When Admin sends DELETE request with program name
	  Then Admin receives 401 Status
	  
	  @DeleteProgramByID3
		Scenario: Check if Admin able to delete a program without Authorization
		Given Admin creates DELETE Request with noauth
		When Admin sends DELETE request with program ID
	  Then Admin receives 401 Status
  