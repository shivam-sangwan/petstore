package api.testcases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.UserEndponits;
import api.requestPayload.User_pojo;
import api.testBase.BaseClass;
import io.restassured.response.Response;

public class UserTest  {
	
	User_pojo up; //bcoz this variable will be used in multiple methods
	Response post_response;
	Response get_response; 
	public Logger logger;
	static String username; //doing same work as placeid..making it static so that
	//..it can be used in multiple methods(get,delet,put) once it got value
	
	//method to set data in variable of 'user_pojo' for serialization
	@BeforeClass()
	public void setup()
	{
	    up = new User_pojo();
		up.setEmail("shivamsangwan2011@gmail.com");
		up.setFirstName("shivam");
		up.setId("12");
		up.setLastName("sangwan");
		up.setPassword("jonsnow");
		up.setPhone("9746537975");
		up.setUsername("a");
		up.setUserStatus("active");
		
		//logging into file
		logger = LogManager.getLogger(this.getClass());
	}
	
	//post api
	@Test(priority =1)
	public void testpostuser() throws IOException
	{  
		
		logger.info("***creating user******");
		
		//up(pojo object) will automatically converted to payload > serialization
		post_response = UserEndponits.createUser(up); 
		
		//now we can do any validation on response
		post_response.then().log().all();
		
		//validating statuscode
		Assert.assertEquals(post_response.getStatusCode(), 200);
		
		//validating header
		String ct = post_response.getHeader("Content-Type");
		Assert.assertEquals(ct, "application/json");
		
	}
	
	//get api
	@Test(priority =2)
	public void testGetuserbyName() throws IOException
	{  
		
		logger.info("***reading user info******");
		
        //extracting username from response
		username = BaseClass.ExtractResponseData(post_response, "username");
		
		//username: used to identify payload for which data need to be fetched
		Response get_response = UserEndponits.readUser(username);
		
		get_response.then().log().all();
		Assert.assertEquals(get_response.getStatusCode(), 200);
		
	}
	
	//put api
	@Test(priority =3)
	public void testUpdateUserByName() throws IOException
	{  
		//below fields will be updated
		up.setLastName("tyagi");
		up.setPassword("football");
		
	    //up contains updated data
		//username: used to identify payload for which data need to be updated
		Response put_response = UserEndponits.updateUser(username, up); 
	
		put_response.then().log().all();
		Assert.assertEquals(put_response.getStatusCode(), 200);
		
		//checking 'data is updated or not' using get api
		Response get_response2 = UserEndponits.readUser(username);
		Assert.assertEquals(get_response2.getStatusCode(), 200);
		
		String lastname = BaseClass.ExtractResponseData(get_response2, "lastName");
		Assert.assertEquals(lastname, "tyagi");
		
	}
	
	
	//delete api
	@Test(priority =4)
	public void testDeleteUserbyName() throws IOException
	{  
        
		Response delete_response = UserEndponits.deleteUser(username);
		
		delete_response.then().log().all();
		Assert.assertEquals(delete_response.getStatusCode(), 200);
		
	}
	
	
	

}
