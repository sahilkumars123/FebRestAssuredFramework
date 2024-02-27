package client;

import java.util.Map;
import java.util.Properties;

import frameworkexception.FrameworkException;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {
//	private static final String BASE_URI = "https://gorest.co.in";
//	private static final String BEARER_TOKEN = "53762d62478eb441d3c28098ca0e436747a19f9652dabc4fa32a3b2c7c85752d";

	private static RequestSpecBuilder specBuilder;
	Properties prop;
	String baseURI;

	public RestClient(Properties prop, String baseURI)
	{
		specBuilder = new RequestSpecBuilder();
		this.prop = prop;
		this.baseURI = baseURI;
	}

	public void addAuthorizationHeader() {
		specBuilder.addHeader("Authorization", "Bearer " + prop.getProperty("tokenID"));
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

	private RequestSpecification createRequestSpec(boolean includeAuth) {
		specBuilder.setBaseUri(baseURI);
		if(includeAuth) {addAuthorizationHeader();}
		return specBuilder.build();
	}

	private RequestSpecification createRequestSpec(Map<String, String> headersMap,boolean includeAuth) {
		specBuilder.setBaseUri(baseURI);
		if(includeAuth) {addAuthorizationHeader();}
		if (headersMap != null) {
			specBuilder.addHeaders(headersMap);
		}
		return specBuilder.build();
	}

	private RequestSpecification createRequestSpec(Map<String, String> headersMap, Map<String, String> queryParams, boolean includeAuth) {
		specBuilder.setBaseUri(baseURI);
		if(includeAuth) {addAuthorizationHeader();}
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
	private RequestSpecification createRequestSpec(Object requestBody, String contentType, boolean includeAuth) {
		specBuilder.setBaseUri(baseURI);
		addAuthorizationHeader();
		setRequestContentType(contentType);
		if (requestBody != null) {
			specBuilder.setBody(requestBody);
		}
		return specBuilder.build();
	}

//passing the body and we need to tell the content type its, json or html or xml, with headers
	private RequestSpecification createRequestSpec(Object requestBody, String contentType,
			Map<String, String> headersMap, boolean includeAuth) {
		specBuilder.setBaseUri(baseURI);
		if(includeAuth) {addAuthorizationHeader();}
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
	public Response get(String serviceUrl, boolean log, boolean includeAuth) {
		if (log) {
			return RestAssured.given(createRequestSpec(includeAuth)).log().all()
					.when()
						.get(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(includeAuth)).when().get(serviceUrl);
	}

	public Response get(String serviceUrl, Map<String, String> headersMap, boolean log, boolean includeAuth) {
		if (log) {
			return RestAssured.given(createRequestSpec(headersMap,includeAuth)).log().all().when().get(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(headersMap,includeAuth)).when().get(serviceUrl);
	}

	public Response get(String serviceUrl, Map<String, String> queryParams, Map<String, String> headersMap,
			boolean log, boolean includeAuth) {
		if (log) {
			return RestAssured.given(createRequestSpec(headersMap, queryParams,includeAuth)).log().all().when().get(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(headersMap, queryParams, includeAuth)).when().get(serviceUrl);
	}

//post calls
	public Response post(String serviceUrl, String contentType, Object requestBody, boolean log, boolean includeAuth) {
		if (log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, includeAuth)).log().all().when().post(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType, includeAuth)).when().post(serviceUrl);

	}

	public Response post(String serviceUrl, String contentType, Object requestBody, Map<String, String> headersMap,
			boolean log, boolean includeAuth) {
		if (log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap, includeAuth)).log().all().when()
					.post(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap, includeAuth)).when().post(serviceUrl);

	}

	public Response put(String serviceUrl, String contentType, Object requestBody, boolean log, boolean includeAuth) {
		if (log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, includeAuth)).log().all().when().put(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType, includeAuth)).when().post(serviceUrl);

	}

	public Response put(String serviceUrl, String contentType, Object requestBody, Map<String, String> headersMap,
			boolean log, boolean includeAuth) {
		if (log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap, includeAuth)).log().all().when()
					.put(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap, includeAuth)).when().post(serviceUrl);

	}

	public Response patch(String serviceUrl, String contentType, Object requestBody, boolean log, boolean includeAuth) {
		if (log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, includeAuth)).log().all().when().patch(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType, includeAuth)).when().post(serviceUrl);
	}

	public Response patch(String serviceUrl, String contentType, Object requestBody, Map<String, String> headersMap,
			boolean log, boolean includeAuth) {
		if (log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap, includeAuth)).log().all().when()
					.patch(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap, includeAuth)).when().post(serviceUrl);
	}

	public Response delete(String serviceUrl, boolean log, boolean includeAuth) {
		if (log) {
			return RestAssured.given(createRequestSpec(includeAuth)).log().all().when().delete(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(includeAuth)).when().delete(serviceUrl);
	}
}
