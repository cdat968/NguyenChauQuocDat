package Railway;

import org.testng.Assert;

import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Constant;
import Constant.MenuItem;
public class RegisterTest extends TestBase {

//	@Test
	public void TC07() {
		Account account = new Account(Constant.REGISTER_EMAIL, Constant.PASSWORD, Constant.PID);
		String actualMsg, expectedMsg;
		
		System.out.println("TC07 - User can't create account with an already in-use email");
		
		System.out.println("Pre-condition: an actived account is existing\n"
				+ "1. Navigate to QA Railway Website");
		homePage.open(Constant.RAILWAY_URL); 
		
		System.out.println("2. Click on \"Register\" tab");
		registerPage = homePage.gotoPage(MenuItem.REGISTER, RegisterPage.class);
		
		System.out.println("3. Enter information of the created account in Pre-condition\n"
				+ "4. Click on \"Register\" button\\n");
		registerPage.register(account);
		
		System.out.println("V.P: Error message \\\"This email address is already in use.\\\" displays above the form.");
		
		actualMsg = registerPage.getErrorMsg();
		expectedMsg = "This email address is already in use.";
		
		Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");		
	}

//	@Test
	public void TC08() {
		String actualMsg1, actualMsg2, actualMsg3, expectedMsg, expectedErrorPwMsg, expectedErrorPidMsg;
		String strEmail = generateRandomString();
		Account account = new Account(strEmail, "", "");
		
		System.out.println("TC 08 - User can't create account while password and PID fields are empty");
		System.out.println("1. Navigate to QA Railway Website");
		homePage.open(Constant.RAILWAY_URL);
		
		System.out.println("2. Click on \"Register\" tab");
		registerPage = homePage.gotoPage(MenuItem.REGISTER, RegisterPage.class);
	
		System.out.println("3. Enter valid email address and leave other fields empty\n"
				+ "4. Click on \"Register\" button");
		registerPage.register(account);
		
		System.out.println("V.P: Message \"There're errors in the form. Please correct the errors and try again.\" appears above the form.\n");
		
		actualMsg1 = registerPage.getErrorMsg();
		expectedMsg = "There're errors in the form. Please correct the errors and try again.";
		Assert.assertEquals(actualMsg1, expectedMsg);
		
		System.out.println("V.P: Next to password fields, error message \"Invalid password length.\" displays\n");
		actualMsg2 = registerPage.getValidateErrorPw();
		expectedErrorPwMsg = "Invalid password length";
		Assert.assertEquals(actualMsg2, expectedErrorPwMsg);
		
		System.out.println("V.P: Next to PID field, error message \"Invalid ID length.\" displays");
		actualMsg3 = registerPage.getValidateErrorPid();
		expectedErrorPidMsg = "Invalid ID length";
		
		Assert.assertEquals(actualMsg3, expectedErrorPidMsg);
		
	}
	
	@Test
	public void TC09() {
		String mainWindow, email;
		
		Boolean actualDisplay;
		
		Account account;
		
		String str = generateRandomString();		
		
		System.out.println("1. Navigate to QA Railway Website\n");
		
		mainWindow = openNewTabAndReturnHandle(Constant.RAILWAY_URL);
		
		System.out.println("V.P: Home page is shown with guide containing href \"create an account\" to \"Register\" page");
		
		Assert.assertEquals(homePage.getTxtLinkToRegister(), "create an account", "HomePage did not shown with guide containing href 'create an account' to 'Register' page");
		
		System.out.println("2. Click on \"Create an account\"\n");
		
		registerPage = homePage.gotoPage(MenuItem.REGISTER, RegisterPage.class);
		
		System.out.println("V.P: Register page is shown");
		
		actualDisplay = !RegisterPage.checkTabPageDisplayed(MenuItem.REGISTER, true);
		
		Assert.assertEquals(actualDisplay, true, "Register page did not shown");
		
		System.out.println("3. Enter valid information into all fields\n"
				+ "4. Click on \"Register\" button");
		
		email = register(str, false);
		
		Constant.WEBDRIVER.close();
		
		Constant.WEBDRIVER.switchTo().window(mainWindow);
		
		account = new Account(email, Constant.PASSWORD,Constant.PID);
		
		registerPage.register(account);
		
		System.out.println("V.P: \"Thank you for registering your account\" is shown");
		
		Assert.assertEquals(registerPage.getTxtContent(), "Thank you for registering your account", "Success message did not shown");

		System.out.println("5. Get email information (webmail address, mailbox and password) and navigate to that webmail \n"
				+ "6. Login to the mailbox\n"
				+ "7. Open email with subject containing \"Please confirm your account\"  and the email of the new account at step 3\n"
				+ "8. Click on the activate link");
		
		register(str, true);
		
		System.out.println("V.P: Redirect to Railways page and message \"Registration Confirmed! You can now log in to the site\" is shown");
		
		Assert.assertEquals(registerPage.getTxtRegisterSuccess(), "Registration Confirmed! You can now log in to the site.", "Success message did not shown");
	}
	
}
