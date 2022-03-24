package com.pages;

import java.io.IOException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.Base;

public class UsresPage extends Base {

	@FindBy(id = "btnAdd")
	WebElement addBtn;

	@FindBy(id = "searchSystemUser_userName")
	WebElement searchUser_name;

	@FindBy(id = "searchBtn")
	WebElement searchBtn;

	@FindBy(xpath = "//*[@id='resultTable']//tr/td/a[contains(text(),'Test QA')]")
	WebElement u_name;

	public String getUserName() {
		String getUserName = u_name.getText();
		return getUserName;
	}

	public UsresPage() throws IOException {
		PageFactory.initElements(driver, this);
	}

	public void clickAdd() {
		if (addBtn.isDisplayed()) {
			addBtn.click();
		} else {
			System.out.println("add button not available.");
		}

	}

	// search user
	public void enterSearchUserName(String username) {
		searchUser_name.sendKeys(username);
	}

	public void clickSearchBtn() {
		searchBtn.click();
	}

}
