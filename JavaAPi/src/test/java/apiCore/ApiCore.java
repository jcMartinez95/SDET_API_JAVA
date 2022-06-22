package apiCore;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.FileReader;
import org.testng.Reporter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class ApiCore {
	
	  public static Response response(String path, String endPoint, String method) {
		
		//JSON file path
		JSONParser parser = new JSONParser();
		Response response = null;
		
		try {
			Object obj = parser.parse(new FileReader(path));
	
			// Convert read JSON file to an object
			JSONObject jsonObject = (JSONObject) obj;
			
			//Set URL, URL type, add object body read from JSON file and get response
			RestAssured.baseURI = "https://reqres.in/api"; 
			RequestSpecification httpRequest = RestAssured.given(); 
			httpRequest.headers("Content-Type", "application/json"); 
			httpRequest.body(jsonObject.toString()); 
			//Switch between GET or POST method
			switch(method.toUpperCase()) {
				case "GET":
					response = httpRequest.get(endPoint);
					break;
				case "POST":
					response = httpRequest.post(endPoint);
					break;	
			}
			
			//Response
			System.out.println("Response: " + response.asPrettyString());
			//Add to report
			// Client request
			Reporter.log("Client request: " + jsonObject.toJSONString() + "<p>");
			// Status code
			Reporter.log("Status Code: " + response.statusCode() + "<p>");
			// Body
			Reporter.log("Response Body: " + response.body().asPrettyString() + "<p>");
			// URL 
			Reporter.log("URL: " + RestAssured.baseURI + endPoint + "<p>");
			// File Path 
			Reporter.log("File Path: " + path + "<p>");
			
			  
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return response;
	}
  
}