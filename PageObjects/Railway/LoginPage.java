package Railway;

import org.openqa.selenium.By;
import Constant.MenuItem;

public class LoginPage extends GeneralPage {
	
	private final By _txtUsername = By.xpath("//input[@id='username']");
	
	private final By _txtPassword = By.xpath("//input[@id='password']");
	
	private final By _btnLogin = By.xpath("//input[@value='login']");

	private final By _lblLoginErrorMsg = By.xpath("//p[@class='message error LoginForm']");
		
	public String getErrorMsg() {
		
		return getText(_lblLoginErrorMsg);
	
	}
	
	public boolean isLoggedIn () {
		
		try {
			
			return checkTabPageDisplayed(MenuItem.LOGOUT);
		
		} catch (Exception e) {
			
			return false;
		
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T extends GeneralPage> T login(Account account) {
		
		enter(_txtUsername, account.getEmail());
		
		enter(_txtPassword, account.getPassword());
		
		click(_btnLogin);
		
		if (!this.isLoggedIn()) {
			
			return (T) this;
		
		}
		
		return (T) new HomePage();
	}
	
}
