package Railway;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Constant.Constant;

public class MyTicketPage extends GeneralPage{
	//table[@class='MyTable']/tbody/tr[td[count(//tr[@class='TableSmallHeader']//th[normalize-space()='Depart Station']/preceding-sibling::th)+ 1][normalize-space()='Sài Gòn'] and td[count(//tr[@class='TableSmallHeader']/th[normalize-space()='Arrive Station']/preceding-sibling::th)+ 1][normalize-space()='Phan Thiết']]//input[@value='Cancel']
	protected final String _btnCancel = "//table[@class='MyTable']/tbody/tr[td[count(//tr[@class='TableSmallHeader']//th[normalize-space()='Depart Station']/preceding-sibling::th)+ 1][normalize-space()='%s'] and td[count(//tr[@class='TableSmallHeader']/th[normalize-space()='Arrive Station']/preceding-sibling::th)+ 1][normalize-space()='%s']]//input[@value='Cancel']";
	protected final By row = By.xpath("//table[@class='MyTable']//tbody/tr");
	protected final By btnCancel = By.xpath("//input[@value='Cancel']");
	//input[@onClick='DeleteTicket(22001);']
	protected final String _isDeleteBtn = "//input[@onClick='%s']";
	
	public WebElement waitForBookedTicket(String from, String to) {
	    WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(20));

	    return wait.until(d -> {
	        for (WebElement row : d.findElements(row)) {
	            if (row.getText().contains(from) && row.getText().contains(to)) {
	                return row;
	            }
	        }
	        return null; // continue waiting
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
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.alertIsPresent());
		
		Alert alert = Constant.WEBDRIVER.switchTo().alert();
		System.out.println("Text alert: "+alert.getText());
		alert.accept();
		return cancelTicketId;
	}
	
	protected Boolean isDeletedTicket(String ticketId) {
		return waitForAllVisible(getBy(_isDeleteBtn, ticketId)).isEmpty();
//		WebElement element = waitForVisibility(getBy(_isDeleteBtn, ticketId));
//		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));
//		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getBy(_isDeleteBtn, ticketId))).isEmpty();
//		return getElements(getBy(_isDeleteBtn, ticketId)).isEmpty();
	}

	
//	protected void cancelBooking(String from, String to) {
//		WebElement ticketElement = waitForBookedTicket(from, to);
//		ticketElement.findElement(btnCancel).click();
//		
//		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.alertIsPresent());
//		
//		Alert alert = Constant.WEBDRIVER.switchTo().alert();
//		System.out.println("Text alert: "+alert.getText());
//		alert.accept();
//		click(_btnCancel, params);
		
//		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.alertIsPresent());
//		
//		Alert alert = Constant.WEBDRIVER.switchTo().alert();
//		System.out.println("Text alert: "+alert.getText());
//		alert.accept();
//	}
	
}
