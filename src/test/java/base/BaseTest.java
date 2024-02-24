package base;

import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import client.RestClient;
import configuration.ConfigurationManager;

public class BaseTest {
	
	Properties prop;
	protected RestClient restClient;
	ConfigurationManager configManager;
	
	@BeforeTest
	@Parameters({"baseURI"})
	public void setup(String baseURI) {
		
		configManager = new ConfigurationManager();
		prop = configManager.initProp();
		//String baseURI = prop.getProperty("baseURI");
		restClient = new RestClient(prop, baseURI);

	}

}
