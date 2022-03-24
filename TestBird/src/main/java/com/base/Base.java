package com.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
	
	
	
	public static void initiation() {
	
	WebDriverManager.chromedriver().setup();

	driver = new ChromeDriver();
	driver.manage().window().maximize();
	// launch the application.
	
	driver.get(prop.getProperty("qa_url"));
	}

}
