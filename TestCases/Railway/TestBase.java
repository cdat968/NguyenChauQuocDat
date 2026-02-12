package Railway;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import Common.EmailService;
import Common.Utilities;
import Common.WindowManager;
import Constant.Constant;
import Constant.EmailAction;
import Constant.MenuItem;

public class TestBase extends Utilities {
	
	String strEmail = generateRandomString();
	HomePage homePage = new HomePage();
	
	@Parameters("browser")
	@BeforeMethod
	public void beforeMethod(@Optional("firefox") String browser) {
		
		String runBrowser = System.getProperty("browser", browser);
		
		if("chrome".equalsIgnoreCase(runBrowser)) {
			Constant.WEBDRIVER = new ChromeDriver();
		}
		else if ("firefox".equalsIgnoreCase(runBrowser)) {
			Constant.WEBDRIVER = new FirefoxDriver();
		}
		else {
			throw new RuntimeException("Unsupported browser: "+ runBrowser);
		}
		System.out.println("Pre-condition");
		
//		Constant.WEBDRIVER = new ChromeDriver();
		
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
		RegisterPage registerPage = new RegisterPage();
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
		System.out.println("email regisrerd:"+email);
		
		return email;
	}
}