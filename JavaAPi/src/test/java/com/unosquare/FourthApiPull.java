package com.unosquare;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import apiCore.*;
import deserialize.JsonConverter;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.*;
import org.testng.*;
import org.json.simple.parser.*;


public class FourthApiPull {

	
  private static ApiCore apiCore;
  private static JsonConverter jsonConverter;
  private static deserialize.Get.Users userData;
  private static deserialize.Get.Resource resourceUserData;
  private static deserialize.Get.Resource resourceSupportData;
  private static deserialize.Get.Users userSupport;
  private static deserialize.Post.Users userDataPost;
  private static deserialize.Post.Register registerDataPost;

  
@BeforeSuite
  public void beforeSuite() { 
	  apiCore = new ApiCore();   
	  jsonConverter = new JsonConverter();
	  userData = new deserialize.Get.Users(); 
	  userSupport = new deserialize.Get.Users();
	  resourceUserData = new deserialize.Get.Resource();
	  resourceSupportData = new deserialize.Get.Resource();
	  userDataPost = new deserialize.Post.Users(); 
	  registerDataPost = new deserialize.Post.Register();
  }

@BeforeTest
public void beforeTests()
{
	    RestAssured.baseURI = "https://reqres.in/api";
    System.out.println("Before Tests \n "
    		+ "Loaded Base URL: " + RestAssured.baseURI);
}
	
