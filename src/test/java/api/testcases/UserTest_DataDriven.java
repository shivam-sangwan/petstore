package api.testcases;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import api.endpoints.UserEndponits;
import api.requestPayload.User_pojo;
import api.utilities.DataProviderUtilities;
import io.restassured.response.Response;

public class UserTest_DataDriven {
      //read payload data from excel using dataprovider
	  //Note: if excel have 10 rows then below method will run 10 times
	  //i.e. 10 different users will get created
	
	
	@Test(priority =1, dataProvider = "Data", dataProviderClass = DataProviderUtilities.class)
	public void testpostuser(String id, String username,String firstName, String lastName, String email, String password, String phone, String userStatus) throws IOException
	{  
		
		User_pojo up = new User_pojo();
		up.setEmail(email);
		up.setFirstName(firstName);
		up.setId(id);
		up.setLastName(lastName);
		up.setPassword(password);
		up.setPhone(phone);
		up.setUsername(username);
		up.setUserStatus(userStatus);
		
		Response post_response = UserEndponits.createUser(up); 
		Assert.assertEquals(post_response.getStatusCode(), 200);
		
	}
	
}
