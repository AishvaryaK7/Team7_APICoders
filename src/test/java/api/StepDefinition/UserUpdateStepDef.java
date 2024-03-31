package api.StepDefinition;





import java.io.IOException;

import api.RequestPayload.UserUpdatePayload;

import api.Utilities.*;
import groovyjarjarantlr4.v4.runtime.misc.LogManager;
import io.cucumber.java.en.*;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import api.Request.UserUpdateRequest;
import api.RequestPayload.UserUpdatePayload;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
//import org.junit.Assert;
import org.junit.runner.Request;
public class UserUpdateStepDef extends BaseClass {
	 private Response response;
	   
	 
	  
	   
	   
	   @Given("Admin sets authorization with a valid bearer token")
	   public void admin_sets_authorization_with_a_valid_bearer_token() {
		   setRequestHeader();
	   }

	   
	   // Update Admin RoleId
	   
	   @Given("Admin creates a PUT request with a valid request body to update the admin role ID")
	   public void admin_creates_a_put_request_with_a_valid_request_body_to_update_the_admin_role_id() {
		  

		   UserUpdatePayload.UserRoleListupdate();
		   log.info("Admin creates a PUT request with a valid request body to update the admin role ID");
		   // RequestSpecification request = given().body(payload.toJSONString());
		    
		   
	   }

	   @When("Admin sends the HTTPS request to {string}")
	   public void admin_sends_the_https_request_to(String string) {
		   
		   UserUpdatePayload.UserRoleListupdate();
		   log.info("Admin creates a PUT request with a valid request body to update the admin role ID http");
	   }

	 


	@Then("Admin receives a {int} OK status with a response body indicating success")
	   public void admin_receives_a_ok_status_with_a_response_body_indicating_success(int int1) {
		log.info("Admin creates a PUT request with a valid request body to update the admin role ID success");
		assertEquals(int1,UserUpdatePayload.statuscode());
	   }

	   		
	


	@Given("Admin creates a PUT request with an invalid request body to update the admin role ID")
	   public void admin_creates_a_put_request_with_an_invalid_request_body_to_update_the_admin_role_id() {
		   		    
		    
		UserUpdatePayload.UserRoleListupdateInvalid();
		log.info("Admin creates a PUT request with a invalid request body to update the admin role ID");
		    
	   }

	   @When("Admin sends the HTTPS request with {string}")
	   public void admin_sends_the_https_request_with(String string) {
		   UserUpdatePayload. userroleupdate();
		   log.info("Admin creates a PUT request with a invalid request body to update the admin role ID http");
	   }

	   @Then("Admin receives a {int} Bad Request status with a message and boolean success details")
	   public void admin_receives_a_bad_request_status_with_a_message_and_boolean_success_details(int int1) {
		   assertEquals(int1,UserUpdatePayload.statuscode());
		   log.info("Admin creates a PUT request with a invalid request body to update the admin role ID bad");
		   	   }

	@Given("Admin creates a PUT request with a valid request body to update the admin role ID using an invalid admin ID")
	   public void admin_creates_a_put_request_with_a_valid_request_body_to_update_the_admin_role_id_using_an_invalid_admin_id() {
		 UserUpdatePayload.UserRoleListupdate();
		   
		 log.info("Admin creates a PUT request with a valid request body to update the admin role ID  invalid admin");
		    
	   }

	   @When("Admin sends the HTTPS request to the  {string}")
	   public void admin_sends_the_https_request_to_the(String string) {
		   UserUpdatePayload. userroleupdateinvalid();
		   
			 log.info("Admin creates a PUT request with a valid request body to update the admin role ID  invalid admin http");
	   }

	   @Then("Admin receives a {int} Bad Request status with a message and boolean success detail")
	   public void admin_receives_a_bad_request_status_with_a_message_and_boolean_success_detail(int int1) {
		   assertEquals(int1,UserUpdatePayload.statuscode());
		   
			 log.info("Admin creates a PUT request with a valid request body to update the admin role ID  invalid admin bad request");
	   }

	   @Given("Admin creates a PUT request attempting to update an admin to a duplicate admin role ID")
	   public void admin_creates_a_put_request_attempting_to_update_an_admin_to_a_duplicate_admin_role_id() {
		  
		   UserUpdatePayload.UserRoleListupdate();
		   
			 log.info("Admin creates a PUT request attempting to update an admin to a duplicate admin role ID"); 
	
	  
	   }

	   @When("Admin sends the HTTPS request along {string}")
	   public void admin_sends_the_https_request_along(String string) {
		   UserUpdatePayload. userroleupdate();
			 log.info("Admin creates a PUT request attempting to update an admin to a duplicate admin role ID http");
	   }

