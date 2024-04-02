package api.Utilities;

import static io.restassured.RestAssured.given;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Random;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class BaseClass 
{
	
	public static RequestSpecification ReqSpec;
	public static Response response;
	
	//Resource Bundle for Credentials.properties file
	public static ResourceBundle login = ResourceBundle.getBundle("Credentials");
	
	//Resource Bundle for Routes.properties file
	public static ResourceBundle routes = ResourceBundle.getBundle("Routes");

	//Resource Bundle for Path.properties file
	public static ResourceBundle path = ResourceBundle.getBundle("Path");
	
	
	
	public static boolean backgroundExecuted = false;

	public static Logger log = LogManager.getLogger();
	
	

	
	//Sets the Request Header with Authentication 
	public void setRequestHeader(String BearerToken) 
	{		
		ReqSpec= new RequestSpecBuilder().setBaseUri(routes.getString("baseURL")).setContentType("application/json").build();
		ReqSpec.header("Authorization","Bearer " +BearerToken);
		//System.out.println("Inside SetRequest Header - Bearer Token - "+BearerToken);
	}
	
	public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }

        return randomString.toString();
    }
	public static RequestSpecification getRequestSpecification(String token) {
		return RestAssured.given()
        .header("Authorization", "Bearer " + token).contentType(ContentType.JSON);
	}

	
	
}
