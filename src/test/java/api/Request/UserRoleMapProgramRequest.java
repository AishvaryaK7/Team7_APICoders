package api.Request;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;

import api.Utilities.BaseClass;
import api.Utilities.LoggingSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UserRoleMapProgramRequest extends BaseClass
	{
		public static Response response;
		
		
		public static Response GetUserRoleMapProgramRequest(String Program_Get)
		{	
			
			response = given().spec(ReqSpec).when().filter(new LoggingSetup()).get(Program_Get);
	
			return response;
		}	
		public static Response DeleteUserRoleMapProgramRequest(String Program_Delete)
		{	
			response = given().spec(ReqSpec).when().filter(new LoggingSetup()).delete(Program_Delete);
	
			return response;
		}	

	}