	   @Then("Admin receives a {int} Bad Request status with the message and boolean success details")
	   public void admin_receives_a_bad_request_status_with_the_message_and_boolean_success_details(int int1) {
		   assertEquals(int1,UserUpdatePayload.statuscode());
			 log.info("Admin creates a PUT request attempting to update an admin to a duplicate admin role ID http status");
		   }

	   @Given("Admin creates the PUT request with a valid request body to update the admin role ID")
	   public void admin_creates_the_put_request_with_a_valid_request_body_to_update_the_admin_role_id() {
		   

		   UserUpdatePayload.UserRoleListupdate();
		    
			 log.info("Admin creates the PUT request with a valid request body to update the admin role ID");
	  
	   }

	   @When("Admin sends the HTTPS request to an invalid {string}")
	   public void admin_sends_the_https_request_to_an_invalid(String string) {
		   UserUpdatePayload. userroleupdateinvalid();
		   log.info("Admin creates the PUT request with a valid request body to update the admin role ID invalid http");
	  }
	   

	   @Then("Admin receives a {int} Not Found status with a message and boolean success details")
	   public void admin_receives_a_not_found_status_with_a_message_and_boolean_success_details(int int1) {
		   assertEquals(int1,UserUpdatePayload.statuscode());
		   log.info("Admin creates the PUT request with a valid request body to update the admin role ID 404 not found");
		   
	   }

	   
	   
	   
	   
	   
	   // Update Admin RoleStatus
	   
	   
	   @Given("Admin creates a PUT request with valid data in the request body to update role status1")
	   public void admin_creates_a_put_request_with_valid_data_in_the_request_body_to_update_role_status1() {
		   UserUpdateRequest.RolestatusUpdateUsers();
		   log.info("Admin creates a PUT request with valid data in the request body to update role status1");
	          
	   }

	

	@When("Admin sends the HTTPS request to the  endpoint01")
	   public void admin_sends_the_https_request_to_the_endpoint01() {
		 UserUpdatePayload.userrolestatusupdate(); 
		  log.info("Admin creates a PUT request with valid data in the request body to update role status1 http");
	   }

	   @Then("Admin receives a {int} OK status with a response message indicating success")
	   public void admin_receives_a_ok_status_with_a_response_message_indicating_success(int int1) {
		   assertEquals(int1,UserUpdatePayload.statuscode()); 
		   log.info("Admin creates a PUT request with valid data in the request body to update role status1 status code");
	   }

	   @Given("Admin creates a PUT request with invalid data in the request body for role status2")
	   public void admin_creates_a_put_request_with_invalid_data_in_the_request_body_for_role_status2() {
		   
		   UserUpdateRequest.RolestatusUpdateUsersInvalidData();
		   log.info("Admin creates a PUT request with invalid data in the request body to update role status1");
		   
	   }

	   @When("Admin sends the HTTPS request to the  endpoint2")
	   public void admin_sends_the_https_request_to_the_endpoint2() {
		   UserUpdatePayload.userrolestatusupdateinvalid();
		   log.info("Admin creates a PUT request with invalid data in the request body to update role status1 http");
	   }

	   @Then("Admin receives a {int} OK status with a response message indicating success")
	   public void admin_receives_the_ok_status_with_a_response_message_indicating_success(int int1) {
		   assertEquals(int1,UserUpdatePayload.statuscode());
		   log.info("Admin creates a PUT request with invalid data in the request body to update role status1 status code");
	   }
	   
	   @Given("Admin creates a PUT request with valid data in the request body but uses an invalid Admin ID")
	   public void admin_creates_a_put_request_with_valid_data_in_the_request_body_but_uses_an_invalid_admin_id() {
		   UserUpdateRequest.RolestatusUpdateUsersInvalidData();
		   log.info("Admin creates a PUT request with valid data in the request body but uses an invalid Admin ID");
		   
		   
	   }

	   @When("Admin sends the HTTPS request to the endpoints")
	   public void admin_sends_the_https_request_to_the_endpoints() {
		   UserUpdatePayload.userrolestatusupdateinvalid();
		   log.info("Admin creates a PUT request with valid data in the request body but uses an invalid Admin ID  http");
		   
	   }

	   @Then("Admin receives a {int} Not Found status with an  message")
	   public void admin_receives_a_not_found_status_with_an_message(int int1) {
		   assertEquals(int1,UserUpdatePayload.statuscode());
		   log.info("Admin creates a PUT request with valid data in the request body but uses an invalid Admin ID status code");
		   
		   }

