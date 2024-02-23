package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import client.RestClient;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.User;
import utils.StringUtils;
import static org.hamcrest.Matchers.*;

public class CreateUserTest {

	@Test
	public void createUser() {
		RestClient restClient = new RestClient();
		User user = new User("sahil",StringUtils.getRandomEmailId(), "male", "active");
		
	Integer userID =	restClient.post("/public/v2/users","json",user,true)
			.then().log().all()
				.statusCode(201)
					.extract()
					 .path("id");
	
	System.out.println("user id is :: "+userID);
	
	//get call
	RestClient restClienGET = new RestClient();
	restClienGET.get("/public/v2/users/"+userID, true)
		.then().log().all()
			.statusCode(200)
				.and()
					.body("id", equalTo(userID));
						
	}
	
}