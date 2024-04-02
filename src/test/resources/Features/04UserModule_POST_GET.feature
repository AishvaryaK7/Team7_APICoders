Feature: User Controller module Requests
  I want to use this request to create  Admin user,Get Admin user,Update user and delete
 
  @Post&GetRequestsfor_UserController 
  Scenario Outline: Check if admin is able to create a new Admin with valid endpoint and request body with all possible scenarios
    ###POST Request having mandatory,mandatory&additional,invalid data###
    Given Admin creates POST request by reading from Excel
    When Admin sends HTTPS Request with the "<rowNumber>"
    Then Admin receives "<expectedStatusCode>" with response body.
    
    ###Get Request to retrieve all Admin ###
    Given Admin creates GET Request when "<expectedStatusCode>" is 201
    Then Check admin able to retrieve all Admin and created admin is present with valid endpoint when "<expectedStatusCode>" is 201
    
    ###Get Request to retrieve all the available roles ###
    Given Admin creates and sends GET Request to retreive all the available roles  when  "<expectedStatusCode>" is 201
    Then Check if admin is able to retreive all the available roles with valid endpoint  when "<expectedStatusCode>" is 201
    
    ###Get Request to retrieve a Admin with valid Admin ID ###
    Given Admin creates and sends GET Request to retrieve a Admin with valid Admin ID when  "<expectedStatusCode>" is 201
    Then Admin receives 200 OK Status with response body when  "<expectedStatusCode>" is 201
    
    ###Get Request to get count of active and inactive Admins for all roles and for specifiedroles ###
    Given Admin creates and sends GET Request to get count of active and inactive Admins  when  "<expectedStatusCode>" is 201
    Then Check if admin able to to get count of active and inactive Admins with valid endpoint  when "<expectedStatusCode>" is 201
    Given Admin creates and sends GET Request  to get count of active and inactive Admins for specified role id when  "<expectedStatusCode>" is 201
    Then Check if admin able to get count of active and inactive Admins for specified role id with valid endpoint  when "<expectedStatusCode>" is 201
    
    ###Get Request to retreive all active Admins ###
    Given Admin creates and sends GET Request to retreive all active Admins  when  "<expectedStatusCode>" is 201
    Then Check if admin is able to retreive all active Admins with valid endpoint  when "<expectedStatusCode>" is 201 
    
    ###Get Request to retreive Admins by valid role ID ### 
    Given Admin creates and sends GET Request to retreive Admins by valid role ID when  "<expectedStatusCode>" is 201
    Then Check if admin is able to retreive Admins by valid role ID with valid endpoint when "<expectedStatusCode>" is 201   
    
    ###Get Request to retrieve all Admins with roles ### 
    Given Admin creates and sends GET Request to retrieve all Admins with roles when  "<expectedStatusCode>" is 201
    Then Check if admin is able to retrieve all Admins with roles with valid endpoint when "<expectedStatusCode>" is 201
    
    ###Get Request to retrieve all Admins with filters ### 
    Given Admin creates and sends GET Request to retrieve all Admins with filters when  "<expectedStatusCode>" is 201
    Then Check if admin is able to retrieve all Admins with filters with valid endpoint when "<expectedStatusCode>" is 201                                                            
    
        
    Examples:
    | rowNumber | expectedStatusCode |
    | 0 | 201 |
    | 1 | 201 |
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
    | 25 | 400 |
    | 26 | 400 |
    | 27 | 400 |
    | 28 | 400 |
    | 29 | 400 |
    | 30 | 400 |
    | 31 | 400 |
    | 32 | 400 |
    | 33 | 400 |
    | 34 | 400 |
    | 35 | 400 |
    | 36 | 400 |
    | 37 | 400 |
    | 38 | 201 |
    | 39 | 201 |
    | 40 | 201 |
    | 41 | 201 |
    | 42 | 201 |
    | 43 | 201 |
    | 44 | 201 |
    | 45 | 201 |
    | 46 | 201 |
    | 47 | 400 |
    | 48 | 400 |
    | 49 | 400 |
    | 50 | 400 |
    | 51 | 400 |
    | 52 | 400 |
    
    
    @Post&GetRequestsfor_NoAuth 
    Scenario: To Check No Authorization scenarios for all requests(Negative cases)
    
    #POST Request####
    Given Admin sets authorization to No Auth
    When Admin sends HTTPS POST Request with all mandatory fields and additional fields and with va;id endpoint
    Then Admin receives status 401 with Unauthorized message
    
    ##Get Request to retreive all the available roles####
    Given Admin sets authorization to No Auth
    When Admin creates and sends GET Request to retreive all the available roles without Authorization
    Then Admin receives status 401 with Unauthorized message
    
    ##Get Request to retrieve all Admin without Authorization####
    Given Admin sets authorization to No Auth
    When Admin creates and sends GET Request to retrieve all Admin without Authorization
    Then Admin receives status 401 with Unauthorized message
    
    ##Get Request to retrieve a Admin with valid Admin ID without Authorization####
    Given Admin sets authorization to No Auth
    When Admin creates and sends GET Request to retrieve a Admin with valid Admin ID with No authorization
    Then Admin receives status 401 with Unauthorized message
    
    ##Get Request to retrieve all active Admins without Authorization####
    Given Admin sets authorization to No Auth
    When Admin creates and sends GET Request to retrieve all active Admins with No authorization
    Then Admin receives status 401 with Unauthorized message
    
    ##Get Request to get count of active and inactive Admins  without Authorization####
    Given Admin sets authorization to No Auth
    When Admin creates and sends GET Request to get count of active and inactive Admins with No authorization
    Then Admin receives status 401 with Unauthorized message
    
    ##Get Request to get the Admins by program batches for valid batch ID without Authorization####
    Given Admin sets authorization to No Auth
    When Admin creates and sends GET Request to get the Admins by program batches for valid batch ID with No authorization
    Then Admin receives status 401 with Unauthorized message
    
    ##Get Request to get the Admins for valid program Id without Authorization####
    Given Admin sets authorization to No Auth
    When Admin creates and sends GET Request to get the Admins for valid program Id with No authorization
    Then Admin receives status 401 with Unauthorized message
    
    ##Get Request to retreive Admins by valid role ID without Authorization####
    Given Admin sets authorization to No Auth
    When Admin creates and sends GET Request to retreive Admins by valid role ID with No authorization
    Then Admin receives status 401 with Unauthorized message
    
    ##Get Request to retrieve all Admins with roles without Authorization####
    Given Admin sets authorization to No Auth
    When Admin creates and sends GET Request to retrieve all Admins with roles with No authorization
    Then Admin receives status 401 with Unauthorized message
    
    ##Get Request to retrieve all Admins with filters without Authorization####
    Given Admin sets authorization to No Auth
    When Admin creates and sends GET Request to retrieve all Admins with filters No authorization
    Then Admin receives status 401 with Unauthorized message
    
    @Post&GetRequestsfor_invalidEndpoint 
    Scenario: To Check with invalid End point and invalid valus in end point for all Requests
    
    ##Get Request  to retreive all the available roles with invalid End point ###
    When Admin creates and sends GET Request to retreive all the available roles with invalid End point
    Then Admin receives status 404 with Not Found error message
    
    ##Get Request  to retrieve all Admin with invalid endpoint ###
    When Admin creates and sends GET Request to retrieve all Admin with invalid endpoint
    Then Admin receives status 404 with Not Found error message
    
    ##Get Request to retrieve a Admin with valid Admin ID and invalid endpoint and with invalid Admin ID###
    When Admin creates and sends GET Request to retrieve a Admin with valid Admin ID and invalid endpoint
    Then Admin receives status 404 with Not Found error message
    When Admin creates and sends GET Request to retrieve a Admin with invalid Admin ID and valid endpoint
    Then Admin receives status 404 with Not Found error message
    
    ##Get Request  to retrieve all active Admins with invalid endpoint ###
    When Admin creates and sends GET Request to retrieve all active Admins with invalid endpoint
    Then Admin receives status 404 with Not Found error message
    
    ##Get Request  to get count of active and inactive Admins with invalid endpoint and with invalid role ID ###
    When Admin creates and sends GET Request to get count of active and inactive Admins with invalid endpoint
    Then Admin receives status 404 with Not Found error message
    When Admin creates and sends GET Request to get count of active and inactive Admins with invalid role ID and valid endpoint
    Then Admin receives status 404 with Not Found error message
    
    ##Get Request  to get the Admins by program batches for valid batch ID with invalid endpoint and with invalid batch ID ###
    When Admin creates and sends GET Request to get the Admins by program batches for valid batch ID with invalid endpoint
    Then Admin receives status 404 with Not Found error message
    When Admin creates and sends GET Request to get the Admins by program batches for invalid batch ID with valid endpoint
    Then Admin receives status 404 with Not Found error message
    
    ##Get Request  to get the Admins for valid program Id with invalid endpoint and with invalid program Id ###
    When Admin creates and sends GET Request to get the Admins for valid program Id with invalid endpoint
    Then Admin receives status 404 with Not Found error message
    When Admin creates and sends GET Request to get the Admins for invalid program Id with valid endpoint
    Then Admin receives status 404 with Not Found error message
    
    ##Get Request  to retreive Admins by valid role ID with invalid endpoint and with invalid role ID ###
    When Admin creates and sends GET Request to retreive Admins by valid role ID with invalid endpoint
    Then Admin receives status 404 with Not Found error message
    When Admin creates and sends GET Request to get the Admins for invalid role ID with valid endpoint
    Then Admin receives status 404 with Not Found error message
    
    ##Get Request  to retrieve all Admins with roles with invalid endpoint ###
    When Admin creates and sends GET Request to retrieve all Admins with roles with invalid endpoint
    Then Admin receives status 404 with Not Found error message
    
    ##Get Request  to retrieve all Admins with filters with invalid endpoint ###
    When Admin creates and sends GET Request to retrieve all Admins with filters with invalid endpoint
    Then Admin receives status 404 with Not Found error message
    
    
        
    
    
    