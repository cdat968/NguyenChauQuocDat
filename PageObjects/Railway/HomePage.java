package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;

public class HomePage extends GeneralPage {
	public final By _txtTitle = By.xpath("//div[@id='content']//h1[text()='Welcome to Safe Railway']");
	public final By _txtLinkToRegister = By.xpath("//div[@id='content']//a[@href='/Account/Register.cshtml']");
	
	public String getTxtLinkToRegister() {
		waitForVisibility(_txtLinkToRegister);
		scrollToElement(getElement(_txtLinkToRegister));
		return getElement(_txtLinkToRegister).getText();
	}

	public WebElement getElementTxtTitle() {
		return Constant.WEBDRIVER.findElement(_txtTitle);
	}
	public String getTxtTitle() {
		return this.getElementTxtTitle().getText(); 
	}
	
}
