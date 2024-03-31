package api.Utilities;

import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class BaseClass 
{
	public static String BearerToken = "";
	public static Logger log = LogManager.getLogger();
	public static RequestSpecification ReqSpec;
	public static Response response;
	
	//Resource Bundle for Credentials.properties file
	public static ResourceBundle login = ResourceBundle.getBundle("Credentials");
	
	//Resource Bundle for Routes.properties file
	public static ResourceBundle routes = ResourceBundle.getBundle("Routes");

	//Resource Bundle for Path.properties file
	public static ResourceBundle path = ResourceBundle.getBundle("Path");
	public static ExcelUtilis xlutils=new ExcelUtilis(path.getString("TestDataFile"));

	//public static Logger log = LogManager.getLogger();
	
	//Payload Objects
	

	//Sets the Request Header with Authentication 
	public void setRequestHeader() 
	{		
		ReqSpec= new RequestSpecBuilder().setBaseUri(routes.getString("baseURL")).setContentType("application/json").build();
		ReqSpec.header("Authorization","Bearer "+BearerToken);
	}
}
