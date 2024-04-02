package api.StepDefinition;
import static org.testng.Assert.assertEquals;
//import static org.testng.Assert.assertTrue;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;

import api.Utilities.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;


import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;




public class BatchStepDef extends BaseClass {
	
	Response response;
	String Token;
	JSONObject payload;
	JSONObject jsonObject = new JSONObject();
    ResourceBundle routes = ResourceBundle.getBundle("Routes");
    ResourceBundle path = ResourceBundle.getBundle("Path");

    Random random = new Random();

    int randomNumber = random.nextInt(10000);
    String InvalidBearerToken="";
    int rowNum;
    Object[][] testData;
    String filePath = path.getString("TestDataForUserPost");
    String sheetName = "Batch";
    

   
private TestContextSetUp testContext;
	
	public BatchStepDef(TestContextSetUp testContext)
	{
		this.testContext = testContext;
		
	}

	@Given("Admin creates Batch POST request by reading from Excel")
	public void admin_creates_Batch_post_request_by_reading_from_excel() throws IOException {
		
        testData = ExcelUtilis.readDataFromExcel(filePath, sheetName);
		
	}   
	 
  

   @When("Admin sends HTTPS Request with the {string} to create Batch")
	public void admin_sends_Batch_https_request_with_the_Batch(String rowNumber) {
	
		
        rowNum=Integer.parseInt(rowNumber);

	        jsonObject.put("batchDescription",testData[Integer.parseInt(rowNumber)][0]);
	        String batchName = (String) testData[Integer.parseInt(rowNumber)][1] + "_" + randomNumber;
	        //jsonObject.put("batchName", batchName);

	       jsonObject.put("batchName", testData[Integer.parseInt(rowNumber)][1]);
	        jsonObject.put("batchNoOfClasses", testData[Integer.parseInt(rowNumber)][2]);
	        jsonObject.put("batchStatus", testData[Integer.parseInt(rowNumber)][3]);
	        jsonObject.put("programId", Cache.getData("PROGRAMID_7"));

                // Send the request using RestAssured
                response = getRequestSpecification(testContext.getBearerToken()).filter(new LoggingSetup())
        				.body(jsonObject).when().post(routes.getString("Batch_Post_URL")); // Replace with your endpoint


	}

	@Then("Admin receives {string} with response body for Batch creation.")
	public void admin_receives_with_response_body_Batch(String responsecode) {
   
		if(response.getStatusCode() ==201) {
			
			log.info("Successfull Batch created with response body :" +"\n" +response.getBody().asString());
			
			Assert.assertEquals(response.getStatusCode(),Integer.parseInt(responsecode)); 

			
			int batchId=response.jsonPath().getInt("batchId");
			Cache.setData("batchId_"+rowNum, batchId);
			
			String batchName=response.jsonPath().getString("batchName");
			Cache.setData("batchName_"+rowNum, batchName);

	        // Assert on expected values
			Assert.assertEquals(response.jsonPath().getString("batchDescription"),jsonObject.get("batchDescription"));

			Assert.assertEquals( response.jsonPath().getString("batchName"),jsonObject.get("batchName"));

			Assert.assertEquals( response.jsonPath().getInt("batchNoOfClasses"),Integer.parseInt(jsonObject.get("batchNoOfClasses").toString()));
			Assert.assertEquals(jsonObject.get("batchStatus"), response.jsonPath().getString("batchStatus"));
			Assert.assertEquals( response.jsonPath().getInt("programId"),Integer.parseInt(jsonObject.get("programId").toString()));
	       // assertEquals(jsonObject.get("batchName"), jsonPath.getString("programName"));
	        
			//SChema validation
	        response.then()
            .assertThat()
                .body(matchesJsonSchemaInClasspath(path.getString("Batch_Post_JSONSchema")));
	        
	        Assert.assertEquals(response.header("Content-Type"), "application/json");
	        
		}
		if(response.getStatusCode() ==400) {
			//System.out.println("400 status line"+response.getStatusLine());
			Assert.assertEquals(response.getStatusCode(),Integer.parseInt(responsecode));
			Assert.assertEquals(response.header("Content-Type"), "application/json");
			log.info("Request failed");
			log.error("400 Bad Request");
		}
	}
    

  

		
	@Given("Admin creates GET Request with valid BatchID")
	public void admin_creates_GET_Request_with_valid_BatchID() {
		
		response = getRequestSpecification(testContext.getBearerToken()).filter(new LoggingSetup())
				.when().get("/batches/batchId/" + Cache.getData("batchId_0")); 

	}
	