	   @Given("Admin creates a PUT request targeting a nonexisting\\/unassigned RoleID in the request body")
	   public void admin_creates_a_put_request_targeting_a_nonexisting_unassigned_role_id_in_the_request_body() {
	      
		   UserUpdateRequest.RolestatusUpdateUsersInvalidData();
		   
		   log.info("Admin creates a PUT request targeting a nonexisting\\\\/unassigned RoleID in the request body");
		   
	   }

	   @When("Admin sends the HTTPS request to the  endpoint1")
	   public void admin_sends_the_https_request_to_the_endpoint1() {
		   UserUpdatePayload.userrolestatusupdateinvalid();
		   log.info("Admin creates a PUT request targeting a nonexisting\\\\/unassigned RoleID in the request body http ");
	   }

	   @Then("Admin receives a {int} Not Found status with the error message")
	   public void admin_receives_a_not_found_status_with_the_error_message(int int1) {
		   assertEquals(int1,UserUpdatePayload.statuscode());
		   log.info("Admin creates a PUT request targeting a nonexisting\\\\/unassigned RoleID in the request body http status code ");
		   
	   }

	   @Given("Admin creates a PUT request with valid data in the request body")
	   public void admin_creates_a_put_request_with_valid_data_in_the_request_body() {
		    
		
		   UserUpdateRequest.RolestatusUpdateUsers(); 
		   log.info("Admin creates a PUT request with valid data in the request body");
		   
	   }

	   @When("Admin sends the HTTPS request to an invalid endpoint")
	   public void admin_sends_the_https_request_to_an_invalid_endpoint() {
		   UserUpdatePayload.userrolestatusupdateinvalid();
		   log.info("Admin creates a PUT request with valid data in the request body http");
	   }

	   @Then("Admin receives a {int} Not Found status with error message")
	   public void admin_receives_a_not_found_status_with_error_message(int int1) {
		   
		   assertEquals(int1,UserUpdatePayload.statuscode());
		   log.info("Admin creates a PUT request with valid data in the request body http status code");
		   }

	   
	   
	   
	   
	   
	    // Update Admin RoleProgramBatchStatus
	   
	   
	   
	   @Given("Admin creates  PUT request with valid data in request body for program\\/batch assignment")
	   public void admin_creates_put_request_with_valid_data_in_request_body_for_program_batch_assignment() throws IOException {
	       
		   UserUpdateRequest.RoleProgramBatchStatusUpdate();
		   log.info("Admin creates  PUT request with valid data in request body for program\\\\/batch assignment");
		   
		  
	   }

	   @When("Admin send the HTTPS request to the  endpoint")
	   public void admin_send_the_https_request_to_the_endpoint() {
		   UserUpdatePayload.userroleprogrambatchstatusupdate();
		   log.info("Admin creates  PUT request with valid data in request body for program\\\\/batch assignment http");
	   }

	   @Then("Admin receives a {int} OK status with response indicating success")
	   public void admin_receives_a_ok_status_with_response_indicating_success(int int1) {
		   assertEquals(int1,UserUpdatePayload.statuscode());
		   log.info("Admin creates  PUT request with valid data in request body for program\\\\/batch assignment http statuscode");
		   
	   }

	   @Given("Admin create a PUT request with invalid data in request body for program\\\\/batch assignment")
	   public void admin_create_a_put_request_with_invalid_data_in_request_body_for_program_batch_assignment() throws IOException {
		   UserUpdateRequest.RoleProgramBatchStatusInvalidUpdate();
		   log.info("Admin create a PUT request with invalid data in request body for program\\\\/batch assignment");
	   }

	   @When("Admin send the HTTPS request to  the endpoint3")
	   public void admin_send_the_https_request_to_the_endpoint3() {
		   UserUpdatePayload.userroleprogrambatchstatusinvalidupdate();
		   log.info("Admin create a PUT request with invalid data in request body for program\\\\/batch assignment http");
		   
	   }

	   @Then("Admin receives status {int} Bad Request with  message")
	   public void admin_receives_status_bad_request_with_message(int int1) {
		   assertEquals(int1,UserUpdatePayload.statuscode());
		   log.info("Admin create a PUT request with invalid data in request body for program\\\\/batch assignment http success");

	   }

	   @Given("Admin creates a PUT request with valid data in the request body for program\\/batch assignment using an invalid Admin ID")
	   public void admin_creates_a_put_request_with_valid_data_in_the_request_body_for_program_batch_assignment_using_an_invalid_admin_id() throws IOException {
		   UserUpdateRequest.RoleProgramBatchStatusInvalidUpdate();
		log.info("Admin creates a PUT request with valid data in the request body for program\\/batch assignment using an invalid Admin ID");
		   
	   }

