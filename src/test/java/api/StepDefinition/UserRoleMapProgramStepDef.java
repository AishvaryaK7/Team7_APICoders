package api.StepDefinition;
		
import api.Request.UserLoginRequest;
import api.Request.UserRoleMapProgramRequest;
import api.Utilities.BaseClass;

import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static io.restassured.RestAssured.baseURI;


	public class UserRoleMapProgramStepDef extends BaseClass {

		 String User_Role_Map_URL;
		 String InvalidLogin_URL;
		 String BaseURL = routes.getString("baseURL");
		 static String AdminId;
		 static boolean NoAUTHFlag=false;
		 

		 @Given("Admin creates GET Request to retrieve all Admins assigned to programs\\/batches")
		 public void admin_creates_GET_Request_to_retrieve_all_Admins_assigned_to_programs_batches() {
			 baseURI = BaseURL;
			 User_Role_Map_URL = routes.getString("User_Role_Program_Batch_Map_Get_URL"); 
		 }

		 @Given("Admin creates GET Request to retrieve Admin assigned to Program\\/Batch by AdminId")
		 public void admin_creates_GET_Request_to_retrieve_Admin_assigned_to_Program_Batch_by_AdminId() {
			 baseURI = BaseURL;
			 User_Role_Map_URL = routes.getString("User_Role_Program_Batch_Map_Get_URL"); 
			 User_Role_Map_URL = User_Role_Map_URL +"/"+AdminId;
		 }

		 @Given("Admin creates GET Request to retrieve Admin assigned to Program\\/Batch by invalid AdminID")
		 public void admin_creates_GET_Request_to_retrieve_Admin_assigned_to_Program_Batch_by_invalid_AdminID() {
			 baseURI = BaseURL;
			 User_Role_Map_URL = routes.getString("User_Role_Program_Batch_Map_Get_URL"); 
			 User_Role_Map_URL = User_Role_Map_URL +"/"+"-1";
		 }


		 @Given("Admin creates DELETE Request to delete Admin assigned to program\\/batch by AdminId")
		 public void admin_creates_DELETE_Request_to_delete_Admin_assigned_to_program_batch_by_AdminId() {
			 baseURI = BaseURL;
			 User_Role_Map_URL = routes.getString("User_Role_Program_Batch_Map_Delete_URL"); 
			 User_Role_Map_URL = User_Role_Map_URL +"/"+AdminId;
			
		 }

		 @Given("Admin creates DELETE Request to delete Admin assigned to program\\/batch by invalid AdminId")
		 public void admin_creates_DELETE_Request_to_delete_Admin_assigned_to_program_batch_by_invalid_AdminId() {
			 baseURI = BaseURL;
			 User_Role_Map_URL = routes.getString("User_Role_Program_Batch_Map_Delete_URL"); 
			 User_Role_Map_URL = User_Role_Map_URL +"/"+"-1";
		 }

			/*
			 * @Then("Admin receives {int}") public void admin_receives(int StatusCode) {
			 * System.out.println("Status code is : " + StatusCode); //
			 * assertEquals(404,StatusCode); assertEquals(StatusCode,
			 * response.getStatusCode()); assertEquals(response.header("Content-Type"),
			 * "application/json"); }
			 */
		 
		@When("Admin sends HTTPS Request")
		public void Admin_sends_HTTPS_Request() 
		{
			if(!NoAUTHFlag) {
				response = UserRoleMapProgramRequest.UserRoleMapProgramRequest(User_Role_Map_URL);
			}
			else 
				response = UserRoleMapProgramRequest.UserRoleMapProgramRequestNOAuth(User_Role_Map_URL);
			if(AdminId == null) {
					AdminId=response.jsonPath().getString("userId[0]");
			}
			//System.out.println("Response Received from Get Program : " + response +" AdminID : " + AdminId); 
		    
		}

		@Then("Admin receives {int} Status Code")
		public void Admin_receives_200_OK(int StatusCode)
		{
			//System.out.print("Status code is : " + StatusCode);
			assertEquals(StatusCode, response.getStatusCode());
		   // assertEquals(200,StatusCode);
			assertEquals(response.header("Content-Type"), "application/json");	
		}
		
		@Given("Admin sets Authorization")
		public void admin_sets_Authorization() {
			baseURI = BaseURL;
		    String Email = "numpyninja@gmail.com";
		    String Passwd= "lmsHackathon@2024";
		    String Login_URL = routes.getString("UserLogin_Post_URL");
			response = UserLoginRequest.postLogin(Email, Passwd,Login_URL);
		}
			
		/****************************** NO Authorization Test Cases *************************************************
		 * ********************************************************************************************************
		 */
		
		
		
		@Given("Admin sets Authorization to No Auth")
		public void admin_sets_Authorization_to_No_Auth() {
			System.out.println("Admin sets Authorization to No Auth ");
			baseURI = BaseURL;
			NoAUTHFlag=true;
		   AdminId = null;
		}

		@Then("Admin receives status {int} with Unauthorized message")
		public void admin_receives_status_with_Unauthorized_message(int StatusCode) {
			System.out.print("Expected Status code is : " + StatusCode + " Actual Status Code : " + response.getStatusCode());
			assertEquals(StatusCode, response.getStatusCode());
		}


		@Given("Admin creates GET Request to retrieve Admin assigned to Program\\/Batch by valid AdminID")
		public void admin_creates_GET_Request_to_retrieve_Admin_assigned_to_Program_Batch_by_valid_AdminID() {
			 baseURI = BaseURL;
			 User_Role_Map_URL = routes.getString("User_Role_Program_Batch_Map_Get_URL"); 
			 User_Role_Map_URL = User_Role_Map_URL +"/"+AdminId;
		}


		@Given("Admin creates DELETE Request to delete Admin assigned to program\\/batch by valid AdminId")
		public void admin_creates_DELETE_Request_to_delete_Admin_assigned_to_program_batch_by_valid_AdminId() {
			 baseURI = BaseURL;
			 User_Role_Map_URL = routes.getString("User_Role_Program_Batch_Map_Delete_URL"); 
			 User_Role_Map_URL = User_Role_Map_URL +"/"+AdminId;
		}
		
	
}