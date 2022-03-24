package com.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.Base;

public class DashboardPage extends Base {

	@FindBy(xpath = "//*[@id='mainMenu']/ul/li[1]")
	WebElement adminTab;
	
	
	 
	

	
	public DashboardPage() throws IOException {
		PageFactory.initElements(driver, this);
	}

	
	public void clickAdminTab() {
		adminTab.click();
	}
	

}
