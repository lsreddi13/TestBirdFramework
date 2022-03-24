package com.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.base.Base;

public class CreateUser extends Base {

	// role
	@FindBy(name = "systemUser[userType]")
	WebElement role;

	@FindBy(id = "systemUser_employeeName_empName")
	WebElement emp_userName;

	@FindBy(xpath = "//div[@class='ac_results']/ul/li/strong")
	WebElement selectEmp;

	@FindBy(id = "systemUser_userName")
	WebElement newUserName;

	@FindBy(id = "systemUser_status")
	WebElement selectStatus;

	@FindBy(id = "systemUser_password")
	WebElement new_pwd;

	@FindBy(id = "systemUser_confirmPassword")
	WebElement new_confirm_pwd;

	@FindBy(id = "btnSave")
	WebElement saveBtn;

	public CreateUser() throws IOException {
		PageFactory.initElements(driver, this);
	}

	public void selectRole() {
		Select selectRole = new Select(role);
		selectRole.selectByVisibleText("Admin");
	}

	public void enterEmpUserName(String emp_user_name) {
		emp_userName.sendKeys(emp_user_name);
	}

	public void clickEmp() {
		selectEmp.click();
	}

	public void enterNewUserName(String new_UserName) {
		newUserName.sendKeys(new_UserName);
	}

	public void selectStatus(String status_val) {
		Select select_Status = new Select(selectStatus);
		select_Status.selectByVisibleText(status_val);
	}

	public void enterPwd(String New_Pwd) {
		new_pwd.sendKeys(New_Pwd);
	}

	public void enterConfirmPwd(String pwd) {
		new_confirm_pwd.sendKeys(pwd);
	}

	public void clickSave() {
		saveBtn.click();
	}

}
