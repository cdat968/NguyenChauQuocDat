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

	private final By selectDate = By.xpath("//form[@method='post']//select[@name='Date']");
	private final By selectSeatType = By.xpath("//form[@method='post']//select[@name='SeatType']");
	private final By selectTicketAmount = By.xpath("//form[@method='post']//select[@name='TicketAmount']");
	private final By btnBookTicket = By.xpath("//form[@method='post']//input[@value='Book ticket']");
	private final By SuccessMsg = By.xpath("//div[@id='content']//h1[@align='center']");
	private final By txtTitleTable = By.xpath("//table[@class='MyTable MedTable']//tr[@class='TableSmallHeader']/th");
	private final String txtInfoTicket = "//table[@class='MyTable WideTable']//tr[@class='OddRow']//td[text()='%s']";
	private final String columnTicketInfo = "//table[contains(@class, 'MyTable')]//tr/td[count(//th[normalize-space()='%s']/preceding-sibling::th)+1]";

	protected String getTextTitleTable() {
		return getText(txtTitleTable);
	}
	
	protected String getSuccessMsg() {
		return getText(SuccessMsg);
	}
	
	protected String getInforTicket(String value) {
		return getText(txtInfoTicket, value);
	}
	
	protected String getInforTicket(SeatType seatType) {
		return getText(txtInfoTicket, seatType.getText());
	}
	
	
	
	protected String getDepartureStation(int daysFromToDay) {
		LocalDate targetDate = LocalDate.now().plusDays(daysFromToDay);
		return targetDate.format(DateTimeFormatter.ofPattern("M/d/yyyy"));
	}
	
	protected LocalDate getDepartDay() {
		String daytext = new Select(waitForVisibility(selectDate))
				.getFirstSelectedOption()
				.getText();
		return LocalDate.parse(daytext, DateTimeFormatter.ofPattern("M/d/yyyy"));
	}
	
	protected String convertDateToString(int days, LocalDate departDate) {
		LocalDate targetDate = departDate.plusDays(days);
		return targetDate.format(DateTimeFormatter.ofPattern("M/d/yyyy"));
	}
	
	protected void selectDepartureDate(String selectedDepartDay) {
//		LocalDate targetDate = LocalDate.now().plusDays(daysFromToday);
//		String expectedDateTxt = targetDate.format(DateTimeFormatter.ofPattern("M/d/yyyy"));
		WebElement element = waitForVisibility(selectDate);
		scrollToElement(element);
		Select selectDate = new Select(element);
		boolean isSelected = false;
		
		for (WebElement option : selectDate.getOptions()) {
			if (option.getText().trim().equals(selectedDepartDay)) {
				option.click();
				isSelected = true;
				break;
			}
		}
		
		if (!isSelected) {
			throw new RuntimeException("System does not find date: " +selectedDepartDay);
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
		selectByText(selectSeatType, seatType.getText());
	}
	
	protected void selectTicketAmount(int number) {
		selectByText(selectTicketAmount, String.valueOf(number));
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
	
	protected Ticket getBookedTicketInfo() {
		String actualDate = getText(columnTicketInfo, "Depart Date");
		String actualDepartStation = getText(columnTicketInfo, "Depart Station");
		String actualArriveStation = getText(columnTicketInfo, "Arrive Station");
		SeatType actualSeatType = SeatType.fromText(getText(columnTicketInfo, "Seat Type"));
		int actualAmountTicket = Integer.parseInt(getText(columnTicketInfo, "Amount"));
		
		return new Ticket(actualDate, actualDepartStation, actualArriveStation, actualSeatType, actualAmountTicket);
	}
	
	protected BookTicketPage bookTicket(Ticket ticket) {
		selectDepartureDate(ticket.getDepartureDate());
		selectDepartureLocation(LocationType.DEPARTURE, ticket.getDepartureFrom());
		selectArrivalLocation(LocationType.ARRIVE, ticket.getArrivalAt());
		selectSeatType(ticket.getSeatType());
		selectTicketAmount(ticket.getTicketAmount());
		click(btnBookTicket);
		
		return this;
	}
	
	protected void assertTicket(Ticket actualTicket, Ticket expectedTicket) {
		 Assert.assertEquals(actualTicket.getDepartureDate(), expectedTicket.getDepartureDate(),
		            "Departure date mismatch");

		 Assert.assertEquals(actualTicket.getDepartureFrom(), expectedTicket.getDepartureFrom(),
		            "Departure station mismatch");

		 Assert.assertEquals(actualTicket.getArrivalAt(), expectedTicket.getArrivalAt(),
		            "Arrival station mismatch");

		 Assert.assertEquals(actualTicket.getSeatType(), expectedTicket.getSeatType(),
		            "Seat type mismatch");

		 Assert.assertEquals(actualTicket.getTicketAmount(), expectedTicket.getTicketAmount(),
		            "Ticket amount mismatch");
	}
	
}