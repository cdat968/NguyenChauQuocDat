package Railway;

import org.openqa.selenium.By;

public class ResetPasswordPage extends GeneralPage{
	private final By _txtNewPw = By.xpath("//form[@method='post']//input[@id='newPassword']");
	private final By _txtConfirmNewPw = By.xpath("//form[@method='post']//input[@id='confirmPassword']");
	private final By _btnResetPw = By.xpath("//form[@method='post']//input[@title='Reset password']");
	private final By _formChangePw = By.xpath("//form[@method='post']//legend[text()='Password Change Form']");
	private final By _txtPwResetToken = By.xpath("//form[@method='post']//input[@id='resetToken']");
	private final By _successMsgResetPw = By.xpath("//div[@id='content']//p[@class='message success']");
	private final By _errorMsgResetPw = By.xpath("//div[@id='content']//p[@class='message error']");
	private final By _errorMsgConfirmPw = By.xpath("//div[@id='content']//label[@class='validation-error']");


	public void resetPassword(String password, String confirmPassword) {
		enter(_txtNewPw, password);
		enter(_txtConfirmNewPw, confirmPassword);
		click(_btnResetPw);
	}

	public Boolean isDisplayFormChangePw() {
		return isDisplayed(_formChangePw);
	}
	
	public Boolean isDisplayResetToken() {
		return isDisplayed(_txtPwResetToken);
	}
	
	public String getSuccessMsg() {
		return getText(_successMsgResetPw);
	}
	
	public String getErrorMsg() {
		return getText(_errorMsgResetPw);
	}
	
	public String getErrorConfirmPw() {
		return getText(_errorMsgConfirmPw);
	}
}
