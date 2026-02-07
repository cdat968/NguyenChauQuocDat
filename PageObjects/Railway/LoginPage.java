package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;
import Constant.MenuItem;

public class LoginPage extends GeneralPage {
	
	private final By _txtUsername = By.xpath("//input[@id='username']");
	private final By _txtPassword = By.xpath("//input[@id='password']");
	private final By _btnLogin = By.xpath("//input[@value='login']");
	private final By _lblLoginErrorMsg = By.xpath("//p[@class='message error LoginForm']");
	private final By _linkForgotPw = By.xpath("//div[@id='content']//a[text()='Forgot Password page']");
	private final By _txtEmailForgotPw = By.xpath("//form[@method='post']//input[@id='email']");
	private final By _btnSubmitEmailFgPw = By.xpath("//form[@method='post']//input[@value='Send Instructions']");
	
	private final By _formChangePw = By.xpath("//form[@method='post']//legend[text()='Password Change Form']");
	private final By _txtPwResetToken = By.xpath("//form[@method='post']//input[@id='resetToken']");
	private final By _txtNewPw = By.xpath("//form[@method='post']//input[@id='newPassword']");
	private final By _txtConfirmNewPw = By.xpath("//form[@method='post']//input[@id='confirmPassword']");
	private final By _btnResetPw = By.xpath("//form[@method='post']//input[@title='Reset password']");
	private final By _successMsgResetPw = By.xpath("//div[@id='content']//p[@class='message success']");
	
	
	
//	private final String _linkForgotPw = "//div[@id='content']//a[text()='%s']";
	public Boolean isDisplayFormChangePw() {
		return isDisplayed(_formChangePw);
	}
	
	public Boolean isDisplayResetToken() {
		return isDisplayed(_txtPwResetToken);
	}
	
	public String getSuccessMsg() {
		return getText(_successMsgResetPw);
	}
	
	public WebElement getBtnLogin() {
		return Constant.WEBDRIVER.findElement(_btnLogin);
	}
	
	public WebElement getLblLoginErrorMsg() {
		return Constant.WEBDRIVER.findElement(_lblLoginErrorMsg);
	}
	
	public String getErrorMsg() {
		return this.getLblLoginErrorMsg().getText();
	}
	
	public boolean isLoggedIn () {
		try {
			return checkTabPageDisplayed(MenuItem.LOGOUT, false);
		} catch (Exception e) {
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T extends GeneralPage> T login(Account account) {
		enter(_txtUsername, account.getEmail());
		enter(_txtPassword, account.getPassword());
		
		this.getBtnLogin().click();
		
		if (!this.isLoggedIn()) {
			return (T) this;
		}
		return (T) new HomePage();
	}
	
	public void forgotPassword(String email) {
		click(_linkForgotPw);
		enter(_txtEmailForgotPw, email);
		getElement(_btnSubmitEmailFgPw).click();
	}
	
	public void changePassword(String password) {
		enter(_txtNewPw, password);
		enter(_txtConfirmNewPw, password);
		click(_btnResetPw);
	}
	
	
}
