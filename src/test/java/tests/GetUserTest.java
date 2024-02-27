package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import base.BaseTest;
import client.RestClient;

public class GetUserTest extends BaseTest {

	@BeforeMethod
	public void create() {
		restClient = new RestClient(prop, baseURI);
	}
	
	@Test(priority =3)
	public void getAllUsers() {
		
		restClient.get("/public/v2/users",true, true)
			.then().log().all()
				.statusCode(200);
	}
	
	@Test(priority = 2)
	public void getSpecificUser() {
		
		restClient.get("/public/v2/users/6663407", true,true)
			.then().log().all()
				.statusCode(200)
					.and()
						.body("id", equalTo(6663407));
	}
	
	@Test(priority = 1)
	public void getSPecificUser_WithQueryParam() {
		
		Map<String,String> queryParams = new HashMap<String, String>();
		queryParams.put("name", "sahil");
		queryParams.put("status", "active");
		
		restClient.get( "/public/v2/users",queryParams, null, true,true)
			.then().log().all()
				.statusCode(200);
						
	}
	
	
}
