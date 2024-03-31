package api.StepDefinition;
import org.json.simple.JSONObject;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import java.io.IOException;
import junit.framework.Assert;
import api.Request.ProgramRequest;
import api.RequestPayload.ProgramPayload;
import api.Utilities.BaseClass;
import api.Utilities.ExcelUtilis;
import api.Utilities.TestContextSetUp;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class Program_stepDef extends BaseClass{
	static Response response;
    JSONObject payload;
	Object[][] testData;
	String filePath = "..\\RestAssusred_March2024\\src\\test\\resources\\TestData\\TestData.xlsx";
    String sheetName = "ProgramController";
    String endpoint;
    static int responseStatusCode;
   	String BaseURL = routes.getString("baseURL"); 
	private static TestContextSetUp testContext;
		
	public Program_stepDef(TestContextSetUp testContext)
	{
		this.testContext = testContext;
	}
	
	@Given("Admin creates POST request by reading from Excel {string} with auth")
	public void admin_creates_post_request_by_reading_from_excel(String string) {
        try {
			testData = ExcelUtilis.readDataFromExcel(filePath, sheetName);
			payload = ProgramPayload.createRequestObject(testData[Integer.parseInt(string)]);
			setRequestHeader(testContext.getBearerToken());   
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@When("Admin sends HTTPS Request")
	public void admin_sends_HTTPS_Request() {
		System.out.println("token ::::::: " + Token);
	    response = given().spec(ReqSpec).body(payload).when().post(routes.getString("Program_Post_URL")); 
        System.out.println(response.asPrettyString());
	}
	
	@Then("Admin receives {string} Created Status with response body")
	public void admin_receives_Created_Status_with_response_body(String expectedStatusCode) {
		if(Integer.parseInt(expectedStatusCode) == 201) {
			PROGRAMID = response.jsonPath().get("programId");
			PROGRAMNAME = response.jsonPath().get("programName");	
			//Data Validation
		    Assert.assertEquals(payload.get("programDescription"), response.jsonPath().getString("programDescription"));
		    Assert.assertEquals(payload.get("programName"), response.jsonPath().getString("programName"));
		    Assert.assertEquals(payload.get("programStatus"), response.jsonPath().getString("programStatus"));
			
		  //Schema validation
	       response.then()
	       .assertThat().body(matchesJsonSchemaInClasspath(path.getString("Program_Post_JSONSchema")));        
	       Assert.assertEquals(response.header("Content-Type"), "application/json");
		}
		if(Integer.parseInt(expectedStatusCode) == 400) {
			System.out.println("400 status line"+response.getStatusLine());
			assertEquals(Integer.parseInt(expectedStatusCode), response.getStatusCode());
			Assert.assertEquals(response.header("Content-Type"), "application/json");		
		}
	}
	
	@Given("Admin creates POST Request for the LMS with request body with auth")
	public void admin_creates_post_request_for_the_lms_with_request_body_with_auth() {
        try {
			testData = ExcelUtilis.readDataFromExcel(filePath, sheetName);
			payload = ProgramPayload.createRequestObject(testData[0]);
			setRequestHeader(testContext.getBearerToken());   
		    System.out.println(payload);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	@When("Admin sends HTTPS Request and request Body with invalid endpoint")
	public void admin_sends_https_request_and_request_body_with_invalid_endpoint() {
	    response = given().spec(ReqSpec).body(payload).when().post("InvalidEndPoint"); 
	}

	@Then("Admin receives {int} Status")
	public void admin_receives_status(int expectedStatusCode) {
	    if(expectedStatusCode == 200) {
			System.out.println("200 status line : "+response.getStatusCode());
			assertEquals(expectedStatusCode, response.getStatusCode());
			Assert.assertEquals(response.header("Content-Type"), "application/json");		
		} 
		if(expectedStatusCode == 400) {
			System.out.println("400 status line : "+response.getStatusCode());
			assertEquals(expectedStatusCode, response.getStatusCode());
			Assert.assertEquals(response.header("Content-Type"), "application/json");		
		} 
		if(expectedStatusCode == 401) {
			System.out.println("401 status line : "+response.getStatusCode());
			assertEquals(expectedStatusCode, response.getStatusCode());
			Assert.assertEquals(response.header("Content-Type"), "application/json");		
		} 		
	}
	
	@Given("Admin creates POST Request for the LMS with request body with existing program name with auth")
	public void admin_creates_post_request_for_the_lms_with_request_body_with_existing_program_name_with_auth() {
		try {
			testData = ExcelUtilis.readDataFromExcel(filePath, sheetName);
			payload = ProgramPayload.createRequestObject(testData[0]);
			payload.put("programName", PROGRAMNAME);
			setRequestHeader(testContext.getBearerToken());   
		    System.out.println(payload);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
	}
	
	@When("Admin sends HTTPS Request and request Body with endpoint with invalid method")
	public void admin_sends_https_request_and_request_body_with_endpoint_with_invalid_method() {
		response = given().spec(ReqSpec).body(payload).when().get(routes.getString("Program_Post_URL"));
	}
	
	@Given ("Admin creates POST Request for the LMS with invalid request body with auth")
	public void Admin_creates_POST_Request_for_the_LMS_with_invalid_request_body_with_auth(){
		try {
			testData = ExcelUtilis.readDataFromExcel(filePath, sheetName);
			payload = ProgramPayload.createRequestObject(testData[0]);
			payload.put("programName", (int) Math.floor(Math.random() * (1000)+1));	
			//setRequestHeader(Token);
			setRequestHeader(testContext.getBearerToken());   
		    System.out.println(payload);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	@Given("Admin creates GET Request for the LMS API with auth")
	public void admin_creates_get_request_for_the_lms_api_with_auth() {
		setRequestHeader(testContext.getBearerToken());   
	}
	
	@When("Admin sends HTTPS Request with endpoint")
	public void admin_sends_https_request_with_endpoint() {
		endpoint = routes.getString("Program_GetAll_URL");
		response = ProgramRequest.GetProgram(endpoint);		
	}
	
	@When ("Admin sends HTTPS get Request with invalid endpoint")
	public void Admin_sends_HTTPS_get_Request_with_invalid_endpoint() {
		response = given().spec(ReqSpec).get("InvalidEndPoint");
	}
	
	@When ("Admin sends HTTPS Request with invalid method")
	public void Admin_sends_HTTPS_get_Request_with_invalid_method() {
		response = given().spec(ReqSpec).post(routes.getString("Program_GetAll_URL"));
	}

	@When("Admin sends GET Request for the LMS API for valid program ID")
	public void admin_sends_get_request_for_the_lms_api_for_valid_program_id() {
		response = given().spec(ReqSpec).pathParam("programId",PROGRAMID).get(routes.getString("Program_GetByProgramID"));
	}

	@When("Admin sends GET Request for the LMS API for invalid program ID")
	public void admin_sends_get_request_for_the_lms_api_for_invalid_program_id() {
		response = given().spec(ReqSpec).pathParam("programId",-1).get(routes.getString("Program_GetByProgramID"));
	}
	
	@When("Admin sends GET Request for the LMS API with invalid baseURL")
	public void admin_sends_get_request_for_the_lms_api_with_invalid_baseurl() {
		response = given().spec(ReqSpec).pathParam("programId",PROGRAMID).get("/invalidendpoint/{programId}");
	}
	
	@When("Admin sends GET Request with valid endpoint for all users")
	public void admin_sends_get_request_with_valid_endpoint_for_all_users() {
		response = given().spec(ReqSpec).get(routes.getString("Program_GetAllWithUsers"));
	}
	
	@When("Admin sends GET Request with invalid endpoint for all users")
	public void admin_sends_get_request_with_invalid_endpoint_for_all_users() {
		response = given().spec(ReqSpec).get("InvalidEndPoint");
	}
	@When("Admin sends GET Request with invalid method for all users")
	public void admin_sends_get_request_with_invalid_method_for_all_users() {
		response = given().spec(ReqSpec).post(routes.getString("Program_GetAllWithUsers"));
	}
	
//*************************************************** NO AUTH **********************************	/
  	
  	@Given("Admin creates POST Request for the LMS with request body with noauth")
  	public void admin_creates_post_request_for_the_lms_with_request_body_with_noauth() {
  		try {
			testData = ExcelUtilis.readDataFromExcel(filePath, sheetName);
			payload = ProgramPayload.createRequestObject(testData[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.out.println(payload);
	    setRequestHeader("");
  	}

  	@Given("Admin creates GET Request for the LMS API with noauth")
  	public void admin_creates_get_request_for_the_lms_api_with_noauth() {
		setRequestHeader("");
  	}
}