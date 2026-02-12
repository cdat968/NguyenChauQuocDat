package Common;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Constant.Constant;

public class Utilities extends WindowManager {

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
	
	public static void open(String url) {
		Constant.WEBDRIVER.navigate().to(url);
	}
	
	public static void close() {
		Constant.WEBDRIVER.close();
	}
	
	public static void openNewTab(String url) {
		Constant.WEBDRIVER.switchTo().newWindow(WindowType.TAB);
		Constant.WEBDRIVER.get(url);
	}
	
	public static void closeCurrentTabAndBack() {
		close();
		WindowManager.switchBack();
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
		return Long.toString(System.currentTimeMillis(), 36) + Integer.toString(ThreadLocalRandom.current().nextInt(1000, 9999), 36);
	}
	
	public static WebElement waitForVisibility(By locator) {
		return waitforVisibility(locator, Constant.SECONDS);
	}
	
	public static WebElement waitforVisibility(By locator, int timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(timeOutInSeconds));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public static List<WebElement> waitForAllVisible(By locator) {
		return waitForAllVisible(locator, Constant.SECONDS);
	}
	
	public static List<WebElement> waitForAllVisible(By locator, int timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(timeOutInSeconds));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	public static Alert waitAlert() {
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
}