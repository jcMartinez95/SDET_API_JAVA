package com.unosquare;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.FileReader;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



public class SecondApiPull {
	
	String path = null;
	String endPoint = null;

	//------------------ Second Pull Request - First Approach ------------------
	@Test(priority = 1, enabled = true)
	  public void secondPullRequest_firstApproach() {
		  
		Reporter.log("--------- Second Pull Request --------- <p>");  
		Reporter.log("--------- First Approach --------- <p>");  

		path = CurrentPath("\\src\\main\\java\\Json\\Register.json");
		endPoint = "/register";
			
		JSONParser parser = new JSONParser();
		
		try {
			Object obj = parser.parse(new FileReader(path));
	
			// Convert read JSON file to an object
			JSONObject jsonObject = (JSONObject) obj;
	
			//Add object headers & body read from JSON file
			RequestSpecification httpRequest = RestAssured.given(); 
			httpRequest.headers("Content-Type", "application/json"); 
			httpRequest.body(jsonObject.toString()); 
			//Response obtained with POST request
			Response response = httpRequest.post(endPoint);
			System.out.println("Response: " + response.asPrettyString());
			
			//Add to report
			// Body
			Reporter.log("Response Body: " + response.body().asPrettyString() + "<p>");
			// Status code
			Reporter.log("Status Code: " + response.statusCode() + "<p>");
			// JSON Body being sent
			Reporter.log("JSON Body being posted: " + jsonObject + "<p>");
			// URL 
			Reporter.log("URL: " + RestAssured.baseURI + "/users" + "<p>");
			// End
			Reporter.log("------------------------------------- <p>");
			  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//------------------ Second Pull Request - Second Approach ------------------
	@Test(priority = 2, enabled = true)
	public void secondPullRequest_secondApproach() {
		  
		Reporter.log("--------- Second Approach --------- <p>");  
		
		path = CurrentPath("\\src\\main\\java\\Json\\Login.json");
		endPoint = "/login";	
			
		JSONParser parser = new JSONParser();
		
		try {
			Object obj = parser.parse(new FileReader(path));
	
			// Convert read JSON file to an object
			JSONObject jsonObject = (JSONObject) obj;
	
			//Add object headers & body read from JSON file
			RequestSpecification httpRequest = RestAssured.given(); 
			httpRequest.headers("Content-Type", "application/json"); 
			httpRequest.body(jsonObject.toString()); 
			//Response obtained with POST request
			Response response = httpRequest.post(endPoint);
			System.out.println("Response: " + response.asPrettyString());
			
			//Add to report
			// Body
			Reporter.log("Response Body: " + response.body().asPrettyString() + "<p>");
			// Status code
			Reporter.log("Status Code: " + response.statusCode() + "<p>");
			// JSON Body being sent
			Reporter.log("JSON Body being posted: " + jsonObject + "<p>");
			// URL 
			Reporter.log("URL: " + RestAssured.baseURI + "/users" + "<p>");
			// End
			Reporter.log("------------------------------------- <p>");
			  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

private static String CurrentPath(String path)
  {
	  System.getProperty("user.dir");
      String fileName = System.getProperty("user.dir") + path;
      return fileName;
  }
  
  @BeforeMethod
  public void beforeMethod() {
	  RestAssured.baseURI = "https://reqres.in/api";
  }

}
