package org.h2k.retailmenot1.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Utility {

	static WebDriver driver;
	static WebElement e;
	  // Create Properties class object to read properties file
	public static Properties pro=new Properties();
	  
	public static WebDriver getDriverInstance(String browserType)
	{

		if(browserType.equals("FF"))
		{
			driver = new FirefoxDriver();
		}
		else if(browserType.equals("IE"))
		{
			System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		else
		{
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			driver = new ChromeDriver();
		}

		return driver;
	}

//	public static WebElement findElementByReference(String locator,String value)
//	{
//		 
//		switch(locator)
//		{
//		case "id":
//			e=driver.findElement(By.id(value));
//			break;
//		}
//		return e;
//	}

	public static Properties loadObjectProperties(String FileName) throws IOException
	{
		FileInputStream fis=new FileInputStream(FileName);

		  // Load file so we can use into our script 
		  pro.load(fis);

		 return pro;
	}
	

	
	public static String[][] readExcel(String fileName) throws BiffException, IOException
	{
		File f = new File(fileName);
		FileInputStream fis = new FileInputStream(f);
		Workbook wb = Workbook.getWorkbook(fis);
		Sheet sheet = wb.getSheet(0);
		int rows = sheet.getRows();
		int cols = sheet.getColumns();
		System.out.println("rows--->" + rows);

		System.out.println("cols--->" + cols);

		String inputArr[][]= new String[rows][cols];

		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<cols;j++)
			{
				Cell cell =sheet.getCell(j,i);
				inputArr[i][j]=cell.getContents();
			}
		}
		return inputArr;

	}

}