	   @When("Admin sends the HTTPS request to the  endpoint4")
	   public void admin_sends_the_https_request_to_the_endpoint4() {
		   UserUpdatePayload.userroleprogrambatchstatusinvalidupdate();
		   log.info("Admin creates a PUT request with valid data in the request body for program\\/batch assignment using an invalid Admin ID http");
		   
	   }

	   @Then("Admin receive the status {int} Not Found  with error message")
	   public void admin_receive_the_status_not_found_with_error_message(int int1) {
		   assertEquals(int1,UserUpdatePayload.statuscode());
		   log.info("Admin creates a PUT request with valid data in the request body for program\\/batch assignment using an invalid Admin ID status code");
	   }

	   @Given("Admin creates a PUT request with valid data in the request body for program\\/batch assignment")
	   public void admin_creates_a_put_request_with_valid_data_in_the_request_body_for_program_batch_assignment() throws IOException {
		   UserUpdateRequest.RoleProgramBatchStatusUpdate();
		   log.info("Admin creates a PUT request with valid data in the request body for program\\\\/batch assignment");
		    
	   }

	   @When("Admin sends the HTTPS request to an invalid endpoint5")
	   public void admin_sends_the_https_request_to_an_invalid_endpoint5() {
		   UserUpdatePayload.userroleprogrambatchstatusupdate();
		   log.info("Admin creates a PUT request with invalid data in the request body for program\\\\/batch assignment");
	   }

	   @Then("Admin receives the {int} Not Found status with a error message")
	   public void admin_receives_the_not_found_status_with_a_error_message(int int1) {
		   assertEquals(int1,UserUpdatePayload.statuscode());
		   log.info("Admin creates a PUT request with invalid data in the request body for program\\\\/batch assignment status code");
		   }
	   
	   
	   
	   
	   // Update Admin LoginStatus

	   @Given("Admin creates a PUT request with valid data in the request body to update login status")
	   public void admin_creates_a_put_request_with_valid_data_in_the_request_body_to_update_login_status() {
		  
		   UserUpdateRequest.loginUpdateUsers();
		   log.info("Admin creates a PUT request with valid data in the request body to update login status");

	   }

	   @When("Admin sends the HTTPS request to the endpoint6")
	   public void admin_sends_the_https_request_to_the_endpoint6() {
		   UserUpdatePayload.userloginstatusupdate();
		   log.info("Admin creates a PUT request with valid data in the request body to update login status http");
	   }

	   @Then("Admin receives a {int} OK status")
	   public void admin_receives_a_ok_status(int int1) {
		   assertEquals(int1,UserUpdatePayload.statuscode());
		   log.info("Admin creates a PUT request with valid data in the request body to update login status http status code");
		   }

	   @Given("Admin creates a PUT request with invalid data in the request body to update login status")
	   public void admin_creates_a_put_request_with_invalid_data_in_the_request_body_to_update_login_status() {
		   UserUpdateRequest.loginInvalidUpdateUsers();
		   log.info("Admin creates a PUT request with invalid data in the request body to update login status");
	   }

	   @When("Admin sends the HTTPS request to the  endpoint7")
	   public void admin_sends_the_https_request_to_the_endpoint7() {
		   UserUpdatePayload.userloginstatusinvalidupdate();
		   log.info("Admin creates a PUT request with invalid data in the request body to update login status http");
	   }

	   @Then("Admin receives a {int} Bad Request status with a message and boolean details as success")
	   public void admin_receives_a_bad_request_status_with_a_message_and_boolean_details_as_success(int int1) {
		   UserUpdatePayload.statuscode();
		   log.info("Admin creates a PUT request with invalid data in the request body to update login status http status code");
	   }

	   @Given("Admin creates a PUT request with the valid data in  request body but uses an invalid Admin ID")
	   public void admin_creates_a_put_request_with_the_valid_data_in_request_body_but_uses_an_invalid_admin_id() {
		   UserUpdateRequest.loginInvalidUpdateUsers();
		   
		   log.info("Admin creates a PUT request with the valid data in  request body but uses an invalid Admin ID");
	   }

	   @When("Admin sends the HTTPS request to the endpoint3")
	   public void admin_sends_the_https_request_to_the_endpoint3() {
		   UserUpdatePayload.userloginstatusinvalidupdate();
		   log.info("Admin creates a PUT request with the valid data in  request body but uses an invalid Admin ID http");
	   }

	   @Then("Admin receive {int} Not Found status with an error message")
	   public void admin_receive_not_found_status_with_an_error_message(int int1) {
		   //UserUpdatePayload.statuscode(); 
		   assertEquals(int1,UserUpdatePayload.statuscode());
		   log.info("Admin creates a PUT request with the valid data in  request body but uses an invalid Admin ID http status code");
	   }
	   

}	

