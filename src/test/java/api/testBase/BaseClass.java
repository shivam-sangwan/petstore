package api.testBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class BaseClass {
	
	    //to extract any value from any response
		public static String ExtractResponseData(Response response, String key) 
		{
			String s = response.asString();
			JsonPath js = new JsonPath(s);
			String value = js.get(key).toString();
			return value;
		}
		
		//loading properties file which contains endpoints of Http methods for User_pojo module/resource
		public static Properties getEndpoints() throws IOException
		{
			FileInputStream file = new FileInputStream("C:\\Users\\shiva\\eclipse-workspace\\petStore\\Test_Data\\application.properties");
			Properties propobj = new Properties();
			propobj.load(file);
			return propobj;
			
		}
		
		

}
