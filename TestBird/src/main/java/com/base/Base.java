package com.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	public static WebDriver driver;
	public static Properties prop;
	//properties file
	public Base() throws IOException {
		
		prop = new Properties();
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/config.properties");
		prop.load(fis);
		
	}
	
	public static void takeElementScreenshot(WebElement we, String name) throws IOException {
		File scrFile = we.getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(scrFile, new File(".\\screenshots\\"+name+".png"));
	}
	
	public static void takepageScreenshot(WebDriver driver, String filename) throws IOException {
		File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen, new File(".\\screenshots\\"+filename+".png"));
	}
	
	
	public static String getScreenhot(WebDriver driver, String screenshotName) throws Exception {
		String dateName = new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
                //after execution, you could see a folder "FailedTestsScreenshots" under src folder
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/"+screenshotName+dateName+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
	
	
	
	public static void initiation() {
		String browser_name = "chrome";
	if( browser_name.equalsIgnoreCase(prop.getProperty("browser"))){
		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		// launch the application.
		
		driver.get(prop.getProperty("qa_url"));
	}else {
		WebDriverManager.firefoxdriver().setup();

		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		// launch the application.
		
		driver.get(prop.getProperty("qa_url"));
	}
	
	}

}
