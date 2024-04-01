package api.StepDefinition;
import static org.testng.Assert.assertEquals;
//import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertSame;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import api.Utilities.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import junit.framework.Assert;
import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;




public class BatchPostStepDef extends ExcelUtilis {
	
	Response response;
	String Token;
	JSONObject payload;
	JSONObject jsonObject = new JSONObject();
    ResourceBundle routes = ResourceBundle.getBundle("Routes");
    ResourceBundle path = ResourceBundle.getBundle("Path");
    private Response response1;
    private JSONObject programPayload;


    static int batchID1;
    static String batchName;
    static int Programid;
    static int ProgramIDActiveStatus = 16636;

    
    Random random = new Random();
   

    int randomNumber = random.nextInt(10000);

    public BatchPostStepDef() {
        // Initialize any variables or perform any setup required
    }
    
	@Given("Admin sets authorization to bearer token")
	public void admin_sets_authorization_to_bearer_token() {
	    Token=login();
	}

	@Given("Admin creates POST request by reading from Excel")
	public void admin_creates_post_request_by_reading_from_excel() {
		System.out.println("My token step2 ");
		
		
	}   
	 
  /*  @Given("Admin sets the status of program to active")
    public void admin_sets_the_status_of_program_to_active() {
    	System.out.println("creating program Id ProgramId " +ProgramIDActiveStatus);
        // Create payload for setting program status to active
        programPayload = new JSONObject();
        programPayload.put("programDescription", "Sample Program Description");
        programPayload.put("programName", "Sample Program+"+randomNumber);
        programPayload.put("programStatus", "ACTIVE");
        
        response1 = getRequestSpecification(Token)
				.body(jsonObject).when().post(routes.getString("Program_Post_URL")); // Replace with your endpoint
        
        JsonPath jsonPath = response1.jsonPath();
        int ProgramIDActiveStatus = jsonPath.getInt("programId");
        System.out.println("ProgramId " +ProgramIDActiveStatus);


    }

    @Then("Program status is updated to active")
    public void program_status_is_updated_to_active() {
        // Validate response status code
    	System.out.println("response code for create program "+ response1.getStatusCode());
        assertEquals(200, response1.getStatusCode());

        // Additional validation if needed
    }*/


	

	
	

	@When("Admin sends HTTPS Request with the {string}")
	public void admin_sends_https_request_with_the(String rowNumber) {
		System.out.println("step3");
		String filePath = "C:\\Users\\rpatil\\Downloads\\Team 7\\Team7_APICoders-master\\Team7_APICoders-master\\src\\test\\resources\\TestData\\BatchTestData.xlsx";
        String sheetName = "Sheet1";

        try {
            Object[][] testData = readDataFromExcel(filePath, sheetName);
            

            
	        // Creating a JSONObject
	        

	        // Adding key-value pairs to the JSONObject
	        jsonObject.put("batchDescription",testData[Integer.parseInt(rowNumber)][0]);
	        String batchName = (String) testData[Integer.parseInt(rowNumber)][1] + "_" + randomNumber;
	        jsonObject.put("batchName", batchName);

	       // jsonObject.put("batchName", testData[Integer.parseInt(rowNumber)][1]);
	        jsonObject.put("batchNoOfClasses", testData[Integer.parseInt(rowNumber)][2]);
	        jsonObject.put("batchStatus", testData[Integer.parseInt(rowNumber)][3]);
	        jsonObject.put("programId", ProgramIDActiveStatus);

	        

    
                //System.out.println("payload is " + jsonObject.toString());

        		
        		
                // Send the request using RestAssured
                response = getRequestSpecification(Token)
        				.body(jsonObject).when().post(routes.getString("Batch_Post_URL")); // Replace with your endpoint

                // Validate response if needed
               // response.then().statusCode(200); // Example validation
                System.out.println("My Response Body:\n" + response.getBody().asString());


           
        } 
        catch (IOException e) {
        	System.out.println("exception thrown");
        	
            e.printStackTrace();
        }
        
		
	}

