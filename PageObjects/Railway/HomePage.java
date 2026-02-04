package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;

public class HomePage extends GeneralPage {
	public final By _txtTitle = By.xpath("//div[@id='content']//h1[text()='Welcome to Safe Railway']");
	
	public HomePage open() {
		Constant.WEBDRIVER.navigate().to(Constant.RAILWAY_URL);
		return this;
	}
	public WebElement getElementTxtTitle() {
		return Constant.WEBDRIVER.findElement(_txtTitle);
	}
	public String getTxtTitle() {
		return this.getElementTxtTitle().getText(); 
	}
	
}
