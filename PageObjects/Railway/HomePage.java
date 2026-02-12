package Railway;

import org.openqa.selenium.By;

public class HomePage extends GeneralPage {
	
	public final By txtTitle = By.xpath("//div[@id='content']//h1[text()='Welcome to Safe Railway']");
	public final By txtLinkToRegister = By.xpath("//div[@id='content']//a[@href='/Account/Register.cshtml']");

	public String getTextLinkToRegister() {
		return getText(txtLinkToRegister);
	}

	public String getTextTitle() {
		return getText(txtTitle); 
	}
	
}
