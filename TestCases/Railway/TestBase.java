package Railway;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Common.EmailService;
import Common.Utilities;
import Common.WindowManager;
import Constant.Constant;
import Constant.EmailAction;
import Constant.MenuItem;

public class TestBase extends Utilities {
	
	String strEmail = generateRandomString();

	HomePage homePage = new HomePage();
	
	RegisterPage registerPage = new RegisterPage();
	
	LoginPage loginPage = new LoginPage();
	
	@BeforeMethod
	public void beforeMethod() {
		
		System.out.println("Pre-condition");
		
		Constant.WEBDRIVER = new ChromeDriver();
		
		Constant.WEBDRIVER.manage().window().maximize();
		
		open(Constant.RAILWAY_URL);
		
	}
	
	@AfterMethod
	public void afterMethod() {
		
		System.out.println("Post-condition");
		
		Constant.WEBDRIVER.quit();
		
	}
	
	public String registerAndActiveAccount() {
		
		Account account = new Account("", Constant.PASSWORD, Constant.PID);
		
		EmailService mailService = new EmailService();
		
		WindowManager.saveCurrentWindow();
		
		String email = mailService.generateEmail(strEmail);
		
		closeCurrentTabAndBack();
		
		registerPage = homePage.gotoPage(MenuItem.REGISTER, RegisterPage.class);
		
		account.setEmail(email);
		
		registerPage.register(account);
		
		WindowManager.saveCurrentWindow();
		
		mailService.waitAndHandleEmail(email, EmailAction.ACTIVATE_ACCOUNT);
		
		closeCurrentTabAndBack();
		
		return email;
	}
}