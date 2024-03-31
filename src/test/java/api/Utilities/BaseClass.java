package api.Utilities;

import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class BaseClass 
{
	
	public static RequestSpecification ReqSpec;
	public static Response response;
	
	public static String Token;
	public static int PROGRAMID;
	public static String PROGRAMNAME;
	
	//Resource Bundle for Credentials.properties file
	public static ResourceBundle login = ResourceBundle.getBundle("Credentials");
	
	//Resource Bundle for Routes.properties file
	public static ResourceBundle routes = ResourceBundle.getBundle("Routes");

	//Resource Bundle for Path.properties file
	public static ResourceBundle path = ResourceBundle.getBundle("Path");
	
	
	public static ExcelUtilis xlutils=new ExcelUtilis(path.getString("TestDataFile"));
	
	public static boolean backgroundExecuted = false;

	public static Logger log = LogManager.getLogger();
	
	//Payload Objects

	
	//Sets the Request Header with Authentication 
	public void setRequestHeader(String BearerToken) 
	{		
		ReqSpec= new RequestSpecBuilder().setBaseUri(routes.getString("baseURL")).setContentType("application/json").build();
		ReqSpec.header("Authorization","Bearer " +BearerToken);
		System.out.println("Inside SetRequest Header - Bearer Token - "+BearerToken);
	}
}
