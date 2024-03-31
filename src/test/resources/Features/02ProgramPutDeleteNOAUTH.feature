Feature: ProgramPutDeleteNOAuth

Background:
Given Admin sets Authorization to No Auth

@Put Update  by Program Id
Scenario:Check if Admin able to update a program without Authorization
Given Admin creates PUT Request for the LMS API endpoint with request Body with mandatory , additional  fields. 
When Admin sends HTTPS Request with valid endpoint
Then Admin receives 401 Unauthorized

@Put Update by Program Name
Scenario: Check if Admin able to update a program without Authorization
Given Admin creates PUT Request for the LMS API endpoint with request Body with mandatory , additional  fields. 
When Admin sends HTTPS Request with valid endpoint
Then Admin receives 401 Unauthorized

@Delete by Program Name
Scenario: Check if Admin able to delete a program without Authorization
Given Admin creates DELETE Request for the LMS API endpoint  and  valid programName
When Admin sends HTTPS Request with endpoint
Then Admin receives 401 Unauthorized

@Delete by Program Id
Scenario: Check if Admin able to delete a program without Authorization
Given Admin creates DELETE Request for the LMS API endpoint  and  valid programId
When Admin sends HTTPS Request with endpoint
Then Admin receives 401 Unauthorized



