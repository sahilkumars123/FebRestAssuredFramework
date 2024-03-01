package tests;

import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import client.RestClient;
import constants.APIHttpStatus;

public class GetUserTest extends BaseTest {
	
	

	@BeforeMethod
	public void create() {
		restClient = new RestClient(prop, baseURI);
	}
	
	@Test(priority =3, enabled = false)
	public void getAllUsers() {
		
		restClient.get(GOREST_ENDPOINT,true, true)
			.then().log().all()
				.statusCode(APIHttpStatus.OK_200.getCode());
	}
	
	@Test(priority = 2)
	public void getSpecificUser() {
		
		restClient.get(GOREST_ENDPOINT+"/6663407", true,true)
			.then().log().all()
				.statusCode(APIHttpStatus.OK_200.getCode())
					.and()
						.body("id", equalTo(6663407));
	}
	
	@Test(priority = 1)
	public void getSPecificUser_WithQueryParam() {
		
		Map<String,String> queryParams = new HashMap<String, String>();
		queryParams.put("name", "sahil");
		queryParams.put("status", "active");
		
		restClient.get( GOREST_ENDPOINT,queryParams, null, true,true)
			.then().log().all()
				.statusCode(APIHttpStatus.OK_200.getCode());
						
	}
	
	
}
