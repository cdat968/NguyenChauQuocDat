package Railway;

import org.openqa.selenium.By;

public class RegisterPage extends GeneralPage{

	private final By _txtEmail = By.xpath("//form[@id='RegisterForm']//input[@id='email']");
	
	private final By _txtPassword = By.xpath("//form[@id='RegisterForm']//input[@id='password']");
	
	private final By _txtConfirmPassword = By.xpath("//form[@id='RegisterForm']//input[@id='confirmPassword']");
	
	private final By _txtPid = By.xpath("//form[@id='RegisterForm']//input[@id='pid']");
	
	private final By _btnRegister = By.xpath("//form[@id='RegisterForm']//input[@title='Register']");
	
	private final By _lblRegisterErrorMsg = By.xpath("//p[@class='message error']");
	
	private final By _lblPasswordErrorMsg = By.xpath("//form[@id='RegisterForm']//li[@class='password']//label[@class='validation-error']");
	
	private final By _lblPidErrorMsg = By.xpath("//form[@id='RegisterForm']//li[@class='pid-number']//label[@class='validation-error']");
	
	private final By _txtContentAfterRegister = By.xpath("//div[@id='content']/h1[@align='center']");
	
	private final By _txtRegisterSuccess = By.xpath("//div[@id='content']/p");

	public String getTextContent() {
		
		return getText(_txtContentAfterRegister);
	}

	public String getTextRegisterSuccess() {
		
		return getText(_txtRegisterSuccess);
	}
	
	public String getErrorMsg() {
		
		return getText(_lblRegisterErrorMsg);
	}
	
	public String getValidateErrorPw() {
		
		return getText(_lblPasswordErrorMsg);
	}
	
	public String getValidateErrorPid() {
		
		return getText(_lblPidErrorMsg);
	}
	
	public RegisterPage register(Account account) {
		
		enter(_txtEmail, account.getEmail());
		
		enter(_txtPassword, account.getPassword());
		
		enter(_txtConfirmPassword, account.getPassword());
		
		enter(_txtPid, account.getPid());
		
		getElement(_btnRegister).click();
		
		return this;

	}
}
