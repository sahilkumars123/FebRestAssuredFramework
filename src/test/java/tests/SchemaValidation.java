package tests;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import client.RestClient;
import constants.APIHttpStatus;
import pojo.User;
import utils.StringUtils;


public class SchemaValidation extends BaseTest{

	//json
	@BeforeMethod
	public void getUserSetup() {
		restClient = new RestClient(prop, baseURI);
	}
	
	@Test()
	public void createUserAPISchemaTest() {
		
		//1. post:
		User user = new User("Tom", StringUtils.getRandomEmailId(), "male", "active");		
		
		 restClient.post(GOREST_ENDPOINT, "json", user, true, true)
					.then().log().all()
						.assertThat().statusCode(APIHttpStatus.CREATED_201.getCode())
							.body(matchesJsonSchemaInClasspath("createuserschema.json"));

	}
}
