package com.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.Base;

public class LoginPage extends Base {

	@FindBy(id = "txtUsername")
	WebElement email;
	
	@FindBy(id = "txtPassword")
	WebElement pwd;
	
	
	@FindBy(id = "btnLogin")
	WebElement loginBtn;

	public LoginPage() throws IOException {

		PageFactory.initElements(driver, this);

	}

	public void login(String userName, String password) throws InterruptedException {
//		WebElement email = driver.findElement(By.id("txtUsername"));
		email.clear();
		email.sendKeys(userName);

//		WebElement pwd = driver.findElement(By.id("txtPassword"));
		pwd.clear();
		pwd.sendKeys(password);

//		WebElement loginBtn = driver.findElement(By.id("btnLogin"));
		loginBtn.click();
		Thread.sleep(3000);
	}
}
