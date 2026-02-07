package Railway;

import org.testng.Assert;

import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Constant;
import Constant.MenuItem;
public class RegisterTest extends TestBase {

//	@Test
	public void TC07() {
//		Account account = new Account(Constant.REGISTER_EMAIL, Constant.PASSWORD, Constant.PID);
		String actualMsg, expectedMsg;
		
		System.out.println("TC07 - User can't create account with an already in-use email");
		
		System.out.println("Pre-condition: an actived account is existing\n"
				+ "1. Navigate to QA Railway Website");
		homePage.open(Constant.RAILWAY_URL); 
		
		System.out.println("2. Click on \"Register\" tab");
		registerPage = homePage.gotoPage(MenuItem.REGISTER, RegisterPage.class);
		
		System.out.println("3. Enter information of the created account in Pre-condition\n"
				+ "4. Click on \"Register\" button\\n");
		account = new Account(Constant.REGISTER_EMAIL, Constant.PASSWORD, Constant.PID);
		registerPage.register(new Account(Constant.REGISTER_EMAIL, Constant.PASSWORD, Constant.PID));
		
		System.out.println("V.P: Error message \\\"This email address is already in use.\\\" displays above the form.");
		
		actualMsg = registerPage.getErrorMsg();
		expectedMsg = "This email address is already in use.";
		
		Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");		
	}

//	@Test
	public void TC08() {
	
		String email, mainWindow;
		
		System.out.println("TC 08 - User can't create account while password and PID fields are empty");
		
		System.out.println("1. Navigate to QA Railway Website");
		
		mainWindow = openNewTabAndReturnHandle(Constant.RAILWAY_URL);
		
		email = register(strEmail, false, false);
	
		Constant.WEBDRIVER.close();
		
		Constant.WEBDRIVER.switchTo().window(mainWindow);
	
		System.out.println("2. Click on \"Register\" tab");
		
		registerPage = homePage.gotoPage(MenuItem.REGISTER, RegisterPage.class);
	
		System.out.println("3. Enter valid email address and leave other fields empty\n"
				+ "4. Click on \"Register\" button");
		
		registerPage.register(new Account(email, "", ""));
		
		System.out.println("V.P: Message \"There're errors in the form. Please correct the errors and try again.\" appears above the form.\n");
		
		Assert.assertEquals(registerPage.getErrorMsg(), "There're errors in the form. Please correct the errors and try again.", "Error message is not displayed as expected");
		
		System.out.println("V.P: Next to password fields, error message \"Invalid password length.\" displays\n");
		
		Assert.assertEquals(registerPage.getValidateErrorPw(), "Invalid password length", "Error message is not displayed as expected");
		
		System.out.println("V.P: Next to PID field, error message \"Invalid ID length.\" displays");
		
		Assert.assertEquals(registerPage.getValidateErrorPid(), "Invalid ID length", "Error message is not displayed as expected");
		
	}
	
//	@Test
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
		
		email = register(str, false, false);
		
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
		
		register(str, true, false);
		
		System.out.println("V.P: Redirect to Railways page and message \"Registration Confirmed! You can now log in to the site\" is shown");
		
		Assert.assertEquals(registerPage.getTxtRegisterSuccess(), "Registration Confirmed! You can now log in to the site.", "Success message did not shown");
	}
	