	@Then("Admin receives {int} OK Status with response body.")
	public void admin_receives_OK_Status_with_response_body(Integer int1) {
	   
		Assert.assertEquals(response.getStatusCode(), 200); 
	}

	
	@Given("Admin creates GET Request with valid BatchName")
	public void admin_creates_GET_Request_with_valid_BatchName() {
		
		response = getRequestSpecification(testContext.getBearerToken())
				.when().get("/batches/batchName/" + Cache.getData("batchName_4")); 
        
	}
	

	

	@Given("Admin creates GET Request with invalid Batch Name")
	public void admin_creates_GET_Request_with_invalid_Batch_Name() {
		
		
		response = getRequestSpecification(testContext.getBearerToken())
				.when().get("batches/batchName/"+ "abhjkk"); // Replace with your endpoint
		
		

	}
	
	@Given("Admin creates GET Request with valid Batch Name and invalid endpoint")
	public void admin_creates_GET_Request_with_valid_Batch_Name_and_invalid_endpoint() {
		//System.out.print("Admin creates GET Request with valid Batch Name and invalid endpoint");
		response = getRequestSpecification(testContext.getBearerToken())
				.when().get("batches/batchName/foo/" +Cache.getData("batchName_0")); 

	} 
	
//	@Then("Admin receives {int} Not found")
//	public void admin_receives_Not_found(Integer int1) {
//	    // Write code here that turns the phrase above into concrete actions
//	    //throw new io.cucumber.java.PendingException();
//	}
	
	@Given("Admin creates GET Request with valid Program Id")
	public void admin_creates_GET_Request_with_valid_Program_Id() {
		
		response = getRequestSpecification(testContext.getBearerToken())
				.when().get("/batches/program/" +Cache.getData("PROGRAMID_7")); 

	}
	
	@Then("Admin receives {int} OK Status with response body_{int}.")
	public void admin_receives_OK_Status_with_response_body_(Integer int1, Integer int2) {
	 
		Assert.assertEquals(response.getStatusCode(), 200); 

	}

	@Given("Admin creates GET Request with invalid Program Id")
	public void admin_creates_GET_Request_with_invalid_Program_Id() {
		
		response = getRequestSpecification(testContext.getBearerToken())
				.when().get("/batches/program/"+678653467); 
	}

	@Given("Admin creates GET Request with invalid endpoint")
	public void admin_creates_GET_Request_with_invalid_endpoint() {
		
		response = getRequestSpecification(testContext.getBearerToken())
				.when().get("/batches/program/foo/" +Cache.getData("PROGRAMID_7"));

	}

	
	
   //Admin creates GET Request with batch Name with a deleted batch
	
	@Given("Admin creates GET Request with valid batch Name with a deleted batch")
	public void admin_creates_GET_Request_with_valid_batch_Name_with_a_deleted_batch() {
		response = getRequestSpecification(testContext.getBearerToken()).filter(new LoggingSetup())
				.when().get("/batches/batchName/"+(String)Cache.getData("batchName_0"));
		
	}
	
	@Then("Admin receives {int} OK Status with  batchStatus field {string} in the response body_4.")
	public void admin_receives_OK_Status_with_batchStatus_field_in_the_response_body_4(Integer int1, String string) {
		Assert.assertEquals( response.getStatusCode(),200);
	}
	//////PUT REQUEST
	
	
	
	

	

	@Given("Admin creates PUT Request with invalid BatchId and valid Data")
	public void admin_creates_PUT_Request_with_invalid_BatchId_and_valid_Data() throws IOException {
        testData = ExcelUtilis.readDataFromExcel(filePath, sheetName);
		jsonObject.put("batchDescription",testData[0][4]);
		jsonObject.put("batchName",testData[0][5]);
        jsonObject.put("batchNoOfClasses",testData[0][6]);
        jsonObject.put("batchStatus", testData[0][7]);
        jsonObject.put("programName",testData[0][8]);
        jsonObject.put("programId", Cache.getData("PROGRAMID_7"));

        response = getRequestSpecification(testContext.getBearerToken()).filter(new LoggingSetup())
                .body(jsonObject)
                .when()
                .put("/batches/" +10098677); 

        	}

	@Then("Admin receives {int} Not Found Status with message and boolean success details_{int}")
	public void admin_receives_Not_Found_Status_with_message_and_boolean_success_details_(Integer int1, Integer int2) {
		Assert.assertEquals(response.getStatusCode(), 404); 


	}

