package api.StepDefinition;

import api.Request.UserLoginRequest;

import api.Utilities.BaseClass;

import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static io.restassured.RestAssured.baseURI;

public class UserLoginStepDef extends BaseClass
{
	 String Email;
	 String Passwd;
	 String Login_URL;
	 String InvalidLogin_URL;
	 String BaseURL = routes.getString("baseURL");
	 

	@Given("Admin creates request with valid credentials")
	public void admin_creates_request_with_valid_credentials()
	{    
	    baseURI = BaseURL;
	    Email = login.getString("Valid_Email");
	    Passwd= login.getString("Valid_Password");
	}

	@When("Admin calls Post Https method  with valid endpoint")
	public void admin_calls_Post_Https_method_with_valid_endpoint() 
	{
		Login_URL = routes.getString("UserLogin_Post_URL");
		response = UserLoginRequest.postLogin(Email, Passwd,Login_URL);
	    
	}

	@Then("Admin receives {int} created with auto generated token")
	public void admin_receives_created_with_auto_generated_token(int StatusCode)
	{
	    assertEquals(201,StatusCode);
	}

	@When("Admin calls Post Https method  with invalid endpoint")
	public void admin_calls_Post_Https_method_with_invalid_endpoint() 
	{
		InvalidLogin_URL = "InvalidEndpoint";
		response = UserLoginRequest.postLogin(Email, Passwd,InvalidLogin_URL);
	}

	@Then("Admin receives {int} unauthorized")
	public void admin_receives_unauthorized(int StatusCode) 
	{
		assertEquals(401,StatusCode);
	}

	@Given("Admin creates request with invalid credentials")
	public void admin_creates_request_with_invalid_credentials() 
	{
		baseURI = BaseURL;
		Email = login.getString("InValid_Email");
	    Passwd= login.getString("InValid_Password");
	}

	@Then("Admin receives {int} Bad request")
	public void admin_receives_Bad_request(int StatusCode) 
	{
		assertEquals(400,StatusCode);
	}


}
