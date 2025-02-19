package api.endpoints;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

import api.requestPayload.User_pojo;
import api.testBase.BaseClass;


import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class UserEndponits {
          //class to perform 'curd' operation on User api
          //refer swagger for api contract: "https://petstore.swagger.io/"
	
	
	//1: method for post api of User
	public static Response createUser(User_pojo payload) throws IOException
	{
		String post_url = BaseClass.getEndpoints().getProperty("post_url"); //taking data from properties file
		
		Response response = given().log().all()
		.header("Content-Type", "application/json")
		.header("accept", "application/json")
		.body(payload)
		.when().post(post_url); //post hit hote hi response generate hoga
		return response;
	}
	
	
	   //2: method for get api of User_pojo.
		public static Response readUser(String Username) throws IOException
		{
			String get_url = BaseClass.getEndpoints().getProperty("get_url");
			
			Response response = given().log().all()
			.pathParams("Username",Username)  
			//"Username" is corrsponding to {Username} of geturl
			//pathparam will populate {Username} with provided value 
			.when().get(get_url); //post hit hote hi response generate hoga
			return response;
		}
		
		//3: method for put api of User
		public static Response updateUser(String Username, User_pojo payload) throws IOException
		{
			String put_url = BaseClass.getEndpoints().getProperty("put_url"); //taking data from properties file
			
			Response response = given().log().all()
			.header("Content-Type", "application/json")
			.header("accept", "application/json")
			.pathParams("Username",Username) 
			.body(payload)
			.when().put(put_url); //post hit hote hi response generate hoga
			return response;
		}
		
		//4: method for get api of User_pojo.
		public static Response deleteUser(String Username) throws IOException
		{
			String delete_url = BaseClass.getEndpoints().getProperty("delete_url");
			
			Response response = given().log().all()
			.pathParams("Username",Username)   
			.when().delete(delete_url); 
			return response;
		}
		

}