	@Given("Admin creates PUT Request with Valid batch Id")
	public void admin_creates_PUT_Request_with_Valid_batch_Id() throws IOException {
		testData = ExcelUtilis.readDataFromExcel(filePath, sheetName);
		jsonObject.put("batchDescription",testData[0][4]);
		jsonObject.put("batchName",testData[0][5]);
        jsonObject.put("batchNoOfClasses",testData[0][6]);
        jsonObject.put("batchStatus", testData[0][7]);
        jsonObject.put("programName",testData[0][8]);
        jsonObject.put("programId", Cache.getData("PROGRAMID_7"));
	    response = getRequestSpecification(testContext.getBearerToken()).filter(new LoggingSetup())
	            .body(jsonObject)
	            .when()
	            .put("/batches/foo/" +Cache.getData("batchId_0"));

	}

	@Then("Admin receives {int} Bad Request Status with message_{int}")
	public void admin_receives_Bad_Request_Status_with_message_(Integer int1, Integer int2) {
		Assert.assertEquals(response.getStatusCode(),404); 
	}



	


	   @Given("Admin sends a GET request with given {string} {string} {string} {string}")
	  public void admin_sends_https_request_with_the(String rowNumber, 
			String token, String searchString, String invalidEndpoint) {
		
        String mySearchString = "";
        String myEndPoint = routes.getString("Batch_Get_URL"); 
        
        if (searchString.equals("yes"))
        {
        	mySearchString = "?batchName=\"Java11\"";
        }
        
        if (invalidEndpoint.equals("yes"))
        {
        	myEndPoint = "abcdef";
        }
        
        //System.out.println("test2 endpoint " + myEndPoint + mySearchString);

        
        if (token.equals("no"))
        {
            response = getRequestSpecification(InvalidBearerToken)
    				.when().get(myEndPoint + mySearchString); 
        	
        }
        else
        {
        response = getRequestSpecification(testContext.getBearerToken())
				.when().get(myEndPoint + mySearchString);        
        }

    }
	
	   @Then("Admin receives {string}")
	public void admin_receives(String string) {
		//assertEquals(response.getStatusCode(), string); 
		   Assert.assertEquals(response.getStatusCode(),Integer.parseInt(string)); 



	}

	
	
	@Given("Admin creates GET Request with valid Batch ID No Auth")
	public void admin_creates_GET_Request_with_valid_Batch_ID_No_Auth() {
		//System.out.print("Admin creates GET Request with valid Batch ID");

		response = getRequestSpecification(InvalidBearerToken)
				.when().get("/batches/batchId/"+Cache.getData("batchId_0")); // Replace with your endpoint
        //System.out.println("With valid batch id :Response Body" + response.getBody().asString());
          

	}
	
//	@Then("Admin receives with unauthorized message with 401")
//	public void admin_receives_with_unauthorized_message_with(Integer int1) {
//	    // Write code here that turns the phrase above into concrete actions
//	    //throw new io.cucumber.java.PendingException();
//		assertEquals(response.getStatusCode(), 401); 
//
//	}


	

	
	



	
		


	@Given("Admin creates GET Request with invalid Batch ID")
	public void admin_creates_GET_Request_with_invalid_Batch_ID() {
		//System.out.print("Admin creates GET Request with invalid Batch ID");

		response = getRequestSpecification(testContext.getBearerToken())
				.when().get("/batches/batchId/" +12345); }
		


	    
	

	
	
	@Given("Admin creates GET Request with valid Batch ID and invalid endpoint")
	public void Admin_creates_GET_Request_with_valid_Batch_ID_and_invalid_endpoint() {
		//System.out.print("Admin creates GET Request with valid Batch ID and invalid endpoint");
		response = getRequestSpecification(testContext.getBearerToken())
				.when().get("/batcnhes/"+Cache.getData("batchId_0"));
        //System.out.println("Invalid end point");
} 

	@Then("Admin receives {int} not found  Status")
	public void admin_receives_not_found_Status(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
		Assert.assertEquals(response.getStatusCode(), 404); 

	}
	
	/// NoAuthorization ///
	
	@Given("Admin creates GET Request with batch Name")
	public void admin_creates_GET_Request_with_batch_Name() {
		
		//System.out.print("Admin creates GET Request with valid BatchName");
		response = getRequestSpecification(InvalidBearerToken)
				.when().get("/batches/batchName/" +Cache.getData("batchName_0")); // Replace with your endpoint
       
		//System.out.println("GET Request with batch Name" + response.getBody().asString());

	}
	

	
	
	

