package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import client.RestClient;

public class CircuitTest extends BaseTest {
	
	@BeforeMethod
	public void createObject() {
		restClient = new RestClient(prop, baseURI);	
	}
	
	@Test
	public void getAllUsers() {
		
		restClient.get("/api/f1/2017/circuits.json", true, false)
			.then().log().all()
				.statusCode(200);
	}

}
