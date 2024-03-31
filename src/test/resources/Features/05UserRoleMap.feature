Feature: UserRoleProgramBatchMap

Background:
Given Admin sets Authorization

@Get All Admin with Program/Batch 1
Scenario:  Check if Admin is able to retreive all Admins with assigned program batches
Given   Admin creates GET Request to retrieve all Admins assigned to programs/batches
When    Admin sends HTTPS Request
Then    Admin receives 200 OK

@Get Program/batch Details for Admin 1

Scenario:  Check if admin is able to retreive assigned program batches for valid AdminId
Given  Admin creates GET Request to retrieve Admin assigned to Program/Batch by AdminId
When   Admin sends HTTPS Request 
Then   Admin receives 200 OK

 @Get Program/batch Details for Admin 2
 Scenario:  Check if admin is able to retreive assigned program batches for invalid AdminId
 Given      Admin creates GET Request to retrieve Admin assigned to Program/Batch by invalid AdminID
 When       Admin sends HTTPS Request 
 Then       Admin receives 404 OK
 
 @Delete Delete program/batch assigned to a Admin 1
 Scenario: Check if admin is able to delete the program batch for a Admin
 Given  Admin creates DELETE Request to delete Admin assigned to program/batch by AdminId
 When    Admin sends HTTPS Request 
 Then    Admin receives 200 OK
 
 
 @Delete Delete program/batch assigned to a Admin 2
  Scenario:   Check if admin is able to delete the program batch for invalid Admin
  Given     Admin creates DELETE Request to delete Admin assigned to program/batch by invalid AdminId
  When     Admin sends HTTPS Request 
  Then     Admin receives 404

  

  
 
 
 
 
 
 
 

 


