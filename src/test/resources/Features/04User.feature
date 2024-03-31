
Feature: Update Admin RoleId,RoleStatus,Roleprogrambatch status And login status




  Background: 
    Given Admin sets authorization with a valid bearer token

  @tag1RoleID
  Scenario: Update admin role ID with a valid admin ID
    Given Admin creates a PUT request with a valid request body to update the admin role ID
    When Admin sends the HTTPS request to "<endpoint>"
    Then Admin receives a 200 OK status with a response body indicating success

  Scenario: Update admin role ID with a valid admin ID but invalid request body
    Given Admin creates a PUT request with an invalid request body to update the admin role ID
    When Admin sends the HTTPS request with "<endpoint>"
    Then Admin receives a 400 Bad Request status with a message and boolean success details

  Scenario: Update admin role ID with an invalid admin ID
    Given Admin creates a PUT request with a valid request body to update the admin role ID using an invalid admin ID
    When Admin sends the HTTPS request to the  "<endpoint>"
    Then Admin receives a 400 Bad Request status with a message and boolean success detail

  Scenario: Attempt to update an admin role ID to an already existing admin role ID
    Given Admin creates a PUT request attempting to update an admin to a duplicate admin role ID
    When Admin sends the HTTPS request along "<endpoint>"
    Then Admin receives a 400 Bad Request status with the message and boolean success details

  Scenario: Update admin role ID with a valid request to an invalid endpoint
    Given Admin creates the PUT request with a valid request body to update the admin role ID
    When Admin sends the HTTPS request to an invalid "<endpoint>"
    Then Admin receives a 404 Not Found status with a message and boolean success details




  @tag2Rolestatus
  Scenario: Successfully update role status of an admin with valid Admin ID
    Given Admin creates a PUT request with valid data in the request body to update role status1
    When Admin sends the HTTPS request to the  endpoint
    Then Admin receives a 200 OK status with a response message indicating success

  Scenario: Attempt to update role status with valid Admin ID and invalid role status
    Given Admin creates a PUT request with invalid data in the request body for role status2
    When Admin sends the HTTPS request to the  endpoints
    Then Admin receives a 400 Bad Request status with a message and boolean success detail

  Scenario: Attempt to update role status with an invalid Admin ID
    Given Admin creates a PUT request with valid data in the request body but uses an invalid Admin ID
    When Admin sends the HTTPS request to the endpoints
    Then Admin receives a 404 Not Found status with an  message

  Scenario: Attempt to update role status for a non-existing/unassigned RoleID
    Given Admin creates a PUT request targeting a nonexisting/unassigned RoleID in the request body
    When Admin sends the HTTPS request to the  endpoint1
    Then Admin receives a 404 Not Found status with the error message

  Scenario: Attempt to update role status with a valid request to an invalid endpoint
    Given Admin creates a PUT request with valid data in the request body
    When Admin sends the HTTPS request to an invalid endpoint
    Then Admin receives a 404 Not Found status with error message 


  @tag3programbatch
  Scenario: Successfully assign Admin to program/batch with valid Admin ID
    Given Admin creates  PUT request with valid data in request body for program/batch assignment
    When Admin send the HTTPS request to the  endpoint
    Then Admin receives a 200 OK status with response indicating success

  Scenario: Attempt to assign Admin to program/batch with valid Admin ID but invalid data
    Given Admin create a PUT request with invalid data in request body for program/batch assignment
    When Admin send the HTTPS request to  the endpoint3
    Then Admin receives status 400 Bad Request with  message 

  Scenario: Attempt to assign Admin to program/batch with an invalid Admin ID
    Given Admin creates a PUT request with valid data in the request body for program/batch assignment using an invalid Admin ID
    When Admin sends the HTTPS request to the  endpoint4
    Then Admin receive the status 404 Not Found  with error message

  Scenario: Attempt to assign Admin to program/batch with valid Admin ID to an invalid endpoint
    Given Admin creates a PUT request with valid data in the request body for program/batch assignment
    When Admin sends the HTTPS request to an invalid endpoint5
    Then Admin receives the 404 Not Found status with a error message

 @tag4
  Scenario: Successfully update Admin login status with valid Admin ID
    Given Admin creates a PUT request with valid data in the request body to update login status
    When Admin sends the HTTPS request to the endpoint6
    Then Admin receives a 200 OK status

  Scenario: Attempt to update Admin login status with valid Admin ID and invalid data
    Given Admin creates a PUT request with invalid data in the request body to update login status
    When Admin sends the HTTPS request to the  endpoint7
    Then Admin receives a 400 Bad Request status with a message and boolean details as success

  Scenario: Attempt to update Admin login status with invalid Admin ID
    Given Admin creates a PUT request with the valid data in  request body but uses an invalid Admin ID
    When Admin sends the HTTPS request to the endpoint3
    Then Admin receive 404 Not Found status with an error message




