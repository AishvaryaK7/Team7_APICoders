package api.TestRunner;

import org.junit.runner.RunWith;



import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
//import io.cucumber.testng.AbstractTestNGCucumberTests;


//import io.cucumber.testng.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/Features/04User.feature",
	
		glue ={"api.StepDefinition"},
				
		monochrome = true,
		plugin = {"pretty","html:target/Results.html",
				"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
				
		)


public class TestRunner{
//extends AbstractTestNGCucumberTests {

}
