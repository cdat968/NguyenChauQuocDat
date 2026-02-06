package Guerrillamail;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Common.Utilities;
import Constant.Constant;

public class GuerrillamailPage extends Utilities {
	public final By checkScrambleAddress = By.xpath("//span[@id='alias-box']//input[@name='alias']");
	public final By buttonClickToEdit = By.xpath("//div[@class='col2']//span[@id='inbox-id']");
	public final By _txtSuccessMsg = By.xpath("//div[@class='status_alert shadow']");
	public final By _txtEmailInput = By.xpath("//div[@class='col2']//input[@type='text']");
	public final By _btnSaveEmail = By.xpath("//div[@class='col2']//button[@class='save button small']");
//	public final By _verifyEmailRegistered = By.xpath("//tbody[@id='email_list']//td[@class='td3' and contains(text(), 'aotungchao1@sharklasers.com')]");
	public final String _verifyEmailRegistered = "//tbody[@id='email_list']//td[@class='td3' and contains(text(), '%s')]";
	public final By	_linkToVerifyEmail = By.xpath("//div[@class='email']//div[@class='email_body']//a[@target='_blank']");
	public static By _txtEmail = By.xpath("//span[@id='email-widget']");
	public final By _emailDomain = By.xpath("//select[@id='gm-host-select']");
	
	public GuerrillamailPage open() {
		Constant.WEBDRIVER.navigate().to(Constant.EMAIL_URL);
		return this;
	}
	
	public String getEmailDomain() {
		Select select = new Select(getElement(_emailDomain));
		return select.getFirstSelectedOption().getText().trim();
	}
	
	public GuerrillamailPage generateRandomEmail(String str, Boolean isActive) {
		
		if (Utilities.getElement(checkScrambleAddress).isSelected()) {
			Utilities.getElement(checkScrambleAddress).click();
		}
		
		Utilities.getElement(buttonClickToEdit).click();
		
		Utilities.getElement(_txtEmailInput).clear();
		Utilities.getElement(_txtEmailInput).sendKeys(str);
		Utilities.getElement(_btnSaveEmail).click();
		waitForVisibility(_txtSuccessMsg);
		Utilities.getElement(_txtEmail).getText();
		
		if (isActive) {
			waitForVisibility(By.xpath(String.format(_verifyEmailRegistered, str)));
		}
		
		return this;
	}
	
	
	
}