	@Given("Admin creates PUT Request with valid BatchId and Data")
	public void admin_creates_PUT_Request_with_valid_BatchId_and_Data() {
		//System.out.print("Admin creates PUT Request with valid BatchId and Data");

		JSONObject payload = new JSONObject();
        
        payload.put("batchName", "JavaMp_apicoders");
        payload.put("batchDescription", "MorningBatch");
        payload.put("batchStatus", "ACTIVE");
        payload.put("batchNoOfClasses", 20);
        payload.put("programId", Cache.getData("PROGRAMID_7"));
       payload.put("programName", "SMPO");

        
        response = getRequestSpecification(InvalidBearerToken).filter(new LoggingSetup())
                .body(payload)
                .when()
                .put("/batches/" +Cache.getData("batchId_0")); 
  //System.out.println("Admin creates PUT Request with valid BatchId and Data :Response Body" + response.getBody().asString());
     

	}
	
//	@Then("Admin receives {int} unauthorized")
//	public void admin_receives_unauthorized(Integer int1) {
//	    // Write code here that turns the phrase above into concrete actions
//	    //throw new io.cucumber.java.PendingException();
//	}


	 
	
	@Given("Admin creates GET Request with valid Batch ID_NoAuth")
	public void admin_creates_GET_Request_with_valid_Batch_ID_NoAuth() {
		//System.out.print("Admin creates GET Request with valid BatchId");
		response = getRequestSpecification(InvalidBearerToken)
				.when().get("/batches/batchID/" +Cache.getData("batchId_0")); 
     //System.out.println(" with valid Batch ID_NoAuth :Response Body" + response.getBody().asString());

       

	}
	
	
	@Then("Admin receives {int}  Status with error message unauthorized for batch")
	public void admin_receives_status_with_error_message_unauthorized_for_batch(Integer int1) {
		Assert.assertEquals(response.getStatusCode(), 401);
		log.info("Delete Batch Respose body" +response.getBody().asString());
	}

	

	@Given("Admin creates GET Request with program id_NoAuth")
	public void admin_creates_GET_Request_with_program_id_NoAuth() {
		
		//System.out.print("Admin creates GET Request with valid ProgramId");
		response = getRequestSpecification(InvalidBearerToken)
				.when().get("/batches/program/" +Cache.getData("PROGRAMID_7")); 
        //System.out.println("with program id_NoAuth :Response Body" + response.getBody().asString());

       

		
	}
	

	
	////DELETE
	
	@Given("Admin creates DELETE Request with valid BatchId_NoAuth")
	public void admin_creates_DELETE_Request_with_valid_BatchId_NoAuth() {
		
		response = getRequestSpecification(InvalidBearerToken)
				.when().get("/batches/" +Cache.getData("batchId_0") ); 


	}

	

	@Given("Admin creates DELETE Request with valid BatchId_WithAuth1")
	public void admin_creates_DELETE_Request_with_valid_BatchId_WithAuth1() {
		
		response = getRequestSpecification(testContext.getBearerToken())
				.when().delete("/batches/" + Cache.getData("batchId_0")).then().log().all().extract().response(); 
		
		//System.out.println("Deleted batch ID: " + Cache.getData("batchId_0"));
		log.info("Delete Batch Respose body" +response.getBody().asString());

		

   
             
	}

	@Then("Admin receives {int} Ok status with message")
	public void admin_receives_Ok_status_with_message(Integer int1) {
		 //System.out.println("Delete with program id_NoAuth :Response Body" + response.getBody().asString());

		Assert.assertEquals(response.getStatusCode(), 200);
		log.info("Delete Batch Respose body" +response.getBody().asString());


	}
     //With Invalid End point
	@Given("Admin creates DELETE Request with valid BatchId_WithAuth2")
	public void admin_creates_DELETE_Request_with_valid_BatchId_WithAuth2() {
		response = getRequestSpecification(testContext.getBearerToken())
				.when().delete("/batches/foo/" + Cache.getData("batchId_0") ); 


	}

	@Then("Admin receives {int} not found")
	public void admin_receives_not_found(Integer int1) {
		Assert.assertEquals( response.getStatusCode(),404);

	}

