package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;
import Constant.MenuItem;

public class GeneralPage extends Utilities {
	private final By lblWelcomemessage = By.xpath("//div[@class='account']/strong");

	public static By TabByText(MenuItem menu, Boolean isActive) {		
		String xpath = "//div[@id='menu']//span[normalize-space(text())='" + menu.getText() + "']";
		
		if (isActive) {
			xpath += "/ancestor-or-self::li[@class='selected']";
		}
		return By.xpath(xpath);
//		return By.xpath("//div[@id='menu']//span[normalize-space(text())='" + menu.getText() + "']");
	}
	public static By ActiveTabByText(MenuItem menu) {
		return By.xpath("//div[@id='menu']//span[normalize-space(text())='" + menu.getText() + "']/ancestor-or-self::li[@class='selected']");

	}
	
	public void gotoTab(MenuItem menuItem) {
		Utilities.getElement(TabByText(menuItem, false)).click();
	}
	
	public static Boolean checkTabPageDisplayed(MenuItem menu, Boolean isActive) {
		return Utilities.getElements(TabByText(menu, isActive)).isEmpty();
	}
	
	
	
//	@SuppressWarnings("unchecked")
//	public <T extends GeneralPage> T gotoPage(MenuItem menu) {
//			gotoTab(menu);
//			
//			switch (menu) {
//			
//			case HOME: 
//				return (T) new HomePage();
//			
//			case FAQ: 
//				return (T) new FAQPage();
//				
//			case LOGIN:
//				return (T) new LoginPage();
//				
//			case REGISTER:
//				return (T) new RegisterPage();
//				
//			default:
//				throw new IllegalArgumentException("Unexpected value: " + menu);
//			}
//	}
		
	
	
	
//	public static Boolean isActiveTab(String tabName, Boolean isSelected) {
//		return !Constant.WEBDRIVER.findElements(TabByText(tabName, isSelected)).isEmpty();
//	}

	protected WebElement getLblWelcomeMessage() {
		return Constant.WEBDRIVER.findElement(lblWelcomemessage);
	}
	
	public String getWelcomeMessage() {
		return this.getLblWelcomeMessage().getText();
	}
	
	public <T> T gotoPage(MenuItem menuItem, Class<T> pageClass) {
		Utilities.getElement(TabByText(menuItem, false)).click();
		
		try {
			return pageClass.getDeclaredConstructor().newInstance();
			
		} catch (Exception e) {
			
			throw new RuntimeException(e);
		}
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
