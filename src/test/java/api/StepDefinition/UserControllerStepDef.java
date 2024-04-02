package api.StepDefinition;

import static org.testng.Assert.assertEquals;


import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;

import api.Request.UserUpdateRequest;
import api.RequestPayload.UserControllerPayload;
import api.RequestPayload.UserUpdatePayload;
import api.Utilities.BaseClass;
import api.Utilities.Cache;
import api.Utilities.ExcelUtilis;
import api.Utilities.LoggingSetup;
import api.Utilities.TestContextSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class UserControllerStepDef extends BaseClass {
	Response response;
    JSONObject payload;
	Object[][] testData;
	int rowNum;
	String filePath = path.getString("TestDataForUserPost");
	String sheetName = path.getString("User_Post_Sheet");
	String InvalidBearerToken;
	String bearerToken;
	String endpoint;
	String invalidendpointurl;
	String RoleID = "R01";
	String Status = "Active";
	
	String Delete_URL;
	String invaliduserId ="123";
	String InvalidDelete_URL;
	String userId;
	
	
private TestContextSetUp testContext;
	
	public UserControllerStepDef(TestContextSetUp testContext)
	{
		this.testContext = testContext;
		
	}
	

	

	@Given("Admin creates POST request by reading from Excel")
	public void admin_creates_post_request_by_reading_from_excel() throws IOException {
       testData = ExcelUtilis.readDataFromExcel(filePath, sheetName);
        
        
		
	}

	@When("Admin sends HTTPS Request with the {string}")
	public void admin_sends_https_request_with_the(String rowNumber) throws Exception {
		
		payload = UserControllerPayload.PostRequestPayload(testData[Integer.parseInt(rowNumber)]);
	
		rowNum=Integer.parseInt(rowNumber);


//      response = getRequestSpecification((String)Cache.getData("BearerToken")).body(payload).log().body()
//    		  .when().post(routes.getString("User_Post_URL")).then().log().ifValidationFails().extract().response();
		
		response = getRequestSpecification(testContext.getBearerToken()).body(payload).filter(new LoggingSetup())
	    		  .when().post(routes.getString("User_Post_URL"));
    		  
		      
        
		
	}

	@Then("Admin receives {string} with response body.")
	public void admin_receives_with_response_body(String expectedStatusCode) {
		
		if(response.getStatusCode() ==201) {
			String userid=response.jsonPath().getString("userId");
			Cache.setData("userid_"+rowNum, userid);
			Assert.assertEquals( response.getStatusCode(),Integer.parseInt(expectedStatusCode));
			
			
		 //Data Validation
		    Assert.assertEquals( response.jsonPath().getString("userFirstName"),payload.get("userFirstName"));
			Assert.assertEquals( response.jsonPath().getString("userLastName"),payload.get("userLastName"));
			Assert.assertEquals(response.jsonPath().getString("userMiddleName"),payload.get("userMiddleName") );
			Assert.assertEquals(response.jsonPath().getString("userPhoneNumber"),payload.get("userPhoneNumber") );
			Assert.assertEquals(response.jsonPath().getString("userLocation"),payload.get("userLocation"));
			Assert.assertEquals( response.jsonPath().getString("userTimeZone"),payload.get("userTimeZone"));
			Assert.assertEquals(response.jsonPath().getString("userLinkedinUrl"),payload.get("userLinkedinUrl"));
			Assert.assertEquals( response.jsonPath().getString("userEduUg"),payload.get("userEduUg"));
			Assert.assertEquals(response.jsonPath().getString("userEduPg"),payload.get("userEduPg") );
			Assert.assertEquals(response.jsonPath().getString("userVisaStatus"),payload.get("userVisaStatus"));
			Assert.assertEquals(response.jsonPath().getString("userLoginEmail"),UserControllerPayload.userLogin.get("userLoginEmail"));
			
			//SChema validation
	        response.then()
            .assertThat()
                .body(matchesJsonSchemaInClasspath(path.getString("User_Post_JSONSchema")));
	        
	        Assert.assertEquals(response.header("Content-Type"), "application/json");

			
		}
		if(response.getStatusCode() ==400) {
			//System.out.println("400 status line"+response.getStatusLine());
			Assert.assertEquals(Integer.parseInt(expectedStatusCode), response.getStatusCode());
			Assert.assertEquals(response.header("Content-Type"), "application/json");
			
		}
	}
	
	
	
	@Given("Admin creates GET Request when {string} is {int}")
	public void admin_creates_get_request_when_is(String expectedStatusCode, Integer success) {
		if((Integer.parseInt(expectedStatusCode) == success)) {
			    response = getRequestSpecification(testContext.getBearerToken()).filter(new LoggingSetup()).when().get(routes.getString("User_Get_URL_all_Admins"));
			    System.out.println("Get Request Successfull");
		
		}
	}

	@Then("Check admin able to retrieve all Admin and created admin is present with valid endpoint when {string} is {int}")
	public void check_admin_able_to_retrieve_all_admin_and_created_admin_is_present_with_valid_endpoint_when_is(String expectedStatusCode, Integer success) {
		if((Integer.parseInt(expectedStatusCode) == success)) {
		List<String> userids = response.getBody().jsonPath().get("userId");
        Assert.assertTrue(userids.contains(Cache.getData("userid_"+rowNum)));
		Assert.assertEquals(response.header("Content-Type"), "application/json");
		Assert.assertEquals(response.getStatusCode(), 200);
	    System.out.println("user id found in GET all users request:" +Cache.getData("userid_"+rowNum));
	    
		}

	}
	
	@Given("Admin creates and sends GET Request to retreive all the available roles  when  {string} is {int}")
	public void admin_creates_and_sends_get_request_to_retreive_all_the_available_roles_when_is(String expectedStatusCode, Integer success) {
		if((Integer.parseInt(expectedStatusCode) == success)) {
			response = getRequestSpecification(testContext.getBearerToken()).filter(new LoggingSetup()).when().get(routes.getString("User_Get_URL_all_roles"));
		    System.out.println("Get Request for all roles Successfull");
		}
	}

	@Then("Check if admin is able to retreive all the available roles with valid endpoint  when {string} is {int}")
	public void check_if_admin_is_able_to_retreive_all_the_available_roles_with_valid_endpoint_when_is(String expectedStatusCode, Integer success) {
		if((Integer.parseInt(expectedStatusCode) == success)) {
			List<String> roleids = response.getBody().jsonPath().get("roleId");
			ArrayList<String> roles=new ArrayList<String>();
			roles.add("R01");
			roles.add("R02");
			roles.add("R03");
			
			Assert.assertTrue(roleids.containsAll(roles));
			Assert.assertEquals(response.header("Content-Type"), "application/json");
			Assert.assertEquals(response.getStatusCode(), 200);
			
			roleids.clear();
			
			 //System.out.println("user id found in GET all roles request:" +roles);
			}
	}

	@Given("Admin creates and sends GET Request to retrieve a Admin with valid Admin ID when  {string} is {int}")
	public void admin_creates_and_sends_get_request_to_retrieve_a_admin_with_valid_admin_id_when_is(String expectedStatusCode, Integer success) {
		if((Integer.parseInt(expectedStatusCode) == success)) {
			response = getRequestSpecification(testContext.getBearerToken()).filter(new LoggingSetup()).when().get(routes.getString("User_Get_URL_Admin_adminid")+Cache.getData("userid_"+rowNum));
		    System.out.println("Get Request to retrieve a Admin with valid Admin ID Successfull");
		}
	}

	@Then("Admin receives {int} OK Status with response body when  {string} is {int}")
	public void admin_receives_ok_status_with_response_body_when_is(Integer int1, String expectedStatusCode, Integer success) {
		if((Integer.parseInt(expectedStatusCode) == success)) {
			Assert.assertEquals(response.getStatusCode(),200);
			//SChema validation
//	        response.then()
//            .assertThat()
//                .body(matchesJsonSchemaInClasspath("\\SchemaValidation\\CreateUserSchema.json"));
	        
	        Assert.assertEquals(response.header("Content-Type"), "application/json");
			}
	}

	@Given("Admin creates and sends GET Request to get count of active and inactive Admins  when  {string} is {int}")
	public void admin_creates_and_sends_get_request_to_get_count_of_active_and_inactive_admins_when_is(String expectedStatusCode, Integer success) {
		if((Integer.parseInt(expectedStatusCode) == success)) {
			response = getRequestSpecification(testContext.getBearerToken()).filter(new LoggingSetup()).when().get(routes.getString("User_Get_URL_count_active_inactive_Admins"));
		    System.out.println("Get Request to get count of active and inactive Admins Successfull");
		}
	}

	@Then("Check if admin able to to get count of active and inactive Admins with valid endpoint  when {string} is {int}")
	public void check_if_admin_able_to_to_get_count_of_active_and_inactive_admins_with_valid_endpoint_when_is(String expectedStatusCode, Integer success) {
		if((Integer.parseInt(expectedStatusCode) == success)) {
			
			Assert.assertEquals(response.header("Content-Type"), "application/json");
			Assert.assertEquals(response.getStatusCode(), 200);
			
			}
	}
	@Given("Admin creates and sends GET Request  to get count of active and inactive Admins for specified role id when  {string} is {int}")
	public void admin_creates_and_sends_get_request_to_get_count_of_active_and_inactive_admins_for_specified_role_id_when_is(String expectedStatusCode, Integer success) {
		if((Integer.parseInt(expectedStatusCode) == success)) {
			ArrayList<String> roles=new ArrayList<String>();
			roles.add("all");
			roles.add("R01");
			roles.add("R02");
			roles.add("R03");
			for (int i = 0; i < roles.size(); i++) {
				System.out.println(routes.getString("User_Get_URL_count_active_inactive_Admins_ByID")+roles.get(i));
			response = getRequestSpecification(testContext.getBearerToken()).filter(new LoggingSetup()).when().get(routes.getString("User_Get_URL_count_active_inactive_Admins_ByID")+roles.get(i).trim());
			Assert.assertEquals(response.getStatusCode(), 200);
		    System.out.println("Get Request to get count of active and inactive Admins for specified role id:" +roles.get(i));
			}
			
		}
	}

	@Then("Check if admin able to get count of active and inactive Admins for specified role id with valid endpoint  when {string} is {int}")
	public void check_if_admin_able_to_get_count_of_active_and_inactive_admins_for_specified_role_id_with_valid_endpoint_when_is(String expectedStatusCode, Integer success) {
          if((Integer.parseInt(expectedStatusCode) == success)) {
			
			Assert.assertEquals(response.header("Content-Type"), "application/json");
			Assert.assertEquals(response.getStatusCode(), 200);
			
			}
	}
	@Given("Admin creates and sends GET Request to retreive all active Admins  when  {string} is {int}")
	public void admin_creates_and_sends_get_request_to_retreive_all_active_admins_when_is(String expectedStatusCode, Integer success) {
		if((Integer.parseInt(expectedStatusCode) == success)) {
			response = getRequestSpecification(testContext.getBearerToken()).filter(new LoggingSetup()).when().get(routes.getString("User_Get_URL_all_active_Admins"));
		    System.out.println("Get Request to retreive all active Admins  Successfull");
		}
	}

	@Then("Check if admin is able to retreive all active Admins with valid endpoint  when {string} is {int}")
	public void check_if_admin_is_able_to_retreive_all_active_admins_with_valid_endpoint_when_is(String expectedStatusCode, Integer success) {
		if((Integer.parseInt(expectedStatusCode) == success)) {
			List<String> userids = response.getBody().jsonPath().get("userId");
			
			Assert.assertTrue(userids.contains(Cache.getData("userid_"+rowNum)));
			Assert.assertEquals(response.header("Content-Type"), "application/json");
			Assert.assertEquals(response.getStatusCode(), 200);
			
			 System.out.println("user id found in GET all roles request:" +Cache.getData("userid_"+rowNum));
			 
			}
	}
	
	@Given("Admin creates and sends GET Request to retreive Admins by valid role ID when  {string} is {int}")
	public void admin_creates_and_sends_get_request_to_retreive_admins_by_valid_role_id_when_is(String expectedStatusCode, Integer success) {
		if((Integer.parseInt(expectedStatusCode) == success)) {
			response = getRequestSpecification(testContext.getBearerToken()).filter(new LoggingSetup()).when().get(routes.getString("User_Get_URL_Admins_by_valid_roleID"));
		    System.out.println("Get Request to retreive Admins by valid role ID  Successfull");
		}
	}

	@Then("Check if admin is able to retreive Admins by valid role ID with valid endpoint when {string} is {int}")
	public void check_if_admin_is_able_to_retreive_admins_by_valid_role_id_with_valid_endpoint_when_is(String expectedStatusCode, Integer success) {
		if((Integer.parseInt(expectedStatusCode) == success)) {
//			List<String> userids = response.getBody().jsonPath().get("userId");
//			
//			Assert.assertTrue(userids.contains(userid));
			Assert.assertEquals(response.header("Content-Type"), "application/json");
			Assert.assertEquals(response.getStatusCode(), 200);
			
			 //System.out.println("user id found in GET all roles request:" +userid);
			}
	}

	@Given("Admin creates and sends GET Request to retrieve all Admins with roles when  {string} is {int}")
	public void admin_creates_and_sends_get_request_to_retrieve_all_admins_with_roles_when_is(String expectedStatusCode, Integer success) {
		if((Integer.parseInt(expectedStatusCode) == success)) {
			response = getRequestSpecification(testContext.getBearerToken()).filter(new LoggingSetup()).when().get(routes.getString("User_Get_URL_all_Admins_with_roles"));
		    System.out.println("Get Request to retrieve all Admins with roles  Successfull");
		}
	}

	@Then("Check if admin is able to retrieve all Admins with roles with valid endpoint when {string} is {int}")
	public void check_if_admin_is_able_to_retrieve_all_admins_with_roles_with_valid_endpoint_when_is(String expectedStatusCode, Integer success) {
		if((Integer.parseInt(expectedStatusCode) == success)) {
			List<String> userids = response.getBody().jsonPath().get("userId");
			
			Assert.assertTrue(userids.contains(Cache.getData("userid_"+rowNum)));
			Assert.assertEquals(response.header("Content-Type"), "application/json");
			Assert.assertEquals(response.getStatusCode(), 200);
			
			 System.out.println("user id found in GET all roles request:" +Cache.getData("userid_"+rowNum));
			 
			}
	}

	@Given("Admin creates and sends GET Request to retrieve all Admins with filters when  {string} is {int}")
	public void admin_creates_and_sends_get_request_to_retrieve_all_admins_with_filters_when_is(String expectedStatusCode, Integer success) {
		if((Integer.parseInt(expectedStatusCode) == success)) {
			response = getRequestSpecification(testContext.getBearerToken()).filter(new LoggingSetup()).when().get(routes.getString("User_Get_URL_all_Admins_with_filters"));
		    System.out.println("Get Request to retrieve all Admins with filters Successfull");
		}
	}

	@Then("Check if admin is able to retrieve all Admins with filters with valid endpoint when {string} is {int}")
	public void check_if_admin_is_able_to_retrieve_all_admins_with_filters_with_valid_endpoint_when_is(String expectedStatusCode, Integer success) {
		if((Integer.parseInt(expectedStatusCode) == success)) {
			Assert.assertEquals(response.header("Content-Type"), "application/json");
			Assert.assertEquals(response.getStatusCode(), 200);
			
			}
	}
	
	@Given("Admin creates and sends GET Request to get the Admins for valid program Id when  {string} is {int}")
	public void admin_creates_and_sends_get_request_to_get_the_admins_for_valid_program_id_when_is(String expectedStatusCode, Integer success) {
		
			response = getRequestSpecification(testContext.getBearerToken()).filter(new LoggingSetup())
					.when().get(routes.getString("User_Get_URL_Admins_by_program_Id")+Cache.getData("PROGRAMID_7"));
		    System.out.println("Get Request to get the Admins for valid program Id Successfull:"+Cache.getData("PROGRAMID_7"));
		
	}

	@Then("Check if admin is able to get the Admins for valid program Id with valid endpoint when {string} is {int}")
	public void check_if_admin_is_able_to_get_the_admins_for_valid_program_id_with_valid_endpoint_when_is(String expectedStatusCode, Integer success) {
		
			Assert.assertEquals(response.header("Content-Type"), "application/json");
			Assert.assertEquals(response.getStatusCode(), 200);
			
			
	}
	
	@Given("Admin sets authorization to No Auth")
	public void admin_sets_authorization_to_no_auth() {
		InvalidBearerToken="";

	}

	@When("Admin sends HTTPS POST Request with all mandatory fields and additional fields and with va;id endpoint")
	public void admin_sends_https_post_request_with_all_mandatory_fields_and_additional_fields_and_with_va_id_endpoint() throws IOException {
		
				
		response = getRequestSpecification(InvalidBearerToken)
			.body(UserControllerPayload.PostRequestPayload_fornegativecases()).filter(new LoggingSetup()).when().post(routes.getString("User_Post_URL"));
	}

	@Then("Admin receives status {int} with Unauthorized message")
	public void admin_receives_status_with_unauthorized_message(Integer int1) {
		Assert.assertEquals(response.getStatusCode(), 401);
		
	}

	@When("Admin creates and sends GET Request to retreive all the available roles without Authorization")
	public void admin_creates_and_sends_get_request_to_retreive_all_the_available_roles_without_authorization() {
		response = getRequestSpecification(InvalidBearerToken).filter(new LoggingSetup()).when()
				.get(routes.getString("User_Get_URL_all_roles"));
	}
	
	@When("Admin creates and sends GET Request to retrieve all Admin without Authorization")
	public void admin_creates_and_sends_get_request_to_retrieve_all_admin_without_authorization() {
		response = getRequestSpecification(InvalidBearerToken).filter(new LoggingSetup()).when()
				.get(routes.getString("User_Get_URL_all_Admins"));
	}

	@When("Admin creates and sends GET Request to retrieve a Admin with valid Admin ID with No authorization")
	public void admin_creates_and_sends_get_request_to_retrieve_a_admin_with_valid_admin_id_with_no_authorization() {
		response = getRequestSpecification(InvalidBearerToken).filter(new LoggingSetup()).when()
				.get(routes.getString("User_Get_URL_Admin_adminid")+Cache.getData("userid_0"));
	}

	@When("Admin creates and sends GET Request to retrieve all active Admins with No authorization")
	public void admin_creates_and_sends_get_request_to_retrieve_all_active_admins_with_no_authorization() {
		response = getRequestSpecification(InvalidBearerToken).filter(new LoggingSetup()).when()
				.get(routes.getString("User_Get_URL_all_active_Admins"));
	}

	@When("Admin creates and sends GET Request to get count of active and inactive Admins with No authorization")
	public void admin_creates_and_sends_get_request_to_get_count_of_active_and_inactive_admins_with_no_authorization() {
		response = getRequestSpecification(InvalidBearerToken).filter(new LoggingSetup()).when()
				.get(routes.getString("User_Get_URL_count_active_inactive_Admins"));
	}

	@When("Admin creates and sends GET Request to get the Admins by program batches for valid batch ID with No authorization")
	public void admin_creates_and_sends_get_request_to_get_the_admins_by_program_batches_for_valid_batch_id_with_no_authorization() {
		response = getRequestSpecification(InvalidBearerToken).filter(new LoggingSetup()).when()
				.get(routes.getString("User_Get_URL_Admins_by_program_batchesid")+Cache.getData("batchId_0"));
	}

	@When("Admin creates and sends GET Request to get the Admins for valid program Id with No authorization")
	public void admin_creates_and_sends_get_request_to_get_the_admins_for_valid_program_id_with_no_authorization() {
		response = getRequestSpecification(InvalidBearerToken).filter(new LoggingSetup()).when()
				.get(routes.getString("User_Get_URL_Admins_by_program_Id")+Cache.getData("PROGRAMID_7"));
	}

	@When("Admin creates and sends GET Request to retreive Admins by valid role ID with No authorization")
	public void admin_creates_and_sends_get_request_to_retreive_admins_by_valid_role_id_with_no_authorization() {
		response = getRequestSpecification(InvalidBearerToken).filter(new LoggingSetup()).when()
				.get(routes.getString("User_Get_URL_Admins_by_valid_roleID"));
	}

	@When("Admin creates and sends GET Request to retrieve all Admins with roles with No authorization")
	public void admin_creates_and_sends_get_request_to_retrieve_all_admins_with_roles_with_no_authorization() {
		response = getRequestSpecification(InvalidBearerToken).filter(new LoggingSetup()).when()
				.get(routes.getString("User_Get_URL_all_Admins_with_roles"));
	}

	@When("Admin creates and sends GET Request to retrieve all Admins with filters No authorization")
	public void admin_creates_and_sends_get_request_to_retrieve_all_admins_with_filters_no_authorization() {
		response = getRequestSpecification(InvalidBearerToken).filter(new LoggingSetup()).when()
				.get(routes.getString("User_Get_URL_all_Admins_with_filters"));
	}
	
	@When("Admin creates and sends GET Request to retreive all the available roles with invalid End point")
	public void admin_creates_and_sends_get_request_to_retreive_all_the_available_roles_with_invalid_end_point() {
		response = getRequestSpecification(testContext.getBearerToken()).filter(new LoggingSetup()).when()
				.get(routes.getString("User_Get_URL_all_roles")+"/foo");
	}

	@Then("Admin receives status {int} with Not Found error message")
	public void admin_receives_status_with_not_found_error_message(Integer int1) {
		Assert.assertEquals(response.getStatusCode(), 404);
	}

	@When("Admin creates and sends GET Request to retrieve all Admin with invalid endpoint")
	public void admin_creates_and_sends_get_request_to_retrieve_all_admin_with_invalid_endpoint() {
		response = getRequestSpecification(testContext.getBearerToken()).filter(new LoggingSetup()).when()
				.get(routes.getString("User_Get_URL_all_Admins")+"/foo");
	}

	@When("Admin creates and sends GET Request to retrieve a Admin with valid Admin ID and invalid endpoint")
	public void admin_creates_and_sends_get_request_to_retrieve_a_admin_with_valid_admin_id_and_invalid_endpoint() {
		response = getRequestSpecification(testContext.getBearerToken()).filter(new LoggingSetup()).when()
				.get(routes.getString("User_Get_URL_Admin_adminid")+"foo"+Cache.getData("userid_0"));
	}

	@When("Admin creates and sends GET Request to retrieve a Admin with invalid Admin ID and valid endpoint")
	public void admin_creates_and_sends_get_request_to_retrieve_a_admin_with_invalid_admin_id_and_valid_endpoint() {
		response = getRequestSpecification(testContext.getBearerToken()).filter(new LoggingSetup()).when()
				.get(routes.getString("User_Get_URL_Admin_adminid")+"abc123");
	}

	@When("Admin creates and sends GET Request to retrieve all active Admins with invalid endpoint")
	public void admin_creates_and_sends_get_request_to_retrieve_all_active_admins_with_invalid_endpoint() {
		response = getRequestSpecification(testContext.getBearerToken()).filter(new LoggingSetup()).when()
				.get(routes.getString("User_Get_URL_all_active_Admins")+"/foo");
	}

	@When("Admin creates and sends GET Request to get count of active and inactive Admins with invalid endpoint")
	public void admin_creates_and_sends_get_request_to_get_count_of_active_and_inactive_admins_with_invalid_endpoint() {
		response = getRequestSpecification(testContext.getBearerToken()).filter(new LoggingSetup()).when()
				.get(routes.getString("User_Get_URL_count_active_inactive_Admins")+"/foo");
	}

	@When("Admin creates and sends GET Request to get count of active and inactive Admins with invalid role ID and valid endpoint")
	public void admin_creates_and_sends_get_request_to_get_count_of_active_and_inactive_admins_with_invalid_role_id_and_valid_endpoint() {
		response = getRequestSpecification(testContext.getBearerToken()).filter(new LoggingSetup()).when()
				.get(routes.getString("User_Get_URL_count_active_inactive_Admins_ByID")+"ABC01");
	}

	@When("Admin creates and sends GET Request to get the Admins by program batches for valid batch ID with invalid endpoint")
	public void admin_creates_and_sends_get_request_to_get_the_admins_by_program_batches_for_valid_batch_id_with_invalid_endpoint() {
		response = getRequestSpecification(testContext.getBearerToken()).filter(new LoggingSetup()).when()
				.get(routes.getString("User_Get_URL_Admins_by_program_batchesid")+"foo/"+Cache.getData("batchId_0"));
	}

	@When("Admin creates and sends GET Request to get the Admins by program batches for invalid batch ID with valid endpoint")
	public void admin_creates_and_sends_get_request_to_get_the_admins_by_program_batches_for_invalid_batch_id_with_valid_endpoint() {
		response = getRequestSpecification(testContext.getBearerToken()).filter(new LoggingSetup()).when()
				.get(routes.getString("User_Get_URL_Admins_by_program_batchesid")+"00000");
	}

	@When("Admin creates and sends GET Request to get the Admins for valid program Id with invalid endpoint")
	public void admin_creates_and_sends_get_request_to_get_the_admins_for_valid_program_id_with_invalid_endpoint() {
		response = getRequestSpecification(testContext.getBearerToken()).filter(new LoggingSetup()).when()
				.get(routes.getString("User_Get_URL_Admins_by_program_Id")+"foo/"+Cache.getData("PROGRAMID_7"));
	}

	@When("Admin creates and sends GET Request to get the Admins for invalid program Id with valid endpoint")
	public void admin_creates_and_sends_get_request_to_get_the_admins_for_invalid_program_id_with_valid_endpoint() {
		response = getRequestSpecification(testContext.getBearerToken()).filter(new LoggingSetup()).when()
				.get(routes.getString("User_Get_URL_Admins_by_program_Id")+"00000");
	}

	@When("Admin creates and sends GET Request to retreive Admins by valid role ID with invalid endpoint")
	public void admin_creates_and_sends_get_request_to_retreive_admins_by_valid_role_id_with_invalid_endpoint() {
		response = getRequestSpecification(testContext.getBearerToken()).filter(new LoggingSetup()).when()
				.get(routes.getString("User_Get_URL_all_Admins_with_roles")+"/foo/R01");
	}

	@When("Admin creates and sends GET Request to get the Admins for invalid role ID with valid endpoint")
	public void admin_creates_and_sends_get_request_to_get_the_admins_for_invalid_role_id_with_valid_endpoint() {
		response = getRequestSpecification(testContext.getBearerToken()).filter(new LoggingSetup()).when()
				.get(routes.getString("User_Get_URL_all_Admins_with_roles")+"/A666C");
	}

	@When("Admin creates and sends GET Request to retrieve all Admins with roles with invalid endpoint")
	public void admin_creates_and_sends_get_request_to_retrieve_all_admins_with_roles_with_invalid_endpoint() {
		response = getRequestSpecification(testContext.getBearerToken()).filter(new LoggingSetup()).when()
				.get(routes.getString("User_Get_URL_all_Admins_with_roles")+"/foo");
	}

	@When("Admin creates and sends GET Request to retrieve all Admins with filters with invalid endpoint")
	public void admin_creates_and_sends_get_request_to_retrieve_all_admins_with_filters_with_invalid_endpoint() {
		response = getRequestSpecification(testContext.getBearerToken()).filter(new LoggingSetup()).when()
				.get(routes.getString("User_Get_URL_all_Admins_with_filters")+"/foo");
	}
	@Given("Admin creates and sends GET Request to get the Admins by program batches for valid batch ID when  {string} is {int}")
	public void admin_creates_and_sends_get_request_to_get_the_admins_by_program_batches_for_valid_batch_id_when_is(String expectedStatusCode, Integer success) {
		
			response = getRequestSpecification(testContext.getBearerToken()).filter(new LoggingSetup())
					.when().get(routes.getString("User_Get_URL_Admins_by_program_batchesid")+Cache.getData("batchId_0"));
		    System.out.println("Get Request to get the Admins for valid program Id Successfull:"+Cache.getData("batchId_0"));
		
	}

	@Then("Check if admin is able to get the Admins by program batches for valid batch ID with valid endpoint when {string} is {int}")
	public void check_if_admin_is_able_to_get_the_admins_by_program_batches_for_valid_batch_id_with_valid_endpoint_when_is(String expectedStatusCode, Integer success) {
		
			Assert.assertEquals(response.header("Content-Type"), "application/json");
			Assert.assertEquals(response.getStatusCode(), 200);
			
			
	}
	
	
	//-----------PUT REQUEST------------//
	
	@Given("Admin creates PUT request to update Admin details by reading from Excel")
	public void admin_creates_put_request_to_update_admin_details_by_reading_from_excel() {
		try {
			testData = ExcelUtilis.readDataFromExcel(filePath, sheetName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@When("Admin sends HTTPS PUT Request to update Admin details with the {string}")
	public void admin_sends_https_put_request_to_update_admin_details_with_the(String rowNumber) {
       payload = UserControllerPayload.PutRequestPayload(testData[Integer.parseInt(rowNumber)]);
        rowNum=Integer.parseInt(rowNumber);

		response = getRequestSpecification(testContext.getBearerToken()).body(payload).filter(new LoggingSetup())
	    		  .when().put(routes.getString("User_Put_URL")+Cache.getData("userid_0"));
	}

	@Then("Admin receives {string} with response body for Updated user.")
	public void admin_receives_with_response_body_for_updated_user(String expectedStatusCode) {
		if(response.getStatusCode() ==200) {
			
			Assert.assertEquals( response.getStatusCode(),Integer.parseInt(expectedStatusCode));
		 //Data Validation
		    Assert.assertEquals( response.jsonPath().getString("userId"),Cache.getData("userid_0"));
			Assert.assertEquals( response.jsonPath().getString("userFirstName"),payload.get("userFirstName"));
			Assert.assertEquals(response.jsonPath().getString("userLastName"),payload.get("userLastName"));
			//Assert.assertEquals(payload.get("userMiddleName"), response.jsonPath().getString("userMiddleName"));
			Assert.assertEquals(response.jsonPath().getString("userPhoneNumber"),payload.get("userPhoneNumber") );
			Assert.assertEquals( response.jsonPath().getString("userLocation"),payload.get("userLocation"));
			Assert.assertEquals( response.jsonPath().getString("userTimeZone"),payload.get("userTimeZone"));
			Assert.assertEquals( response.jsonPath().getString("userLinkedinUrl"),payload.get("userLinkedinUrl"));
			Assert.assertEquals(response.jsonPath().getString("userEduUg"),payload.get("userEduUg"));
			Assert.assertEquals(response.jsonPath().getString("userEduPg"),payload.get("userEduPg"));
			Assert.assertEquals(response.jsonPath().getString("userComments"),payload.get("userComments"));
			Assert.assertEquals(response.jsonPath().getString("userVisaStatus"),payload.get("userVisaStatus") );
			Assert.assertEquals(response.jsonPath().getString("userLoginEmail"),payload.get("userLoginEmail"));
			
			
			
			//SChema validation
	        response.then()
            .assertThat()
                .body(matchesJsonSchemaInClasspath(path.getString("User_Put_JSONSchema")));
	        
	        Assert.assertEquals(response.header("Content-Type"), "application/json");
	}
		if(response.getStatusCode() ==400) {
			Assert.assertEquals( response.getStatusCode(),Integer.parseInt(expectedStatusCode));
			Assert.assertEquals(response.header("Content-Type"), "application/json");
			
		}
	

	}
	
	
	
	//--PUT_noauthorization---//
	
	@When("Admin creates and sends Put Request to update  admin with No authorization")
	public void admin_creates_and_sends_put_request_to_update_admin_with_no_authorization() {
		response = getRequestSpecification(InvalidBearerToken).body(UserControllerPayload.PutRequestPayload_fornegativecases()).filter(new LoggingSetup())
	    		  .when().put(routes.getString("User_Put_URL")+Cache.getData("userid_0"));
	}
	
	@When("Admin creates and sends Put Request to update admin with invalid adminid")
	public void admin_creates_and_sends_put_request_to_update_admin_with_invalid_adminid() {

		response = getRequestSpecification(testContext.getBearerToken()).body(UserControllerPayload.PutRequestPayload_fornegativecases()).filter(new LoggingSetup())
	    		  .when().put(routes.getString("User_Put_URL")+"124tghhv");
	}
	
	//-------added by aishwarya--------------//
	
	//Update User Role ID
		@Given("Admin creates a PUT request with a request body to update the admin role ID {string}")
		public void admin_creates_a_put_request_with_a_request_body_to_update_the_admin_role_id(String roleId) 
		{
			bearerToken = testContext.getBearerToken();
			setRequestHeader(bearerToken);
			
			payload = UserUpdatePayload.UserRoleListPayload(roleId);
			System.out.println(payload);
		}

		@When("Admin sends the HTTPS request to endpoint for admin Id")
		public void admin_sends_the_https_request_to_endpoint_for_admin_id()
		{
			endpoint = routes.getString("User_Update_RoleID");
			response = UserUpdateRequest.UserUpdateRequestValidID(payload,endpoint);
		}

		@Then("Admin receives a {string} with a response body")
		public void admin_receives_a_with_a_response_body(String expectedStatusCode) 
		{
			Assert.assertEquals(response.getStatusCode(),Integer.parseInt(expectedStatusCode));
		}
		
		//Update User Role ID - Invalid Request Body
		@Given("Admin creates a PUT request to update the admin role ID with a invalid request body")
		public void admin_creates_a_put_request_to_update_the_admin_role_id_with_a_invalid_request_body()
		{
			bearerToken = testContext.getBearerToken();
			setRequestHeader(bearerToken);
			payload= UserUpdatePayload.UserRoleListInvalidPayload(RoleID);
		}

		@Then("Admin receives a {int} Bad Request status with a message and boolean success details")
		public void admin_receives_a_bad_request_status_with_a_message_and_boolean_success_details(int expectedStatusCode) 
		{
			Assert.assertEquals(response.getStatusCode(),expectedStatusCode);
		}
			
		@When("Admin sends the HTTPS request to endpoint for Invalid admin Id")
		public void admin_sends_the_https_request_to_endpoint_for_invalid_admin_id()
		{
			endpoint = routes.getString("User_Update_RoleID");
			response = UserUpdateRequest.UserUpdateRequestInvalidID(payload, endpoint);
		}

		//Update User Role ID - Invalid Endpoint
		@Given("Admin creates a PUT request with a request body to update the admin role ID")
		public void admin_creates_a_put_request_with_a_request_body_to_update_the_admin_role_id()
		{
			bearerToken = testContext.getBearerToken();
			setRequestHeader(bearerToken);
			payload = UserUpdatePayload.UserRoleListPayload(RoleID);
		}

		@When("Admin sends the HTTPS request with invalid endpoint for role ID")
		public void admin_sends_the_https_request_with_invalid_endpoint_for_role_ID() 
		{
			String invalidendpoint = routes.getString("User_Update_RoleID_Invalid_URL");
			response = UserUpdateRequest.UserUpdateRequestValidID(payload,invalidendpoint);
		}

		@Then("Admin receives a {int} Not Found status with a message and boolean success details")
		public void admin_receives_a_not_found_status_with_a_message_and_boolean_success_details(int expectedStatusCode) 
		{
			Assert.assertEquals(response.getStatusCode(),expectedStatusCode);
		}
		
		////Update User Role ID - No Authorization
		@Given("Admin creates a PUT request with a request body to update the admin role ID with no authorization")
		public void admin_creates_a_put_request_with_a_request_body_to_update_the_admin_role_id_with_no_authorization() 
		{
			bearerToken = "";
			setRequestHeader(bearerToken);
			
			payload = UserUpdatePayload.UserRoleListPayload(RoleID);
		}

		@Then("Admin receives a {int} unauthorized status with a message and boolean success details")
		public void admin_receives_a_unauthorized_status_with_a_message_and_boolean_success_details(int expectedStatusCode) 
		{
			Assert.assertEquals(response.getStatusCode(),expectedStatusCode);
		}
		
		//-----------------------------User Role Status Update------------------------------------
		
		//Update User Role Status
		@Given("Admin creates a PUT request with valid data in the request body to update role ID {string} with status {string}")
		public void admin_creates_a_put_request_with_valid_data_in_the_request_body_to_update_role_id_with_status(String roleId, String status) 
		{
			bearerToken = testContext.getBearerToken();
			setRequestHeader(bearerToken);
			payload = UserUpdatePayload.UserRoleStatusPayload(roleId, status);
		}

		@When("Admin sends the HTTPS request to the endpoint for role status")
		public void admin_sends_the_https_request_to_the_endpoint_for_role_status() 
		{
			endpoint = routes.getString("User_Update_Rolestatus");
			response = UserUpdateRequest.UserUpdateRequestValidID(payload, endpoint);
		}

		@Then("Admin receives a {string} with a response message")
		public void admin_receives_a_with_a_response_message(String expectedStatusCode) 
		{
			Assert.assertEquals(response.getStatusCode(),Integer.parseInt(expectedStatusCode));
		}
		
		//Update User Role Status 
		@Given("Admin creates a PUT request with a request body to update the admin role status")
		public void admin_creates_a_put_request_with_a_request_body_to_update_the_admin_role_status()
		{
			bearerToken = testContext.getBearerToken();
			setRequestHeader(bearerToken);
			payload = UserUpdatePayload.UserRoleStatusPayload(RoleID, Status);
		}
		
		//Update User Role Status - Invalid User Id
		@When("Admin sends the HTTPS request to the endpoint for role status with Invalid Admin Id")
		public void admin_sends_the_https_request_to_the_endpoint_for_role_status_with_invalid_admin_id() 
		{
			endpoint = routes.getString("User_Update_Rolestatus");
			response = UserUpdateRequest.UserUpdateRequestInvalidID(payload, endpoint);
		}

		//Update User Role Status - Invalid Endpoint
		@When("Admin sends the HTTPS request with invalid endpoint for role status")
		public void admin_sends_the_https_request_with_invalid_endpoint_for_role_status()
		{
			invalidendpointurl = routes.getString("User_Update_Rolestatus_Invalid_URL");
			response = UserUpdateRequest.UserUpdateRequestValidID(payload, invalidendpointurl);
		}

		//Update User Role Status - No Authorization
		@Given("Admin creates a PUT request with a request body to update the admin role status with no authorization")
		public void admin_creates_a_put_request_with_a_request_body_to_update_the_admin_role_status_with_no_authorization() 
		{
			bearerToken = "";
			setRequestHeader(bearerToken);
			payload = UserUpdatePayload.UserRoleStatusPayload(RoleID, Status);
		}
		
		//-----------------------------User Program Batch Role Update------------------------------------
		
		//Update User with Program/Batch - Valid 
		@Given("Admin creates PUT request with request body for program\\/batch assignment")
		public void admin_creates_put_request_with_request_body_for_program_batch_assignment() 
		{
			bearerToken = testContext.getBearerToken();
			setRequestHeader(bearerToken);
			
			payload = UserUpdatePayload.UserProgramBatchRolePayload(RoleID, Status);
		}

		@When("Admin send the HTTPS request to the endpoint to update Program\\/Batch")
		public void admin_send_the_https_request_to_the_endpoint_to_update_program_batch() 
		{
			endpoint = routes.getString("User_Update_RoleProgramBatchStatus");
			response = UserUpdateRequest.UserUpdateRequestValidID(payload, endpoint);
		}

		@Then("Admin receives a {int} Ok status with a message and boolean success details")
		public void admin_receives_a_ok_status_with_a_message_and_boolean_success_details(int expectedStatusCode) 
		{
			Assert.assertEquals(response.getStatusCode(),expectedStatusCode);
		}
		
		//Update User with Program/Batch - Scenario Outline
		@Given("Admin creates PUT request with request body for program\\/batch assignment with roleId {string} and Status {string}")
		public void admin_creates_put_request_with_request_body_for_program_batch_assignment_with_role_id_and_status(String roleId, String status) 
		{
			bearerToken = testContext.getBearerToken();
			setRequestHeader(bearerToken);
			
			payload = UserUpdatePayload.UserProgramBatchRolePayload(roleId, status);
		}


		//Update User with Program/Batch - Invalid User ID
		@When("Admin send the HTTPS request to the endpoint to update Program\\/Batch with Invalid Admin Id")
		public void admin_send_the_https_request_to_the_endpoint_to_update_program_batch_with_invalid_admin_id()
		{
			endpoint = routes.getString("User_Update_RoleProgramBatchStatus");
			response = UserUpdateRequest.UserUpdateRequestInvalidID(payload, endpoint);
		}

		//Update User with Program/Batch - Invalid Endpoint
		@When("Admin send the HTTPS request with Invalid endpoint to update program\\/batch")
		public void admin_send_the_https_request_with_invalid_endpoint_to_update_program_batch() 
		{
			invalidendpointurl = routes.getString("User_Update_RoleProgramBatch_Invalid_URL");
			response = UserUpdateRequest.UserUpdateRequestValidID(payload, invalidendpointurl);
		}

		//Update User With Program/Batch - No Authorization
		@Given("Admin create a PUT request with request body for program\\/batch assignment with no authorization")
		public void admin_create_a_put_request_with_request_body_for_program_batch_assignment_with_no_authorization() 
		{
			bearerToken = "";
			setRequestHeader(bearerToken);
			
			payload = UserUpdatePayload.UserProgramBatchRolePayload(RoleID, Status);
		}
		
		//-----------------------------Update User Login Status-----------------------------------
		
		//Update User Login Status with User ID
		@Given("Admin creates a PUT request with request body to update Login Status")
		public void admin_creates_a_put_request_with_request_body_to_update_login_status() 
		{
			bearerToken = testContext.getBearerToken();
			setRequestHeader(bearerToken);
			payload = UserUpdatePayload.UserLoginStatusPayload(RoleID, Status);
		}

		@When("Admin sends the HTTPS request to the endpoint to update Login Status")
		public void admin_sends_the_https_request_to_the_endpoint_to_update_login_status() 
		{
			endpoint = routes.getString("User_Upadte_LoginStatus");
			response = UserUpdateRequest.UserUpdateRequestValidID(payload, endpoint);
		}
		
		//Update User Login Status - Scenario Outline
		@Given("Admin creates a PUT request with request body to update Login Status with LoginEmail {string} , Status {string} and password {string}")
		public void admin_creates_a_put_request_with_request_body_to_update_login_status_with_login_email_status_and_password(String email, String status, String password)
		{
			bearerToken = testContext.getBearerToken();
			setRequestHeader(bearerToken);
			
			payload = UserUpdatePayload.UserLoginStatusPayloadScenario(RoleID,email,status,password);
		}

		//Update User Login Status - Invalid User ID
		@When("Admin sends the HTTPS request to the endpoint to update Login Status with Invalid Admin Id")
		public void admin_sends_the_https_request_to_the_endpoint_to_update_login_status_with_invalid_admin_id() 
		{
			endpoint = routes.getString("User_Upadte_LoginStatus");
			response = UserUpdateRequest.UserUpdateRequestInvalidID(payload, endpoint);
		}

		//Update User Login Status with User ID - Invalid Endpoint
		@When("Admin sends the HTTPS request with Invalid endpoint to update Login Status")
		public void admin_sends_the_https_request_with_invalid_endpoint_to_update_login_status() 
		{
			invalidendpointurl = routes.getString("User_Upadte_LoginStatus_Invalid_URL");
			response = UserUpdateRequest.UserUpdateRequestValidID(payload, invalidendpointurl);
		}

		//Update User Login Status with User ID - No Authorization
		@Given("Admin create a PUT request with request body to update Login Status with no authorization")
		public void admin_create_a_put_request_with_request_body_to_update_login_status_with_no_authorization() 
		{
			bearerToken = "";
			setRequestHeader(bearerToken);
			
			payload = UserUpdatePayload.UserLoginStatusPayload(RoleID, Status);
		}

		//Update User Login Status with User ID - Invalid Request Body
		@Given("Admin creates a PUT request to update login status for Admin ID with a invalid request body")
		public void admin_creates_a_put_request_to_update_login_status_for_admin_id_with_a_invalid_request_body() 
		{
			bearerToken = testContext.getBearerToken();
			setRequestHeader(bearerToken);
			
			payload = UserUpdatePayload.UserLoginStatusInvalidPayload(RoleID, Status);
		}
		
		
		
		//-----------------Delete User-----------------//
		//Delete User ID
		@Given("Admin creates DELETE request to delete admin details with a valid Admin ID")
		public void admin_creates_delete_request_to_delete_admin_details_with_a_valid_admin_id() 
		{
			bearerToken = testContext.getBearerToken();
			setRequestHeader(bearerToken);
			userId=(String) Cache.getData("userid_0");
			System.out.println("deleted user:"+(String) Cache.getData("userid_0"));
		
		}

		@When("Admin sends the HTTPS request to the endpoint to Delete User")
		public void admin_sends_the_https_request_to_the_endpoint_to_delete_user() 
		{
			Delete_URL = routes.getString("User_Delete_URL");
			response = given().spec(ReqSpec).pathParam("userID",userId).when().delete(Delete_URL).then().extract().response();
		}

		@Then("Admin receives a {int} OK status with a success message")
		public void admin_receives_a_ok_status_with_a_success_message(int expectedStatusCode) 
		{
			Assert.assertEquals(response.getStatusCode(),expectedStatusCode);
			log.info("Delete user Respose body" +response.getBody().asString());
		}

		//Delete User with Invalid UserID
		@Given("Admin creates DELETE request to delete admin details with an invalid Admin ID")
		public void admin_creates_delete_request_to_delete_admin_details_with_an_invalid_admin_id() 
		{
			bearerToken = testContext.getBearerToken();
			setRequestHeader(bearerToken);
			userId=invaliduserId;
		}

		//Delete User with Invalid Endpoint
		@When("Admin sends the HTTPS request with invalid endpoint to Delete User")
		public void admin_sends_the_https_request_with_invalid_endpoint_to_delete_user() 
		{
			InvalidDelete_URL = routes.getString("User_Delete_URL_Invalid_URL");
			response = given().spec(ReqSpec).pathParam("userID", userId).when().delete(InvalidDelete_URL).then().extract().response();
		}
		
		//Delete User with No Authorization
		@Given("Admin creates DELETE request to delete admin details without authentication")
		public void admin_creates_delete_request_to_delete_admin_details_without_authentication() 
		{
			bearerToken = "";
			setRequestHeader(bearerToken);
			userId=(String) Cache.getData("userid_0");
			
		}
	
	

}
