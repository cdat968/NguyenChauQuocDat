package Railway;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Constant.Constant;

public class MyTicketPage extends GeneralPage{
	
	protected final By row = By.xpath("//table[@class='MyTable']//tbody/tr");
	protected final By btnCancel = By.xpath("//input[@value='Cancel']");
	protected final String isDeleteBtn = "//input[@onClick='%s']";
	
	public WebElement waitForBookedTicket(String from, String to) {
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(20));

	    return wait.until(d -> {
	        for (WebElement row : d.findElements(row)) {
	            if (row.getText().contains(from) && row.getText().contains(to)) {
	                return row;
	            }
	        }
	        return null; 
	    });
	}
	
	public String getTicketIdFromCancelButton(WebElement button) {
		String onClick = button.getAttribute("onClick");
		return onClick.replace("\\D+", "");
	}
	
	protected String cancelBooking(String from, String to) {
		WebElement ticketElement = waitForBookedTicket(from, to);
		WebElement cancelBtn = ticketElement.findElement(btnCancel);
		String cancelTicketId = getTicketIdFromCancelButton(cancelBtn);
		cancelBtn.click();
		waitAlert();
		Alert alert = Constant.WEBDRIVER.switchTo().alert();
		alert.accept();
		
		return cancelTicketId;
	}
	
	protected Boolean isTicketPresent(String ticketId) {
		return waitForAllVisible(getBy(isDeleteBtn, ticketId)).size() > 0;
	}	
}
