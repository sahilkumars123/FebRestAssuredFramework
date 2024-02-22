package client;

import java.util.Map;

import frameworkexception.FrameworkException;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {
	
	//1. to create different request specs --- RequestSpecification
	//2 . to create get and post request --- Response
	
	
	//Encapsulation
	private static final String BASE_URI = "https//gorest.co.in";
	private static final String BEARER_TOKEN = "53762d62478eb441d3c28098ca0e436747a19f9652dabc4fa32a3b2c7c85752d";
	
	private static RequestSpecBuilder specbuilder;
	
	static {
		specbuilder = new RequestSpecBuilder();
	}
	
	private void addAuthorizationHeader() {
		specbuilder.addHeader("Authorization", "Bearer "+BEARER_TOKEN);
	}
	
	private void setContentType(String contentType) {
		//json, xml , mutipart, text   //JSON,json, Json
		switch (contentType.toLowerCase()) {
		case "json":
			specbuilder.setContentType(ContentType.JSON);
			break;
		case "xml":
			specbuilder.setContentType(ContentType.XML);
			break;
		case "text":
			specbuilder.setContentType(ContentType.TEXT);
			break;
		case "multipart":
			specbuilder.setContentType(ContentType.MULTIPART);
			break;
		default:
			System.out.println("please pass the correct content type"+contentType);
			throw new FrameworkException("INVALIDCONTENTTYPE");
		}
	}
	
	//spec ---- base uri + authorization header 
	private RequestSpecification createRequestSpecification() {
		specbuilder.setBaseUri(BASE_URI);
		addAuthorizationHeader();
	 return	specbuilder.build();	
	}
	
	//spec --- base uri + authorization header + multiple headers
	private RequestSpecification createRequestSpecification(Map<String, String> headersMap) {
		specbuilder.setBaseUri(BASE_URI);
		if(headersMap!=null) {
			specbuilder.addHeaders(headersMap);
		}
		addAuthorizationHeader();
		return	specbuilder.build();	
	}
	
	//spec --- base uri + authorization header + multiple headers + multiple queryparams
	private RequestSpecification createRequestSpecification(Map<String, String> headersMap, Map<String, String> queryParams) {
		specbuilder.setBaseUri(BASE_URI);
		if(headersMap!=null) {
			specbuilder.addHeaders(headersMap);
		}
		if(queryParams!=null) {
			specbuilder.addQueryParams(queryParams);
		}
		addAuthorizationHeader();
		return	specbuilder.build();	
	}
	
	//post call spec
	//- body - serialization
	//-content type = json/xml/mutipart/text
	//query param - NOT NEEDED
	
	private RequestSpecification createRequestSpecification(Object requestBody, String contentType) {
		specbuilder.setBaseUri(BASE_URI);
		//specbuilder.setContentType(contentType); -- wrong approach
		setContentType(contentType);
		addAuthorizationHeader();
		if(requestBody!=null) {
			specbuilder.setBody(requestBody);
		}
		return	specbuilder.build();		
	}
	
	private RequestSpecification createRequestSpecification(Map<String, String> headersMap, Object requestBody, String contentType) {
		specbuilder.setBaseUri(BASE_URI);
		//specbuilder.setContentType(contentType); -- wrong approach
		setContentType(contentType);
		addAuthorizationHeader();
		if(headersMap!=null) {
			specbuilder.addHeaders(headersMap);
		}
		if(requestBody!=null) {
			specbuilder.setBody(requestBody);
		}
		return	specbuilder.build();		
	}
	
	
	
	//Http Methods Utils:
	public Response get(String serviceUrl) {	
	return	RestAssured.given(createRequestSpecification())
			.when()
				.get(serviceUrl);	
	}
	
	public Response get(String serviceUrl, Map<String, String> headersMap) {	
		return	RestAssured.given(createRequestSpecification(headersMap))
				.when()
					.get(serviceUrl);	
		}
	
	public Response get(String serviceUrl, Map<String, String> headersMap, Map<String, String> queryParams) {	
		return	RestAssured.given(createRequestSpecification(headersMap, queryParams))
				.when()
					.get(serviceUrl);	
		}
	
	public Response post(String serviceUrl, String contentType, Object requestBody) {
		return	RestAssured.given(createRequestSpecification(requestBody, contentType))
				.when()
					.post(serviceUrl);		
	}
	
	public Response post(String serviceUrl, String contentType, Object requestBody,Map<String, String> headersMap) {
		return	RestAssured.given(createRequestSpecification(headersMap, requestBody, contentType))
				.when()
					.post(serviceUrl);		
	}
	
	//put
	public Response put(String serviceUrl, String contentType, Object requestBody) {
		return	RestAssured.given(createRequestSpecification(requestBody, contentType))
				.when()
					.put(serviceUrl);		
	}
	
	public Response put(String serviceUrl, String contentType, Object requestBody,Map<String, String> headersMap) {
		return	RestAssured.given(createRequestSpecification(headersMap, requestBody, contentType))
				.when()
					.put(serviceUrl);		
	}
	
	//patch
	public Response patch(String serviceUrl, String contentType, Object requestBody) {
		return	RestAssured.given(createRequestSpecification(requestBody, contentType))
				.when()
					.patch(serviceUrl);		
	}
	
	public Response patch(String serviceUrl, String contentType, Object requestBody,Map<String, String> headersMap) {
		return	RestAssured.given(createRequestSpecification(headersMap, requestBody, contentType))
				.when()
					.patch(serviceUrl);		
	}
	
	//delete
	public Response delete(String serviceUrl) {
		
		return	RestAssured.given(createRequestSpecification())
				.when()
					.delete(serviceUrl);		
	}
}
