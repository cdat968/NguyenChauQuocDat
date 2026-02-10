package Common;

import Constant.Constant;
import Constant.EmailAction;
import Guerrillamail.GuerrillamailPage;

public class EmailService extends Utilities {
	
	GuerrillamailPage guerrillamailPage = new GuerrillamailPage();
	
	public String generateEmail(String prefix) {
		openNewTab(Constant.EMAIL_URL);
		return guerrillamailPage.createEmail(prefix);
	}
	
	public void waitAndHandleEmail(String email, EmailAction action) {
		
		openNewTab(Constant.EMAIL_URL);
		
		String verifyLink = guerrillamailPage.waitForEmail(email, action);
		
		System.out.println("verifyLINK: "+ verifyLink);
		
		open(verifyLink);
		
		closeCurrentTabAndBack();
	}
}
