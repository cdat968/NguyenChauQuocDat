package Railway;

import org.openqa.selenium.By;

public class ForgotPasswordPage extends GeneralPage {
	
	private final By linkForgotPw = By.xpath("//div[@id='content']//a[text()='Forgot Password page']");
	private final By txtEmailForgotPw = By.xpath("//form[@method='post']//input[@id='email']");
	private final By btnSubmitEmailFgPw = By.xpath("//form[@method='post']//input[@value='Send Instructions']");
	
	public void forgotPassword(String email) {
		click(linkForgotPw);
		enter(txtEmailForgotPw, email);
		getElement(btnSubmitEmailFgPw).click();
	}
}
