package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;

public class GeneralPage {
	private final By TabLogin = By.xpath("//div[@id='menu']//a[@href='/Account/Login.cshtml']");
	private final By TabLogout = By.xpath("//div[@id='menu']//a[@href='/Account/Logout']");
	private final By TabFAQ = By.xpath("//a[@href='/Page/FAQ.cshtml']");
	private final By TabHome = By.xpath("//div[@id='menu']//a[@href='../']");
	private final By lblWelcomemessage = By.xpath("//div[@class='account']/strong");
	private final By TabRegister = By.xpath("//div[@id='menu']//a[@href='/Account/Register.cshtml']");
	
	protected WebElement getTabRegister() {
		return Constant.WEBDRIVER.findElement(TabRegister);
	}
		
	protected WebElement getTabLogin() {
		return Constant.WEBDRIVER.findElement(TabLogin);
	}
	
	protected WebElement getTabLogout() {
		return Constant.WEBDRIVER.findElement(TabLogout);
	}
	
	protected WebElement getLblWelcomeMessage() {
		return Constant.WEBDRIVER.findElement(lblWelcomemessage);
	}
	
	protected WebElement getTabFAQ() {
		return Constant.WEBDRIVER.findElement(TabFAQ);
	}
	protected WebElement getTabHome() {
		return Constant.WEBDRIVER.findElement(TabHome);
	}
	
	public String getWelcomeMessage() {
		return this.getLblWelcomeMessage().getText();
	}
	public void gotoHomePage() {
		this.getTabHome().click();
	}
	
	public RegisterPage gotoRegisterPage() {
		this.getTabRegister().click();
		return new RegisterPage();
	}
	
	public FAQPage gotoFAQPage() {
		this.getTabFAQ().click();
		return new FAQPage();
	}
	
	public HomePage logout() {
		this.getTabLogout().click();
		return new HomePage();
	}
	
	public LoginPage gotoLoginPage() {
		this.getTabLogin().click();
		return new LoginPage();
	}
}


//private By TabByText(String tabName) {
//	"//div[@id='menu']//span[text()='Home']"
//	return By.xpath("//div[@id='menu']//span[text()='" + tabName + "']");
//}
//private WebElement getTab(String tabName) {
//	return Constant.WEBDRIVER.findElement(TabByText(tabName));
//}
//
//public void clickTab(String tabName) {
//	this.getTab(tabName).click();
//}
//private WebElement (String tabName) {
//return Constant.WEBDRIVER.findElement(TabByText(tabName)).click();
//}

//a[@href='../']/span[text()='Home']/ancestor-or-self::li[@class='selected']
