package tests;

import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import client.RestClient;
import constants.APIHttpStatus;
import io.restassured.response.Response;
import utils.JsonPathValidator;

public class CircuitTest extends BaseTest {
	
	@BeforeMethod
	public void createObject() {
		restClient = new RestClient(prop, baseURI);
		
	}
	
	@Test
	public void getAllUsers() {
		
	Response circuitResponse =	restClient.get(CIRCUITTEST_ENDPOINT+"/2017/circuits.json", true, false);
//			.then().log().all()
//				.statusCode(APIHttpStatus.OK_200.getCode());
			
			circuitResponse	
				.then()
					.assertThat()
						.statusCode(APIHttpStatus.OK_200.getCode());
	
	JsonPathValidator js = new JsonPathValidator();
	List<String> countryList = js.readList(circuitResponse, "$..CircuitTable.Circuits[*].Location.country");
	
	System.out.println(countryList);
	
	for(String s : countryList) {
		System.out.println(s);
	}
	
	}
}
