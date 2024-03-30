package api.StepDefinition;

import api.Request.UserLogoutRequest;
import api.Utilities.BaseClass;
import api.Utilities.TestContextSetUp;

import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserLogoutStepDef extends BaseClass
{
	String bearerToken;
	String InvalidBearerToken = null;
	String Logout_URL;
	String InvalidLogout_URL;
	int responseStatusCode;
	
	private TestContextSetUp testContext;
	
	public UserLogoutStepDef(TestContextSetUp testContext)
	{
		this.testContext = testContext;
		
	}
	
	@Given("Admin creates request")
	public void admin_creates_request() 
	{
		bearerToken = testContext.getBearerToken();
	    setRequestHeader(bearerToken);
	    log.info("Logger"+bearerToken);
	}

	@When("Admin calls Get Https method with valid endpoint")
	public void admin_calls_Get_Https_method_with_valid_endpoint() 
	{
	    Logout_URL=routes.getString("UserLogout_Get_URL");
	    response = UserLogoutRequest.getlogout(Logout_URL);
	    responseStatusCode=response.getStatusCode();
	 }

	@Then("Admin receives {int} ok  and response with {string}")
	public void admin_receives_ok_and_response_with(int StatusCode, String StatusMsg)
	{
		assertEquals(StatusCode,responseStatusCode);
		//status message
	}

	@When("Admin calls Get Https method withinvalid endpoint")
	public void admin_calls_Get_Https_method_withinvalid_endpoint() 
	{
		InvalidLogout_URL = routes.getString("Invalid_UserLogout_URL");
		response =UserLogoutRequest.getlogout(InvalidLogout_URL) ;
		responseStatusCode=response.getStatusCode();
	}

	@Then("Admin receives {int} Not found")
	public void admin_receives_Not_found(int StatusCode) 
	{
		assertEquals(StatusCode,responseStatusCode);
	}
	
	@Given("Admin creates request with no authorization")
	public void admin_creates_request_with_no_authorization() 
	{
		setRequestHeader(InvalidBearerToken);
		System.out.println("Given - Header Not set");
	}


	@Then("Admin receives {int}  unauthorized")
	public void admin_receives_unauthorized(int StatusCode) 
	{
		assertEquals(StatusCode,responseStatusCode);
	}

}
