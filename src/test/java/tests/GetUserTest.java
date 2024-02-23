package tests;

import org.testng.annotations.Test;

import client.RestClient;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class GetUserTest {
	
	RestClient restClient;

	@Test
	public void getAllUsers() {
		restClient = new RestClient();
		
		restClient.get("/public/v2/users", true)
			.then().log().all()
				.statusCode(200);
	}
	
	@Test
	public void getSPecificUser() {
		restClient = new RestClient();
		
		restClient.get("/public/v2/users/6663407", true)
			.then().log().all()
				.statusCode(200)
					.and()
						.body("id", equalTo(6663407));
	}
	
	@Test
	public void getSPecificUser_WithQueryParam() {
		restClient = new RestClient();
		Map<String,String> queryParams = new HashMap<String, String>();
		queryParams.put("name", "sahil");
		queryParams.put("status", "active");
		
		restClient.get( "/public/v2/users",queryParams, null, true)
			.then().log().all()
				.statusCode(200);
						
	}
	
	
}
