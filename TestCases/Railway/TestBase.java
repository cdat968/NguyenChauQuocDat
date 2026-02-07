package Railway;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Common.Utilities;
import Common.Utilities.OpenType;
import Constant.Constant;
import Guerrillamail.GuerrillamailPage;

public class TestBase extends Utilities {
	
	String strEmail = generateRandomString();
	
	Account account = new Account(null, null, null);
	HomePage homePage = new HomePage();
	RegisterPage registerPage = new RegisterPage();
	FAQPage faqPage = new FAQPage();
	Utilities utilities = new Utilities();
	LoginPage loginPage = new LoginPage();
	GuerrillamailPage guerrillamailPage = new GuerrillamailPage();
	
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Pre-condition");
		
		Constant.WEBDRIVER = new ChromeDriver();
		Constant.WEBDRIVER.manage().window().maximize();
		open(Constant.RAILWAY_URL, OpenType.CURRENT_WINDOW);
		
	}
	@AfterMethod
	public void afterMethod() {
		System.out.println("Post-condition");
		
		Constant.WEBDRIVER.quit();
	}
	
	public String register(String str, Boolean isActive) {
		String email = "";
//		System.out.println("String email TESTBASE enter::::" + str);
		
		guerrillamailPage.open(Constant.EMAIL_URL, OpenType.NEW_TAB);
		
		Boolean isFindEmail = guerrillamailPage.isFindVerifyEmail(guerrillamailPage._verifyEmailRegistered, str);
		
		while (!isFindEmail) {
			System.out.println("verify run again:"+ isFindEmail);
		 	email = guerrillamailPage.generateRandomEmail(str, isActive);
			break;
			
		}
		
		System.out.println("\nRegister isActive "+ isActive + " have email: " +email+ "\n");
		
		return email;

	}
}
