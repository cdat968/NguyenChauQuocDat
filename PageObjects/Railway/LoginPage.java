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
	
	public WebElement getTxtUsername() {
		return Constant.WEBDRIVER.findElement(_txtUsername);
	}

	public WebElement getTxtPassword() {
		return Constant.WEBDRIVER.findElement(_txtPassword);
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
	
}
