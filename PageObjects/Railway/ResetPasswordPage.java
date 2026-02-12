package Railway;

import org.openqa.selenium.By;

public class ResetPasswordPage extends GeneralPage{
	private final By txtNewPw = By.xpath("//form[@method='post']//input[@id='newPassword']");
	private final By txtConfirmNewPw = By.xpath("//form[@method='post']//input[@id='confirmPassword']");
	private final By btnResetPw = By.xpath("//form[@method='post']//input[@title='Reset password']");
	private final By formChangePw = By.xpath("//form[@method='post']//legend[text()='Password Change Form']");
	private final By txtPwResetToken = By.xpath("//form[@method='post']//input[@id='resetToken']");
	private final By successMsgResetPw = By.xpath("//div[@id='content']//p[@class='message success']");
	private final By errorMsgResetPw = By.xpath("//div[@id='content']//p[@class='message error']");
	private final By errorMsgConfirmPw = By.xpath("//div[@id='content']//label[@class='validation-error']");


	public void resetPassword(String password, String confirmPassword) {
		enter(txtNewPw, password);
		enter(txtConfirmNewPw, confirmPassword);
		click(btnResetPw);
	}

	public Boolean isDisplayFormChangePw() {
		return isDisplayed(formChangePw);
	}
	
	public Boolean isDisplayResetToken() {
		return isDisplayed(txtPwResetToken);
	}
	
	public String getSuccessMsg() {
		return getText(successMsgResetPw);
	}
	
	public String getErrorMsg() {
		return getText(errorMsgResetPw);
	}
	
	public String getErrorConfirmPw() {
		return getText(errorMsgConfirmPw);
	}
}
