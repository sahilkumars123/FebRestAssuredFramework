package client;

import java.util.Map;

import frameworkexception.FrameworkException;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {
	private static final String BASE_URI = "https://gorest.co.in";
	private static final String BEARER_TOKEN = "53762d62478eb441d3c28098ca0e436747a19f9652dabc4fa32a3b2c7c85752d";

	private static RequestSpecBuilder specBuilder;

	public RestClient()
	{
		specBuilder = new RequestSpecBuilder();
	}

	public void addAuthorizationHeader() {
		specBuilder.addHeader("Authorization", "Bearer " + BEARER_TOKEN);
	}

	private void setRequestContentType(String contentType) { // json , JSON, Json
		switch (contentType.toLowerCase()) {
		case "json":
			specBuilder.setContentType(ContentType.JSON);
			break;
		case "text":
			specBuilder.setContentType(ContentType.TEXT);
			break;
		case "xml":
			specBuilder.setContentType(ContentType.XML);
			break;
		case "multipart":
			specBuilder.setContentType(ContentType.MULTIPART);
			break;
		default:
			System.out.println("please pass the right content type");
			throw new FrameworkException("INVALIDCONTENTTYPE");
		}
	}

	private RequestSpecification createRequestSpec() {
		specBuilder.setBaseUri(BASE_URI);
		addAuthorizationHeader();
		return specBuilder.build();
	}

	private RequestSpecification createRequestSpec(Map<String, String> headersMap) {
		specBuilder.setBaseUri(BASE_URI);
		addAuthorizationHeader();
		if (headersMap != null) {
			specBuilder.addHeaders(headersMap);
		}
		return specBuilder.build();
	}

	private RequestSpecification createRequestSpec(Map<String, String> headersMap, Map<String, String> queryParams) {
		specBuilder.setBaseUri(BASE_URI);
		addAuthorizationHeader();
		if (headersMap != null) {
			specBuilder.addHeaders(headersMap);
		}
		if (queryParams != null) {
			specBuilder.addQueryParams(queryParams);
		}
		return specBuilder.build();
	}

//request spec for post calls
//passing the body and we need to tell the content type its, json or html or xml
	private RequestSpecification createRequestSpec(Object requestBody, String contentType) {
		specBuilder.setBaseUri(BASE_URI);
		addAuthorizationHeader();
		setRequestContentType(contentType);
		if (requestBody != null) {
			specBuilder.setBody(requestBody);
		}
		return specBuilder.build();
	}

//passing the body and we need to tell the content type its, json or html or xml, with headers
	private RequestSpecification createRequestSpec(Object requestBody, String contentType,
			Map<String, String> headersMap) {
		specBuilder.setBaseUri(BASE_URI);
		addAuthorizationHeader();
		setRequestContentType(contentType);
		if (headersMap != null) {
			specBuilder.addHeaders(headersMap);
		}
		if (requestBody != null) {
			specBuilder.setBody(requestBody);
		}
		return specBuilder.build();
	}

//Http Methods Utils:
	public Response get(String serviceUrl, boolean log) {
		if (log) {
			return RestAssured.given(createRequestSpec()).log().all()
					.when()
						.get(serviceUrl);
		}
		return RestAssured.given(createRequestSpec()).when().get(serviceUrl);
	}

	public Response get(String serviceUrl, Map<String, String> headersMap, boolean log) {
		if (log) {
			return RestAssured.given(createRequestSpec(headersMap)).log().all().when().get(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(headersMap)).when().get(serviceUrl);
	}

	public Response get(String serviceUrl, Map<String, String> queryParams, Map<String, String> headersMap,
			boolean log) {
		if (log) {
			return RestAssured.given(createRequestSpec(headersMap, queryParams)).log().all().when().get(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(headersMap, queryParams)).when().get(serviceUrl);
	}

//post calls
	public Response post(String serviceUrl, String contentType, Object requestBody, boolean log) {
		if (log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType)).log().all().when().post(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType)).when().post(serviceUrl);

	}

	public Response post(String serviceUrl, String contentType, Object requestBody, Map<String, String> headersMap,
			boolean log) {
		if (log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap)).log().all().when()
					.post(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap)).when().post(serviceUrl);

	}

	public Response put(String serviceUrl, String contentType, Object requestBody, boolean log) {
		if (log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType)).log().all().when().put(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType)).when().post(serviceUrl);

	}

	public Response put(String serviceUrl, String contentType, Object requestBody, Map<String, String> headersMap,
			boolean log) {
		if (log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap)).log().all().when()
					.put(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap)).when().post(serviceUrl);

	}

	public Response patch(String serviceUrl, String contentType, Object requestBody, boolean log) {
		if (log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType)).log().all().when().patch(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType)).when().post(serviceUrl);
	}

	public Response patch(String serviceUrl, String contentType, Object requestBody, Map<String, String> headersMap,
			boolean log) {
		if (log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap)).log().all().when()
					.patch(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap)).when().post(serviceUrl);
	}

	public Response delete(String serviceUrl, boolean log) {
		if (log) {
			return RestAssured.given(createRequestSpec()).log().all().when().delete(serviceUrl);
		}
		return RestAssured.given(createRequestSpec()).when().delete(serviceUrl);
	}
}
