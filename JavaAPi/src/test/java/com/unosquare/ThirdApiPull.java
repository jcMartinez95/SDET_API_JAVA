package com.unosquare;

import org.testng.annotations.Test;

import apiCore.ApiCore;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.json.simple.parser.ParseException;



public class ThirdApiPull {
	
	private static ApiCore apiCore;
	
	@BeforeSuite
	  public void beforeSuite() { 
		  apiCore = new ApiCore();   
	  }
	
	//------------------ Third Pull Request ------------------
	@Test(priority = 1, enabled = true)
	  public void thirdPullRequest() throws IOException, ParseException{	  

		System.out.println("------------------ Response Post Login ------------------");
		Reporter.log("--------- Third Pull Request  --------- <p>");  
		
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

	private static String CurrentPath(String path){
		
		  System.getProperty("user.dir");
	      String fileName = System.getProperty("user.dir") + path;
	      return fileName;
	  }
	  
	  @BeforeMethod
	  public void beforeMethod() {
		  RestAssured.baseURI = "https://reqres.in/api";
	  }

}
