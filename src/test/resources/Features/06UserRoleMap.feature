Feature: UserRoleProgramBatchMap

@Get_AllAdmin_Program/Batch
Scenario: Check if Admin is able to retreive all Admins with assigned program batches
Given Admin creates GET Request to retrieve all Admins assigned to programs/batches
When Admin sends Get HTTPS Request for UserRoleProgramBatchMap
Then Admin receives 200 Status Code for UserRoleProgramBatchMap

@Get_Program/batch_Admin_validid

Scenario: Check if admin is able to retreive assigned program batches for valid AdminId
Given Admin creates GET Request to retrieve Admin assigned to Program/Batch by AdminId
When Admin sends Get HTTPS Request for UserRoleProgramBatchMap
Then Admin receives 200 Status Code for UserRoleProgramBatchMap

@Get_Program/batch_Admin_invalidid
Scenario: Check if admin is able to retreive assigned program batches for invalid AdminId
Given Admin creates GET Request to retrieve Admin assigned to Program/Batch by invalid AdminID
When Admin sends Get HTTPS Request for UserRoleProgramBatchMap
Then Admin receives 404 Bad request Status Code for UserRoleProgramBatchMap
 
@Delete_validid
Scenario: Check if admin is able to delete the program batch for a Admin
Given Admin creates DELETE Request to delete Admin assigned to program/batch by AdminId
When Admin sends Get DELETE HTTPS Request for UserRoleProgramBatchMap
Then Admin receives 200 Status Code for UserRoleProgramBatchMap
 
 
@Delete_invalidid
Scenario: Check if admin is able to delete the program batch for invalid Admin
Given Admin creates DELETE Request to delete Admin assigned to program/batch by invalid AdminId
When Admin sends Get DELETE HTTPS Request for UserRoleProgramBatchMap
Then Admin receives 404 Bad request Status Code for UserRoleProgramBatchMap


###########noauth########
@GetAllAdminwithProgram/Batch2_noauth
Scenario: Check if admin is able to retreive all Admins with assigned program batches with No Authorization
Given Admin creates GET Request to retreive all Admins with assigned program batches with No Authorization
When Admin sends Get HTTPS Request for UserRoleProgramBatchMap
Then Admin receives status 401 with Unauthorized message for UserRoleProgramBatchMap

@GetProgram/batchDetailsforAdmin3_noauth
Scenario: Check if admin is able to retreive assigned program batches for valid AdminId with No authorization
Given Admin creates GET Request to retreive assigned program batches for valid AdminId with No authorization
When Admin sends Get HTTPS Request for UserRoleProgramBatchMap
Then Admin receives status 401 with Unauthorized message for UserRoleProgramBatchMap
 
@DeleteDeleteprogram/batchassignedtoaAdmin3_noauth
Scenario: Check if admin is able to delete the program batch for valid Admin and No Authorization
Given Admin creates GET Request to delete the program batch for valid Admin and No Authorization
When Admin sends Get DELETE HTTPS Request for UserRoleProgramBatchMap
Then Admin receives status 401 with Unauthorized message for UserRoleProgramBatchMap
  

  
 
 
 
 
 
 
 

 


