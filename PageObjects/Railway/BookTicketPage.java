package Railway;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import Constant.Constant;
import Constant.LocationType;
import Constant.SeatType;

public class BookTicketPage extends GeneralPage {
	private final By _selectDate = By.xpath("//form[@method='post']//select[@name='Date']");
	private final By _selectSeatType = By.xpath("//form[@method='post']//select[@name='SeatType']");
	private final By _selectTicketAmount = By.xpath("//form[@method='post']//select[@name='TicketAmount']");
	private final By _btnBookTicket = By.xpath("//form[@method='post']//input[@value='Book ticket']");
	private final By _SuccessMsg = By.xpath("//div[@id='content']//h1[@align='center']");
	//table[@class='MyTable WideTable']//tr[@class='OddRow']//td[text()='Nha Trang']
	private final String _txtInfoTicket = "//table[@class='MyTable WideTable']//tr[@class='OddRow']//td[text()='%s']";
	
	private final By _txtTitleTable = By.xpath("//table[@class='MyTable MedTable']//tr[@class='TableSmallHeader']/th");
	//li[text()='Đà Nẵng to Sài Gòn']/ancestor::tr//a[@class='BoxLink']

	protected String getTextTitleTable() {
		return getText(_txtTitleTable);
	}
	
	protected String getSuccessMsg() {
		return getText(_SuccessMsg);
	}
	
	protected String getInforTicket(String value) {
		return getText(_txtInfoTicket, value);
	}
	
	protected String getInforTicket(SeatType seatType) {
		return getText(_txtInfoTicket, seatType.getText());
	}
	
	protected String getDepartureStation(int daysFromToDay) {
		LocalDate targetDate = LocalDate.now().plusDays(daysFromToDay);
		return targetDate.format(DateTimeFormatter.ofPattern("M/d/yyyy"));
	}
	
	protected void selectDepartureDate(int daysFromToday) {
		LocalDate targetDate = LocalDate.now().plusDays(daysFromToday);
		
		String expectedDateTxt = targetDate.format(DateTimeFormatter.ofPattern("M/d/yyyy"));
		
		WebElement element = waitForVisibility(_selectDate);
		
		scrollToElement(element);
		
		Select selectDate = new Select(element);
		
		boolean isSelected = false;
		
		for (WebElement option : selectDate.getOptions()) {
			
			if (option.getText().trim().equals(expectedDateTxt)) {
			
				option.click();
				
				isSelected = true;
				
				break;
			}
		}
		
		if (!isSelected) {
			
			throw new RuntimeException("System does not find date: " +expectedDateTxt);
		}
	}
	
	protected void selectByText(By locator, String text) {
		Select select = new Select(waitForVisibility(locator));
		
		boolean isFound = false;
		
		for (WebElement option : select.getOptions()) {
			if (option.getText().trim().equals(text)) {
				option.click();
				isFound = true;
				break;
			}
		}
		
		if (!isFound) {
			Assert.fail("Không tìm thấy option "+ text);
		}
	}
	
	protected void selectSeatType(SeatType seatType) {
		selectByText(_selectSeatType, seatType.getText());
	}
	
	protected void selectTicketAmount(int number) {
		selectByText(_selectTicketAmount, String.valueOf(number));
	}
	
	protected void waitForOptionsExist(By locator, String city) {
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(Constant.SECONDS));
		
		wait.until(d -> {
			Select select = new Select(waitForVisibility(locator));
			return select.getOptions().stream().anyMatch(o -> o.getText().trim().equals(city));
		});
	}
	
	protected String getTxtSelectedOption(LocationType type, String cityName) {
		By selectLocator = type.getLocator();
		waitForOptionsExist(selectLocator, cityName);
		Select select = new Select(waitForVisibility(selectLocator));
		return select.getFirstSelectedOption().getText();
	}
	
	protected void selectLocationByVisibleText(LocationType type, String cityName) {
		By selectLocator = type.getLocator();

		try {
			
			waitForOptionsExist(selectLocator, cityName);
			
		} catch (TimeoutException e) {
			 Assert.fail("System does not find location " + cityName + " in " + type);
		}
		Select select = new Select(waitForVisibility(selectLocator));
		select.selectByVisibleText(cityName);
		
//		wait.until(d -> selectLocation.getOptions().stream().anyMatch(o -> o.getText().trim().equals(cityName)));
		

//		boolean isSelected = false;
		
//		for (WebElement option : selectLocation.getOptions()) {
//			if (option.getText().trim().equals(cityName)) {
//				option.click();
//				isSelected = true;
//				break;
//			}
//		}
//		
//		if (!isSelected) {
//			Assert.fail("System does not find location "+ cityName + " in type: "+type);
//		}
	}
	
	
	
	protected void selectDepartureLocation(LocationType type, String city) {
		if (type != LocationType.DEPARTURE) {
			throw new IllegalArgumentException("DepartureCity only uses for DEPARTURE");
		}
		selectLocationByVisibleText(type, city);
	}
	
	protected void selectArrivalLocation(LocationType type, String city) {
		if (type != LocationType.ARRIVE) {
			throw new IllegalArgumentException("ArrivalCity only uses for ARRIVAL");
		}
		selectLocationByVisibleText(type, city);
	}
	
	protected BookTicketPage bookTicket(Ticket ticket) {
		selectDepartureDate(ticket.getDepartureDate());
		selectDepartureLocation(LocationType.DEPARTURE, ticket.getDepartureFrom());
		selectArrivalLocation(LocationType.ARRIVE, ticket.getArrivalAt());
		selectSeatType(ticket.getSeatType());
		selectTicketAmount(ticket.getTicketAmount());
		click(_btnBookTicket);
		
		return this;
	}
	
}


//private final By _selectDepartFrom = By.xpath("//form[@method='post']//select[@name='DepartStation']");
//private final By _selectArriveAt = By.xpath("//form[@method='post']//select[@name='ArriveStation']");
//protected void selectLocation(LocationType type, String value) {
//selectByVisibleText(type.getLocator(), value);
//}