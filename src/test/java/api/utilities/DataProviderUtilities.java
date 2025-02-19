package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviderUtilities {
	//separate class is made for dataproviders so that these can be used in..
	//...multiple test cases
	
	//dataproviders read data from excel and provide that data to test methods in test cases
	//dataprovider create a 2d array of same size as 'excel which contains test data'.
	
	 @DataProvider(name = "Data")
	  public Object[][] getAllData() throws IOException
	  {
		
		String xlfile = System.getProperty("user.dir")+"//testData//data1.xls";
		String xlsheet = "sheet1";
		ExcelUtilities eu = new ExcelUtilities(xlfile);
		int rows = eu.getRowNum(xlsheet);
		int cols = eu.getCellNum(xlsheet);
		
		//creating 2d array with same size as excel from where data is needed to be read
		Object apiData[][] = new Object[rows][cols];
		
		//read data from excel and stores in 2d array
		for(int i=1;i<=rows;i++) 
		{
			for(int j=0;j<=cols;j++) 
			{
				apiData[i-1][j] = eu.getCellData(xlsheet, i, j); //"i-1" becoz row starts from 1 in excel but 
				                       //...in 2d array first element will be stored at [0][0] position
			}
		}
		  return apiData;
	  }
	
	//note: above we have stored all cells data through 2 loops i.e. didnt have to write getcell(0),getcel(1)
	// for each element....but in absence of data provider we have to use  getcell(0),getcel(1)
	//for passing data to each element...ex: lp.sendkeys(eu.getCellData(sheet, i, 1))...here "i" is row..."1" is cell
	
	
	
	
	//dummy data provider
	@DataProvider(name = "data for calculation")
	  Object[][] calc() 
	  {
		Object c[][] = new Object[2][3];
		return c;
	  }
	
	
}
