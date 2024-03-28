package api.StepDefinition;

import static org.junit.Assert.assertEquals;

import api.Request.UserLogoutRequest;
import api.Utilities.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserLogoutStepDef extends BaseClass
{
	String Logout_URL;
	
	@Given("Admin creates request")
	public void admin_creates_request() 
	{
	    setRequestHeader();
	}

	@When("Admin calls Get Https method with valid endpoint")
	public void admin_calls_Get_Https_method_with_valid_endpoint() 
	{
	    Logout_URL=routes.getString("UserLogout_Get_URL");
	    response = UserLogoutRequest.getlogout(Logout_URL);
	 }

	@Then("Admin receives {int} ok  and response with {string}")
	public void admin_receives_ok_and_response_with(int StatusCode, String StatusMsg)
	{
		assertEquals(200,StatusCode);
		//status message
	}

	@When("Admin calls Get Https method withinvalid endpoint")
	public void admin_calls_Get_Https_method_withinvalid_endpoint() 
	{
	    
	}

	@Then("Admin receives {int} Not found")
	public void admin_receives_Not_found(int StatusCode) 
	{
	    
	}

	@Then("Admin receives {int}  unauthorized")
	public void admin_receives_unauthorized(int StatusCode) 
	{
	    
	}

}
