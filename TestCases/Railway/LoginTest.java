package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;
import Common.EmailService;
import Common.Utilities;
import Common.WindowManager;
import Constant.Constant;
import Constant.MenuItem;

public class LoginTest extends TestBase {
	
	@Test
	public void TC01() {
		LoginPage loginPage = new LoginPage();
		Account account = new Account(Constant.USERNAME, Constant.PASSWORD, null);
		
		System.out.println("TC01 - User can log into Railway with valid username and password");
		
		System.out.println("1. Navigate to QA Railway Website");
		WindowManager.open(Constant.RAILWAY_URL);
		
		System.out.println("2. Click on 'Login' tab");
		loginPage = homePage.gotoPage(MenuItem.LOGIN, LoginPage.class);
		
		System.out.println("3. Enter valid Email and Password");
		System.out.println("4. Click on Login button");
		System.out.println("V.P: User is logged into Railway. Welcome user message is displayed.");
		loginPage.login(account);
		
		String actualMsg = loginPage.getWelcomeMessage();
		String expectedMsg = "Welcome " + Constant.USERNAME;
		Assert.assertEquals(actualMsg, expectedMsg, "Welcome message is not displayed as expected");
	}
	@Test
	public void TC02() {
		
		String actualMsg, expectedMsg;
		Account account = new Account(Constant.BLANK_USERNAME, Constant.PASSWORD, null);
		LoginPage loginPage = new LoginPage();
		
		System.out.println("TC02 - User can not login with blank 'Username' textbox");
		System.out.println("1. Navigate to QA Railway Website");
		WindowManager.open(Constant.RAILWAY_URL);
		
		System.out.println("2. Click on 'Login' tab");
		loginPage = homePage.gotoPage(MenuItem.LOGIN, LoginPage.class);
		
		System.out.println("3. User doesn't type any words into 'Username' textbox but enter valid information into 'Password' textbox");
		System.out.println("4. Click on 'Login' button");
		System.out.println("User can't login and message 'There was a problem with your login and/or errors exist in your form.' appears.");
		loginPage.login(account);
		
		actualMsg = loginPage.getErrorMsg();
		expectedMsg = "There was a problem with your login and/or errors exist in your form.";
		Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");
	}
	
	@Test
	public void TC03() {
		
		String actualMsg, expectedMsg;
		Account account = new Account(Constant.USERNAME, Constant.INVALID_PASSWORD, null);
		LoginPage loginPage = new LoginPage();
		
		System.out.println("TC03 - User cannot log into Railway with invalid password");
		
		System.out.println("1. Navigate to QA Railway Website");
		WindowManager.open(Constant.RAILWAY_URL);
		
		System.out.println("2. Click on 'Login' tab");
		loginPage = homePage.gotoPage(MenuItem.LOGIN, LoginPage.class);
		
		System.out.println("3. Enter valid Email and invalid Password\n");
		System.out.println("4. Click on 'Login' button");
		System.out.println("Error message 'There was a problem with your login and/or errors exist in your form.' is displayed");
		loginPage.login(account);

		actualMsg = loginPage.getErrorMsg();
		expectedMsg = "There was a problem with your login and/or errors exist in your form.";
		Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");
		
	}
	
	@Test
	public void TC04() {
		
		String actualMsg, expectedMsg, expectedMsg2;
		Account account = new Account(Constant.USERNAME, Constant.BLANK_PASSWORD, null);
		LoginPage loginPage = new LoginPage();
	
		System.out.println("TC04 - System shows message when user enters wrong password many times");
		
		System.out.println("1. Navigate to QA Railway Website");	
		WindowManager.open(Constant.RAILWAY_URL);
		
		System.out.println("2. Click on Login tab");
		loginPage.clickTab(MenuItem.LOGIN);
		
		System.out.println("3. Enter valid information into 'Username' textbox except 'Password' textbox.");
		System.out.println("4. Click on 'Login' button");
		System.out.println("V.P: 'Invalid username or password. Please try again' is shown");
		System.out.println("5. Repeat step 3 and 4 three more times.");
		System.out.println("V.P: User can't login and message 'You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.' appears.");
		
		for (int i = 1; i <= Constant.TIMES_LOGIN; i++) {
			loginPage.login(account);
			actualMsg = loginPage.getErrorMsg();
			if (i < Constant.TIMES_LOGIN) {
				expectedMsg = "Invalid username or password. Please try again";
				Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected message 1");
			} else {
				expectedMsg2 = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";
				Assert.assertEquals(actualMsg, expectedMsg2, "Error message is not displayed as expected message 2");
			}
		}	
	}

	@Test 
	public void TC05() {
		
		EmailService mailService = new EmailService();
		String strEmail = Utilities.generateRandomString();
		Account account = new Account("", Constant.PASSWORD, "");
		LoginPage loginPage = new LoginPage();
		
		System.out.println("TC05 - User can't login with an account hasn't been activated");
		
		System.out.println("Pre-condition: a not-active account is existing");
		String email = mailService.generateEmail(strEmail);
		
		System.out.println("1. Navigate to QA Railway Website");
		WindowManager.open(Constant.RAILWAY_URL);
		
		System.out.println("2. Click on 'Login' tab");
		loginPage = loginPage.gotoPage(MenuItem.LOGIN, LoginPage.class);
		
		System.out.println("3. Enter username and password of account hasn't been activated.");
		System.out.println("4. Click on 'Login' button");
		account.setEmail(email);
		loginPage.login(account);
		
		System.out.println("String email LOGINTEST enter::::"+email);
		System.out.println("V.P: User can't login and message 'Invalid username or password. Please try again.' appears.");
		String actualMsg = loginPage.getErrorMsg();
		String expectedMsg = "Invalid username or password. Please try again.";
		Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");
	}
}
