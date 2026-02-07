package Guerrillamail;

import java.lang.invoke.ConstantBootstraps;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import Common.Utilities;
import Constant.Constant;

public class GuerrillamailPage extends Utilities {
	
	public final By checkScrambleAddress = By.xpath("//span[@id='alias-box']//input[@name='alias']");
	public final By buttonClickToEdit = By.xpath("//div[@class='col2']//span[@id='inbox-id']");
	public final By _txtSuccessMsg = By.xpath("//div[@class='status_alert shadow']");
	public final By _txtEmailInput = By.xpath("//div[@class='col2']//input[@type='text']");
	public final By _btnSaveEmail = By.xpath("//div[@class='col2']//button[@class='save button small']");
	public final By	_linkToVerify = By.xpath("//div[@class='email']//div[@class='email_body']//a[@target='_blank']");
	public static By _txtEmail = By.xpath("//span[@id='email-widget']");
	public final By _emailDomain = By.xpath("//select[@id='gm-host-select']");
	
//	public final String _verifyEmailRegistered = "//tbody[@id='email_list']//td[@class='td3' and contains(text(), '%s')]";
//	public final String _verifyEmailRegistered = "//tbody[@id='email_list']//td[@class='td3' and contains(normalize-space(),'Please confirm your account mlc39x6f71z@sharklasers.com')]";
	public final String _verifyEmailRegistered = "//tbody[@id='email_list']//td[@class='td3' and contains(normalize-space(),'Please confirm your account %s')]";
	public final String _verifyForgotPw = "//tbody[@id='email_list']//td[@class='td3' and contains(normalize-space(),'Please reset your password %s')]"; 
	
	public String getEmailDomain() {
		Select select = new Select(getElement(_emailDomain));
		return select.getFirstSelectedOption().getText().trim();
	}
	public Boolean isFindVerifyEmail(String dynamicXpath, String email) {
		return !getElements(getBy(dynamicXpath, email)).isEmpty();
	}
	
	public String generateRandomEmail(String str, Boolean isActive, Boolean isResetPw) {
		String email, linkToVerify;
		if (getElement(checkScrambleAddress).isSelected()) {
			getElement(checkScrambleAddress).click();
		}
		
		getElement(buttonClickToEdit).click();
		
		getElement(_txtEmailInput).clear();
		getElement(_txtEmailInput).sendKeys(str);
		getElement(_btnSaveEmail).click();
		waitForVisibility(_txtSuccessMsg);
		email = getElement(_txtEmail).getText();
		
		if (isActive) {
			click(_verifyEmailRegistered, email);
			linkToVerify = getText(_linkToVerify);
			open(linkToVerify, OpenType.NAVIGATE_URL);
		} 
		else if (isResetPw) {
			click(_verifyForgotPw, email);
			linkToVerify = getText(_linkToVerify);
			open(linkToVerify, OpenType.NAVIGATE_URL);
		}
		
		return email;
	}
}

//public final By _verifyEmailRegistered = By.xpath("//tbody[@id='email_list']//td[@class='td3' and contains(text(), 'aotungchao1@sharklasers.com')]");
