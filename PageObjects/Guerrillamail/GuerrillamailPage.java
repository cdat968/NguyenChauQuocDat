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
	
	public final By _txtSuccessMsg = By.xpath("//div[@class='status_alert shadow']");
	
	public final By _txtEmailInput = By.xpath("//div[@class='col2']//input[@type='text']");
	
	public final By _btnSaveEmail = By.xpath("//div[@class='col2']//button[@class='save button small']");
	
	public final By	_linkToVerify = By.xpath("//div[@class='email']//div[@class='email_body']//a[@target='_blank']");
	
	public static By _txtEmail = By.xpath("//span[@id='email-widget']");
	
	public final By _emailDomain = By.xpath("//select[@id='gm-host-select']");
	
	public final String _verifyEmailRegistered = "//tbody[@id='email_list']//td[@class='td3' and contains(normalize-space(),'Please confirm your account %s')]";
	
	public final String _verifyForgotPw = "//tbody[@id='email_list']//td[@class='td3' and contains(normalize-space(),'Please reset your password %s')]"; 
	
	public String getEmailDomain() {
		
		Select select = new Select(getElement(_emailDomain));
		
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
		
		getElement(_txtEmailInput).clear();
		
		getElement(_txtEmailInput).sendKeys(emailPrefix);
		
		getElement(_btnSaveEmail).click();
		
		waitForVisibility(_txtSuccessMsg);
	}
	public String createEmail(String prefix) {
		
		resetInbox(prefix);
	
		return getElement(_txtEmail).getText();
	}
	
	public String waitForEmail(String email, EmailAction action) {
		
		String mailLocator; 
		
		switch (action) {
			case ACTIVATE_ACCOUNT:
				mailLocator = _verifyEmailRegistered;
				break;
			
			case RESET_PASSWORD:  
				mailLocator = _verifyForgotPw;
				break;
			
			default: 
				throw new IllegalArgumentException("Unexpected value: " + action);	
		}
		
		WaitUntilMailArrive(mailLocator, email);
		
		click(mailLocator, email);
		
		return getText(_linkToVerify);
		
	}
	
	public Boolean isEmailPresent(String locator, String email) {
	
		return !getElements(getBy(locator, email)).isEmpty();
	
	}
	
	public void WaitUntilMailArrive(String locator, String email) {
		
		Wait<WebDriver> wait = new FluentWait<>(Constant.WEBDRIVER)
		
				.withTimeout(Duration.ofSeconds(Constant.SECONDS))
				
				.pollingEvery(Duration.ofSeconds(5))
				
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
