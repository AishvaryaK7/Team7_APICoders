Feature: Batch delete
 #Delete
       
    #Check if admin able to delete a Batch without authorization
    Scenario: Check if admin able to delete a Batch without authorization
    Given Admin creates DELETE Request with valid BatchId_NoAuth
    Then Admin receives 401  Status with error message unauthorized for batch
    
    
    #Check if admin able to delete a Batch with valid Batch ID
    Scenario: Check if admin able to delete a Batch with valid Batch ID
    Given Admin creates DELETE Request with valid BatchId_WithAuth1
    Then Admin receives 200 Ok status with message
    
    #GET REQUEST{ by Batch Name} for deleted Batch
   
   #Check if admin able to retrive a batch after deleting the batch with valid Batch Name
   ##implement this step
   Scenario: Check if admin able to retrive a batch after deleting the batch  
   and Admin sets Authorization to Bearer Token.
   Given Admin creates GET Request with valid batch Name with a deleted batch
   Then Admin receives 200 OK Status with  batchStatus field "Inactive" in the response body_4.
   
   #GET REQUEST{ by Batch ID } for deleted Batch
   #Check if admin able to retrive  a deleted batch with valid Batch ID
   Scenario: Check if admin able to retrive a deleted batch using batch name and Admin sets Authorization to Bearer Token.
   Given Admin creates GET Request with valid Batch id
   Then Admin receives 200 OK Status with  batchStatus field "Inactive" in the response body_4.
    
    #GET REQUEST{ by Batch Program ID } for deleted Batch
   
   #Check if admin able to retrive a batch after the programID is deleted
   Scenario: Check if admin able to retrive a batch after the programID is deleted
   Given Admin creates GET Request with program id
   Then Admin receives 404 Not Found Status with message and boolean success details
   
    #Check if admin able to delete a Batch with invalid endpoint
    Scenario: Check if admin able to delete a Batch with invalid endpoint
    Given Admin creates DELETE Request with valid BatchId_WithAuth2
    Then Admin receives 404 not found
    
    #Check if admin able to delete a Batch with invalid Batch ID
    Scenario: Check if admin able to delete a Batch with invalid Batch ID
    Given Admin creates DELETE Request with invalid BatchId_WithAuth3
    Then Admin receives 404 Not Found Status with message and boolean success details  