package api.testcases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import api.endpoints.EcommerceEndpoints;
import api.endpoints.UserEndponits;
import api.requestPayload.Login_Request;
import api.requestPayload.User_pojo;
import api.responsePayload.Login_Response;
import api.testBase.BaseClass;
import io.restassured.response.Response;

public class EcommerceTest {
	
	Login_Request lreq;
	Response login_response;
	public Logger logger;
	static String token;
	static String Userid;
	
	//website to test: "https://rahulshettyacademy.com/client/"
	//refer "ecommerce" doc for api contract details
	
	@BeforeClass()
	public void setup()
	{
		
	    lreq = new Login_Request();
		lreq.setUserEmail("shivamsangwan2011@gmail.com");
		lreq.setUserPassword("Aa1@Aa1@K");
		
		//logging into file
		logger = LogManager.getLogger(this.getClass());
	}
	
	
	//part1: login api
	@Test(priority =1)
	public void testLogin() throws IOException
	{  
		
		login_response = EcommerceEndpoints.login(lreq);
		Login_Response lres = login_response.then().log().all()
		.extract().response().as(Login_Response.class); //deserilization
	    
		//fetching token and userid from response
		String token = lres.getToken();
		String Userid = lres.getUserId();
		
	}
	
	//part2: Create Product api(no serialization/deserialization due to simple payload/response)
	@Test(priority =1)
	public void testCreateProduct() throws IOException
	{  
		
		Response createProduct_response = EcommerceEndpoints.createProduct(Userid,token);
	    
		//fetching pid using jsonpath(no deserilization)
		String pid = BaseClass.ExtractResponseData(createProduct_response, "productid");
	    
		
		
	}
}
