 Feature: UserRoleProgramBatchMapwithNoAuth
 Background:
 Given Admin sets Authorization to No Auth
 @Get All Admin with Program/Batch 2
 Scenario:  Check if admin is able to retreive all Admins with assigned program batches with No Authorization
 Given   Admin creates GET Request to retrieve all Admins assigned to programs/batches
 When    Admin sends HTTPS Request
 Then    Admin receives status 401 with Unauthorized message

 @Get Program/batch Details for Admin 3
 Scenario: Check if admin is able to retreive assigned program batches for valid AdminId with No authorization
 Given   Admin creates GET Request to retrieve Admin assigned to Program/Batch by valid AdminID
 When    Admin sends HTTPS Request
 Then    Admin receives status 401 with Unauthorized message
 
 @Delete Delete program/batch assigned to a Admin 3
 Scenario:  Check if admin is able to delete the program batch for valid Admin and No Authorization
 Given Admin creates DELETE Request to delete Admin assigned to program/batch by valid AdminId
 When  Admin sends HTTPS Request
 Then  Admin receives status 401 with Unauthorized message