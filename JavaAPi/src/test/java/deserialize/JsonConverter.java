package deserialize;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.BeforeSuite;

import deserialize.Get.Users;
import deserialize.Get.Resource;
import io.restassured.path.json.JsonPath;

public class JsonConverter {

	
	private static deserialize.Get.Users usersData;
	private static deserialize.Get.Resource resourceUsersData;
	private static deserialize.Post.Users usersDataPost;
	private static deserialize.Post.Register registerDataPost;

	@BeforeSuite
	  public void beforeSuite() { 
		  usersData = new deserialize.Get.Users(); 
		  resourceUsersData = new deserialize.Get.Resource();
		  usersDataPost = new deserialize.Post.Users();
		  registerDataPost = new deserialize.Post.Register();
		  
	  }
	
	  public static deserialize.Get.Users jsonConverterUsers(String path, String reference) {
		  
		//--------- JsonConverter ---------
		
		try {
			// Convert read JSON file to a JsonPath
			JsonPath jsonPath = JsonPath.from(new String(Files.readAllBytes(Paths.get(path))));
			switch(reference.toUpperCase()) {
				case "USERDATA":
					usersData = jsonPath.getObject("data", deserialize.Get.Users.class);
					break;
				case "USERSUPPORT":
					usersData = jsonPath.getObject("support", deserialize.Get.Users.class);
					break;
			}
			  
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return usersData;
	}
	  
	  public static Resource jsonConverterResource(String path, String reference) {
		  
			//--------- JsonConverter ---------
			
			try {
				// Convert read JSON file to a JsonPath
				JsonPath jsonPath = JsonPath.from(new String(Files.readAllBytes(Paths.get(path))));
				switch(reference.toUpperCase()) {
					case "USERDATA":
						resourceUsersData = jsonPath.getObject("data", deserialize.Get.Resource.class);
						break;
					case "USERSUPPORT":
						resourceUsersData = jsonPath.getObject("support", deserialize.Get.Resource.class);
						break;	
				}
				  
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			return resourceUsersData;
		}
	  
	  public static deserialize.Post.Users jsonConverterUsersPost(String path, String reference) {
		  
			//--------- JsonConverter ---------
			
			try {
				// Convert read JSON file to a JsonPath
				JsonPath jsonPath = JsonPath.from(new String(Files.readAllBytes(Paths.get(path))));
				switch(reference.toUpperCase()) {
					case "USERDATAPOST":
						usersDataPost = jsonPath.getObject("", deserialize.Post.Users.class);
						break;		
				}
				  
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			return usersDataPost;
		}
	  
	  public static deserialize.Post.Register jsonConverterRegisterPost(String path, String reference) {
		  
			//--------- JsonConverter ---------
			
			try {
				// Convert read JSON file to a JsonPath
				JsonPath jsonPath = JsonPath.from(new String(Files.readAllBytes(Paths.get(path))));
				switch(reference.toUpperCase()) {
					case "REGISTERDATAPOST":
						registerDataPost = jsonPath.getObject("", deserialize.Post.Register.class);
						break;		
				}
				  
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			return registerDataPost;
		}	  
  
}