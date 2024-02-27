package base;

import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import client.RestClient;
import configuration.ConfigurationManager;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public class BaseTest {
	
	
	public final static String GOREST_ENDPOINT = "/public/v2/users";
	public final static String CIRCUITTEST_ENDPOINT = "/api/f1";

	
	protected Properties prop;
	protected RestClient restClient;
	protected ConfigurationManager configManager;
	protected String baseURI;

	@BeforeTest
	@Parameters({"baseURI"})
	public void setup(String baseURI) {
		
		RestAssured.filters(new AllureRestAssured());
		
		configManager = new ConfigurationManager();
		prop = configManager.initProp();
		//String baseURI = prop.getProperty("baseURI");
		this.baseURI = baseURI;
		//restClient = new RestClient(prop, baseURI);

	}

}
