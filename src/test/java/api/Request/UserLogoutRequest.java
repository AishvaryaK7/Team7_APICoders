package api.Request;

import api.Utilities.BaseClass;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class UserLogoutRequest extends BaseClass
{
	public static Response getlogout(String Logout_URL)
	{
		response = given().spec(ReqSpec).when().get(routes.getString("UserLogout_Get_URL")).then().extract().response();
		System.out.println(response.statusCode());
		return response;
	}
	
}
