package api.StepDefinition;
import org.json.simple.JSONObject;
import org.testng.Assert;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import java.io.IOException;

import api.Request.ProgramRequest;
import api.RequestPayload.ProgramPayload;
import api.Utilities.BaseClass;
import api.Utilities.Cache;
import api.Utilities.ExcelUtilis;
import api.Utilities.LoggingSetup;
import api.Utilities.TestContextSetUp;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ProgramStepDef extends BaseClass{
	static Response response;
    JSONObject payload;
	Object[][] testData;
	String filePath = path.getString("TestDataForUserPost"); 
    String sheetName = "ProgramController";
    String endpoint;
    static int responseStatusCode;
   	String BaseURL = routes.getString("baseURL"); 
	private static TestContextSetUp testContext;
	int rowNum;
	String PROGRAMNAME;
		
	public ProgramStepDef(TestContextSetUp testContext)
	{
		this.testContext = testContext;
	}
	
	@Given("Admin creates POST request by reading from Excel {string} with auth")
	public void admin_creates_post_request_by_reading_from_excel(String rowNumber) {
        try {
			testData = ExcelUtilis.readDataFromExcel(filePath, sheetName);
			payload = ProgramPayload.createRequestObject(testData[Integer.parseInt(rowNumber)]);
			setRequestHeader(testContext.getBearerToken());  
			rowNum=Integer.parseInt(rowNumber);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@When("Admin sends HTTPS Request")
	public void admin_sends_HTTPS_Request() {
		//System.out.println("token ::::::: " + Token);
	    response = given().spec(ReqSpec).body(payload).when().post(routes.getString("Program_Post_URL")); 
        System.out.println(response.asPrettyString());
	}
	
	@Then("Admin receives {string} Created Status with response body")
	public void admin_receives_Created_Status_with_response_body(String expectedStatusCode) {
		if(response.getStatusCode() == 201) {
			Assert.assertEquals( response.getStatusCode(),Integer.parseInt(expectedStatusCode));
			int PROGRAMID = response.jsonPath().get("programId");
			PROGRAMNAME = response.jsonPath().get("programName");
			Cache.setData("PROGRAMID_"+rowNum, PROGRAMID);
			Cache.setData("PROGRAMNAME_"+rowNum, PROGRAMNAME);
			//Data Validation
		    Assert.assertEquals(response.jsonPath().getString("programDescription"),payload.get("programDescription"));
		    Assert.assertEquals( response.jsonPath().getString("programName"),payload.get("programName"));
		    Assert.assertEquals( response.jsonPath().getString("programStatus"),payload.get("programStatus"));
			
		  //Schema validation
	       response.then()
	       .assertThat().body(matchesJsonSchemaInClasspath(path.getString("Program_Post_JSONSchema")));        
	       Assert.assertEquals(response.header("Content-Type"), "application/json");
		}
		if(response.getStatusCode() == 400) {
			//System.out.println("400 status line"+response.getStatusLine());
			Assert.assertEquals(response.getStatusCode(),Integer.parseInt(expectedStatusCode) );
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
			Assert.assertEquals(response.getStatusCode(),expectedStatusCode );
			Assert.assertEquals(response.header("Content-Type"), "application/json");		
		} 
		if(expectedStatusCode == 400) {
			System.out.println("400 status line : "+response.getStatusCode());
			Assert.assertEquals(response.getStatusCode(),expectedStatusCode );
			Assert.assertEquals(response.header("Content-Type"), "application/json");		
		} 
		if(expectedStatusCode == 401) {
			System.out.println("401 status line : "+response.getStatusCode());
			Assert.assertEquals(response.getStatusCode(),expectedStatusCode );
			Assert.assertEquals(response.header("Content-Type"), "application/json");		
		} 		
	}
	
	@Given("Admin creates POST Request for the LMS with request body with existing program name with auth")
	public void admin_creates_post_request_for_the_lms_with_request_body_with_existing_program_name_with_auth() {
		try {
			testData = ExcelUtilis.readDataFromExcel(filePath, sheetName);
			payload = ProgramPayload.createRequestObject(testData[0]);
			payload.put("programName", Cache.getData("PROGRAMNAME_"+rowNum));
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
		response = given().spec(ReqSpec).pathParam("programId",Cache.getData("PROGRAMID_"+rowNum)).get(routes.getString("Program_GetByProgramID"));
	}

	@When("Admin sends GET Request for the LMS API for invalid program ID")
	public void admin_sends_get_request_for_the_lms_api_for_invalid_program_id() {
		response = given().spec(ReqSpec).pathParam("programId",-1).get(routes.getString("Program_GetByProgramID"));
	}
	
	@When("Admin sends GET Request for the LMS API with invalid baseURL")
	public void admin_sends_get_request_for_the_lms_api_with_invalid_baseurl() {
		response = given().spec(ReqSpec).pathParam("programId",Cache.getData("PROGRAMID_"+rowNum)).get("/invalidendpoint/{programId}");
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
  	
  //---------Put&Delete-------//	
  	@Given("Admin creates PUT Request for the LMS with noauth")
  	public void admin_creates_put_request_for_the_lms_with_noauth() {
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
  	
  	@Given("Admin creates DELETE Request with noauth")
	public void admin_creates_delete_request_with_noauth() {
		setRequestHeader("");   
	}
  	
  	
  //*************************************************** PUT AND DELETE **********************************	/
	
  	@Given ("Admin creates PUT Request by reading from Excel {string} with auth")
	public void Admin_creates_PUT_Request_by_reading_from_excel(String rowNumber) {
		try {
			testData = ExcelUtilis.readDataFromExcel(filePath, sheetName);
			payload = ProgramPayload.createRequestObjectForUpdate(testData[Integer.parseInt(rowNumber)]);
			setRequestHeader(testContext.getBearerToken());   
		    System.out.println("update ::: " + payload);
		    rowNum=Integer.parseInt(rowNumber);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	@When ("Admin sends PUT Request with ID")
	public void Admin_SENDS_PUT_Request_with_id() {
		response = given().spec(ReqSpec).pathParam("programId",Cache.getData("PROGRAMID_7")).
				body(payload).when().put(routes.getString("program_PutByProgramID_URL"));
	}
	
	@When ("Admin sends PUT Request with Name")
	public void Admin_SENDS_PUT_Request_with_name() {
		response = given().spec(ReqSpec).pathParam("programName",Cache.getData("PROGRAMNAME_0")).
				body(payload).filter(new LoggingSetup()).when().put(routes.getString("program_PutByProgramName_URL"));
	}

	
	@Then("Admin receives {string} updated Status")
	public void admin_receives_updated_status(String expectedStatusCode) {
		
		if(response.getStatusCode()==200){
			
			Assert.assertEquals(response.getStatusCode(),Integer.parseInt(expectedStatusCode));
			
            PROGRAMNAME = response.jsonPath().get("programName");
            Cache.setData("PROGRAMNAME_"+rowNum, PROGRAMNAME);
            
            
            
			//data validation
			
		    Assert.assertEquals(response.jsonPath().getString("programDescription"),payload.get("programDescription"));
		    Assert.assertEquals( response.jsonPath().getString("programName"),payload.get("programName"));
		    Assert.assertEquals( response.jsonPath().getString("programStatus"),payload.get("programStatus"));
			
		  //Schema validation
	       response.then()
	       .assertThat().body(matchesJsonSchemaInClasspath(path.getString("Program_Post_JSONSchema")));        
	       Assert.assertEquals(response.header("Content-Type"), "application/json");
		}
		if(response.getStatusCode()==400){
			
			Assert.assertEquals(response.getStatusCode(),Integer.parseInt(expectedStatusCode));
			
			Assert.assertEquals(response.header("Content-Type"), "application/json");		
		}
//		if(response.getStatusCode()==404){
//			Assert.assertEquals(Integer.parseInt(expectedStatusCode),response.getStatusCode());
//			
//			Assert.assertEquals(response.header("Content-Type"), "application/json");		
//		}
	}
	
	@When ("Admin sends PUT Request with invalid program ID")
	public void Admin_SENDS_PUT_Request_with_invalid_program_id() {
		response = given().spec(ReqSpec).pathParam("programId",-1).
				body(payload).when().filter(new LoggingSetup()).put(routes.getString("program_PutByProgramID_URL"));
	}
	
	@Given ("Admin creates PUT Request with invalid request body with auth")
	public void Admin_creates_PUT_Request_with_invalid_request_body_with_auth(){
		try {
			testData = ExcelUtilis.readDataFromExcel(filePath, sheetName);
			payload = ProgramPayload.createRequestObjectForUpdate(testData[0]);
			payload.put("programName", (int) Math.floor(Math.random() * (1000)+1));	
			setRequestHeader(testContext.getBearerToken());   
		    System.out.println(payload);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	@Given ("Admin creates PUT Request with auth")
	public void Admin_creates_PUT_Request_with_auth() {
		try {
			testData = ExcelUtilis.readDataFromExcel(filePath, sheetName);
			payload = ProgramPayload.createRequestObjectForUpdate(testData[0]);
			setRequestHeader(testContext.getBearerToken());   
		    System.out.println(payload);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	@When("Admin sends PUT Request with invalid endpoint")
	public void admin_sends_put_request_with_invalid_endpoint() {
		System.out.println("programid:"+Cache.getData("PROGRAMID_2"));
		response = given().spec(ReqSpec).pathParam("programId",Cache.getData("PROGRAMID_0")).
				body(payload).when().put("/putprograma/{programId}");

	}
	
	@When("Admin sends PUT Request with invalid method")
	public void admin_sends_put_request_with_invalid_method() {
		System.out.println("prgm name for invaid_method:"+Cache.getData("PROGRAMNAME_0"));
		
		response = given().spec(ReqSpec).pathParam("programName",Cache.getData("PROGRAMNAME_0")).
				body(payload).filter(new LoggingSetup()).when().get(routes.getString("program_PutByProgramName_URL"));	
	}

	@When ("Admin sends PUT Request with invalid program name")
	public void Admin_SENDS_PUT_Request_with_invalid_program_name() {
		response = given().spec(ReqSpec).pathParam("programName","abc").
				body(payload).when().put(routes.getString("program_PutByProgramName_URL"));
	}		
		
	@Given("Admin creates DELETE Request with auth")
	public void admin_creates_delete_request_with_auth() {
		setRequestHeader(testContext.getBearerToken());   
	}
	
	@When("Admin sends DELETE request with program name")
	public void admin_sends_delete_request_with_program_name() {
		endpoint = routes.getString("program_DeleteByProgramName_URL");
		response = ProgramRequest.DeleteProgram(endpoint,(String)Cache.getData("PROGRAMNAME_2"));		
	}
	
	@When("Admin sends DELETE request with program ID")
	public void admin_sends_delete_request_with_program_id() {
		endpoint = routes.getString("program_DeleteByProgramID_URL");
		response = ProgramRequest.DeleteProgram(endpoint, (Integer)Cache.getData("PROGRAMID_7"));		
	}
	
	@When("Admin sends DELETE request with invalid program name")
	public void admin_sends_delete_request_with_invalid_program_name() {
		endpoint = routes.getString("program_DeleteByProgramName_URL");
		response = ProgramRequest.DeleteProgram(endpoint, "abc");		
	}
	
	@When("Admin sends DELETE request with invalid program ID")
	public void admin_sends_delete_request_with_invalid_program_id() {
		endpoint = routes.getString("program_DeleteByProgramID_URL");
		response = ProgramRequest.DeleteProgram(endpoint,-1);		
	}
	
	@Then("Admin receives {int} deleted Status")
	public void admin_receives_deleted_status(int expectedStatusCode) {

		if(expectedStatusCode == 200) {
			System.out.println("200 status line : "+response.getStatusCode());
			
			Assert.assertEquals(response.getStatusCode(),expectedStatusCode);
			log.info("Delete Program Respose body" +response.getBody().asString());
		} 	
		if(expectedStatusCode == 404) {
			System.out.println("404 status line : "+response.getStatusCode());
			Assert.assertEquals(response.getStatusCode(),expectedStatusCode);	
			log.info("Delete Program Respose body" +response.getBody().asString());
			}
	}
}
