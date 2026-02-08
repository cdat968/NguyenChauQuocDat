package Common;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Constant.Constant;

public class Utilities {

	public static WebElement getElement(By locator) {
		return Constant.WEBDRIVER.findElement(locator);
	}

	public static List<WebElement> getElements(By locator) {
		return Constant.WEBDRIVER.findElements(locator);
	}
	
	public static By getBy(String locator, Object... params) {
		return By.xpath(String.format(locator, params));
	}
	
	public static String getText(By locator) {
		WebElement element = waitForVisibility(locator);
		scrollToElement(element);
		return element.getText();
	}

	public static String getText(String dynamicXpath, Object... params) {
		By locator = getBy(dynamicXpath, params);
		return getText(locator);
	}
	
	public static Boolean isDisplayed(By locator) {
		WebElement element = waitForVisibility(locator);
		scrollToElement(element);
		return !getElements(locator).isEmpty();
	}
	
	public static void click(String dynamicXpath, Object... value) {
		By locator = getBy(dynamicXpath, value);
		WebElement element = waitForVisibility(locator);
		scrollToElement(element);
		element.click();
	}
	
	public static void click(By locator) {
		WebElement element = waitForVisibility(locator);
		scrollToElement(element);
		element.click();
	}
	
	
	public enum OpenType{
		NAVIGATE_URL,
		CURRENT_WINDOW,
		NEW_TAB,
		NEW_WINDOW
	}
	public void open(String url, OpenType openType) {	
		switch (openType) {
			case NAVIGATE_URL:
				Constant.WEBDRIVER.navigate().to(url);
				break;
				
			case CURRENT_WINDOW:
				Constant.WEBDRIVER.get(url);
				break;
				
			case NEW_TAB:
				Constant.WEBDRIVER.switchTo().newWindow(WindowType.TAB);
				Constant.WEBDRIVER.get(url);
				break;
			case NEW_WINDOW:
				Constant.WEBDRIVER.switchTo().newWindow(WindowType.WINDOW);
				Constant.WEBDRIVER.get(url);
				break;
		}
	}
	public void open(String url) {
		open(url, OpenType.NAVIGATE_URL);
	}

	public String openNewTabAndReturnHandle(String url) {
		open(url, OpenType.NAVIGATE_URL);
		return Constant.WEBDRIVER.getWindowHandle();
	}
	
	public static void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
		js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
	}
	public void enter(By locator, String value) {
		waitForVisibility(locator).clear();
		scrollToElement(getElement(locator));
		getElement(locator).sendKeys(value);
	}
	
	public static String generateRandomString() {
		return Long.toString(System.currentTimeMillis(), 36)
		         + Integer.toString(
		                 ThreadLocalRandom.current().nextInt(1000, 9999),
		                 36
		             );
	}
	
	public static WebElement waitForVisibility(By locator) {
		return waitforVisibility(locator, Constant.SECONDS);
	}

	public static WebElement waitforVisibility(By locator, int timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(timeOutInSeconds));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		
	}
	
}



//public <T> T open(String url, Class<T> pageClass) {
//	try {
//		Constant.WEBDRIVER.navigate().to(url);
//		return pageClass.getDeclaredConstructor().newInstance();
//		
//	} catch (Exception e) {
//		
//		throw new RuntimeException(e);
//	}	
//}
