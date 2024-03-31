package api.Request;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;

import api.Utilities.BaseClass;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UserRoleMapProgramRequest extends BaseClass
	{
		public static Response response;
		
		//User Login Post Request
		@SuppressWarnings("unchecked")
		public static Response UserRoleMapProgramRequest(String Program_Get)
		{	
			RestAssured.baseURI=routes.getString("baseURL");
			//System.out.println("UserRoleMapProgramRequest called ");
			//System.out.println("Value of baseURI " + RestAssured.baseURI);
			//System.out.println("Value of BearerToken " + BearerToken);
			response = given()
					.header("Authorization", "Bearer " + BearerToken) 
			        .when().get(Program_Get)
			        .then().extract().response();
			
			
			if (response.statusCode()==200)
			{
				String FirstBatchID = response.jsonPath().getString("batchId");
				//System.out.println("Program Request Successful");
				//System.out.println("UserRoleMapProgramRequest --> Response  " + response.getBody());
			}	
			else 
			{ 
				System.out.println("Get Program Request Failed"); 
			}
				
			return response;
		}	

	}
