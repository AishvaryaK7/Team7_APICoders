Feature: User Controller module PUT Requests
@PutRequest_for_update_admin
    Scenario Outline: Check if admin is able to update a valid Admin with valid endpoint and request body with all possible scenarios
    ###Put Request having mandatory,mandatory&additional,invalid data###
    Given Admin creates PUT request to update Admin details by reading from Excel
    When Admin sends HTTPS PUT Request to update Admin details with the "<rowNumber>"
    Then Admin receives "<expectedStatusCode>" with response body for Updated user.
    
     Examples:
    | rowNumber | expectedStatusCode |
    | 0 | 200 |
    | 1 | 200 |
    | 2 | 400 |
    | 3 | 400 |
    | 4 | 400 |
    | 5 | 400 |
    | 6 | 400 |
    | 7 | 400 |
    | 8 | 400 |
    | 9 | 400 |
    | 10 | 400 |
    | 11 | 400 |
    | 12 | 400 |
    | 13 | 400 |
    | 14 | 400 |
    | 15 | 400 |
    | 16 | 400 |
    | 17 | 400 |
    | 18 | 400 |
    | 19 | 400 |
    | 20 | 400 |
    | 21 | 400 |
    | 22 | 400 |
    | 23 | 400 |
    | 24 | 400 |
    | 25 | 200 |
    | 26 | 200 |
    | 27 | 200 |
    | 28 | 200 |
    | 29 | 200 |
    | 30 | 200 |
    | 31 | 200 |
    | 32 | 200 |
    | 33 | 200 |
    
    
    @PutRequest_for_update_admin_invalid_adminid_token
    Scenario: To Check No Authorization &invalid endpoint&invallid data(Negative cases)
    
    ##PUT Request to update admin with no authorization####
    Given Admin sets authorization to No Auth
    When Admin creates and sends Put Request to update  admin with No authorization
    Then Admin receives status 401 with Unauthorized message
    
    ##PUT Request to update admin with no authorization####
    When Admin creates and sends Put Request to update admin with invalid adminid
    Then Admin receives status 404 with Not Found error message		
    
    @tag1Update_RoleID_from_ScenarioOutline
  Scenario Outline: Update admin role ID with admin ID
    Given Admin creates a PUT request with a request body to update the admin role ID "<roleId>"
    When Admin sends the HTTPS request to endpoint for admin Id
    Then Admin receives a "<expectedStatusCode>" with a response body 
 Examples:
  | roleId | expectedStatusCode |
  | R01 | 400 |
  | R03 | 200 |
  | R03 | 400 |
  | R02 | 200 |
  | R0 | 400 |
  
   Scenario: Update admin role ID with Invalid request body
    Given Admin creates a PUT request to update the admin role ID with a invalid request body
    When Admin sends the HTTPS request to endpoint for admin Id
    Then Admin receives a 400 Bad Request status with a message and boolean success details
    
  Scenario: Update admin role ID with Invalid Admin Id
    Given Admin creates a PUT request with a request body to update the admin role ID
    When Admin sends the HTTPS request to endpoint for Invalid admin Id
    Then Admin receives a 404 Not Found status with a message and boolean success details
    
   Scenario: Update admin role ID with Invalid endpoint
    Given Admin creates a PUT request with a request body to update the admin role ID 
    When Admin sends the HTTPS request with invalid endpoint for role ID 
    Then Admin receives a 404 Not Found status with a message and boolean success details
    
   Scenario: Update admin role ID with no authorization 
    Given Admin creates a PUT request with a request body to update the admin role ID with no authorization
		When Admin sends the HTTPS request to endpoint for admin Id
    Then Admin receives a 401 unauthorized status with a message and boolean success details
    		
  
 @tag2Update_Rolestatus_from_ScenarioOutline
  Scenario Outline: Update role status of an admin with Admin ID
    Given Admin creates a PUT request with valid data in the request body to update role ID "<roleId>" with status "<status>"
    When Admin sends the HTTPS request to the endpoint for role status
    Then Admin receives a "<expectedStatusCode>" with a response message
 Examples:
  | roleId |status| expectedStatusCode |
  | R02 |Active| 200 |
  | R01	|OK | 400|
  | R01 |Active| 404 |
  | R0  |Active| 404 |  
  
  Scenario: Update admin role staus with Invalid Admin ID
    Given Admin creates a PUT request with a request body to update the admin role status
    When Admin sends the HTTPS request to the endpoint for role status with Invalid Admin Id 
    Then Admin receives a 404 Not Found status with a message and boolean success details
  
   Scenario: Update admin role staus with Invalid endpoint
    Given Admin creates a PUT request with a request body to update the admin role status 
    When Admin sends the HTTPS request with invalid endpoint for role status
    Then Admin receives a 404 Not Found status with a message and boolean success details
    
   Scenario: Update admin role status with no authorization 
    Given Admin creates a PUT request with a request body to update the admin role status with no authorization
		When Admin sends the HTTPS request to the endpoint for role status
    Then Admin receives a 401 unauthorized status with a message and boolean success details
  
 #@tag3Update_Program_Batch_UserRole
 #Scenario: Assign Admin to program/batch with valid Admin ID
    #Given Admin creates PUT request with request body for program/batch assignment
    #When Admin send the HTTPS request to the endpoint to update Program/Batch
    #Then Admin receives a 200 Ok status with a message and boolean success details
    
  Scenario Outline: Assign Admin to program/batch with valid Admin ID
    Given Admin creates PUT request with request body for program/batch assignment with roleId "<roleId>" and Status "<status>"
    When Admin send the HTTPS request to the endpoint to update Program/Batch
    Then Admin receives a "<expectedStatusCode>" with a response message
  Examples:
  | roleId |status| expectedStatusCode |
  | R01 |Active| 400 |
  | R01	|OK| 400|
  | R02 |Active| 200 |
  | R02 |OK| 400|  
  | R0 |Active| 404| 
  
  Scenario: Assign Admin to program/batch with Invalid Admin ID
    Given Admin creates PUT request with request body for program/batch assignment
    When Admin send the HTTPS request to the endpoint to update Program/Batch with Invalid Admin Id
    Then Admin receives a 404 Not Found status with a message and boolean success details
    
  Scenario: Assign Admin to program/batch with valid Admin ID with Invalid Endpoint
    Given Admin creates PUT request with request body for program/batch assignment
    When Admin send the HTTPS request with Invalid endpoint to update program/batch
    Then Admin receives a 404 Not Found status with a message and boolean success details 
    
  Scenario: Assign Admin to program/batch with valid Admin ID with No Authorization
    Given Admin create a PUT request with request body for program/batch assignment with no authorization
    When Admin send the HTTPS request to the endpoint to update Program/Batch
    Then Admin receives a 401 unauthorized status with a message and boolean success details
    
 @tag4Update_User_Login_Status

   
  Scenario Outline: Update Admin login status with Admin ID
   Given Admin creates a PUT request with request body to update Login Status with LoginEmail "<email>" , Status "<status>" and password "<password>"
   When Admin sends the HTTPS request to the endpoint to update Login Status
   Then Admin receives a "<expectedStatusCode>" with a response message
 Examples:
  | email         |status| password | expectedStatusCode |
  | team77@api.com |Active|  pass    | 200  |
  | team78@api.com |Active|          | 200  |
  | team79@api.com |InActive|        | 200  |
  | team80@api.com	|OK    |  pass    | 400  |
  | team81@api.com |      |  pass    | 400  |
  |               |Active|  pass    | 400  |  
  | team82@api.com |InActive| pass   | 200  |
  | team83@api.com |Active| pass   | 200  |
   
   Scenario: Update Admin login status with Invalid Admin ID
    Given Admin creates a PUT request with request body to update Login Status
    When Admin sends the HTTPS request to the endpoint to update Login Status with Invalid Admin Id
    Then Admin receives a 404 Not Found status with a message and boolean success details
 
  Scenario: Update Admin login status for Admin ID with Invalid endpoint
    Given Admin creates a PUT request with request body to update Login Status
    When Admin sends the HTTPS request with Invalid endpoint to update Login Status
    Then Admin receives a 404 Not Found status with a message and boolean success details 
    
  Scenario: Update Admin login status for Admin ID with No Authorization
    Given Admin create a PUT request with request body to update Login Status with no authorization
    When Admin sends the HTTPS request to the endpoint to update Login Status
    Then Admin receives a 401 unauthorized status with a message and boolean success details
    
  Scenario: Update admin login status for Admin ID with Invalid request body
    Given Admin creates a PUT request to update login status for Admin ID with a invalid request body
    When Admin sends the HTTPS request to the endpoint to update Login Status
    Then Admin receives a 400 Bad Request status with a message and boolean success details
    
    ###Get Request to get the Admins for valid program Id###
    Given Admin creates and sends GET Request to get the Admins for valid program Id when  "<expectedStatusCode>" is 201
    Then Check if admin is able to get the Admins for valid program Id with valid endpoint when "<expectedStatusCode>" is 201
    
    ###Get Request to get the Admins by program batches for valid batch ID###
    Given Admin creates and sends GET Request to get the Admins by program batches for valid batch ID when  "<expectedStatusCode>" is 201
    Then Check if admin is able to get the Admins by program batches for valid batch ID with valid endpoint when "<expectedStatusCode>" is 201
      
    