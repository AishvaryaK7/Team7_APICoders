package api.Utilities;
import io.cucumber.java.Before;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import java.io.FileOutputStream;
import java.io.PrintStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.cucumber.java.Scenario;

public class LoggingSetup implements Filter{
	

	private static  final Logger logger=LogManager.getLogger(LoggingSetup.class);
	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {
		
		Response response=ctx.next(requestSpec, responseSpec);
		if(response.getStatusCode()!=200&response.getStatusCode()!=201) {
			logger.error("\n Method =>"+requestSpec.getMethod()+"\n URI =>"+requestSpec.getURI()+"\n Request Body =>"+requestSpec.getBody()+"\n Response Body =>"+response.getBody().prettyPrint());
		}
		return response;
	}


}
