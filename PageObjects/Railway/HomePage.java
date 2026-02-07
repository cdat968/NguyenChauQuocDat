package Railway;

import org.openqa.selenium.By;

public class HomePage extends GeneralPage {
	
	public final By _txtTitle = By.xpath("//div[@id='content']//h1[text()='Welcome to Safe Railway']");
	public final By _txtLinkToRegister = By.xpath("//div[@id='content']//a[@href='/Account/Register.cshtml']");

	public String getTxtLinkToRegister() {
		return getText(_txtLinkToRegister);
	}

	public String getTxtTitle() {
		return getText(_txtTitle); 
	}
	
}
