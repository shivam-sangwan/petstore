package api.endpoints;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;

import api.requestPayload.Login_Request;
import api.requestPayload.User_pojo;
import api.testBase.BaseClass;
import io.restassured.response.Response;

public class EcommerceEndpoints {
	
	//1: login api
		public static Response login(Login_Request payload) throws IOException
		{
			String login_url = BaseClass.getEndpoints().getProperty("login_url"); //taking data from properties file
			
			Response response = given().header("Content-Type","application/json")
		    .body(payload) //serialization
			.when().post(login_url);
			return response;
		}
		
	//2: create product
		public static Response createProduct(String Userid, String token) throws IOException
		{
			String login_url = BaseClass.getEndpoints().getProperty("login_url"); //taking data from properties file
			
			Response response = given().header("Authorization",token) //using token provided by login response
			.formParam("productName", "qwerty")
			.formParam("productAddedBy", Userid) //userid provided by login response
			.formParam("productCategory", "fashion")
			.formParam("productSubCategory", "shirts")
			.formParam("productPrice", 11500)
			.formParam("productDescription", "Addias Originals")
			.formParam("productFor", "women")
			.multiPart("File",new File("C:\\Users\\shiva\\Pictures\\flower.jpg"))
	        .when().post("/api/ecom/product/add-product");
			return response;
		}   
			

}
