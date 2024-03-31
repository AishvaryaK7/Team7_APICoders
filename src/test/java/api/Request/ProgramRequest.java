package api.Request;
import org.json.simple.JSONObject;
import api.Utilities.BaseClass;
//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
//import io.cucumber.java.en.*;
import static io.restassured.RestAssured.given;

import io.restassured.response.Response;

public class ProgramRequest extends BaseClass{
	
	public static Response response;
	
	public static Response CreateProgram(JSONObject payload) {
		response = given().body(payload).when().post(routes.getString("Program_Post_URL"));
		return response;
	}
	
	public static Response GetProgram(String endpoint) {
		response = given().spec(ReqSpec).when().get(endpoint);
		return response;
	}
	
	public static void ValidateStatus(Integer int1) {
	     response.then().statusCode(int1); // Example validation
	}
}
