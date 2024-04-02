package api.StepDefinition;
		

import org.testng.Assert;

import api.Request.UserRoleMapProgramRequest;
import api.Utilities.BaseClass;
import api.Utilities.Cache;
import api.Utilities.TestContextSetUp;



import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;





	public class UserRoleMapProgramStepDef extends BaseClass {

		 String User_Role_Map_URL;
		 String User_Role_Map_URL_Delete;
		 String InvalidLogin_URL;
		 
		 String bearerToken;
			String InvalidBearerToken = "";
			private TestContextSetUp testContext;
			
			public UserRoleMapProgramStepDef(TestContextSetUp testContext)
			{
				this.testContext = testContext;
				
			}
		 

		 @Given("Admin creates GET Request to retrieve all Admins assigned to programs\\/batches")
		 public void admin_creates_GET_Request_to_retrieve_all_Admins_assigned_to_programs_batches() {
			 bearerToken = testContext.getBearerToken();
			    setRequestHeader(bearerToken);
			 User_Role_Map_URL = routes.getString("User_Role_Program_Batch_Map_Get_URL"); 
		 }

		 @Given("Admin creates GET Request to retrieve Admin assigned to Program\\/Batch by AdminId")
		 public void admin_creates_GET_Request_to_retrieve_Admin_assigned_to_Program_Batch_by_AdminId() {
			 bearerToken = testContext.getBearerToken();
			    setRequestHeader(bearerToken);
			 User_Role_Map_URL = routes.getString("User_Role_Program_Batch_Map_Get_URL"); 
			 User_Role_Map_URL = User_Role_Map_URL +"/"+Cache.getData("userid_0");
		 }

		 @Given("Admin creates GET Request to retrieve Admin assigned to Program\\/Batch by invalid AdminID")
		 public void admin_creates_GET_Request_to_retrieve_Admin_assigned_to_Program_Batch_by_invalid_AdminID() {
			 bearerToken = testContext.getBearerToken();
			    setRequestHeader(bearerToken);
			 User_Role_Map_URL = routes.getString("User_Role_Program_Batch_Map_Get_URL"); 
			 User_Role_Map_URL = User_Role_Map_URL +"/"+"-1";
		 }


		 @Given("Admin creates DELETE Request to delete Admin assigned to program\\/batch by AdminId")
		 public void admin_creates_DELETE_Request_to_delete_Admin_assigned_to_program_batch_by_AdminId() {
			 bearerToken = testContext.getBearerToken();
			    setRequestHeader(bearerToken);
			 User_Role_Map_URL_Delete = routes.getString("User_Role_Program_Batch_Map_Delete_URL"); 
			 User_Role_Map_URL_Delete = User_Role_Map_URL_Delete +"/"+Cache.getData("userid_0");
			
		 }

		 @Given("Admin creates DELETE Request to delete Admin assigned to program\\/batch by invalid AdminId")
		 public void admin_creates_DELETE_Request_to_delete_Admin_assigned_to_program_batch_by_invalid_AdminId() {
			 bearerToken = testContext.getBearerToken();
			    setRequestHeader(bearerToken);
			    User_Role_Map_URL_Delete = routes.getString("User_Role_Program_Batch_Map_Delete_URL"); 
			    User_Role_Map_URL_Delete = User_Role_Map_URL_Delete +"/"+"-1";
		 }

		 
		 @When("Admin sends Get HTTPS Request for UserRoleProgramBatchMap")
		 public void admin_sends_get_https_request_for_user_role_program_batch_map() {
			
				response = UserRoleMapProgramRequest.GetUserRoleMapProgramRequest(User_Role_Map_URL);
		 }
		 
		 @When("Admin sends Get DELETE HTTPS Request for UserRoleProgramBatchMap")
		 public void admin_sends_get_delete_https_request_for_user_role_program_batch_map() {
			 
			 System.out.println("Userid in userrolebatchprogram:"+Cache.getData("userid_0"));
			 
			 response = UserRoleMapProgramRequest.DeleteUserRoleMapProgramRequest(User_Role_Map_URL_Delete);
			 log.info("Delete program/batch assigned to a Admin" +response.getBody().asString());
		 }



		 
		 @Then("Admin receives {int} Status Code for UserRoleProgramBatchMap")
		 public void admin_receives_status_code_for_user_role_program_batch_map(Integer int1) {
			 Assert.assertEquals(response.getStatusCode(),200);
		 }

		 @Then("Admin receives {int} Bad request Status Code for UserRoleProgramBatchMap")
		 public void admin_receives_bad_request_status_code_for_user_role_program_batch_map(Integer int1) {
			 Assert.assertEquals(response.getStatusCode(),404);
		 }
		

			
		/****************************** NO Authorization Test Cases *************************************************
		 * ********************************************************************************************************
		 */
		
		 

		 @Given("Admin creates GET Request to retreive all Admins with assigned program batches with No Authorization")
		 public void admin_creates_get_request_to_retreive_all_admins_with_assigned_program_batches_with_no_authorization() {
			 setRequestHeader(InvalidBearerToken);
			 User_Role_Map_URL = routes.getString("User_Role_Program_Batch_Map_Get_URL"); 
		 }

		 

		 @Then("Admin receives status {int} with Unauthorized message for UserRoleProgramBatchMap")
		 public void admin_receives_status_with_unauthorized_message_for_user_role_program_batch_map(Integer int1) {
			 Assert.assertEquals( response.getStatusCode(),401);
		 }

		 @Given("Admin creates GET Request to retreive assigned program batches for valid AdminId with No authorization")
		 public void admin_creates_get_request_to_retreive_assigned_program_batches_for_valid_admin_id_with_no_authorization() {
			 setRequestHeader(InvalidBearerToken);
			 User_Role_Map_URL = routes.getString("User_Role_Program_Batch_Map_Get_URL"); 
			 User_Role_Map_URL = User_Role_Map_URL +"/"+Cache.getData("userid_0");
		 }

		 @Given("Admin creates GET Request to delete the program batch for valid Admin and No Authorization")
		 public void admin_creates_get_request_to_delete_the_program_batch_for_valid_admin_and_no_authorization() {
			 setRequestHeader(InvalidBearerToken);
			 User_Role_Map_URL_Delete = routes.getString("User_Role_Program_Batch_Map_Delete_URL"); 
			 User_Role_Map_URL_Delete = User_Role_Map_URL_Delete +"/"+Cache.getData("userid_0");
		 }

}