//	@Test
	public void TC10() {
		System.out.println("TC10 - Reset password shows error if the new password is same as current");
		System.out.println("Pre-condition: an actived account is existing");
		String mainWindow, email;
		
		mainWindow = openNewTabAndReturnHandle(Constant.RAILWAY_URL);
		email = register(strEmail, false, false);
		Constant.WEBDRIVER.close();
		Constant.WEBDRIVER.switchTo().window(mainWindow);
		
		registerPage = homePage.gotoPage(MenuItem.REGISTER, RegisterPage.class);
		registerPage.register(new Account(email, Constant.PASSWORD, Constant.PID));
		
		register(email, true, false);
		Constant.WEBDRIVER.close();
		Constant.WEBDRIVER.switchTo().window(mainWindow);
		
		System.out.println("1. Navigate to QA Railway Login page");
		open(Constant.RAILWAY_URL);
		
		System.out.println("2. Click on \"Forgot Password page\" link\n"
				+ "3. Enter the email address of the activated account\n"
				+ "4. Click on \"Send Instructions\" button");
		loginPage = homePage.gotoPage(MenuItem.LOGIN, LoginPage.class);
		forgotPasswordPage.forgotPassword(email);
		
		System.out.println("5. Login to the mailbox (the same mailbox when creating account)\n"
				+ "6. Open email with subject contaning \"Please reset your password\" and the email of the account at step 3\n"
				+ "7. Click on reset link");
		register(email, false, true);
		
		System.out.println("V.P: Redirect to Railways page and Form \"Password Change Form\" is shown with the reset password token");
		Assert.assertEquals(resetPasswordPage.isDisplayFormChangePw(), true, "Password Change Form is not displayed as expected");
		Assert.assertEquals(resetPasswordPage.isDisplayResetToken(), true, "The reset password token is not displayed as expected");
		
		System.out.println("8. Input same password into 2 fields 'new password' and 'confirm password'\n"
				+ "9. Click Reset Password");
		resetPasswordPage.resetPassword(Constant.PASSWORD, Constant.PASSWORD);
		
		System.out.println("V.P: Message \"The new password cannot be the same with the current password\" is shown");
		Assert.assertEquals(resetPasswordPage.getSuccessMsg(), "The new password cannot be the same with the current password", "Message is not displayed as expected");
		
	}
	
	@Test
	public void TC11() {
		System.out.println("TC11 - Reset password shows error if the new password and confirm password doesn't match");
		System.out.println("Pre-condition: an actived account is existing");
		String mainWindow, email;
		
		mainWindow = openNewTabAndReturnHandle(Constant.RAILWAY_URL);
		email = register(strEmail, false, false);
		Constant.WEBDRIVER.close();
		Constant.WEBDRIVER.switchTo().window(mainWindow);
		
		registerPage = homePage.gotoPage(MenuItem.REGISTER, RegisterPage.class);
		registerPage.register(new Account(email, Constant.PASSWORD, Constant.PID));
		
		register(email, true, false);
		Constant.WEBDRIVER.close();
		Constant.WEBDRIVER.switchTo().window(mainWindow);
		
		System.out.println("1. Navigate to QA Railway Login page");
		open(Constant.RAILWAY_URL);
		
		System.out.println("2. Click on \"Forgot Password page\" link\n"
				+ "3. Enter the email address of the activated account\n"
				+ "4. Click on \"Send Instructions\" button");
		loginPage = homePage.gotoPage(MenuItem.LOGIN, LoginPage.class);
		forgotPasswordPage.forgotPassword(email);
		
		System.out.println("5. Login to the mailbox (the same mailbox when creating account)\n"
				+ "6. Open email with subject contaning \"Please reset your password\" and the email of the account at step 3\n"
				+ "7. Click on reset link");
		register(email, false, true);
		
		System.out.println("V.P: Redirect to Railways page and Form \"Password Change Form\" is shown with the reset password token");
		Assert.assertEquals(resetPasswordPage.isDisplayFormChangePw(), true, "Password Change Form is not displayed as expected");
		Assert.assertEquals(resetPasswordPage.isDisplayResetToken(), true, "The reset password token is not displayed as expected");
		
		System.out.println("8. Input same password into 2 fields 'new password' and 'confirm password'\n"
				+ "9. Click Reset Password");
		resetPasswordPage.resetPassword(Constant.PASSWORD, Constant.WRONG_CONFIRM_PW);
		
		System.out.println("V.P: Error message \"Could not reset password. Please correct the errors and try again.\" displays above the form.\n"
				+ "\n"
				+ "Error message \"The password confirmation did not match the new password.\" displays next to the confirm password field.");
		
		Assert.assertEquals(resetPasswordPage.getErrorMsg(), "Could not reset password. Please correct the errors and try again.", "Error message FORM RESET PASSWORD is not displayed as expected");
		Assert.assertEquals(resetPasswordPage.getErrorConfirmPw(), "The password confirmation did not match the new password.", "Error message of password confirmation is not displayed as expected");
		
	}

	
}
