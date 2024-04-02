package api.TestRunner;
	import api.Utilities.TestContextSetUp;
	import io.cucumber.testng.AbstractTestNGCucumberTests;
	import io.cucumber.testng.CucumberOptions;

	
//@RunWith(Cucumber.class) //Junit execution

	@CucumberOptions(
			plugin = {"pretty",
					"html:target/Cucumber_html_report",
					"json:target/Cucumber_json_report.json",
					"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}, 
			monochrome=true,  
            features = {"src/test/resources/Features"},
			glue= {"api.StepDefinition","api.BackgroundHooks"},
			publish=true
			)


	public class TestRunner extends AbstractTestNGCucumberTests{
		public static TestContextSetUp testContext = new TestContextSetUp();

	}