	@Then("Admin receives {string} with response body.")
	public void admin_receives_with_response_body(String responsecode) {
        System.out.println("last step");

		assertEquals(response.getStatusCode(),Integer.parseInt(responsecode)); 
        JsonPath jsonPath = response.jsonPath();

        
		if(response.getStatusCode() ==201) {

	        // Assert on expected values
	        assertEquals(jsonObject.get("batchDescription"), jsonPath.getString("batchDescription"));
	        //assertEquals(12345, jsonPath.getInt("batchId"));
	        batchID1= jsonPath.getInt("batchId");
	        Programid= jsonPath.getInt("programId");
	        Programid = ProgramIDActiveStatus;

	        System.out.println("ID is" + batchID1);
	        batchName= jsonPath.get("batchName");
	        System.out.println("BatchName is" + batchName);

	        assertEquals(jsonObject.get("batchName"), jsonPath.getString("batchName"));

	        assertEquals(Integer.parseInt(jsonObject.get("batchNoOfClasses").toString()), jsonPath.getInt("batchNoOfClasses"));
	        assertEquals(jsonObject.get("batchStatus"), jsonPath.getString("batchStatus"));
	        assertEquals(Integer.parseInt(jsonObject.get("programId").toString()), jsonPath.getInt("programId"));
	       // assertEquals(jsonObject.get("batchName"), jsonPath.getString("programName"));
	        
			//SChema validation
	        response.then()
            .assertThat()
                .body(matchesJsonSchemaInClasspath(path.getString("Batch_Post_JSONSchema")));
	        
		}
	}
    

  

		
	@Given("Admin creates GET Request with valid BatchID")
	public void admin_creates_GET_Request_with_valid_BatchID() {
		
		System.out.println("Admin creates GET Request with valid BatchID");
		response = getRequestSpecification(Token)
				.when().get("/batches/batchId/" + batchID1); // Replace with your endpoint
        System.out.println(" Get With Valid BAtch IdResponse Body" + response.getBody().asString());
        
        System.out.println("My Response Body:\n" + response.getBody().asString());
        System.out.println(" Hello Batch Id" + batchID1);


	}
	
