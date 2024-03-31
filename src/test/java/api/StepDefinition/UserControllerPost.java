package api.StepDefinition;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import api.RequestPayload.UserControllerPayload;
import api.Utilities.BaseClass;
import api.Utilities.Cache;
import api.Utilities.ExcelUtilis;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junit.framework.Assert;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class UserControllerPost extends BaseClass {
	Response response;
    JSONObject payload;
	Object[][] testData;
	
	
	@Given("Admin sets authorization to bearer token")
	public void admin_sets_authorization_to_bearer_token() {
	    login1();
	}

	@Given("Admin creates POST request by reading from Excel")
	public void admin_creates_post_request_by_reading_from_excel() throws IOException {
		String filePath = path.getString("TestDataForUserPost"); 
        String sheetName = path.getString("User_Post_Sheet");
        testData = ExcelUtilis.readDataFromExcel(filePath, sheetName);
        
        
		
	}

	@When("Admin sends HTTPS Request with the {string}")
	public void admin_sends_https_request_with_the(String rowNumber) {
		payload = UserControllerPayload.PostRequestPayload(testData[Integer.parseInt(rowNumber)]);

//      response = getRequestSpecification((String)Cache.getData("BearerToken"))
//        				.body(payload).log().all().when().post(routes.getString("User_Post_URL")).then().log().all().extract().response(); // Replace with your endpoint
//      
      response = getRequestSpecification((String)Cache.getData("BearerToken"))
				.body(payload).when().post(routes.getString("User_Post_URL"));     
        
		
	}

	@Then("Admin receives {string} with response body.")
	public void admin_receives_with_response_body(String expectedStatusCode) {
		
		if(Integer.parseInt(expectedStatusCode) ==201) {
			String userid=response.jsonPath().getString("userId");
			Cache.setData("userid", userid);
		    assertEquals(Integer.parseInt(expectedStatusCode), response.getStatusCode());
		 //Data Validation
		    Assert.assertEquals(payload.get("userFirstName"), response.jsonPath().getString("userFirstName"));
			Assert.assertEquals(payload.get("userLastName"), response.jsonPath().getString("userLastName"));
			Assert.assertEquals(payload.get("userMiddleName"), response.jsonPath().getString("userMiddleName"));
			Assert.assertEquals(payload.get("userPhoneNumber"), response.jsonPath().getString("userPhoneNumber"));
			Assert.assertEquals(payload.get("userLocation"), response.jsonPath().getString("userLocation"));
			Assert.assertEquals(payload.get("userTimeZone"), response.jsonPath().getString("userTimeZone"));
			Assert.assertEquals(payload.get("userLinkedinUrl"), response.jsonPath().getString("userLinkedinUrl"));
			Assert.assertEquals(payload.get("userEduUg"), response.jsonPath().getString("userEduUg"));
			Assert.assertEquals(payload.get("userEduPg"), response.jsonPath().getString("userEduPg"));
			Assert.assertEquals(payload.get("userVisaStatus"), response.jsonPath().getString("userVisaStatus"));
			Assert.assertEquals(UserControllerPayload.userLogin.get("userLoginEmail"), response.jsonPath().getString("userLoginEmail"));
			
			//SChema validation
	        response.then()
            .assertThat()
                .body(matchesJsonSchemaInClasspath(path.getString("User_Post_JSONSchema")));
	        
	        Assert.assertEquals(response.header("Content-Type"), "application/json");

			
		}
		if(Integer.parseInt(expectedStatusCode) ==400) {
			System.out.println("400 status line"+response.getStatusLine());
			assertEquals(Integer.parseInt(expectedStatusCode), response.getStatusCode());
			Assert.assertEquals(response.header("Content-Type"), "application/json");
			
		}
	}
	
	
	
	@Given("Admin creates GET Request when {string} is {int}")
	public void admin_creates_get_request_when_is(String expectedStatusCode, Integer success) {
		if((Integer.parseInt(expectedStatusCode) == success)) {
			    response = getRequestSpecification((String)Cache.getData("BearerToken")).when()
						.get(routes.getString("User_Get_URL_all_Admins"));
			    System.out.println("Get Request Successfull");
		
		}
	}

	@Then("Check admin able to retrieve all Admin and created admin is present with valid endpoint when {string} is {int}")
	public void check_admin_able_to_retrieve_all_admin_and_created_admin_is_present_with_valid_endpoint_when_is(String expectedStatusCode, Integer success) {
		if((Integer.parseInt(expectedStatusCode) == success)) {
		List<String> userids = response.getBody().jsonPath().get("userId");
        Assert.assertTrue(userids.contains(Cache.getData("userid")));
		Assert.assertEquals(response.header("Content-Type"), "application/json");
		Assert.assertEquals(response.getStatusCode(), 200);
	    System.out.println("user id found in GET all users request:" +Cache.getData("userid"));
	    userids.clear();
		}

	}
	
	@Given("Admin creates and sends GET Request to retreive all the available roles  when  {string} is {int}")
	public void admin_creates_and_sends_get_request_to_retreive_all_the_available_roles_when_is(String expectedStatusCode, Integer success) {
		if((Integer.parseInt(expectedStatusCode) == success)) {
			response = getRequestSpecification((String)Cache.getData("BearerToken")).when()
					.get(routes.getString("User_Get_URL_all_roles"));
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
			response = getRequestSpecification((String)Cache.getData("BearerToken")).when()
					.get(routes.getString("User_Get_URL_Admin_adminid")+Cache.getData("userid"));
		    System.out.println("Get Request to retrieve a Admin with valid Admin ID Successfull");
		}
	}

	@Then("Admin receives {int} OK Status with response body when  {string} is {int}")
	public void admin_receives_ok_status_with_response_body_when_is(Integer int1, String expectedStatusCode, Integer success) {
		if((Integer.parseInt(expectedStatusCode) == success)) {
			assertEquals(response.getStatusCode(),200);
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
			response = getRequestSpecification((String)Cache.getData("BearerToken")).when()
					.get(routes.getString("User_Get_URL_count_active_inactive_Admins"));
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
			response = getRequestSpecification((String)Cache.getData("BearerToken")).when()
					.get(routes.getString("User_Get_URL_count_active_inactive_Admins_ByID")+roles.get(i));
			Assert.assertEquals(response.getStatusCode(), 200);
		    System.out.println("Get Request to get count of active and inactive Admins for specified role id:" +roles.get(i));
			}
			roles.clear();
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
			response = getRequestSpecification((String)Cache.getData("BearerToken")).when()
					.get(routes.getString("User_Get_URL_all_active_Admins"));
		    System.out.println("Get Request to retreive all active Admins  Successfull");
		}
	}

	@Then("Check if admin is able to retreive all active Admins with valid endpoint  when {string} is {int}")
	public void check_if_admin_is_able_to_retreive_all_active_admins_with_valid_endpoint_when_is(String expectedStatusCode, Integer success) {
		if((Integer.parseInt(expectedStatusCode) == success)) {
			List<String> userids = response.getBody().jsonPath().get("userId");
			
			Assert.assertTrue(userids.contains(Cache.getData("userid")));
			Assert.assertEquals(response.header("Content-Type"), "application/json");
			Assert.assertEquals(response.getStatusCode(), 200);
			
			 System.out.println("user id found in GET all roles request:" +Cache.getData("userid"));
			 userids.clear();
			}
	}
	
	@Given("Admin creates and sends GET Request to retreive Admins by valid role ID when  {string} is {int}")
	public void admin_creates_and_sends_get_request_to_retreive_admins_by_valid_role_id_when_is(String expectedStatusCode, Integer success) {
		if((Integer.parseInt(expectedStatusCode) == success)) {
			response = getRequestSpecification((String)Cache.getData("BearerToken")).when()
					.get(routes.getString("User_Get_URL_Admins_by_valid_roleID"));
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
			response = getRequestSpecification((String)Cache.getData("BearerToken")).when()
					.get(routes.getString("User_Get_URL_all_Admins_with_roles"));
		    System.out.println("Get Request to retrieve all Admins with roles  Successfull");
		}
	}

	@Then("Check if admin is able to retrieve all Admins with roles with valid endpoint when {string} is {int}")
	public void check_if_admin_is_able_to_retrieve_all_admins_with_roles_with_valid_endpoint_when_is(String expectedStatusCode, Integer success) {
		if((Integer.parseInt(expectedStatusCode) == success)) {
			List<String> userids = response.getBody().jsonPath().get("userId");
			
			Assert.assertTrue(userids.contains(Cache.getData("userid")));
			Assert.assertEquals(response.header("Content-Type"), "application/json");
			Assert.assertEquals(response.getStatusCode(), 200);
			
			 System.out.println("user id found in GET all roles request:" +Cache.getData("userid"));
			 userids.clear();
			}
	}

	@Given("Admin creates and sends GET Request to retrieve all Admins with filters when  {string} is {int}")
	public void admin_creates_and_sends_get_request_to_retrieve_all_admins_with_filters_when_is(String expectedStatusCode, Integer success) {
		if((Integer.parseInt(expectedStatusCode) == success)) {
			response = getRequestSpecification((String)Cache.getData("BearerToken")).when()
					.get(routes.getString("User_Get_URL_all_Admins_with_filters"));
		    System.out.println("Get Request to retrieve all Admins with filters Successfull");
		}
	}

	@Then("Check if admin is able to retrieve all Admins with filters with valid endpoint when {string} is {int}")
	public void check_if_admin_is_able_to_retrieve_all_admins_with_filters_with_valid_endpoint_when_is(String expectedStatusCode, Integer success) {
		if((Integer.parseInt(expectedStatusCode) == success)) {
			Assert.assertEquals(response.header("Content-Type"), "application/json");
			Assert.assertEquals(response.getStatusCode(), 200);
			
			 System.out.println("user id found in GET all roles request:" +Cache.getData("userid"));
			}
	}
	
	@Given("Admin sets authorization to No Auth")
	public void admin_sets_authorization_to_no_auth() {
		login1();

	}

	@When("Admin sends HTTPS POST Request with all mandatory fields and additional fields and with va;id endpoint")
	public void admin_sends_https_post_request_with_all_mandatory_fields_and_additional_fields_and_with_va_id_endpoint() throws IOException {
		
		JSONObject request = new JSONObject();
        request.put("userEduPg", "BA");
		request.put("userEduUg", "MTECH");
		request.put("userFirstName", "SRee");
		request.put("userLastName", "vidya");
		request.put("userLinkedinUrl", "www.linkedin.com");
		request.put("userLocation", "USA");
	
		JSONObject userLogin = new JSONObject();
        userLogin.put("loginStatus", "active");
        String randomString = BaseClass.generateRandomString(6);
        userLogin.put("userLoginEmail", randomString+"_"+ "test.sree@gmail.com");
        userLogin.put("password", "test123");
        //userLogin.put("userLoginEmail", "sree.malineni.72@gmail.com");
       
        request.put("userLogin", userLogin);

        request.put("userMiddleName", "NA");
        Random random = new Random();
        int randomNumber = 10000 + random.nextInt(90000);
        //request.put("userPhoneNumber", row[9]);
        request.put("userPhoneNumber", "89697"+randomNumber);
		
        JSONArray userRoleMapsArray = new JSONArray();
		JSONObject userRoleMap = new JSONObject();
        userRoleMap.put("roleId", "R01");
        userRoleMap.put("userRoleStatus", "active");
        
       
        userRoleMapsArray.add(userRoleMap);

        request.put("userRoleMaps", userRoleMapsArray);
        
		request.put("userTimeZone", "EST");
		request.put("userVisaStatus", "H1");
		request.put("userComments", "test");
		
		response = getRequestSpecificationnoAUTH()
			.body(request).when().post(routes.getString("User_Post_URL"));
	}

	@Then("Admin receives status {int} with Unauthorized message")
	public void admin_receives_status_with_unauthorized_message(Integer int1) {
		Assert.assertEquals(response.getStatusCode(), 401);
		
	}

	@When("Admin creates and sends GET Request to retreive all the available roles without Authorization")
	public void admin_creates_and_sends_get_request_to_retreive_all_the_available_roles_without_authorization() {
		response = getRequestSpecificationnoAUTH().given()
				.get(routes.getString("User_Get_URL_all_roles"));
	}
	
	@When("Admin creates and sends GET Request to retrieve all Admin without Authorization")
	public void admin_creates_and_sends_get_request_to_retrieve_all_admin_without_authorization() {
		response = getRequestSpecificationnoAUTH().given()
				.get(routes.getString("User_Get_URL_all_Admins"));
	}

	@When("Admin creates and sends GET Request to retrieve a Admin with valid Admin ID with No authorization")
	public void admin_creates_and_sends_get_request_to_retrieve_a_admin_with_valid_admin_id_with_no_authorization() {
		response = getRequestSpecificationnoAUTH().given()
				.get(routes.getString("User_Get_URL_Admin_adminid")+Cache.getData("userid"));
	}

	@When("Admin creates and sends GET Request to retrieve all active Admins with No authorization")
	public void admin_creates_and_sends_get_request_to_retrieve_all_active_admins_with_no_authorization() {
		response = getRequestSpecificationnoAUTH().given()
				.get(routes.getString("User_Get_URL_all_active_Admins"));
	}

	@When("Admin creates and sends GET Request to get count of active and inactive Admins with No authorization")
	public void admin_creates_and_sends_get_request_to_get_count_of_active_and_inactive_admins_with_no_authorization() {
		response = getRequestSpecificationnoAUTH().given()
				.get(routes.getString("User_Get_URL_count_active_inactive_Admins"));
	}

	@When("Admin creates and sends GET Request to get the Admins by program batches for valid batch ID with No authorization")
	public void admin_creates_and_sends_get_request_to_get_the_admins_by_program_batches_for_valid_batch_id_with_no_authorization() {
		response = getRequestSpecificationnoAUTH().given()
				.get(routes.getString("User_Get_URL_Admins_by_program_batchesid")+"8554");
	}

	@When("Admin creates and sends GET Request to get the Admins for valid program Id with No authorization")
	public void admin_creates_and_sends_get_request_to_get_the_admins_for_valid_program_id_with_no_authorization() {
		response = getRequestSpecificationnoAUTH().given()
				.get(routes.getString("User_Get_URL_Admins_by_program_Id")+"16213");
	}

	@When("Admin creates and sends GET Request to retreive Admins by valid role ID with No authorization")
	public void admin_creates_and_sends_get_request_to_retreive_admins_by_valid_role_id_with_no_authorization() {
		response = getRequestSpecificationnoAUTH().given()
				.get(routes.getString("User_Get_URL_Admins_by_valid_roleID"));
	}

	@When("Admin creates and sends GET Request to retrieve all Admins with roles with No authorization")
	public void admin_creates_and_sends_get_request_to_retrieve_all_admins_with_roles_with_no_authorization() {
		response = getRequestSpecificationnoAUTH().given()
				.get(routes.getString("User_Get_URL_all_Admins_with_roles"));
	}

	@When("Admin creates and sends GET Request to retrieve all Admins with filters No authorization")
	public void admin_creates_and_sends_get_request_to_retrieve_all_admins_with_filters_no_authorization() {
		response = getRequestSpecificationnoAUTH().given()
				.get(routes.getString("User_Get_URL_all_Admins_with_filters"));
	}
	
	@When("Admin creates and sends GET Request to retreive all the available roles with invalid End point")
	public void admin_creates_and_sends_get_request_to_retreive_all_the_available_roles_with_invalid_end_point() {
		response = getRequestSpecification((String)Cache.getData("BearerToken")).when()
				.get(routes.getString("User_Get_URL_all_roles")+"/foo");
	}

	@Then("Admin receives status {int} with Not Found error message")
	public void admin_receives_status_with_not_found_error_message(Integer int1) {
		Assert.assertEquals(response.getStatusCode(), 404);
	}

	@When("Admin creates and sends GET Request to retrieve all Admin with invalid endpoint")
	public void admin_creates_and_sends_get_request_to_retrieve_all_admin_with_invalid_endpoint() {
		response = getRequestSpecification((String)Cache.getData("BearerToken")).given()
				.get(routes.getString("User_Get_URL_all_Admins")+"/foo");
	}

	@When("Admin creates and sends GET Request to retrieve a Admin with valid Admin ID and invalid endpoint")
	public void admin_creates_and_sends_get_request_to_retrieve_a_admin_with_valid_admin_id_and_invalid_endpoint() {
		response = getRequestSpecification((String)Cache.getData("BearerToken")).given()
				.get(routes.getString("User_Get_URL_Admin_adminid")+"foo"+Cache.getData("userid"));
	}

	@When("Admin creates and sends GET Request to retrieve a Admin with invalid Admin ID and valid endpoint")
	public void admin_creates_and_sends_get_request_to_retrieve_a_admin_with_invalid_admin_id_and_valid_endpoint() {
		response = getRequestSpecification((String)Cache.getData("BearerToken")).given()
				.get(routes.getString("User_Get_URL_Admin_adminid")+"abc123");
	}

	@When("Admin creates and sends GET Request to retrieve all active Admins with invalid endpoint")
	public void admin_creates_and_sends_get_request_to_retrieve_all_active_admins_with_invalid_endpoint() {
		response = getRequestSpecification((String)Cache.getData("BearerToken")).given()
				.get(routes.getString("User_Get_URL_all_active_Admins")+"/foo");
	}

	@When("Admin creates and sends GET Request to get count of active and inactive Admins with invalid endpoint")
	public void admin_creates_and_sends_get_request_to_get_count_of_active_and_inactive_admins_with_invalid_endpoint() {
		response = getRequestSpecification((String)Cache.getData("BearerToken")).given()
				.get(routes.getString("User_Get_URL_count_active_inactive_Admins")+"/foo");
	}

	@When("Admin creates and sends GET Request to get count of active and inactive Admins with invalid role ID and valid endpoint")
	public void admin_creates_and_sends_get_request_to_get_count_of_active_and_inactive_admins_with_invalid_role_id_and_valid_endpoint() {
		response = getRequestSpecification((String)Cache.getData("BearerToken")).given()
				.get(routes.getString("User_Get_URL_count_active_inactive_Admins_ByID")+"ABC01");
	}

	@When("Admin creates and sends GET Request to get the Admins by program batches for valid batch ID with invalid endpoint")
	public void admin_creates_and_sends_get_request_to_get_the_admins_by_program_batches_for_valid_batch_id_with_invalid_endpoint() {
		response = getRequestSpecification((String)Cache.getData("BearerToken")).given()
				.get(routes.getString("User_Get_URL_Admins_by_program_batchesid")+"foo/8554");
	}

	@When("Admin creates and sends GET Request to get the Admins by program batches for invalid batch ID with valid endpoint")
	public void admin_creates_and_sends_get_request_to_get_the_admins_by_program_batches_for_invalid_batch_id_with_valid_endpoint() {
		response = getRequestSpecification((String)Cache.getData("BearerToken")).given()
				.get(routes.getString("User_Get_URL_Admins_by_program_batchesid")+"00000");
	}

	@When("Admin creates and sends GET Request to get the Admins for valid program Id with invalid endpoint")
	public void admin_creates_and_sends_get_request_to_get_the_admins_for_valid_program_id_with_invalid_endpoint() {
		response = getRequestSpecification((String)Cache.getData("BearerToken")).given()
				.get(routes.getString("User_Get_URL_Admins_by_program_Id")+"foo/16213");
	}

	@When("Admin creates and sends GET Request to get the Admins for invalid program Id with valid endpoint")
	public void admin_creates_and_sends_get_request_to_get_the_admins_for_invalid_program_id_with_valid_endpoint() {
		response = getRequestSpecification((String)Cache.getData("BearerToken")).given()
				.get(routes.getString("User_Get_URL_Admins_by_program_Id")+"00000");
	}

	@When("Admin creates and sends GET Request to retreive Admins by valid role ID with invalid endpoint")
	public void admin_creates_and_sends_get_request_to_retreive_admins_by_valid_role_id_with_invalid_endpoint() {
		response = getRequestSpecification((String)Cache.getData("BearerToken")).given()
				.get(routes.getString("User_Get_URL_all_Admins_with_roles")+"/foo/R01");
	}

	@When("Admin creates and sends GET Request to get the Admins for invalid role ID with valid endpoint")
	public void admin_creates_and_sends_get_request_to_get_the_admins_for_invalid_role_id_with_valid_endpoint() {
		response = getRequestSpecification((String)Cache.getData("BearerToken")).given()
				.get(routes.getString("User_Get_URL_all_Admins_with_roles")+"/A666C");
	}

	@When("Admin creates and sends GET Request to retrieve all Admins with roles with invalid endpoint")
	public void admin_creates_and_sends_get_request_to_retrieve_all_admins_with_roles_with_invalid_endpoint() {
		response = getRequestSpecification((String)Cache.getData("BearerToken")).given()
				.get(routes.getString("User_Get_URL_all_Admins_with_roles")+"/foo");
	}

	@When("Admin creates and sends GET Request to retrieve all Admins with filters with invalid endpoint")
	public void admin_creates_and_sends_get_request_to_retrieve_all_admins_with_filters_with_invalid_endpoint() {
		response = getRequestSpecification((String)Cache.getData("BearerToken")).given()
				.get(routes.getString("User_Get_URL_all_Admins_with_filters")+"/foo");
	}



}
