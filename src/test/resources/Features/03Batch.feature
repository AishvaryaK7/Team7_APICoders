Feature: Batch Mangement
  I want to use this request to create Batch
  Background:
  Given Admin sets authorization to bearer token

#  Scenario: Check if Admin able to create a program with valid endpoint and request body with Authorization
#    Given Admin sets the status of program to active
#    Then Program status is updated to active.

  
  Scenario Outline: Check if admin able to create a Batch with valid endpoint and request body 
    Given Admin creates POST request by reading from Excel
    When Admin sends HTTPS Request with the "<rowNumber>"
    Then Admin receives "<expectedStatusCode>" with response body.
     
    
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
  
   #GET REQUEST{ by Batch Name }
    #Retrieve a batch with valid BATCH NAME
    Scenario: Check if admin able to retrieve a batch with valid BATCH NAME
    and Admin sets Authorization to Bearer Token.
    Given Admin creates GET Request with valid BatchName
    Then Admin receives 200 OK Status with response body_1.                                                                   
    
   #Retrieve a batch with invalid BATCH NAME                                                               
   Scenario: Check if admin able to retrieve a batch with invalid BATCH NAME
   and Admin sets Authorization to Bearer Token.
   Given Admin creates GET Request with invalid Batch Name
   Then Admin receives 404 Not Found Status with message and boolean success details
   
   #Check if admin able to retrieve a batch with invalid endpoint                                                            
   Scenario: Check if admin able to retrieve a batch with invalid endpoint
   and Admin sets Authorization to Bearer Token.
   Given Admin creates GET Request with valid Batch Name and invalid endpoint
   Then Admin receives 404 Not found
  
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
   Then Admin receives 404  Status with  error message Not Found.
   
   #GET REQUEST{ by Batch Program ID } for deleted Batch
   
   #Check if admin able to retrive a batch after the programID is deleted
   Scenario: Check if admin able to retrive a batch after the programID is deleted
   Given Admin creates GET Request with program id
   Then Admin receives 404 Not Found Status with message and boolean success details
   
   #GET REQUEST{ by Batch ID } for deleted Batch
   #Check if admin able to retrive  a deleted batch with valid Batch ID
   Scenario: Check if admin able to retrive a deleted batch using batch name
   and Admin sets Authorization to Bearer Token.
   Given Admin creates GET Request with valid Batch ID
   Then Admin receives 200 OK Status with  batchStatus field "Inactive" in the response body.
   
   #GET REQUEST{ by Batch Name} for deleted Batch
   
   #Check if admin able to retrive a batch after deleting the batch with valid Batch Name
   
   Scenario: Check if admin able to retrive a batch after deleting the batch 
   and Admin sets Authorization to Bearer Token.
   Given Admin creates GET Request with valid batch Name with a deleted batch
   Then Admin receives 200 OK Status with  batchStatus field "Inactive" in the response body_4.
   
   #PUT REQUEST(Update Batch by batchID)
   
   #Update a Batch with valid batchID and mandatory fields in request body
    Scenario: Check if admin able to update a Batch with valid batchID and 
    mandatory fields in request body
    Given Admin creates PUT Request with valid BatchId and mandatory fields
    Then Admin receives 200 OK Status with updated value in response body. 
    
   #Update a Batch with invalid batchID and mandatory fields in request body 
   Scenario: Check if admin able to update a Batch with invalid batchID
    and mandatory fields in request body
   Given Admin creates PUT Request with invalid BatchId and valid Data
   Then  Admin receives 404 Not Found Status with message and boolean success details_2
   
   #Update a Batch with valid batchID and missing mandatory fields request body  
   Scenario: Check if admin able to update a Batch with valid batchID and
    missing mandatory fields request body 
    Given Admin creates PUT Request with valid batch Id and missing mandatory fields
    Then  Admin receives 400 Bad Request Status with message and boolean Success Details_3  
    
   #Update a Batch with valid batchID and missing mandatory fields request body 
   Scenario: Check if admin able to update a Batch with valid batchID and 
   missing mandatory fields request body
   Given Admin creates PUT Request with valid batch Id and missing mandatory fields in request
   Then  Admin receives 400 Bad Request Status with message and boolean success details_4
   
    #Check if admin able to update a batch with invalid data
    Scenario: Check if admin able to update a batch with invalid data
    Given Admin creates PUT Request with invalida data
    Then Admin receives 400 Bad Request Status with message and boolean Success details_5
    
    #Check if admin able to update a Batch with invalid enpoint
     Scenario: Check if admin able to update a Batch with a valid batchID and
      deleted programID field  in the request body with other mandatory fields
     Given Admin creates PUT Request with Valid batch Id
     Then Admin receives 400 Bad Request Status with message_6
    
    
                          
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
    Given Admin creates GET Request with valid Batch ID
    Then Admin receives 401  Status with error message unauthorized.
   
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
    Then Admin receives 401  Status with error message unauthorized.
    
    #Check if admin able to retrieve a batch without authorization with batch name
    Scenario: Check if admin able to retrieve a batch without authorization
    Given Admin creates GET Request with batch Name
    Then  Admin receives 401  Status with error message unauthorized.
    
    #Check if admin able to retrieve a batch without authorization with program Id 
    Scenario: Check if admin able to retrieve a batch without authorization
    Given Admin creates GET Request with program id_NoAuth
    Then Admin receives 401  Status with error message unauthorized.
    
    #Check if admin able to update a Batch with valid batchID and mandatory fields in request body without authorization
    Scenario: Check if admin able to update a Batch with valid batchID and mandatory fields in request body without authorization
    Given Admin creates PUT Request with valid BatchId and Data
    Then Admin receives 401 unauthorized
    
    
    
    #Delete
       
    #Check if admin able to delete a Batch without authorization
    Scenario: Check if admin able to delete a Batch without authorization
    Given Admin creates DELETE Request with valid BatchId_NoAuth
    Then Admin receives 401 Unauthorized Status 
    
    
    #Check if admin able to delete a Batch with valid Batch ID
    Scenario: Check if admin able to delete a Batch with valid Batch ID
    Given Admin creates DELETE Request with valid BatchId_WithAuth1
    Then Admin receives 200 Ok status with message
    
    #Check if admin able to delete a Batch with invalid endpoint
    Scenario: Check if admin able to delete a Batch with invalid endpoint
    Given Admin creates DELETE Request with valid BatchId_WithAuth2
    Then Admin receives 404 not found
    
    #Check if admin able to delete a Batch with invalid Batch ID
    Scenario: Check if admin able to delete a Batch with invalid Batch ID
    Given Admin creates DELETE Request with invalid BatchId_WithAuth3
    Then Admin receives 404 Not Found Status with message and boolean success details
    
    
                                                              
     