package api.Request;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;

import api.Utilities.BaseClass;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UserLoginRequest extends BaseClass
{
	
	public static Response responseToken;
	public static String bearerToken;
	
	//User Login Post Request
	@SuppressWarnings("unchecked")
	public static Response postLogin(String Email,String Passwd,String Login_URL)
	{	
		
		RestAssured.baseURI=routes.getString("baseURL");
			
		JSONObject payload = new JSONObject();
		payload.put("password", Passwd);
		payload.put("userLoginEmailId", Email);
		
		System.out.println(payload);
			
		responseToken = given().contentType("application/json").body(payload.toJSONString())
		.when().post(Login_URL).then().extract().response();

		System.out.println(responseToken.statusCode());
		
		if (responseToken.statusCode()==200)
		{		
			bearerToken = responseToken.jsonPath().getString("token");
			System.out.println("Authorization successful");
			System.out.println(bearerToken);
		}	
		else if(responseToken.statusCode()==401)
		{ 
			System.out.println("Authorization Failed"); 
		}
		else if(responseToken.statusCode()==400)
		{
			System.out.println("Authorization Failed - Bad Request");
		}
			
		return responseToken;
	}	

}
