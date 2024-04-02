package api.Request;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;

import api.Utilities.BaseClass;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UserLoginRequest extends BaseClass
{
	
	public static Response responseToken;
	static String bearerToken;
	
	//User Login Post Request
	@SuppressWarnings("unchecked")
	public static Response postLogin(String Email,String Passwd,String Login_URL)
	{	
		
		RestAssured.baseURI=routes.getString("baseURL");
			
		JSONObject payload = new JSONObject();
		payload.put("password", Passwd);
		payload.put("userLoginEmailId", Email);
		
		//System.out.println(payload);
			
		responseToken = given().contentType("application/json").body(payload.toJSONString())
		.when().post(Login_URL).then().extract().response();

		//System.out.println(responseToken.statusCode());
		
		if (responseToken.statusCode()==200)
		{		
			bearerToken = responseToken.jsonPath().getString("token");
			log.info("Authorization successful",bearerToken);
		}	
		else if(responseToken.statusCode()==401)
		{ 
			System.out.println("Authorization Failed"); 
			log.info("Request failed");
			log.error("401 Unauthorized");
		}
		else if(responseToken.statusCode()==400)
		{
			System.out.println("Authorization Failed - Bad Request");
			log.info("Request failed");
			log.error("400 Bad Request");
		}
			
		return responseToken;
	}	

}