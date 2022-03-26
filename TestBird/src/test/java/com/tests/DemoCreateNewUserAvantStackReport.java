package com.tests;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.base.Base;
import com.pages.CreateUser;
import com.pages.DashboardPage;
import com.pages.LoginPage;
import com.pages.UsresPage;

public class DemoCreateNewUserAvantStackReport extends Base {
	DashboardPage dp;
	UsresPage up;
	LoginPage lp;
	CreateUser cu;
	ExtentHtmlReporter htmlReporter;

	static ExtentReports extent;
//	static ExtentReports report;
	static ExtentTest test;

	public DemoCreateNewUserAvantStackReport() throws IOException {
		super();
		initiation();

	}

	public String getTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		return dtf.format(now);
	}

	@BeforeClass
	public void testBefore() {
		String val = getTime();

		htmlReporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + "//results//reports//ExtentReportResults_" + val + ".html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("OS", "Windows 11");
		extent.setSystemInfo("Browser", prop.getProperty("browser"));
		extent.setSystemInfo("App version", "HTM demo V2.4");
		extent.setSystemInfo("QA", "sasi");

		// configuration items to change the look and feel
		// add content, manage tests etc
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("Extent Report Demo");
		htmlReporter.config().setReportName("Smoke Test Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
	}

	@AfterClass
	public void tearDown() {
		// to write or update test information to reporter
		extent.flush();
	}

	@AfterMethod
	public void getResult(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAILED ", ExtentColor.RED));
			test.fail(result.getThrowable());
			takepageScreenshot(driver, result.getName()+"_"+getTime());
			
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASSED ", ExtentColor.GREEN));
			takepageScreenshot(driver, result.getName()+"_"+getTime());
		} else {
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIPPED ", ExtentColor.ORANGE));
			test.skip(result.getThrowable());
			takepageScreenshot(driver, result.getName()+"_"+getTime());
		}
	}

//	static WebDriver driver;
//	static String url = prop.getProperty("qa_url");
//	static String url = "https://opensource-demo2.orangehrmlive.com/index.php/auth/login";
	String new_User_Name = "Test QA4";

	@Test(priority = 0)
	public void createNewUserTest() throws InterruptedException, IOException {
		try {

			test = extent.createTest("createNewUserTest", "Verifying login test with valid data");
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
		test = extent.createTest("verifyUsersList", "PASSED test case");
		try {
			lp.login(prop.getProperty("q_name"), prop.getProperty("q_pwd"));
			Thread.sleep(5000);
		
//		UsresPage up = new UsresPage();
			Thread.sleep(5000);
			// verify the newly added user
			up.enterSearchUserName(new_User_Name);

			// click search button
			up.clickSearchBtn();

			Thread.sleep(3000);
			String getUserName = up.getUserName();
			System.out.println("created user with user name as : " + getUserName);
			Assert.assertEquals(getUserName, new_User_Name);

		} catch (Exception e) {

		}
	}

	@Test(priority = 2)
	public void deleteUser() throws InterruptedException, IOException {
		test = extent.createTest("deleteUser", "PASSED test case");
		try {

			Thread.sleep(5000);
			lp.login(prop.getProperty("q_name"), prop.getProperty("q_pwd"));
//		UsresPage up = new UsresPage();
			Thread.sleep(5000);
			// verify the newly added user
			up.enterSearchUserName(new_User_Name);

			// click search button
			up.clickSearchBtn();

			Thread.sleep(3000);
			String getUserName = up.getUserName();
			System.out.println("created user with user name as : " + getUserName);

			Assert.assertEquals(getUserName, new_User_Name);
			Assert.assertEquals(true, false);

		} catch (Exception e) {

		}

	}
}
