package com.unosquare;

import org.testng.annotations.Test;

import apiCore.ApiCore;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.FileReader;
import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;



public class FirstApiTest {
	
	private static ApiCore apiCore;
	
	@BeforeSuite
	  public void beforeSuite() { 
		  apiCore = new ApiCore();   
	  }
	
	//------------------ Third Pull Request ------------------
	@Test(priority = 3, enabled = true)
	  public void sixthApproach() throws IOException, ParseException{	  
		
		System.out.println("------------------ Response Post Login ------------------");
		Reporter.log("--------- Response Post Login  --------- <p>");  
		
		String path = CurrentPath("\\src\\main\\java\\Json\\Login.json");
		try {
			//Get response after posting data extracted from JSON file
			Response test = apiCore.response(path, "/login", "POST");
			Assert.assertEquals(test.statusCode(), 200);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	
	//------------------ Second Pull Request ------------------
	@Test(priority = 2, enabled = false)
	  public void firstPostTest() {
		  
		Reporter.log("--------- Second Pull Request --------- <p>");  
		
		int aux = 0;
		String path = null;
		String endPoint = null;
		
		do {
		//JSON file path
			switch(aux) {
			case 0: path = CurrentPath("\\src\\main\\java\\Json\\Register.json");
					endPoint = "/register";
					break;
			case 1: path = CurrentPath("\\src\\main\\java\\Json\\Login.json");
					endPoint = "/login";
					break;		
			}
			
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
		aux ++;
		}while(aux<2);
	}
	
	//------------------ First Pull Request ------------------
	@Test(priority = 1, enabled = false)
	  public void firstPullTest() {
		  
		Reporter.log("--------- First Pull Request  --------- <p>");
		Reporter.log("--------- First Approach --------- <p>");
		
		//First Approach  
		
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/users/2");
		
		// Assert that correct status code is returned.
		Assert.assertEquals(response.getStatusCode(),200);
		Reporter.log("Status Code: " + response.getStatusCode() + "<p>");
		
		//ContentType
		Assert.assertEquals(response.getContentType(), "application/json; charset=utf-8");
		Reporter.log("Content Type: " + response.getContentType() + "<p>");
		
		//ID
		response.then().body("data.id", equalTo(2));
		//Email
		response.then().body("data.email", equalTo("janet.weaver@reqres.in"));
		//First Name
		response.then().body("data.first_name", equalTo("Janet"));
		//Last Name
		response.then().body("data.last_name", equalTo("Weaver"));
		//Avatar
		response.then().body("data.avatar", equalTo("https://reqres.in/img/faces/2-image.jpg"));
		//URL
		response.then().body("support.url", equalTo("https://reqres.in/#support-heading"));
		//Test
		response.then().body("support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));
		
		
		Reporter.log("Response Content: " + response.getBody().asString() + "<p>");
		
		//------------------ Second Pull Request ------------------
		Reporter.log("--------- Second Approach --------- <p>");
		
		Response secondResponse = given()
		  .when()
		  	.get("https://reqres.in/api/unknown/2")
		  		.then()
		  		.assertThat().statusCode(200)
		  		.assertThat().contentType(ContentType.JSON)
		  		.assertThat().body("data.id", equalTo(2))
		  		.assertThat().body("data.name", equalTo("fuchsia rose"))
		  		.assertThat().body("data.year", equalTo(2001))
		  		.assertThat().body("data.color", equalTo("#C74375"))
		  		.assertThat().body("data.pantone_value", equalTo("17-2031"))
		  		.assertThat().body("support.url", equalTo("https://reqres.in/#support-heading"))
		  		.assertThat().body("support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"))
		  		.log().body()
		  		.extract().response();
		
		Reporter.log("Status Code: " + secondResponse.getStatusCode() + "<p>");
		Reporter.log("Content Type: " + secondResponse.getContentType() + "<p>");
		Reporter.log("Response Content: " + secondResponse.getBody().asString() + "<p>");
		
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
