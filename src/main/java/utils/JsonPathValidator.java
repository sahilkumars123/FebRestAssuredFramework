package utils;

import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;

import frameworkexception.FrameworkException;
import io.restassured.response.Response;

public class JsonPathValidator {
	
	private String convertToString(Response response) {
		return response.getBody().asString();
	}

	public <T> T read(Response response, String jsonPath) {
	 	
		try {
			return JsonPath.read(convertToString(response), jsonPath);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FrameworkException(jsonPath + "is not found");
		}
	}

	public <T> List<T> readList(Response response, String jsonPath) {

		//String getResponseAsaString = response.getBody().asString();
		try {
			return JsonPath.read(convertToString(response), jsonPath);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FrameworkException(jsonPath + "is not found");
		}
	}
	
	//List<Map<String, Object>>
	
	public <T> List<Map<String,T>> readListOfMaps(Response response, String jsonPath) {

		//String getResponseAsaString = response.getBody().asString();
		try {
			return JsonPath.read(convertToString(response), jsonPath);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FrameworkException(jsonPath + "is not found");
		}
	}

}