	@Then("Admin receives {int} OK Status with response body.")
	public void admin_receives_OK_Status_with_response_body(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(), 200); 
	}

	
	@Given("Admin creates GET Request with valid BatchName")
	public void admin_creates_GET_Request_with_valid_BatchName() {
		System.out.print("Admin creates GET Request with valid BatchName");
		response = getRequestSpecification(Token)
				.when().get("/batches/batchName/" + batchName); // Replace with your endpoint
       
		System.out.println(" Get With Valid BAtchName Response Body" + response.getBody().asString());
        System.out.println(" Hello Batch Name is " + batchName);
        
	}
	

	@Then("Admin receives {int} OK Status with response body_1.")
	public void admin_receives_OK_Status_with_response_body_1(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(), 200); 
	}

	@Given("Admin creates GET Request with invalid Batch Name")
	public void admin_creates_GET_Request_with_invalid_Batch_Name() {
		System.out.print("Admin creates GET Request with invalid Batch Name");
		
		response = getRequestSpecification(Token)
				.when().get("batches/batchName/InvalidBatchName"); // Replace with your endpoint
		System.out.println("Admin creates GET Request with invalid Batch Name");

		System.out.println("Response Body" + response.getBody().asString());
		

	}
	
	@Given("Admin creates GET Request with valid Batch Name and invalid endpoint")
	public void admin_creates_GET_Request_with_valid_Batch_Name_and_invalid_endpoint() {
		System.out.print("Admin creates GET Request with valid Batch Name and invalid endpoint");
		response = getRequestSpecification(Token)
				.when().get("batches/ba" +batchName); 

	} 
	
	@Given("Admin creates GET Request with valid Program Id")
	public void admin_creates_GET_Request_with_valid_Program_Id() {
		System.out.print("Admin creates GET Request with valid Program Id");
		response = getRequestSpecification(Token)
				.when().get("/batches/program/" +Programid); 

	}
	
	@Then("Admin receives {int} OK Status with response body_{int}.")
	public void admin_receives_OK_Status_with_response_body_(Integer int1, Integer int2) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
	}

	@Given("Admin creates GET Request with invalid Program Id")
	public void admin_creates_GET_Request_with_invalid_Program_Id() {
		System.out.print("Admin creates GET Request with invalid Program Id");
		response = getRequestSpecification(Token)
				.when().get("/batches/program/0"); 
	}

	@Given("Admin creates GET Request with invalid endpoint")
	public void admin_creates_GET_Request_with_invalid_endpoint() {
		System.out.print("Admin creates GET Request with invalid endpoint");
		response = getRequestSpecification(Token)
				.when().get("/batches/program/123"); 

	}

	@Then("Admin receives {int}  Status with  error message Not Found.")
	public void admin_receives_Status_with_error_message_Not_Found(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	   // throw new io.cucumber.java.PendingException();
	}

	@Given("Admin creates GET Request with program id")
	public void admin_creates_GET_Request_with_program_id() {
		System.out.print("Admin creates GET Request with program id");
		response = getRequestSpecification(Token)
				.when().get("/batches/program/" + Programid); 

		
}

	


	
	
   //Admin creates GET Request with batch Name with a deleted batch
	
	@Given("Admin creates GET Request with valid batch Name with a deleted batch")
	public void admin_creates_GET_Request_with_valid_batch_Name_with_a_deleted_batch() {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
	}
	
	@Then("Admin receives {int} OK Status with  batchStatus field {string} in the response body_4.")
	public void admin_receives_OK_Status_with_batchStatus_field_in_the_response_body_4(Integer int1, String string) {
	    // Write code here that turns the phrase above into concrete actions
	}
	//////PUT REQUEST
	
	@Given("Admin creates PUT Request with valid BatchId and mandatory fields")
	public void admin_creates_PUT_Request_with_valid_BatchId_and_mandatory_fields() {
        System.out.print("Admin creates PUT Request with valid BatchId and mandatory fields");
		JSONObject payload = new JSONObject();
        
        payload.put("batchName", "JavaMp"+batchID1);
        payload.put("batchDescription", "MorningBatch");
        payload.put("batchStatus", "ACTIVE");
        payload.put("batchNoOfClasses", 20);
        payload.put("programId", +Programid);
        payload.put("programName", "SMPO");

        
        response = getRequestSpecification(Token)
                .body(payload)
                .when()
                .put("/batches/" +batchID1); 
		System.out.println("Update batchname with valid batchID" + response.getBody().asString());

        }
	
	@Then("Admin receives {int} OK Status with updated value in response body.")
	public void admin_receives_OK_Status_with_updated_value_in_response_body(Integer int1) {
		
		assertEquals(response.getStatusCode(), int1.intValue()); 
        JsonPath jsonPath = response.jsonPath();

        
		if(response.getStatusCode() ==201) {

	        // Assert on expected values
	        assertEquals(jsonObject.get("batchDescription"), jsonPath.getString("batchDescription"));
	        //assertEquals(12345, jsonPath.getInt("batchId"));
	        batchID1= jsonPath.getInt("batchId");
	        Programid= jsonPath.getInt("programId");

	        System.out.println("ID is" + batchID1);
	        batchName= jsonPath.get("batchName");
	        System.out.println("BatchName is" + batchName);

	        assertEquals(jsonObject.get("batchName"), jsonPath.getString("batchName"));
	        assertEquals(jsonObject.get("batchDescription"), jsonPath.getString("batchDescription"));

	        assertEquals(Integer.parseInt(jsonObject.get("batchNoOfClasses").toString()), jsonPath.getInt("batchNoOfClasses"));
	        assertEquals(jsonObject.get("batchStatus"), jsonPath.getString("batchStatus"));
	        assertEquals(Integer.parseInt(jsonObject.get("programId").toString()), jsonPath.getInt("programId"));
			
	        

				
		}

	}

	@Given("Admin creates PUT Request with invalid BatchId and valid Data")
	public void admin_creates_PUT_Request_with_invalid_BatchId_and_valid_Data() {
        System.out.print("Admin creates PUT Request with invalid BatchId and valid Data");
        JSONObject payload = new JSONObject();
        payload.put("batchName", "JavaMp"+batchID1);
        payload.put("batchDescription", "MorningBatch");
        payload.put("batchStatus", "ACTIVE");
        payload.put("batchNoOfClasses", 22);
        payload.put("programId", +Programid);

        response = getRequestSpecification(Token)
                .body(payload)
                .when()
                .put("/batches/" +123); 
		System.out.println("Update batchname with valid batchID" + response.getBody().asString());

        	}

	@Then("Admin receives {int} Not Found Status with message and boolean success details_{int}")
	public void admin_receives_Not_Found_Status_with_message_and_boolean_success_details_(Integer int1, Integer int2) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
	}

	@Given("Admin creates PUT Request with valid batch Id and missing mandatory fields")
	public void admin_creates_PUT_Request_with_valid_batch_Id_and_missing_mandatory_fields() {
        System.out.print("Admin creates PUT Request with valid batch Id and missing mandatory fields");
		JSONObject payload = new JSONObject();
        payload.put("batchName", "JavaMp"+batchID1);
        payload.put("batchDescription", "MorningBatch");
       // payload.put("batchStatus", "ACTIVE");
       // payload.put("batchNoOfClasses","" );
        payload.put("programId", +Programid);

		response = getRequestSpecification(Token)
	            .body(payload)
	            .when()
	            .put("/batches/" + batchID1);

        System.out.print("end11");

	}

	@Then("Admin receives {int} Bad Request Status with message and boolean Success Details_{int}")
	public void admin_receives_Bad_Request_Status_with_message_and_boolean_Success_Details_(Integer int1, Integer int2) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
	}

	@Given("Admin creates PUT Request with valid batch Id and missing mandatory fields in request")
	public void admin_creates_PUT_Request_with_valid_batch_Id_and_missing_mandatory_fields_in_request() {
	    System.out.print("Admin creates PUT Request with valid batch Id and missing mandatory fields in request");	
        JSONObject payload = new JSONObject();
        payload.put("batchName", "JavaMp"+batchID1);
        //payload.put("batchDescription", "MorningBatch");
       // payload.put("batchStatus", "ACTIVE");
       // payload.put("batchNoOfClasses","" );
        payload.put("programId", +Programid);

		response = getRequestSpecification(Token)
	            .body(payload)
	            .when()
	            .put("/batches/" + batchID1);
    
	}

	@Then("Admin receives {int} Bad Request Status with message and boolean success details_{int}")
	public void admin_receives_Bad_Request_Status_with_message_and_boolean_success_details_(Integer int1, Integer int2) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
	}

	@Given("Admin creates PUT Request with invalida data")
	public void admin_creates_PUT_Request_with_invalida_data() {
		System.out.println("Admin creates PUT Request with invalida data");
	    JSONObject payload = new JSONObject();
	    payload.put("batchStatus", "name");
	    response = getRequestSpecification(Token)
	            .body(payload)
	            .when()
	            .put("/batches/" + batchID1);

	}

	@Then("Admin receives {int} Bad Request Status with message and boolean Success details_{int}")
	public void admin_receives_Bad_Request_Status_with_message_and_boolean_Success_details_(Integer int1, Integer int2) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
	}

	@Given("Admin creates PUT Request with Valid batch Id")
	public void admin_creates_PUT_Request_with_Valid_batch_Id() {
		System.out.print("Admin creates PUT Request with Valid batch Id");
	    JSONObject payload = new JSONObject();
	    payload.put("batchDescription", "EveningBatch");
	    payload.put("batchNoOfClasses", "22");
	    response = getRequestSpecification(Token)
	            .body(payload)
	            .when()
	            .put("/batches/" + batchID1);

	}

	@Then("Admin receives {int} Bad Request Status with message_{int}")
	public void admin_receives_Bad_Request_Status_with_message_(Integer int1, Integer int2) {
	    // Write code here that turns the phrase above into concrete actions
	   // throw new io.cucumber.java.PendingException();
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
        
        System.out.println("test2 endpoint " + myEndPoint + mySearchString);

        
        if (token.equals("no"))
        {
            response = getRequestSpecificationNoAuth()
    				.when().get(myEndPoint + mySearchString); 
        	
        }
        else
        {
        response = getRequestSpecification(Token)
				.when().get(myEndPoint + mySearchString);         }

    }
	@Then("Admin receives {string}")
	public void admin_receives(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
	}

	
	
	@Given("Admin creates GET Request with valid Batch ID")
	public void admin_creates_GET_Request_with_valid_Batch_ID() {
		System.out.print("Admin creates GET Request with valid Batch ID");

		response = getRequestSpecificationNoAuth()
				.when().get("/batches/batchId/batchId"); // Replace with your endpoint
        System.out.println("With valid batch id :Response Body" + response.getBody().asString());
          

	}

	
	
	/*@Then("Admin receives {int} OK Status with response body.")
	public void admin_receives_OK_Status_with_response_body(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
	} */		


	@Given("Admin creates GET Request with invalid Batch ID")
	public void admin_creates_GET_Request_with_invalid_Batch_ID() {
		System.out.print("Admin creates GET Request with invalid Batch ID");

		response = getRequestSpecification(Token)
				.when().get("/batches/batchId/" + 12345); }
		


	    
	

	@Then("Admin receives {int} Not Found Status with message and boolean success details")
	public void admin_receives_Not_Found_Status_with_message_and_boolean_success_details(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
	}
	
	
	@Given("Admin creates GET Request with valid Batch ID and invalid endpoint")
	public void Admin_creates_GET_Request_with_valid_Batch_ID_and_invalid_endpoint() {
		System.out.print("Admin creates GET Request with valid Batch ID and invalid endpoint");
		response = getRequestSpecification(Token)
				.when().get("/batches/"+batchID1);
        System.out.println("Invalid end point");
} 

	@Then("Admin receives {int} not found  Status")
	public void admin_receives_not_found_Status(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
	}
	
	/// NoAuthorization ///
	
	@Given("Admin creates GET Request with batch Name")
	public void admin_creates_GET_Request_with_batch_Name() {
		
		System.out.print("Admin creates GET Request with valid BatchName");
		response = getRequestSpecificationNoAuth()
				.when().get("/batches/batchName/" + batchName); // Replace with your endpoint
       
		System.out.println("GET Request with batch Name" + response.getBody().asString());

	}
	
	

	@Given("Admin creates PUT Request with valid BatchId and Data")
	public void admin_creates_PUT_Request_with_valid_BatchId_and_Data() {
		System.out.print("Admin creates PUT Request with valid BatchId and Data");

		JSONObject payload = new JSONObject();
        
        payload.put("batchName", "JavaMp"+batchID1);
        payload.put("batchDescription", "MorningBatch");
        payload.put("batchStatus", "ACTIVE");
        payload.put("batchNoOfClasses", 20);
        payload.put("programId", +Programid);
       // payload.put("programName", "SMPO");

        
        response = getRequestSpecificationNoAuth()
                .body(payload)
                .when()
                .put("/batches/" +batchID1); 
  System.out.println("Admin creates PUT Request with valid BatchId and Data :Response Body" + response.getBody().asString());
     

	}
	 
	
	@Given("Admin creates GET Request with valid Batch ID_NoAuth")
	public void admin_creates_GET_Request_with_valid_Batch_ID_NoAuth() {
		System.out.print("Admin creates GET Request with valid BatchId");
		response = getRequestSpecificationNoAuth()
				.when().get("/batches/batchID/" + batchID1); 
     System.out.println(" with valid Batch ID_NoAuth :Response Body" + response.getBody().asString());

       

	}
	

	@Given("Admin creates GET Request with program id_NoAuth")
	public void admin_creates_GET_Request_with_program_id_NoAuth() {
		
		System.out.print("Admin creates GET Request with valid ProgramId");
		response = getRequestSpecificationNoAuth()
				.when().get("/batches/program/" + Programid); 
        System.out.println("with program id_NoAuth :Response Body" + response.getBody().asString());

       

		
	}
	
	////DELETE
	
	@Given("Admin creates DELETE Request with valid BatchId_NoAuth")
	public void admin_creates_DELETE_Request_with_valid_BatchId_NoAuth() {
		
		response = getRequestSpecificationNoAuth()
				.when().get("/batches/" + batchID1 ); 


	}

	@Then("Admin receives {int} Unauthorized Status")
	public void admin_receives_Unauthorized_Status(Integer int1) {
		assertSame(401, response.getStatusCode());
		
	}

	@Given("Admin creates DELETE Request with valid BatchId_WithAuth1")
	public void admin_creates_DELETE_Request_with_valid_BatchId_WithAuth1() {
		System.out.println("delete batchId " + batchID1);
		response = getRequestSpecification(Token)
				.when().delete("/batches/" + batchID1 ); 
		

   
             
	}

	@Then("Admin receives {int} Ok status with message")
	public void admin_receives_Ok_status_with_message(Integer int1) {
		 System.out.println("Delete with program id_NoAuth :Response Body" + response.getBody().asString());

		assertSame(response.getStatusCode(), 200);
       


	}
     //With Invalid End point
	@Given("Admin creates DELETE Request with valid BatchId_WithAuth2")
	public void admin_creates_DELETE_Request_with_valid_BatchId_WithAuth2() {
		response = getRequestSpecification(Token)
				.when().delete("/batches1/" + batchID1 ); 


	}

	@Then("Admin receives {int} not found")
	public void admin_receives_not_found(Integer int1) {
		assertSame(404, response.getStatusCode());

	}

	@Given("Admin creates DELETE Request with invalid BatchId_WithAuth3")
	public void admin_creates_DELETE_Request_with_invalid_BatchId_WithAuth3() {
		
		response = getRequestSpecification(Token)
				.when().delete("/batches/" + 123 ); 

		
		
	} 



	
	

	




	


	
	

}

