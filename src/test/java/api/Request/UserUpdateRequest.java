package api.Request;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;

import api.Utilities.BaseClass;
import api.Utilities.Cache;
import api.Utilities.LoggingSetup;

public class UserUpdateRequest extends BaseClass
{
	  private static JSONObject payload = new JSONObject();
	  
	  private static String userId =(String) Cache.getData("userid_0"); // Post User ID 
	  private static String invaliduserId = "123";
	  
	  //Update User role ID - Request call 
	  public static Response UserUpdateRequestValidID(JSONObject payload,String endpoint)
	  {
			
		response = given().spec(ReqSpec).pathParam("userID", userId).body(payload).filter(new LoggingSetup()).when().put(endpoint).then().extract().response();			
		return response;
	  }
	  
	  //Update User Request - Invalid User ID
	  public static Response UserUpdateRequestInvalidID(JSONObject payload,String endpoint)
	  {
			
		response = given().spec(ReqSpec).pathParam("userID", invaliduserId).body(payload).filter(new LoggingSetup()).when().put(endpoint).then().extract().response();
		return response;
	  }

}