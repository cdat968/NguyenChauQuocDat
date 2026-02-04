package Common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;

public class Utilities {
	protected WebElement getElement(By locator) {
		return Constant.WEBDRIVER.findElement(locator);
	}
	public static String generateRandomString() {
		return "user" + System.currentTimeMillis();
	}

	
}
