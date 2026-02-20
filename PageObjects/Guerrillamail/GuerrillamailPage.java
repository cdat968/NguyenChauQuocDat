package Guerrillamail;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import Common.Utilities;
import Constant.Constant;
import Constant.EmailAction;

public class GuerrillamailPage extends Utilities {
	
	public final By checkScrambleAddress = By.xpath("//span[@id='alias-box']//input[@name='alias']");
	public final By buttonClickToEdit = By.xpath("//div[@class='col2']//span[@id='inbox-id']");
	public final By txtSuccessMsg = By.xpath("//div[@class='status_alert shadow']");
	public final By txtEmailInput = By.xpath("//div[@class='col2']//input[@type='text']");
	public final By btnSaveEmail = By.xpath("//div[@class='col2']//button[@class='save button small']");
	public final By	linkToVerify = By.xpath("//div[@class='email']//div[@class='email_body']//a[@target='_blank']");
	public static By txtEmail = By.xpath("//span[@id='email-widget']");
	public final By emailDomain = By.xpath("//select[@id='gm-host-select']");
	public final String verifyEmailRegistered = "//tbody[@id='email_list']//td[@class='td3' and contains(normalize-space(),'Please confirm your account %s')]";
	public final String verifyForgotPw = "//tbody[@id='email_list']//td[@class='td3' and contains(normalize-space(),'Please reset your password %s')]"; 
	
	public String getEmailDomain() {
		
		Select select = new Select(getElement(emailDomain));
		
		return select.getFirstSelectedOption().getText().trim();
	}
	
	public Boolean isFindVerifyEmail(String dynamicXpath, String email) {
	
		return !getElements(getBy(dynamicXpath, email)).isEmpty();
		
	}
	
	public void resetInbox(String emailPrefix) {
		if (getElement(checkScrambleAddress).isSelected()) {
			getElement(checkScrambleAddress).click();
		}
	
		getElement(buttonClickToEdit).click();	
		getElement(txtEmailInput).clear();
		getElement(txtEmailInput).sendKeys(emailPrefix);
		getElement(btnSaveEmail).click();
		waitForVisibility(txtSuccessMsg);
	}
	public String createEmail(String prefix) {
		resetInbox(prefix);
		return getElement(txtEmail).getText();
	}
	
	public String waitForEmail(String email, EmailAction action) {
		String mailLocator; 
		
		switch (action) {
			case ACTIVATE_ACCOUNT:
				mailLocator = verifyEmailRegistered;
				break;
			case RESET_PASSWORD:  
				mailLocator = verifyForgotPw;
				break;
			default: 
				throw new IllegalArgumentException("Unexpected value: " + action);	
		}
		
		WaitUntilMailArrive(mailLocator, email);
		click(mailLocator, email);
		return getText(linkToVerify);
	}
	
	public Boolean isEmailPresent(String locator, String email) {
		return getElements(getBy(locator, email)).size() > 0;
	}
	
	public void WaitUntilMailArrive(String locator, String email) {
		Wait<WebDriver> wait = new FluentWait<>(Constant.WEBDRIVER)
				.withTimeout(Duration.ofSeconds(Constant.SECONDS))
				.pollingEvery(Duration.ofSeconds(Constant.SHORT_TIMEOUT))
				.ignoring(NoSuchElementException.class);
		
		wait.until(d -> {
			if (isEmailPresent(locator, email)) {
				return true;
			}
			resetInbox(email);
			return false;
		});
	}
}
