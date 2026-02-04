package Railway;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Common.Utilities;
import Constant.Constant;

public class LoginTest {
	public void openWebsite() {
        System.out.println("Test is running");
    }
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Pre-condition");
		
//		System.setProperty("Webdriver.chrome.driver", Utilities.getProjectPath() + "\\Executables\\chromedriver.exe");
		Constant.WEBDRIVER = new ChromeDriver();
		Constant.WEBDRIVER.manage().window().maximize();
		Constant.WEBDRIVER.get(Constant.RAILWAY_URL);
		
	}
	@AfterMethod
	public void afterMethod() {
		System.out.println("Post-condition");
		
		Constant.WEBDRIVER.quit();
	}
//	@Test
	public void TC01() {
		System.out.println("User can log into Railway with valid username and password");
		
		HomePage homePage = new HomePage();
		
		homePage.open();
		
		LoginPage loginPage = homePage.gotoLoginPage();
		
		String actualMsg = loginPage.login(Constant.USERNAME, Constant.PASSWORD).getWelcomeMessage();
		
		String expectedMsg = "Welcome " + Constant.USERNAME;
		
		Assert.assertEquals(actualMsg, expectedMsg, "Welcome message is not displayed as expected");
	}
//	@Test
	public void TC02() {
		System.out.println("User can not login with blank 'Username' textbox");
		HomePage homePage = new HomePage();
		homePage.open();
		LoginPage loginPage = homePage.gotoLoginPage();
		loginPage.login(Constant.BLANK_USERNAME, Constant.PASSWORD);
		String actualMsg = loginPage.getErrorMsg();
		String expectedMsg = "There was a problem with your login and/or errors exist in your form.";
		Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");
	}
	
//	@Test
	public void TC03() {
		System.out.println("User cannot log into Railway with invalid password");
		HomePage homePage = new HomePage();
		homePage.open();
		LoginPage loginPage = homePage.gotoLoginPage();
		loginPage.login(Constant.USERNAME, Constant.INVALID_PASSWORD);
		String actualMsg = loginPage.getErrorMsg();
		String expectedMsg = "There was a problem with your login and/or errors exist in your form.";
		Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");
		
	}
	
//	@Test
	public void TC04() {
		System.out.println("System shows message when user enters wrong password many times");
		HomePage homePage = new HomePage();
		homePage.open();
		LoginPage loginPage = homePage.gotoLoginPage();
		SoftAssert softAssert = new SoftAssert();
		for (int i = 1; i <= Constant.TIMES_LOGIN; i++) {
			loginPage.login(Constant.USERNAME, Constant.INVALID_PASSWORD);
			
			String actualMsg = loginPage.getErrorMsg();
			if (i < Constant.TIMES_LOGIN) {
//				System.out.println("login lan thu "+ i);
//				System.out.println("actual message lan thu" + i + ":"+ actualMsg);
				String expectedMsg = "Invalid username or password. Please try again";
				softAssert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected message 1");
			} else {
//				System.out.println("login lan thu "+ i);
//				System.out.println("actual msg:::"+ actualMsg);
				String expectedMsg2 = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";
//				System.out.println("expectedMsg 2::"+ expectedMsg2);
				softAssert.assertEquals(actualMsg, expectedMsg2, "Error message is not displayed as expected message 2");
			}
		}
		softAssert.assertAll();
	
	}
	
//	@Test 
	public void TC05() {
		System.out.println("User can't login with an account hasn't been activated");
		HomePage homePage = new HomePage();
		homePage.open();
		LoginPage loginPage = homePage.gotoLoginPage();
		loginPage.login(Constant.INACTIVE_USERNAME, Constant.PASSWORD);
		String actualMsg = loginPage.getErrorMsg();
		String expectedMsg = "Invalid username or password. Please try again.";
		Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");
		
	}
	@Test
	public void TC06() {
		System.out.println("User is redirected to Home page after logging out");
		HomePage homePage = new HomePage();
		homePage.open();
		LoginPage loginPage = homePage.gotoLoginPage();
		loginPage.login(Constant.USERNAME, Constant.PASSWORD);
		loginPage.gotoFAQPage();
		
		homePage.logout();
		String actualTitle = homePage.getTxtTitle();
		String expectedTitle = "Welcome to Safe Railway";
		Assert.assertEquals(actualTitle, expectedTitle, "Home page does not displayed as expected");
	
	}
}
