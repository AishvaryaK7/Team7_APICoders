Feature: Batch Mangement

  Scenario Outline: Check if admin able to create a Batch with valid endpoint and request body 
    Given Admin creates Batch POST request by reading from Excel
    When Admin sends HTTPS Request with the "<rowNumber>" to create Batch
    Then Admin receives "<expectedStatusCode>" with response body for Batch creation.
     
    
  Examples:
  
| rowNumber | expectedStatusCode |
| 0         | 201                |
| 1         | 400                |
| 2         | 400                |
| 3         | 400                |
| 4         | 201                |
| 5         | 400                |
| 6         | 400                |
| 7         | 201                |
| 8         | 400                |
| 9         | 400                |
| 10        | 400                |
    

    
  
  
     #Retrieve a batch with valid BATCH ID
    Scenario: Check if admin able to retrieve a batch with valid BATCH ID and 
    Admin sets Authorization to Bearer Token. 
    Given Admin creates GET Request with valid BatchID
    Then Admin receives 200 OK Status with response body.   
  
   #GET REQUEST by Batch name
   #Retrieve a batch with valid BATCH NAME
   Scenario: Check if admin able to retrieve a batch with valid BATCH NAME
   and Admin sets Authorization to Bearer Token1.
   Given Admin creates GET Request with valid BatchName
   Then Admin receives 200 OK Status with response body.                                                                   
    
   #Retrieve a batch with invalid BATCH NAME                                                               
   Scenario: Check if admin able to retrieve a batch with invalid BATCH NAME
   and Admin sets Authorization to Bearer Token.
   Given Admin creates GET Request with invalid Batch Name
   Then Admin receives 404 Not Found Status with message and boolean success details
   
   #Check if admin able to retrieve a batch with invalid endpoint                                                            
   Scenario: Check if admin able to retrieve a batch with invalid endpoint
   and Admin sets Authorization to Bearer Token.
   Given Admin creates GET Request with valid Batch Name and invalid endpoint
   Then Admin receives 404 Not Found Status with message and boolean success details
  
   #Get Request by Program ID-----
   
   #Check if admin able to retrieve a batch with valid Program ID
   Scenario: Check if admin able to retrieve a batch with valid Program ID 
    and Admin sets Authorization to Bearer Token.
   Given Admin creates GET Request with valid Program Id
   Then Admin receives 200 OK Status with response body_2.       
   
   #Check if admin able to retrieve a batch with invalid Program Id  
   Scenario: Check if admin able to retrieve a batch with invalid Program Id  
    Given Admin creates GET Request with invalid Program Id  
    Then Admin receives 404 Not Found Status with message and boolean success details
    
   #Check if admin able to retrieve a batch with invalid endpoint                                                    
   Scenario: Check if admin able to retrieve a batch with invalid endpoint
   Given Admin creates GET Request with invalid endpoint
   Then Admin receives 404 Not Found Status with message and boolean success details
   
   
   
   
   
   
   
   #PUT REQUEST(Update Batch by batchID)
   
   Scenario Outline: Check if admin able to update a Batch with valid batchID and mandatory fields in request body 
Given Admin creates PUT request to update Batch by reading from Excel
When Admin sends HTTPS PUT Request to update Batch with the "<rowNumber>"
Then Admin receives "<expectedStatusCode>" with PUT response body for update Batch.
    
Examples:
  
| rowNumber | expectedStatusCode |
| 0         | 200              |
| 1         | 400                |
| 2         | 400                |
| 3         | 400                |
| 4         | 400              |
| 5         | 400                |
| 6         | 400                |
| 7         | 400                |
| 8         | 400                |
| 9         | 400                |
| 10         | 400                |
   
   
    
   #Update a Batch with invalid batchID and mandatory fields in request body 
   Scenario: Check if admin able to update a Batch with invalid batchID
    and mandatory fields in request body
   Given Admin creates PUT Request with invalid BatchId and valid Data
   Then  Admin receives 404 Not Found Status with message and boolean success details_2
 
    #Check if admin able to update a Batch with invalid enpoint
     Scenario: Check if admin able to update a Batch with a valid batchID and
      deleted programID field  in the request body with other mandatory fields
     Given Admin creates PUT Request with Valid batch Id
     Then Admin receives 404 Bad Request Status with message_6
    
    
                          
   #########################################################################################
   
    Scenario Outline: Admin retrieves all batches
    Given Admin sends a GET request with given "<rowNumber>" "<token>" "<searchString>" "<invalidEndpoint>"
    Then Admin receives "<expectedStatusCode>"
   
    Examples:
    | token | searchString  | invalidEndpoint | expectedStatusCode |
    | yes   | no            | no              | 200                |
    | yes   | yes           | no              | 200                |
    | yes   | no            | yes             | 404                |
    | no    | no            | no              | 401                |
    
    #NoAuthorization
    #Retrieve a batch with batch ID without Authorization 
    Scenario: Check if admin able to retrieve a batch with batch ID without authorization
    Given Admin creates GET Request with valid Batch ID No Auth
    Then Admin receives 401  Status with error message unauthorized for batch
   
    ##Authorization to Bearer Token. 
    #GET  REQUEST{ by Batch ID }
    #Retrieve a batch with valid BATCH ID
    Scenario: Check if admin able to retrieve a batch with valid BATCH ID and 
    Admin sets Authorization to Bearer Token. 
    Given Admin creates GET Request with valid BatchID
    Then Admin receives 200 OK Status with response body_3.                                                                  
    
    #Retrieve a batch with invalid BATCH ID
    Scenario: Check if admin able to retrieve a batch with invalid BATCH ID 
    Admin sets Authorization to Bearer Token. 
    Given Admin creates GET Request with invalid Batch ID
    Then Admin receives 404 Not Found Status with message and boolean success details   
    
    #Retrieve a batch with invalid endpoint   
    Scenario: Check if admin able to retrieve a batch with invalid endpoint 
    Given Admin creates GET Request with valid Batch ID and invalid endpoint
    Then Admin receives 404 not found  Status   
     
    
     
    #NoAuthorization
    
    #Check if admin able to retrieve a batch with batch ID without authorization batch id
    Scenario: Check if admin able to retrieve a batch with batch ID without authorization
    Given Admin creates GET Request with valid Batch ID_NoAuth
    Then Admin receives 401  Status with error message unauthorized for batch
    
    #Check if admin able to retrieve a batch without authorization with batch name
    Scenario: Check if admin able to retrieve a batch without authorization
    Given Admin creates GET Request with batch Name
    Then Admin receives 401  Status with error message unauthorized for batch
    
    #Check if admin able to retrieve a batch without authorization with program Id 
    Scenario: Check if admin able to retrieve a batch without authorization
    Given Admin creates GET Request with program id_NoAuth
    Then Admin receives 401  Status with error message unauthorized for batch
    
    #Check if admin able to update a Batch with valid batchID and mandatory fields in request body without authorization
    Scenario: Check if admin able to update a Batch with valid batchID and mandatory fields in request body without authorization
    Given Admin creates PUT Request with valid BatchId and Data
    Then Admin receives 401  Status with error message unauthorized for batch
    
    
    
   
    
    
