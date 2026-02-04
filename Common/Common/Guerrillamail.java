package Common;

import org.openqa.selenium.By;

import Constant.Constant;

public class Guerrillamail extends Utilities {
	public final By checkScrambleAddress = By.xpath("//span[@id='alias-box']//input[@name='alias']");
	public final By buttonClickToEdit = By.xpath("//div[@class='col2']//span[@id='inbox-id']");
	public final By _txtEmailInput = By.xpath("//div[@class='col2']//input[@type='text']");
	public final By _btnSaveEmail = By.xpath("//div[@class='col2']//button[@class='save button small']");
	public final By _verifyEmailRegistered = By.xpath("//tbody[@id='email_list']//td[@class='td3' and contains(text(), 'aotungchao1@sharklasers.com')]");
	public final By	_linkToVerifyEmail = By.xpath("//div[@class='email']//div[@class='email_body']//a[@target='_blank']");
	
	
	public Guerrillamail open() {
		Constant.WEBDRIVER.navigate().to(Constant.EMAIL_URL);
		return this;
	}
	public Boolean isSelected() {
		return this.getElement(checkScrambleAddress).isSelected();
	}
	public void clickButton() {
		this.getElement(buttonClickToEdit).click();
	}
	
	
}
