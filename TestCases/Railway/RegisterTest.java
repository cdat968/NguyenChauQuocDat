package Railway;

import org.testng.Assert;

import org.testng.annotations.Test;

import Common.EmailService;
import Constant.Constant;
import Constant.EmailAction;
import Constant.MenuItem;

public class RegisterTest extends TestBase {

	@Test
	public void TC07() {
		
		Account account = new Account(Constant.REGISTER_EMAIL, Constant.PASSWORD, Constant.PID);
		
		String actualMsg, expectedMsg;
		
		System.out.println("TC07 - User can't create account with an already in-use email");
		
		System.out.println("Pre-condition: an actived account is existing\n");
		
		String email = registerAndActiveAccount();
		
		System.out.println("1. Navigate to QA Railway Website");
		
		open(Constant.RAILWAY_URL);
		
		System.out.println("2. Click on 'Register' tab");
		
		registerPage = homePage.gotoPage(MenuItem.REGISTER, RegisterPage.class);
		
		System.out.println("3. Enter information of the created account in Pre-condition\n");
		
		System.out.println("4. Click on 'Register' ");
		
		account.setEmail(email);
		
		registerPage.register(account);
		
		System.out.println("V.P: Error message 'This email address is already in use.' displays above the form.");
		
		actualMsg = registerPage.getErrorMsg();
		
		expectedMsg = "This email address is already in use.";
		
		Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");		
	}

//	@Test
	public void TC08() {
		
		EmailService mailService = new EmailService();
		
		Account account = new Account("", "", "");
		
		String email;
		
		System.out.println("TC 08 - User can't create account while password and PID fields are empty");
		
		System.out.println("1. Navigate to QA Railway Website");
		
		open(Constant.RAILWAY_URL);
		
		saveCurrentWindow();
		
		email = mailService.generateEmail(strEmail);
	
		closeCurrentTabAndBack();
	
		System.out.println("2. Click on 'Register' tab");
		
		registerPage = homePage.gotoPage(MenuItem.REGISTER, RegisterPage.class);
	
		System.out.println("3. Enter valid email address and leave other fields empty");
		
		System.out.println("4. Click on 'Register' button");
		
		account.setEmail(email);
		
		registerPage.register(account);
		
		System.out.println("V.P: Message \"There're errors in the form. Please correct the errors and try again.\" appears above the form.\n");
		
		String actualErrorMsg = registerPage.getErrorMsg();
		
		String expectedErrorMsg = "There're errors in the form. Please correct the errors and try again.";
		
		Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message is not displayed as expected");
		
		System.out.println("V.P: Next to password fields, error message \"Invalid password length.\" displays\n");
		
		String actualErrorPassword = registerPage.getValidateErrorPw();
		
		String expectedErrorPassword = "Invalid password length";
		
		Assert.assertEquals(actualErrorPassword, expectedErrorPassword, "Error message is not displayed as expected");
		
		System.out.println("V.P: Next to PID field, error message \"Invalid ID length.\" displays");
		
		String actualErrorPid = registerPage.getValidateErrorPid();
		
		String expectedErrorPid = "Invalid ID length"; 
		
		Assert.assertEquals(actualErrorPid, expectedErrorPid, "Error message is not displayed as expected");
	}
	
//	@Test
	public void TC09() {
		
		EmailService mailService = new EmailService();
		
		Account account = new Account("", Constant.PASSWORD,Constant.PID);

		String email;
		
		System.out.println("TC09 - User create and activate account");
		
		System.out.println("1. Navigate to QA Railway Website\n");
		
		open(Constant.RAILWAY_URL);
		
		saveCurrentWindow();
		
		System.out.println("V.P: Home page is shown with guide containing href \"create an account\" to \"Register\" page");
		
		String actualTextLink = homePage.getTextLinkToRegister();
		
		String expectedTextLink = "create an account";
		
		Assert.assertEquals(actualTextLink, expectedTextLink, "HomePage did not shown with guide containing href 'create an account' to 'Register' page");
		
		System.out.println("2. Click on 'Create an account'");
		
		registerPage = homePage.gotoPage(MenuItem.REGISTER, RegisterPage.class);
		
		System.out.println("V.P: Register page is shown");
		
		Boolean actualIsDisplayed = homePage.isTabActive(MenuItem.REGISTER);
		
		Assert.assertEquals(actualIsDisplayed, true, "Register page did not shown");
		
		System.out.println("3. Enter valid information into all fields");
		
		System.out.println("4. Click on 'Register' button");
		
		email = mailService.generateEmail(strEmail);
		
		closeCurrentTabAndBack();
		
		account.setEmail(email);
		
		registerPage.register(account);
		
		saveCurrentWindow();
		
		System.out.println("V.P: \"Thank you for registering your account\" is shown");
		
		String actualMsg = registerPage.getTextContent();
		
		String expectedMsg = "Thank you for registering your account";
		
		Assert.assertEquals(actualMsg, expectedMsg, "Success message did not shown");

		System.out.println("5. Get email information (webmail address, mailbox and password) and navigate to that webmail ");
		
		System.out.println("6. Login to the mailbox");
		
		System.out.println("7. Open email with subject containing 'Please confirm your account'  and the email of the new account at step 3");
		
		System.out.println("8. Click on the activate link");
		
		mailService.waitAndHandleEmail(email, EmailAction.ACTIVATE_ACCOUNT);
		
		closeCurrentTabAndBack();
		
		System.out.println("V.P: Redirect to Railways page and message 'Registration Confirmed! You can now log in to the site' is shown");
		
		String actualSuccessRegisterMsg = registerPage.getTextRegisterSuccess();
		
		String expectedSuccessRegisterMsg = "Registration Confirmed! You can now log in to the site.";
		
		Assert.assertEquals(actualSuccessRegisterMsg, expectedSuccessRegisterMsg, "Success message did not shown");
		
		closeCurrentTabAndBack();
	}	

}
