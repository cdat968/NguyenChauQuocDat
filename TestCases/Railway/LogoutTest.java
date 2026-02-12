package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.WindowManager;
import Constant.Constant;
import Constant.MenuItem;

public class LogoutTest extends TestBase {
	
	@Test
	public void TC06() {
		FAQPage faqPage = new FAQPage();
		Account account = new Account(Constant.USERNAME, Constant.PASSWORD, null);
		LoginPage loginPage = new LoginPage();
		
		System.out.println("User is redirected to Home page after logging out");
		
		System.out.println("1. Navigate to QA Railway Website");		
		WindowManager.open(Constant.RAILWAY_URL);
		
		System.out.println("2. Login with valid Email and Password");
		loginPage = loginPage.gotoPage(MenuItem.LOGIN, LoginPage.class);
		loginPage.login(account);
		
		System.out.println("3. Click on 'FAQ' tab");
		faqPage = loginPage.gotoPage(MenuItem.FAQ, FAQPage.class);
		
		System.out.println("4. Click on 'Log out' tab");
		loginPage.clickTab(MenuItem.LOGOUT);
		
		System.out.println("V.P: Home page displays. 'Log out' tab is disappeared.");
		boolean actualDisplay = LoginPage.checkTabPageDisplayed(MenuItem.LOGOUT);
		Assert.assertFalse(actualDisplay, "Log out tab is not disappeared as expected");
	
	}
}
