package Railway;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Constant.Constant;

public class RegisterTest {
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Pre-condition");
	
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
	public void TC07() {
		System.out.println("User can't create account with an already in-use email");
		HomePage homePage = new HomePage();
		homePage.open();
		RegisterPage registerPage = homePage.gotoRegisterPage();
		
		registerPage.register(Constant.REGISTER_EMAIL, Constant.PASSWORD, Constant.CONFIRM_PASSWORD, Constant.PID);
		String actualMsg = registerPage.getErrorMsg();
		String expectedMsg = "This email address is already in use.";
		
		Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");		
	}
	
	@Test
	public void TC08() {
		System.out.println("User can't create account while password and PID fields are empty");
		HomePage homePage = new HomePage();
		homePage.open();
		RegisterPage registerPage = homePage.gotoRegisterPage();
		registerPage.register(Constant.REGISTER_EMAIL, null, null, null);
		
		SoftAssert softAssert = new SoftAssert();
		String actualMsg = registerPage.getErrorMsg();
		String expectedMsg = "There're errors in the form. Please correct the errors and try again.";
		softAssert.assertEquals(actualMsg, expectedMsg);
		
		String actualErrorPwMsg = registerPage.getValidateErrorPw();
		String expectedErrorPwMsg = "Invalid password length.";
		
		softAssert.assertEquals(actualErrorPwMsg, expectedErrorPwMsg);
		
		String actualErrorPidMsg = registerPage.getValidateErrorPid();
		String expectedErrorPidMsg = "Invalid ID length.";
		softAssert.assertEquals(actualErrorPidMsg, expectedErrorPidMsg);
		
		softAssert.assertAll();
	}
	
}
