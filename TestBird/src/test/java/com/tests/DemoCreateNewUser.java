package com.tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.Base;
import com.pages.CreateUser;
import com.pages.DashboardPage;
import com.pages.LoginPage;
import com.pages.UsresPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DemoCreateNewUser extends Base {
	DashboardPage dp;
	UsresPage up;
	LoginPage lp;
	CreateUser cu;
	ExtentReports reports;

	public DemoCreateNewUser() throws IOException {
		super();
		initiation();

	}

//	static WebDriver driver;
//	static String url = prop.getProperty("qa_url");
//	static String url = "https://opensource-demo2.orangehrmlive.com/index.php/auth/login";
	String new_User_Name = "Test QA4";

	@Test(priority = 0)
	public void test() throws InterruptedException, IOException {
		try {
			ExtentTest test = reports.startTest("TestName");
			lp = new LoginPage();
			lp.login(prop.getProperty("q_name"), prop.getProperty("q_pwd"));

			dp = new DashboardPage();
			dp.clickAdminTab();

			Thread.sleep(5000);

			// Click Add
			up = new UsresPage();
			up.clickAdd();
			Thread.sleep(5000);

			cu = new CreateUser();

			// select role
			cu.selectRole();

			Thread.sleep(3000);
			// enter emp name
			cu.enterEmpUserName("Peter Mac Anderson");

			Thread.sleep(5000);
			cu.clickEmp();

			Thread.sleep(3000);
			// enter user name
			cu.enterNewUserName(new_User_Name);
			// select status
			cu.selectStatus("Enabled");

			// enter pwd
			cu.enterPwd("admin123");

			// enter confirm pwd
			cu.enterConfirmPwd("admin123");

			Thread.sleep(5000);
			// click submit
			cu.clickSave();

			Thread.sleep(5000);
			// verify the newly added user
			up.enterSearchUserName(new_User_Name);

			// click search button
			up.clickSearchBtn();

			Thread.sleep(3000);
			String getUserName = up.getUserName();
			System.out.println("created user with user name as : " + getUserName);
			// verify new user exists in the search result.
			Assert.assertEquals(getUserName, new_User_Name);
		} catch (Exception e) {
			System.out.println("error occurred : " + e);
		} finally {
			// if user is not in admin page then only click Admin tab.
			dp.clickAdminTab();

		}
	}

	@Test(priority = 1)
	public void verifyUsersList() throws InterruptedException, IOException {

		Thread.sleep(5000);

		UsresPage up = new UsresPage();
		Thread.sleep(5000);
		// verify the newly added user
		up.enterSearchUserName(new_User_Name);

		// click search button
		up.clickSearchBtn();

		Thread.sleep(3000);
		String getUserName = up.getUserName();
		System.out.println("created user with user name as : " + getUserName);
		Assert.assertEquals(getUserName, new_User_Name);

	}

}