	@Given("Admin creates DELETE Request with invalid BatchId_WithAuth3")
	public void admin_creates_DELETE_Request_with_invalid_BatchId_WithAuth3() {
		
		response = getRequestSpecification(testContext.getBearerToken())
				.when().delete("/batches/" + 128786093 ); 

		
		
	} 
	
	
	//Admin creates GET Request with batch Name with a deleted batch

	@Given("Admin creates GET Request with program id")
	public void admin_creates_GET_Request_with_program_id() {
		//System.out.print("Admin creates GET Request with program id");
		response = getRequestSpecification(testContext.getBearerToken()).filter(new LoggingSetup())
				.when().get("/batches/program/"+(Integer)Cache.getData("PROGRAMID_7")); 

		
}

	@Then("Admin receives {int} Not Found Status with message and boolean success details")
	public void admin_receives_Not_Found_Status_with_message_and_boolean_success_details(Integer statuscode) {
	    
		Assert.assertEquals(response.getStatusCode(),404); 

	}
	
	@Given("Admin creates GET Request with valid Batch id")
	public void admin_creates_get_request_with_valid_batch_id() {
		response = getRequestSpecification(testContext.getBearerToken()).filter(new LoggingSetup())
				.when().get("/batches/batchId/" + Cache.getData("batchId_0"));
	}
	
	@Given("Admin creates PUT request to update Batch by reading from Excel")
	public void admin_creates_put_request_to_update_batch_by_reading_from_excel() throws IOException {
		String filePath = path.getString("TestDataForUserPost");
        String sheetName = "Batch";
        testData = ExcelUtilis.readDataFromExcel(filePath, sheetName);
	}

	@When("Admin sends HTTPS PUT Request to update Batch with the {string}")
	public void admin_sends_https_put_request_to_update_batch_with_the(String rowNumber) {
		rowNum=Integer.parseInt(rowNumber);

		jsonObject.put("batchDescription",testData[Integer.parseInt(rowNumber)][4]);
		jsonObject.put("batchName",testData[Integer.parseInt(rowNumber)][5]);
        jsonObject.put("batchNoOfClasses",testData[Integer.parseInt(rowNumber)][6]);
        jsonObject.put("batchStatus", testData[Integer.parseInt(rowNumber)][7]);
        jsonObject.put("programName",testData[Integer.parseInt(rowNumber)][8]);
        jsonObject.put("programId", Cache.getData("PROGRAMID_7"));

        response = getRequestSpecification(testContext.getBearerToken()).filter(new LoggingSetup())
                .body(jsonObject).filter(new LoggingSetup()).
                when().put("/batches/" +Cache.getData("batchId_0"));
        
	}

	@Then("Admin receives {string} with PUT response body for update Batch.")
	public void admin_receives_with_put_response_body_for_update_batch(String responsecode) {
		if(response.getStatusCode() ==200) {
			
			log.info("Batch updated successfully with response body :" +"\n" +response.getBody().asString());
			
			String batchName=response.jsonPath().getString("batchName");
			Cache.setData("batchName_"+rowNum, batchName);
			
			Assert.assertEquals(response.getStatusCode(),Integer.parseInt(responsecode));
	        JsonPath jsonPath = response.jsonPath();
	        // Assert on expected values
	        Assert.assertEquals( jsonPath.getString("batchDescription"),jsonObject.get("batchDescription"));
	        Assert.assertEquals( jsonPath.getString("batchName"),jsonObject.get("batchName"));
	        Assert.assertEquals(jsonPath.getInt("batchNoOfClasses"),Integer.parseInt(jsonObject.get("batchNoOfClasses").toString()));
	        Assert.assertEquals(jsonPath.getString("batchStatus"),jsonObject.get("batchStatus"));
	        Assert.assertEquals(jsonPath.getInt("programId"),Integer.parseInt(jsonObject.get("programId").toString()) );
	        //Assert.assertEquals(jsonObject.get("programName"), jsonPath.getString("programName"));
	       // assertEquals(jsonObject.get("batchName"), jsonPath.getString("programName"));
	       
	      //SChema validation
	        response.then()
            .assertThat()
                .body(matchesJsonSchemaInClasspath(path.getString("Batch_Post_JSONSchema")));
	        
	        Assert.assertEquals(response.header("Content-Type"), "application/json");
		}
		
		if(response.getStatusCode() ==400) {
			//System.out.println("400 status line"+response.getStatusLine());
			Assert.assertEquals(response.getStatusCode(),Integer.parseInt(responsecode));
			Assert.assertEquals(response.header("Content-Type"), "application/json");
			
		}
	}

	


	



	
	

	




	


	
	

}

