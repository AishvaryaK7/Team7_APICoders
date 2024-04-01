package api.TestRunner;
	import api.Utilities.TestContextSetUp;
	import io.cucumber.testng.AbstractTestNGCucumberTests;
	import io.cucumber.testng.CucumberOptions;


	
//@RunWith(Cucumber.class) //Junit execution

	@CucumberOptions(
			plugin = {"pretty",}, //reporting purpose
			monochrome=true,  //console output color
			//tags = "@Array",
				//	+ "or @stackFeature or @queueFeature or @Array or @LinkedList", //tags from feature file
			features = {"src/test/resources/Features/01UserLogin.feature",
					"src/test/resources/Features/02Program.feature",
					"src/test/resources/Features/07program_delete.feature",
					"src/test/resources/Features/11UserLogout.feature"}, //location of feature files
			glue= {"api.StepDefinition",},
			publish=true
			) //location of step definition files


	public class TestRunner extends AbstractTestNGCucumberTests{
		public static TestContextSetUp testContext = new TestContextSetUp();

	}
