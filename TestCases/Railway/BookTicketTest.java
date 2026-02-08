package Railway;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import Constant.ArrivalCity;
import Constant.Constant;
import Constant.DepartureCity;
import Constant.LocationType;
import Constant.MenuItem;
import Constant.SeatType;

public class BookTicketTest extends TestBase {
//	@Test
	public void TC12() {
		
		Ticket ticket = new Ticket(2, Constant.NHA_TRANG, Constant.HUE, SeatType.SOFT_BED_AC, 1);
		System.out.println("TC12 - User can book 1 ticket at a time");
		System.out.println("Pre-condition: an actived account is existing");
		String mainWindow, email;
		
		mainWindow = openNewTabAndReturnHandle(Constant.RAILWAY_URL);
		email = register(strEmail, false, false);
		Constant.WEBDRIVER.close();
		Constant.WEBDRIVER.switchTo().window(mainWindow);
		
		registerPage = homePage.gotoPage(MenuItem.REGISTER, RegisterPage.class);
		registerPage.register(new Account(email, Constant.PASSWORD, Constant.PID));
		
		register(email, true, false);
		Constant.WEBDRIVER.close();
		Constant.WEBDRIVER.switchTo().window(mainWindow);
		
		System.out.println("1. Navigate to QA Railway Login page");
		open(Constant.RAILWAY_URL);
		
		System.out.println("2. Login with a valid account ");
		loginPage = homePage.gotoPage(MenuItem.LOGIN, LoginPage.class);
		loginPage.login(new Account(email, Constant.PASSWORD, null)).gotoPage(MenuItem.HOME, HomePage.class);
		System.out.println("3. Click on \"Book ticket\" tab");
		bookTicketPage = homePage.gotoPage(MenuItem.BOOKTICKET, BookTicketPage.class);
		System.out.println("4. Select the next 2 days from 'Depart date'\n"
				+ "5. Select Depart from 'Nha Trang' and Arrive at 'Huế'\n"
				+ "6. Select 'Soft bed with air conditioner' for 'Seat type'\n"
				+ "7. Select '1' for 'Ticket amount'\n"
				+ "8. Click on 'Book ticket' button");
		
		bookTicketPage.bookTicket(ticket);
		
		System.out.println("V.P: Message 'Ticket booked successfully!' displays. Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
		Assert.assertEquals(bookTicketPage.getSuccessMsg(), "Ticket booked successfully!", "Message is not displayed as expected");
		Assert.assertEquals(bookTicketPage.getInforTicket(bookTicketPage.getDepartureStation(2)), bookTicketPage.getDepartureStation(2), "Depart Date is not displayed as expected");
		Assert.assertEquals(bookTicketPage.getInforTicket(Constant.NHA_TRANG), Constant.NHA_TRANG, "Departure Station is not displayed as expected");
		Assert.assertEquals(bookTicketPage.getInforTicket(Constant.HUE), Constant.HUE, "Arrive Station is not displayed as expected");
		Assert.assertEquals(bookTicketPage.getInforTicket(SeatType.SOFT_BED_AC), SeatType.SOFT_BED_AC.getText(), "Seat Type is not displayed as expected");
		Assert.assertEquals(bookTicketPage.getInforTicket(String.valueOf(1)), "1", "Ticket Amount does not match as expected");
		
	}
	
	@Test
	public void TC13() {
		
		Ticket ticket = new Ticket(25, Constant.NHA_TRANG, Constant.SAI_GON, SeatType.SOFT_SEAT_AC, 5);
		System.out.println("TC13 - User can book many tickets at a time");
		System.out.println("Pre-condition: an actived account is existing");
		String mainWindow, email;
		
		mainWindow = openNewTabAndReturnHandle(Constant.RAILWAY_URL);
		email = register(strEmail, false, false);
		Constant.WEBDRIVER.close();
		Constant.WEBDRIVER.switchTo().window(mainWindow);
		
		registerPage = homePage.gotoPage(MenuItem.REGISTER, RegisterPage.class);
		registerPage.register(new Account(email, Constant.PASSWORD, Constant.PID));
		
		register(email, true, false);
		Constant.WEBDRIVER.close();
		Constant.WEBDRIVER.switchTo().window(mainWindow);
		
		System.out.println("1. Navigate to QA Railway Login page");
		open(Constant.RAILWAY_URL);
		
		System.out.println("2. Login with a valid account ");
		loginPage = homePage.gotoPage(MenuItem.LOGIN, LoginPage.class);
		loginPage.login(new Account(email, Constant.PASSWORD, null)).gotoPage(MenuItem.HOME, HomePage.class);
		System.out.println("3. Click on \"Book ticket\" tab");
		bookTicketPage = homePage.gotoPage(MenuItem.BOOKTICKET, BookTicketPage.class);
		System.out.println("4. Select the next 2 days from 'Depart date'\n"
				+ "5. Select Depart from 'Nha Trang' and Arrive at 'Huế'\n"
				+ "6. Select 'Soft bed with air conditioner' for 'Seat type'\n"
				+ "7. Select '1' for 'Ticket amount'\n"
				+ "8. Click on 'Book ticket' button");
		
		bookTicketPage.bookTicket(ticket);
		
		System.out.println("V.P: Message 'Ticket booked successfully!' displays. Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
		Assert.assertEquals(bookTicketPage.getSuccessMsg(), "Ticket booked successfully!", "Message is not displayed as expected");
		Assert.assertEquals(bookTicketPage.getInforTicket(bookTicketPage.getDepartureStation(25)), bookTicketPage.getDepartureStation(25), "Depart Date is not displayed as expected");
		Assert.assertEquals(bookTicketPage.getInforTicket(Constant.NHA_TRANG), Constant.NHA_TRANG, "Departure Station is not displayed as expected");
		Assert.assertEquals(bookTicketPage.getInforTicket(Constant.SAI_GON), Constant.SAI_GON, "Arrive Station is not displayed as expected");
		Assert.assertEquals(bookTicketPage.getInforTicket(SeatType.SOFT_SEAT_AC), SeatType.SOFT_SEAT_AC.getText(), "Seat Type is not displayed as expected");
		Assert.assertEquals(bookTicketPage.getInforTicket(String.valueOf(5)), "5", "Ticket Amount does not match as expected");
		
	}
}
