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
			System.out.println("UserRoleMapProgramRequest called ");
			System.out.println("Value of baseURI " + RestAssured.baseURI);
			System.out.println("Value of BearerToken " + UserLoginRequest.bearerToken);
			response = given()
					.header("Authorization", "Bearer " + UserLoginRequest.bearerToken) 
			        .when().get(Program_Get)
			        .then().extract().response();
			
			System.out.println("Program Request Successful");
			System.out.println("UserRoleMapProgramRequest --> Response  " + response.getBody());
		
			
			if (response.statusCode()==200)
			{
				String FirstBatchID = response.jsonPath().getString("batchId");
			}	
			else 
			{ 
				System.out.println("Get Program Request Failed"); 
			}
				
			return response;
		}	
		public static Response UserRoleMapProgramRequestNOAuth(String Program_Get)
		{	
			RestAssured.baseURI=routes.getString("baseURL");
			System.out.println("UserRoleMapProgramRequest called ");
			System.out.println("Value of baseURI " + RestAssured.baseURI);
			System.out.println("Value of BearerToken " + UserLoginRequest.bearerToken);
			response = given()
			        .when().get(Program_Get)
			        .then().extract().response();
			
			System.out.println("Program Request Successful");
			System.out.println("UserRoleMapProgramRequest --> Response  " + response.getBody());
		
			
			if (response.statusCode()==200)
			{
				String FirstBatchID = response.jsonPath().getString("batchId");
			}	
			else 
			{ 
				System.out.println("Get Program Request Failed"); 
			}
				
			return response;
		}	

	}
