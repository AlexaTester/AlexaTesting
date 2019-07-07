package com.tetrasoft.alexatest.test;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GmailSending 
{
	public Properties properties;
	public FileInputStream fis;	
	private WebDriver driver; 
	public String Actualbrowser;
	
	@BeforeClass
	public void testSetUp() 
	{
		try 
		{
			fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\configuration.properties");
			properties = new Properties();
			properties.load(fis);
			
			System.getProperty("browser");
			
			 if(Actualbrowser.equalsIgnoreCase("firefox") ) 
			  {
				 System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+properties.getProperty("firefoxDriverExepath"));
				  driver = new FirefoxDriver(); 
			  }          
	          else if(Actualbrowser.equalsIgnoreCase("chrome") )
			  {
	        	  System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+properties.getProperty("chromeDriverExepath"));
	  			  driver = new ChromeDriver();
			  }
				
			  Thread.sleep(5000);
			
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}	
		
	}
	
	@Test(priority=1)
	public void navigateToGmailHomePage() 
	{
		try 
		{
			driver.navigate().to(properties.getProperty("application_url"));
			Thread.sleep(5000);
			driver.findElement(By.xpath(properties.getProperty("userid"))).sendKeys(properties.getProperty("userName"));
			Thread.sleep(5000);
			driver.findElement(By.xpath(properties.getProperty("next"))).click();	
			Thread.sleep(5000);
			driver.findElement(By.xpath(properties.getProperty("password"))).sendKeys(properties.getProperty("password"));
			Thread.sleep(5000);
			driver.findElement(By.xpath(properties.getProperty("next"))).click();
			Thread.sleep(10000);
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
	}
	
	
	@Test(priority=2)
	public void composeaMail() 
	{
		try 
		{
			driver.findElement(By.xpath(properties.getProperty("compose"))).click();
			Thread.sleep(20000);
			driver.findElement(By.xpath(properties.getProperty("to"))).sendKeys("itsgourisankar@gmail.com"+","+"gourishankar.muvvala@gmail.com");
			Thread.sleep(5000);
			driver.findElement(By.xpath(properties.getProperty("to"))).sendKeys(",");
			Thread.sleep(5000);
			driver.findElement(By.xpath(properties.getProperty("to"))).sendKeys("nagendranelluri.mca@gmail.com");
			Thread.sleep(5000);
			driver.findElement(By.xpath(properties.getProperty("subject"))).sendKeys("For Alexa Testing From Alexa");
			Thread.sleep(10000);
			driver.findElement(By.xpath(properties.getProperty("body"))).sendKeys("For Alexa Testing From Alexa mic testing 1 2 3...");
			Thread.sleep(5000);
			driver.findElement(By.xpath(properties.getProperty("send"))).click();
			Thread.sleep(10000);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	@AfterClass 
	  public void tearDown() throws InterruptedException 
	  { 
		Thread.sleep(10000);  
		driver.quit(); 
	  }
	 
}
