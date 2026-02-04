package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;

public class RegisterPage extends Utilities{
	private final By _txtEmail = By.xpath("//form[@id='RegisterForm']//input[@id='email']");
	private final By _txtPassword = By.xpath("//form[@id='RegisterForm']//input[@id='password']");
	private final By _txtConfirmPassword = By.xpath("//form[@id='RegisterForm']//input[@id='confirmPassword']");
	private final By _txtPid = By.xpath("//form[@id='RegisterForm']//input[@id='pid']");
	private final By _btnRegister = By.xpath("//form[@id='RegisterForm']//input[@title='Register']");
	private final By _lblRegisterErrorMsg = By.xpath("//p[@class='message error']");
	private final By _lblPasswordErrorMsg = By.xpath("//form[@id='RegisterForm']//li[@class='password']//label[@class='validation-error']");
	private final By _lblPidErrorMsg = By.xpath("//form[@id='RegisterForm']//li[@class='pid-number']//label[@class='validation-error']");
	
	
	
	public String getErrorMsg() {
		return this.getElement(_lblRegisterErrorMsg).getText();
	}
	
	public String getValidateErrorPw() {
		return this.getElement(_lblPasswordErrorMsg).getText();
	}
	
	public String getValidateErrorPid() {
		return this.getElement(_lblPidErrorMsg).getText();
	}
	
	public HomePage register(String email, String password, String confirmPassword, String pid) {
		this.getElement(_txtEmail).sendKeys(email);
		this.getElement(_txtPassword).sendKeys(password);
		this.getElement(_txtConfirmPassword).sendKeys(confirmPassword);
		this.getElement(_txtPid).sendKeys(pid);
		this.getElement(_btnRegister).click();
		
		return new HomePage();
//		this.getTxtEmail().sendKeys(email);
//		this.getTxtPassword().sendKeys(password);
//		this.getTxtConfirmPassword().sendKeys(confirmPassword);
//		this.getTxtPid().sendKeys(pid);
//		
//		this.getBtnRegister().click();
//		
//		return new HomePage();
	}
}

//public WebElement getTxtEmail() {
//	return Constant.WEBDRIVER.findElement(_txtEmail);
//}
//
//public WebElement getTxtPassword() {
//	return Constant.WEBDRIVER.findElement(_txtPassword);
//}
//
//public WebElement getTxtConfirmPassword() {
//	return Constant.WEBDRIVER.findElement(_txtConfirmPassword);
//}
//
//public WebElement getTxtPid() {
//	return Constant.WEBDRIVER.findElement(_txtPid);
//}
//
//public WebElement getBtnRegister() {
//	return Constant.WEBDRIVER.findElement(_btnRegister);
//}
//
//public WebElement getLblRegisterErrorMsg() {
//	return Constant.WEBDRIVER.findElement(_lblRegisterErrorMsg);
//}
