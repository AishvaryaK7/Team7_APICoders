Feature: Program_Put

Background:
     Given Admin sets Authorization
     
     @Update Program by Id1
       Scenario Outline: Check if Admin able to update a program with valid programID endpoint  and valid request body
     Given Admin updates put request by reading from Excel "<rowNumber>"
     When Admin sends HTTPS request
     Then Admin receives "<expectedStatusCode>" updated Status with response body
     Examples:
     |  rowNumber | expectedStatusCode |
     | 0 | 200 |
     | 1 | 404 |
     | 2 | 400 |
     | 3 | 400 |
     | 4 | 404 |
     | 5 | 405 |
     
     @Update Program Using Program Name
       Scenario Outline: Check if Admin able to update a program with  Valid program Name and request body
       Given Admin updates put request by reading from Excel "<rowNumber>"
       When Admin sends HTTPS Request with valid endpoint
       Then Admin receives "<expectedStatusCode>" updated Status with response body
       Examples:
       | rowNumber | expectedStatusCode |
       | 0 | 200 |
       | 1 | 404 |
       | 2 | 400 |
       | 3 | 400 |
       | 4 | 400 |
       | 5 | 200 |
       
       @Delete request by program Name 1
       Scenario: Check if Admin able to delete a program with valid programName
       Given  Admin creates DELETE Request for the LMS API endpoint  and  valid programName
       When Admin sends HTTPS Request with endpoint
       Then Admin receives 200 Ok status with message
       
       @Delete request by Program Name 2
       Scenario:Check if Admin able to delete a program with valid LMS API,invalid programName
       Given Admin creates DELETE Request for the LMS API endpoint  and  invalid {programName}
       When Admin sends HTTPS Request with endpoint
       Then Admin receives 404 Not Found Status with message and boolean success details
       
      @Delete request by Program Id 1
      Scenario: Check if Admin able to delete a program with valid program ID
      Given Admin creates DELETE Request for the LMS API endpoint  and  valid program ID
      When Admin sends HTTPS Request with endpoint
      Then Admin receives 200 Ok status with message
      
      @Delete request by Program Id 2
      Scenario: Check if Admin able to delete a program with valid LMS API,invalid program ID
      Given Admin creates DELETE Request for the LMS API endpoint  and  invalid program ID
      When Admin sends HTTPS Request with endpoint
      Then Admin receives 404 Not Found Status with message and boolean success details
      
      
      
      
      
      
      
      
       
       
       
       
       
       
