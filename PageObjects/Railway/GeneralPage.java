package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;
import Constant.MenuItem;

public class GeneralPage extends Utilities {
	
	private final By lblWelcomemessage = By.xpath("//div[@class='account']/strong");
		
	private final String tabActive = "//div[@id='menu']//span[normalize-space()='%s']/ancestor-or-self::li[@class='selected']";
	
	public static final String tab = "//div[@id='menu']//span[normalize-space()='%s']";
	
	
	public void clickTab(MenuItem menuItem) {
		click(tab, menuItem.getText());
	}
	
	public Boolean isTabActive(MenuItem menu) {
		return !getElements(getBy(tabActive, menu.getText())).isEmpty();
	}
	
	public Boolean isExistTab(MenuItem menu) {
		return !getElements(getBy(tab, menu.getText())).isEmpty();
	}
	
	public static Boolean checkTabPageDisplayed(MenuItem menu) {
		return getElements(getBy(tab, menu.getText())).size() > 0;
	}
	
	public String getWelcomeMessage() {
		return getText(lblWelcomemessage);
	}
	
	public <T> T gotoPage(MenuItem menuItem, Class<T> pageClass) {
		
		clickTab(menuItem);
		
		try {
			
			return pageClass.getDeclaredConstructor().newInstance();
			
		} catch (Exception e) {
			
			throw new RuntimeException(e);
		}
	}

}

