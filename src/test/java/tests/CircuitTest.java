package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import client.RestClient;
import constants.APIHttpStatus;

public class CircuitTest extends BaseTest {
	
	@BeforeMethod
	public void createObject() {
		restClient = new RestClient(prop, baseURI);
	}
	
	@Test
	public void getAllUsers() {
		
		restClient.get(CIRCUITTEST_ENDPOINT+"/2017/circuits.json", true, false)
			.then().log().all()
				.statusCode(APIHttpStatus.OK_200.getCode());
	}
}
