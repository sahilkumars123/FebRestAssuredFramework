package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import client.RestClient;
import constants.APIHttpStatus;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.User;
import utils.ExcelUtil;
import utils.StringUtils;
import static org.hamcrest.Matchers.*;

public class CreateUserTest extends BaseTest {
	
	@BeforeMethod
	public void createObject() {
		restClient = new RestClient(prop, baseURI);	
	}
	
//	@DataProvider
//	public Object[][] getUserData() {
//		return new Object[][] {
//			{"rithika", "female", "active"},
//			{"rajat", "male", "active"},
//			{"rahul", "male", "active"}
//		};
//	}
	
	@DataProvider
	public Object[][] getUserData() {
		return ExcelUtil.getTestData("user");
	}
	
	@Test(dataProvider = "getUserData")
	public void createUser(String name, String gender, String status) {
		User user = new User(name,StringUtils.getRandomEmailId(), gender, status);
		
	Integer userID =	restClient.post(GOREST_ENDPOINT,"json",user,true, true)
			.then().log().all()
				.statusCode(APIHttpStatus.CREATED_201.getCode())
					.extract()
					 .path("id");
	
	System.out.println("user id is :: "+userID);
	
	//get call
	RestClient restClienGET = new RestClient(prop, baseURI);
	restClienGET.get(GOREST_ENDPOINT+"/"+userID, true, true)
		.then().log().all()
			.statusCode(APIHttpStatus.OK_200.getCode())
				.and()
					.body("id", equalTo(userID));
						
	}
	
}