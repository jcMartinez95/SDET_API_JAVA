package com.unosquare;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class FirstApiTest {
	@Test
	  public void firstPullTest() {
		  
		//------------------ First Pull Request ------------------
		Reporter.log("--------- First Pull Request  --------- <p>");
		Reporter.log("--------- First Approach --------- <p>");
		
		//First Approach  
		RestAssured.baseURI = "https://reqres.in/api/";
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
		
		Reporter.log("Response Content: " + secondResponse.getBody().asString());
		
		}

  
  @BeforeMethod
  public void beforeMethod() {
  }

}
