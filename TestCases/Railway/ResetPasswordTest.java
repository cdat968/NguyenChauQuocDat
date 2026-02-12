package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.EmailService;
import Constant.Constant;
import Constant.EmailAction;
import Constant.MenuItem;

public class ResetPasswordTest extends TestBase {
	
	@Test
	public void TC10() {
		
		EmailService mailService = new EmailService();
		ResetPasswordPage resetPasswordPage = new ResetPasswordPage();
		ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
		LoginPage loginPage = new LoginPage();
		
		System.out.println("TC10 - Reset password shows error if the new password is same as current");
		
		System.out.println("Pre-condition: an actived account is existing");
		String email = registerAndActiveAccount();
		
		System.out.println("1. Navigate to QA Railway Login page");
		open(Constant.RAILWAY_URL);
		
		System.out.println("2. Click on 'Forgot Password page' link");
		System.out.println("3. Enter the email address of the activated account");
		System.out.println("4. Click on 'Send Instructions' button");
		loginPage = homePage.gotoPage(MenuItem.LOGIN, LoginPage.class);
		forgotPasswordPage.forgotPassword(email);
		
		System.out.println("5. Login to the mailbox (the same mailbox when creating account)");
		System.out.println("6. Open email with subject contaning 'Please reset your password' and the email of the account at step 3");
		System.out.println("7. Click on reset link");
		mailService.waitAndHandleEmail(email, EmailAction.RESET_PASSWORD);
		
		System.out.println("V.P: Redirect to Railways page and Form 'Password Change Form' is shown with the reset password token");
		boolean actualIsDisplayedFormChangePassword = resetPasswordPage.isDisplayFormChangePw();
		
		Assert.assertTrue(actualIsDisplayedFormChangePassword, "Password Change Form is not displayed as expected");
		boolean actualIsDisplayedPasswordToken = resetPasswordPage.isDisplayResetToken();
		
		Assert.assertTrue(actualIsDisplayedPasswordToken, "The reset password token is not displayed as expected");
		
		System.out.println("8. Input same password into 2 fields 'new password' and 'confirm password'");
		System.out.println("9. Click Reset Password");
		resetPasswordPage.resetPassword(Constant.PASSWORD, Constant.PASSWORD);
		
		System.out.println("V.P: Message 'The new password cannot be the same with the current password' is shown");
		String actualErrorMsg = resetPasswordPage.getSuccessMsg();
		String expectedErrorMsg = "The new password cannot be the same with the current password";
		Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Message is not displayed as expected");
		
	}
	
	@Test
	public void TC11() {
		
		EmailService mailService = new EmailService();
		ResetPasswordPage resetPasswordPage = new ResetPasswordPage();
		ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
		LoginPage loginPage = new LoginPage();
		
		System.out.println("TC11 - Reset password shows error if the new password and confirm password doesn't match");
		
		System.out.println("Pre-condition: an actived account is existing");
		String email = registerAndActiveAccount();
		
		System.out.println("1. Navigate to QA Railway Login page");
		open(Constant.RAILWAY_URL);
		
		System.out.println("2. Click on 'Forgot Password page' link");
		System.out.println("3. Enter the email address of the activated account");
		System.out.println("4. Click on 'Send Instructions' button");
		loginPage = homePage.gotoPage(MenuItem.LOGIN, LoginPage.class);
		forgotPasswordPage.forgotPassword(email);
		
		System.out.println("5. Login to the mailbox (the same mailbox when creating account) ");
		System.out.println("6. Open email with subject contaning 'Please reset your password' and the email of the account at step 3");
		System.out.println("7. Click on reset link");
		mailService.waitAndHandleEmail(email, EmailAction.RESET_PASSWORD);
		
		System.out.println("V.P: Redirect to Railways page and Form \"Password Change Form\" is shown with the reset password token");
		boolean actualIsDisplayedForm = resetPasswordPage.isDisplayFormChangePw();
		Assert.assertTrue(actualIsDisplayedForm, "Password Change Form is not displayed as expected");
		boolean actualIsDisplayedPasswordToken = resetPasswordPage.isDisplayResetToken();
		Assert.assertTrue(actualIsDisplayedPasswordToken, "The reset password token is not displayed as expected");
		
		System.out.println("8. Input same password into 2 fields 'new password' and 'confirm password'");
		System.out.println("9. Click Reset Password");
		resetPasswordPage.resetPassword(Constant.PASSWORD, Constant.WRONG_CONFIRM_PW);
		
		System.out.println("V.P: Error message 'Could not reset password. Please correct the errors and try again.' displays above the form.");
		System.out.println("V.P: Error message 'The password confirmation did not match the new password.' displays next to the confirm password field.");
		String actualErrorMsg = resetPasswordPage.getErrorMsg();
		String expectedErrorMsg = "Could not reset password. Please correct the errors and try again.";
		Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message FORM RESET PASSWORD is not displayed as expected");
		
		String actualErrorPasswordMsg = resetPasswordPage.getErrorConfirmPw();
		String expectedErrorPasswordMsg = "The password confirmation did not match the new password.";
		Assert.assertEquals(actualErrorPasswordMsg, expectedErrorPasswordMsg, "Error message of password confirmation is not displayed as expected");
		
	}
}
