package Railway;

import org.openqa.selenium.By;

public class RegisterPage extends GeneralPage{

	private final By txtEmail = By.xpath("//form[@id='RegisterForm']//input[@id='email']");
	private final By txtPassword = By.xpath("//form[@id='RegisterForm']//input[@id='password']");
	private final By txtConfirmPassword = By.xpath("//form[@id='RegisterForm']//input[@id='confirmPassword']");
	private final By txtPid = By.xpath("//form[@id='RegisterForm']//input[@id='pid']");
	private final By btnRegister = By.xpath("//form[@id='RegisterForm']//input[@title='Register']");
	private final By lblRegisterErrorMsg = By.xpath("//p[@class='message error']");
	private final By lblPasswordErrorMsg = By.xpath("//form[@id='RegisterForm']//li[@class='password']//label[@class='validation-error']");
	private final By lblPidErrorMsg = By.xpath("//form[@id='RegisterForm']//li[@class='pid-number']//label[@class='validation-error']");
	private final By txtContentAfterRegister = By.xpath("//div[@id='content']/h1[@align='center']");
	private final By txtRegisterSuccess = By.xpath("//div[@id='content']/p");

	public String getTextContent() {
		return getText(txtContentAfterRegister);
	}

	public String getTextRegisterSuccess() {
		return getText(txtRegisterSuccess);
	}
	
	public String getErrorMsg() {
		return getText(lblRegisterErrorMsg);
	}
	
	public String getValidateErrorPw() {
		return getText(lblPasswordErrorMsg);
	}
	
	public String getValidateErrorPid() {
		return getText(lblPidErrorMsg);
	}
	
	public RegisterPage register(Account account) {
		enter(txtEmail, account.getEmail());
		enter(txtPassword, account.getPassword());
		enter(txtConfirmPassword, account.getPassword());
		enter(txtPid, account.getPid());
		getElement(btnRegister).click();
		
		return this;

	}
}
