package Railway;

import org.openqa.selenium.By; 
import Constant.MenuItem;

public class LoginPage extends GeneralPage {
	
	private final By txtUsername = By.xpath("//input[@id='username']");
	private final By txtPassword = By.xpath("//input[@id='password']");
	private final By btnLogin = By.xpath("//input[@value='login']");
	private final By lblLoginErrorMsg = By.xpath("//p[@class='message error LoginForm']");
		
	public String getErrorMsg() {
		return getText(lblLoginErrorMsg);
	
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
		enter(txtUsername, account.getEmail());
		enter(txtPassword, account.getPassword());
		click(btnLogin);
		
		if (!this.isLoggedIn()) {
			return (T) this;
		}
		
		return (T) new HomePage();
	}
	
}
