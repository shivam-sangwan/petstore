package api.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xslf.usermodel.XSLFSheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {
	//reading data from excel
    public static FileInputStream fi;
    public static FileOutputStream fo;
    public static XSSFWorkbook wb;
    public static XSSFSheet ws;
    public static XSSFRow row;
    public static XSSFCell cell;
    public static CellStyle style; //to provide colour,font etc. to cells
    String xlfile; 
    
    
    public ExcelUtilities(String xlfile)
    {
    	this.xlfile = xlfile;
    }
	
    //method to give number of rows
    public int getRowNum(String xlsheet) throws IOException
    {
    	fi = new FileInputStream(xlfile);
    	wb = new XSSFWorkbook(fi);
    	ws = wb.getSheet(xlsheet);
    	int rownum = ws.getLastRowNum();
    	
    	wb.close();
    	fi.close();
		return rownum;
    	
    }
    
    
  //method to give number of cells
    public int getCellNum(String xlsheet) throws IOException
    {
    	fi = new FileInputStream(xlfile);
    	wb = new XSSFWorkbook(fi);
    	ws = wb.getSheet(xlsheet);
    	int cellnum = ws.getRow(1).getLastCellNum();
    	
    	wb.close();
    	fi.close();
		return cellnum;
    	
    }
    
    
    
    //method to read from excel
    public String getCellData(String xlsheet,int rownum, int colnum) throws IOException
    {
    	fi = new FileInputStream(xlfile);
    	wb = new XSSFWorkbook(fi);
    	ws = wb.getSheet(xlsheet);
    	
    	String data;
    	try
    	{
    	  data = ws.getRow(rownum).getCell(colnum).toString();	//ager cell m value nhi h to exception ayega
    	}
    	catch(Exception e)
    	{
    		data = "";   //cell khali h to data m bhi khali daal diya
    	}
    	
    	wb.close();
    	fi.close();
		return data;	
    	
    }
    

}