  @Test(priority = 1, enabled = true)
  public void finalPullRequest_GET_1() throws IOException, ParseException{	  
	
	System.out.println("------------------ Final Pull Request - First GET ------------------");
	Reporter.log("--------- Final Pull Request - First GET --------- <p>");  
	
	String path = CurrentPath("\\src\\main\\java\\Json\\Get\\FirstGet.json");
	try {
		userData = jsonConverter.jsonConverterUsers(path, "userData");
		Response response = apiCore.response(path, "/users/2", "GET");
		
		//Status Code
		Assert.assertEquals(response.statusCode(), 200);
		//ID
		response.then().body("data.id", equalTo(userData.getId()));
		//Email
		response.then().body("data.email", equalTo(userData.getEmail()));
		//First Name
		response.then().body("data.first_name", equalTo(userData.getFirst_name()));
		//Last Name
		response.then().body("data.last_name", equalTo(userData.getLast_name()));
		//Avatar
		response.then().body("data.avatar", equalTo(userData.getAvatar()));

	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		 
}
  
  @Test(priority = 2, enabled = true)
  public void finalPullRequest_GET_2() throws IOException, ParseException{	  
	
	System.out.println("------------------ Final Pull Request - Second GET ------------------");
	Reporter.log("--------- Final Pull Request - Second GET --------- <p>");  
	
	String path = CurrentPath("\\src\\main\\java\\Json\\Get\\SecondGet.json");
	String reference = "userSupport";
	String endPoint = "/users/5?page=2";
	try {
		userSupport = jsonConverter.jsonConverterUsers(path, reference);
		Response response = apiCore.response(path, endPoint, "GET");
		
		//Status Code
		Assert.assertEquals(response.statusCode(), 200);
		//URL
		response.then().body("support.url", equalTo(userSupport.getUrl()));
		//Text
		response.then().body("support.text", equalTo(userSupport.getText()));

	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		 
}
  
  @Test(priority = 3, enabled = true)
  public void finalPullRequest_GET_3() throws IOException, ParseException{	  
	
	System.out.println("------------------ Final Pull Request - Third GET ------------------");
	Reporter.log("--------- Final Pull Request - Third GET --------- <p>");  
	
	String path = CurrentPath("\\src\\main\\java\\Json\\Get\\ThirdGet.json");
	String reference = "userData";
	String endPoint = "/users/7?page=3";
	try {
		userData = jsonConverter.jsonConverterUsers(path, reference);
		reference = "userSupport";
		userSupport = jsonConverter.jsonConverterUsers(path, reference);
		
		Response response = given()
		  .when()
		  	.get("https://reqres.in/api"+endPoint)
		  		.then()
		  		.assertThat().statusCode(200)
		  		.assertThat().contentType(ContentType.JSON)
		  		.assertThat().body("data.id", equalTo(userData.getId()))
		  		.assertThat().body("data.email", equalTo(userData.getEmail()))
		  		.assertThat().body("data.first_name", equalTo(userData.getFirst_name()))
		  		.assertThat().body("data.last_name", equalTo(userData.getLast_name()))
		  		.assertThat().body("data.avatar", equalTo(userData.getAvatar()))
		  		.assertThat().body("support.url", equalTo(userSupport.getUrl()))
		  		.assertThat().body("support.text", equalTo(userSupport.getText()))
		  		.log().body()
		  		.extract().response();
		
		//Add to report
		// Status code
		Reporter.log("Status Code: " + response.statusCode() + "<p>");
		// Body
		Reporter.log("Response Body: " + response.body().asPrettyString() + "<p>");
		// URL 
		Reporter.log("URL: " + RestAssured.baseURI + endPoint + "<p>");
		// File Path 
		Reporter.log("File Path: " + path + "<p>");

	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		 
}
  
  
  @Test(priority = 4, enabled = true)
  public void finalPullRequest_GET_4() throws IOException, ParseException{	  
	
	System.out.println("------------------ Final Pull Request - Fourth GET ------------------");
	Reporter.log("--------- Final Pull Request - Fourth GET --------- <p>");  
	
	String path = CurrentPath("\\src\\main\\java\\Json\\Get\\FourthGet.json");
	String reference = "userData";
	String endPoint = "/unknown/2?delay=4";
	try {
		resourceUserData = jsonConverter.jsonConverterResource(path, reference);
		reference = "userSupport";
		resourceSupportData = jsonConverter.jsonConverterResource(path, reference);
		
		Response response = given()
		  .when()
		  	.get(RestAssured.baseURI + endPoint)
		  		.then()
		  		.assertThat().statusCode(200)
		  		.assertThat().contentType(ContentType.JSON)
		  		.assertThat().body("data.id", equalTo(resourceUserData.getId()))
		  		.assertThat().body("data.name", equalTo(resourceUserData.getName()))
		  		.assertThat().body("data.year", equalTo(resourceUserData.getYear()))
		  		.assertThat().body("data.color", equalTo(resourceUserData.getColor()))
		  		.assertThat().body("data.pantone_value", equalTo(resourceUserData.getPantone_value()))
		  		.assertThat().body("support.url", equalTo(resourceSupportData.getUrl()))
		  		.assertThat().body("support.text", equalTo(resourceSupportData.getText()))
		  		.log().body()
		  		.extract().response();
		
		//Add to report
		// Status code
		Reporter.log("Status Code: " + response.statusCode() + "<p>");
		// Body
		Reporter.log("Response Body: " + response.body().asPrettyString() + "<p>");
		// URL 
		Reporter.log("URL: " + RestAssured.baseURI + endPoint + "<p>");
		// File Path 
		Reporter.log("File Path: " + path + "<p>");

	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
	
	 @Test(priority = 5, enabled = true)
	  public void finalPullRequest_POST_1() throws IOException, ParseException{	  
		
		System.out.println("------------------ Final Pull Request - First POST ------------------");
		Reporter.log("--------- Final Pull Request - First POST --------- <p>");  
		
		String path = CurrentPath("\\src\\main\\java\\Json\\Post\\FirstPost.json");
		try {
			userDataPost = jsonConverter.jsonConverterUsersPost(path, "userDataPost");
			Response response = apiCore.response(path, "/users", "POST");
			
			//Status Code
			Assert.assertEquals(response.statusCode(), 201);
			//Name
			response.then().body("name", equalTo(userDataPost.getName()));
			//Job
			response.then().body("job", equalTo(userDataPost.getJob()));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			 
	}
	 

	 @Test(priority = 6, enabled = true)
	  public void finalPullRequest_POST_2() throws IOException, ParseException{	  
		
		 System.out.println("------------------ Final Pull Request - Second POST ------------------");
			Reporter.log("--------- Final Pull Request - Second POST --------- <p>");  
			
			String path = CurrentPath("\\src\\main\\java\\Json\\Post\\SecondPost.json");
			String reference = "userDataPost";
			String endPoint = "/users?delay=5";
			try {
				userDataPost = jsonConverter.jsonConverterUsersPost(path, reference);
				
				Response response = given()
						.header("Content-type", "application/json")
		                .and()
		                .body(userDataPost)
		                .when()
		                .post(RestAssured.baseURI + endPoint)
		                .then()
		                .log().body()
		                .extract().response();
				
				Assertions.assertEquals(201, response.statusCode());
				Assertions.assertEquals("application/json; charset=utf-8", response.contentType());
				Assertions.assertEquals(userDataPost.getName(), response.jsonPath().getString("name"));
				Assertions.assertEquals(userDataPost.getLastName(), response.jsonPath().getString("lastName"));
				Assertions.assertEquals(userDataPost.getJob(), response.jsonPath().getString("job"));
				Assertions.assertEquals(userDataPost.getDateOfBirth(), response.jsonPath().getString("dateOfBirth"));
				Assertions.assertEquals(userDataPost.getStartDate(), response.jsonPath().getString("startDate"));
				Assertions.assertEquals(userDataPost.getVacationDays(), response.jsonPath().getInt("vacationDays"));
				
				
				//Add to report
				// Status code
				Reporter.log("Status Code: " + response.statusCode() + "<p>");
				// Body
				Reporter.log("Response Body: " + response.body().asPrettyString() + "<p>");
				// URL 
				Reporter.log("URL: " + RestAssured.baseURI + endPoint + "<p>");
				// File Path 
				Reporter.log("File Path: " + path + "<p>");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
	}		
	 
	 @Test(priority = 7, enabled = true)
	  public void finalPullRequest_POST_3() throws IOException, ParseException{	  
		
		System.out.println("------------------ Final Pull Request - Third POST ------------------");
		Reporter.log("--------- Final Pull Request - Third POST --------- <p>");  
		
		String path = CurrentPath("\\src\\main\\java\\Json\\Post\\ThirdPost.json");
		try {
			registerDataPost = jsonConverter.jsonConverterRegisterPost(path, "registerDataPost");
			Response response = apiCore.response(path, "/register", "post");
			
			//Status Code
			Assert.assertEquals(response.statusCode(), 200);
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			 
	}	

	 
	 @Test(priority = 8, enabled = true)
	  public void finalPullRequest_POST_4() throws IOException, ParseException{	  
		
		 System.out.println("------------------ Final Pull Request - Fourth POST ------------------");
			Reporter.log("--------- Final Pull Request - Fourth POST --------- <p>");  
			
			String path = CurrentPath("\\src\\main\\java\\Json\\Post\\FourthPost.json");
			String reference = "registerDataPost";
			String endPoint = "/login";
			try {
				registerDataPost = jsonConverter.jsonConverterRegisterPost(path, reference);
				
				Response response = given()
						.header("Content-type", "application/json")
		                .and()
		                .body(registerDataPost)
		                .when()
		                .post(RestAssured.baseURI + endPoint)
		                .then()
		                .assertThat().statusCode(200)
				  		.assertThat().contentType(ContentType.JSON)
		                .log().body()
		                .extract().response();
				
				//Add to report
				// Status code
				Reporter.log("Status Code: " + response.statusCode() + "<p>");
				// Body
				Reporter.log("Response Body: " + response.body().asPrettyString() + "<p>");
				// URL 
				Reporter.log("URL: " + RestAssured.baseURI + endPoint + "<p>");
				// File Path 
				Reporter.log("File Path: " + path + "<p>");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
	}		
	 
	
	 @Test(priority = 9, enabled = true)
	  public void finalPullRequest_PUT_1() throws IOException, ParseException{	  
		
		 System.out.println("------------------ Final Pull Request - First PUT ------------------");
			Reporter.log("--------- Final Pull Request - First PUT --------- <p>");  
			
			String path = CurrentPath("\\src\\main\\java\\Json\\Put\\FirstPut.json");
			String reference = "userDataPost";
			String endPoint = "/users/2";
			try {
				userDataPost = jsonConverter.jsonConverterUsersPost(path, reference);
				
				Response response = given()
						.header("Content-type", "application/json")
		                .and()
		                .body(userDataPost)
		                .when()
		                .put(RestAssured.baseURI + endPoint)
		                .then()
		                .log().body()
		                .extract().response();
				
				Assertions.assertEquals(200, response.statusCode());
				Assertions.assertEquals("application/json; charset=utf-8", response.contentType());
				Assertions.assertEquals(userDataPost.getName(), response.jsonPath().getString("name"));
				Assertions.assertEquals(userDataPost.getLastName(), response.jsonPath().getString("lastName"));
				Assertions.assertEquals(userDataPost.getJob(), response.jsonPath().getString("job"));
				Assertions.assertEquals(userDataPost.getDateOfBirth(), response.jsonPath().getString("dateOfBirth"));
				Assertions.assertEquals(userDataPost.getStartDate(), response.jsonPath().getString("startDate"));
				Assertions.assertEquals(userDataPost.getVacationDays(), response.jsonPath().getInt("vacationDays"));
				
				
				//Add to report
				// Status code
				Reporter.log("Status Code: " + response.statusCode() + "<p>");
				// Body
				Reporter.log("Response Body: " + response.body().asPrettyString() + "<p>");
				// URL 
				Reporter.log("URL: " + RestAssured.baseURI + endPoint + "<p>");
				// File Path 
				Reporter.log("File Path: " + path + "<p>");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
	}		 

  private static String CurrentPath(String path)
  {
	  System.getProperty("user.dir");
      String fileName = System.getProperty("user.dir") + path;
      return fileName;
  }
  
}

