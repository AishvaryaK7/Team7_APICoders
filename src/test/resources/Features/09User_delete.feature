Feature: Delete User 

Scenario: Delete User with a valid Admin ID
    Given Admin creates DELETE request to delete admin details with a valid Admin ID
    When Admin sends the HTTPS request to the endpoint to Delete User
    Then Admin receives a 200 OK status with a success message

Scenario: Delete User with a InValid Admin ID
    Given Admin creates DELETE request to delete admin details with an invalid Admin ID
    When Admin sends the HTTPS request to the endpoint to Delete User
    Then Admin receives a 404 Not Found status with a message and boolean success details
    
Scenario: Delete User with a InValid Endpoint
    Given Admin creates DELETE request to delete admin details with a valid Admin ID
    When Admin sends the HTTPS request with invalid endpoint to Delete User
    Then Admin receives a 404 Not Found status with a message and boolean success details

Scenario: Delete User with No Authorization 
    Given Admin creates DELETE request to delete admin details without authentication
    When Admin sends the HTTPS request to the endpoint to Delete User
    Then Admin receives a 401 unauthorized status with a message and boolean success details