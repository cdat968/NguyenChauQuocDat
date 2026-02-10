package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;
import Constant.MenuItem;

public class GeneralPage extends Utilities {
	private final By lblWelcomemessage = By.xpath("//div[@class='account']/strong");
	private final String tab = "//div[@id='menu']//span[normalize-space()='%s']";
	
	public static By TabByText(MenuItem menu, Boolean isActive) {		
		String xpath = "//div[@id='menu']//span[normalize-space(text())='" + menu.getText() + "']";
		
		if (isActive) {
			xpath += "/ancestor-or-self::li[@class='selected']";
		}
		return By.xpath(xpath);
	}
	public static By ActiveTabByText(MenuItem menu) {
		return By.xpath("//div[@id='menu']//span[normalize-space(text())='" + menu.getText() + "']/ancestor-or-self::li[@class='selected']");

		
	}
	
	public void clickTab(MenuItem menuItem) {
		Utilities.getElement(TabByText(menuItem, false)).click();
	}
	
	public static Boolean checkTabPageDisplayed(MenuItem menu, Boolean isActive) {
		return Utilities.getElements(TabByText(menu, isActive)).isEmpty();
	}
	

	protected WebElement getLblWelcomeMessage() {
		return Constant.WEBDRIVER.findElement(lblWelcomemessage);
	}
	
	public String getWelcomeMessage() {
		return this.getLblWelcomeMessage().getText();
	}
	
	public <T> T gotoPage(MenuItem menuItem, Class<T> pageClass) {
//		Utilities.getElement(TabByText(menuItem, false)).click();
		click(tab, menuItem.getText());
		
		try {
			return pageClass.getDeclaredConstructor().newInstance();
			
		} catch (Exception e) {
			
			throw new RuntimeException(e);
		}
	}

}


//@SuppressWarnings("unchecked")
//public <T extends GeneralPage> T gotoPage(MenuItem menu) {
//		gotoTab(menu);
//		
//		switch (menu) {
//		
//		case HOME: 
//			return (T) new HomePage();
//		
//		case FAQ: 
//			return (T) new FAQPage();
//			
//		case LOGIN:
//			return (T) new LoginPage();
//			
//		case REGISTER:
//			return (T) new RegisterPage();
//			
//		default:
//			throw new IllegalArgumentException("Unexpected value: " + menu);
//		}
//}
	
