package Railway;

import org.openqa.selenium.By;

public class ForgotPasswordPage extends GeneralPage {
	private final By _linkForgotPw = By.xpath("//div[@id='content']//a[text()='Forgot Password page']");
	private final By _txtEmailForgotPw = By.xpath("//form[@method='post']//input[@id='email']");
	private final By _btnSubmitEmailFgPw = By.xpath("//form[@method='post']//input[@value='Send Instructions']");
	
	public void forgotPassword(String email) {
		click(_linkForgotPw);
		enter(_txtEmailForgotPw, email);
		getElement(_btnSubmitEmailFgPw).click();
	}
}
