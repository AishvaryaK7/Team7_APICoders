package api.TestRunner;

import org.junit.runner.RunWith;

import api.Utilities.TestContextSetUp;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)


@CucumberOptions(features ={"src/test/resources/Features/01UserLogin.feature","src/test/resources/Features/07UserLogout.feature"}, 
glue = {"api.StepDefinition","api.BackgroundHooks"}, 
strict = true,
monochrome = true,
//tags = "@GetAll1"

plugin = {"pretty",
		"html:target/Cucumber_html_report",
		"json:target/Cucumber_json_report.json",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
		"io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm"
}
)


public class TestRunner
{	
	public static TestContextSetUp testContext = new TestContextSetUp();
}
