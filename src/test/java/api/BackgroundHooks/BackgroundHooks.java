package api.BackgroundHooks;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.response.Response;
import api.Request.UserLoginRequest;
import api.Utilities.BaseClass;
import api.Utilities.TestContextSetUp;

public class BackgroundHooks extends BaseClass
{
	
	//public static boolean backgroundExecuted = false;
	 String Email;
	 String passwd;
	 String LoginURL;
	 String bearerToken;
	 Response responseBT;
	 private TestContextSetUp testContext;
	
		    @Before
		    public void set(Scenario scenario) 
		    {
		        
		    	System.out.println("Scenario Name ==> " +scenario.getName());
		    	System.out.println("Flag ==> " +backgroundExecuted);
		    	
		    	// Initialize the TestContextSetUp object if it's null
		        if (testContext == null)
		        {
		            testContext = new TestContextSetUp();
		        }
		        
		        if (!backgroundExecuted)
		        {
			        Email = login.getString("Valid_Email");
		            passwd = login.getString("Valid_Password");
		            LoginURL = routes.getString("UserLogin_Post_URL");
		            responseBT=UserLoginRequest.postLogin(Email,passwd,LoginURL);
		            bearerToken = responseBT.jsonPath().getString("token");
		            testContext.setBearerToken(bearerToken);
		            
		            backgroundExecuted = true;
		        }
		        
		        
		    }
}
