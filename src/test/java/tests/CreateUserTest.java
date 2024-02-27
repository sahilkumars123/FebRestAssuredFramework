package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import client.RestClient;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.User;
import utils.StringUtils;
import static org.hamcrest.Matchers.*;

public class CreateUserTest extends BaseTest {
	
	@BeforeMethod
	public void createObject() {
		restClient = new RestClient(prop, baseURI);	
	}

	@Test
	public void createUser() {
		User user = new User("sahil",StringUtils.getRandomEmailId(), "male", "active");
		
	Integer userID =	restClient.post("/public/v2/users","json",user,true, true)
			.then().log().all()
				.statusCode(201)
					.extract()
					 .path("id");
	
	System.out.println("user id is :: "+userID);
	
	//get call
	RestClient restClienGET = new RestClient(prop, baseURI);
	restClienGET.get("/public/v2/users/"+userID, true, true)
		.then().log().all()
			.statusCode(200)
				.and()
					.body("id", equalTo(userID));
						
	}
	
}