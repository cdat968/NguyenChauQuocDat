package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;
import Constant.Constant;
import Constant.LocationType;
import Constant.MenuItem;
import Constant.SeatType;

public class BookTicketTest extends TestBase {
	
//	@Test
	public void TC12() {
		
		BookTicketPage bookTicketPage = new BookTicketPage();
		
		Account account = new Account("", Constant.PASSWORD, null);
		
		Ticket ticket = new Ticket(2, Constant.NHA_TRANG, Constant.HUE, SeatType.SOFT_BED_AC, 1);
		
		System.out.println("TC12 - User can book 1 ticket at a time");
		
		System.out.println("Pre-condition: an actived account is existing");
		
		String email = registerAndActiveAccount();
		
		System.out.println("1. Navigate to QA Railway Login page");
		
		open(Constant.RAILWAY_URL);
		
		System.out.println("2. Login with a valid account ");
		
		loginPage = homePage.gotoPage(MenuItem.LOGIN, LoginPage.class);
		
		account.setEmail(email);
		
		loginPage.login(account);
		
		System.out.println("3. Click on \"Book ticket\" tab");
		
		bookTicketPage = homePage.gotoPage(MenuItem.BOOKTICKET, BookTicketPage.class);
		
		System.out.println("4. Select the next 2 days from 'Depart date'");
		
		System.out.println("5. Select Depart from 'Nha Trang' and Arrive at 'Huế'");
		
		System.out.println("6. Select 'Soft bed with air conditioner' for 'Seat type'");
		
		System.out.println("7. Select '1' for 'Ticket amount'");
		
		System.out.println("8. Click on 'Book ticket' button");		
		
		bookTicketPage.bookTicket(ticket);
		
		System.out.println("V.P: Message 'Ticket booked successfully!' displays. Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
		
		String actualSuccessMsg = bookTicketPage.getSuccessMsg();
		
		Assert.assertEquals(actualSuccessMsg, "Ticket booked successfully!", "Message is not displayed as expected");
		
		String actualDepartureDate = bookTicketPage.getInforTicket(bookTicketPage.getDepartureStation(2));
		
		String expectedDepartureDate = bookTicketPage.getDepartureStation(2);
		
		Assert.assertEquals(actualDepartureDate, expectedDepartureDate, "Depart Date is not displayed as expected");
		
		String actualDepartStation = bookTicketPage.getInforTicket(Constant.NHA_TRANG);
		
		Assert.assertEquals(actualDepartStation, Constant.NHA_TRANG, "Departure Station is not displayed as expected");
		
		String actualArriveStation = bookTicketPage.getInforTicket(Constant.HUE);
		
		Assert.assertEquals(actualArriveStation, Constant.HUE, "Arrive Station is not displayed as expected");
		
		String actualSeatType = bookTicketPage.getInforTicket(SeatType.SOFT_BED_AC);
		
		Assert.assertEquals(actualSeatType, SeatType.SOFT_BED_AC.getText(), "Seat Type is not displayed as expected");
		
		String actualAmountTicket = bookTicketPage.getInforTicket(String.valueOf(1));
		
		Assert.assertEquals(actualAmountTicket, "1", "Ticket Amount does not match as expected");
		
	}
	
//	@Test
	public void TC13() {
		
		BookTicketPage bookTicketPage = new BookTicketPage();
		
		Account account = new Account("", Constant.PASSWORD, null);
		
		Ticket ticket = new Ticket(25, Constant.NHA_TRANG, Constant.SAI_GON, SeatType.SOFT_SEAT_AC, 5);
		
		System.out.println("TC13 - User can book many tickets at a time");
		
		System.out.println("Pre-condition: an actived account is existing");
		
		String email = registerAndActiveAccount();
		
		System.out.println("1. Navigate to QA Railway Login page");
		
		open(Constant.RAILWAY_URL);
		
		System.out.println("2. Login with a valid account ");
		
		loginPage = homePage.gotoPage(MenuItem.LOGIN, LoginPage.class);
		
		account.setEmail(email);
		
		loginPage.login(account);
		
		System.out.println("3. Click on \"Book ticket\" tab");
		
		bookTicketPage = homePage.gotoPage(MenuItem.BOOKTICKET, BookTicketPage.class);
		
		System.out.println("4. Select the next 2 days from 'Depart date'\n");
		
		System.out.println("5. Select Depart from 'Nha Trang' and Arrive at 'Huế'\n");
		
		System.out.println("6. Select 'Soft bed with air conditioner' for 'Seat type'\n");
		
		System.out.println("7. Select '1' for 'Ticket amount'\n");
		
		System.out.println("8. Click on 'Book ticket' button");
		
		bookTicketPage.bookTicket(ticket);
		
		System.out.println("V.P: Message 'Ticket booked successfully!' displays. Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
		
		String actualSuccessMsg = bookTicketPage.getSuccessMsg();
		
		String expectedSuccessMsg = "Ticket booked successfully!";
		
		Assert.assertEquals(actualSuccessMsg, expectedSuccessMsg, "Message is not displayed as expected");
		
		String actualDepartDate = bookTicketPage.getInforTicket(bookTicketPage.getDepartureStation(25));
		
		String expectedDepartDate = bookTicketPage.getDepartureStation(25);
		
		Assert.assertEquals(actualDepartDate, expectedDepartDate, "Depart Date is not displayed as expected");
		
		String actualDepartureStation = bookTicketPage.getInforTicket(Constant.NHA_TRANG);
		
		Assert.assertEquals(actualDepartureStation, Constant.NHA_TRANG, "Departure Station is not displayed as expected");
		
		String actualArriveStation = bookTicketPage.getInforTicket(Constant.SAI_GON); 
		
		Assert.assertEquals(actualArriveStation, Constant.SAI_GON, "Arrive Station is not displayed as expected");
		
		String actualSeatType = bookTicketPage.getInforTicket(SeatType.SOFT_SEAT_AC);
		
		Assert.assertEquals(actualSeatType, SeatType.SOFT_SEAT_AC.getText(), "Seat Type is not displayed as expected");
		
		String actualAmountTicket = bookTicketPage.getInforTicket(String.valueOf(5));
		
		Assert.assertEquals(actualAmountTicket, "5", "Ticket Amount does not match as expected");
		
	}
	
//	@Test 
	public void TC14() {
		
		TimeTablePage timeTablePage = new TimeTablePage();
		
		BookTicketPage bookTicketPage = new BookTicketPage();
		
		TicketPricePage ticketPricePage = new TicketPricePage();
		
		Account account = new Account("", Constant.PASSWORD, null);
		
		System.out.println("TC14 - User can check price of ticket from Timetable");
		
		System.out.println("Pre-condition: an actived account is existing");
		
		String email = registerAndActiveAccount();
		
		System.out.println("1. Navigate to QA Railway Login page");
		
		open(Constant.RAILWAY_URL);
		
		System.out.println("2. Login with a valid account ");
		
		loginPage = homePage.gotoPage(MenuItem.LOGIN, LoginPage.class);
		
		account.setEmail(email);
		
		loginPage.login(account);
		
		System.out.println("3. Click on 'Timetable' tab");
		
		homePage.clickTab(MenuItem.TIMETABLE);
		
		System.out.println("4. Click on 'check price' link of the route from 'Đà Nẵng' to 'Sài Gòn'");
		
		timeTablePage.checkPrice(Constant.DA_NANG, Constant.SAI_GON);
		
		System.out.println("V.P: 'Ticket Price' page is loaded.");
		
		boolean actualExistPage = TicketPricePage.checkTabPageDisplayed(MenuItem.TICKETPRICE);
		
		Assert.assertTrue(actualExistPage, "Ticket Price Page is not displayed as expected");
		
		System.out.println("Ticket table shows 'Ticket price from Đà Nẵng to Sài Gòn'");
		
		String actualTitle = bookTicketPage.getTextTitleTable();
		
		String expectedTitle = "Ticket price from Đà Nẵng to Sài Gòn";
		
		Assert.assertEquals(actualTitle, expectedTitle, "Message is not displayed as expected");
		
		System.out.println("Price for each seat displays correctly HS = 310000, SS = 335000, SSC = 360000, HB = 410000, SB = 460000, SBC = 510000");
		
		String actualPriceHS = ticketPricePage.getPriceBySeatType("HS");
		
		Assert.assertEquals(actualPriceHS, "310000", "Hard Seat Price is not displayed as expected");
		
		String actualPriceSS = ticketPricePage.getPriceBySeatType("SS");
		
		Assert.assertEquals(actualPriceSS, "335000", "Soft Seat Price is not displayed as expected");
		
		String actualPriceSSC = ticketPricePage.getPriceBySeatType("SSC");
		
		Assert.assertEquals(actualPriceSSC, "360000", "Soft seat with air conditioner Price is not displayed as expected");
		
		String actualPriceHB = ticketPricePage.getPriceBySeatType("HB");
		
		Assert.assertEquals(actualPriceHB, "410000", "Hard bed Price is not displayed as expected");
		
		String actualPriceSB = ticketPricePage.getPriceBySeatType("SB");
		
		Assert.assertEquals(actualPriceSB, "460000", "Soft bed Price is not displayed as expected");
		
		String actualPriceSBC = ticketPricePage.getPriceBySeatType("SBC");
		
		Assert.assertEquals(actualPriceSBC, "510000", "Soft bed with air conditioner Price is not displayed as expected");
		
	}

	@Test
	public void TC15() {
		
		BookTicketPage bookTicketPage = new BookTicketPage();
		
		TimeTablePage timeTablePage = new TimeTablePage();
		
		Ticket ticket = new Ticket(1, Constant.QUANG_NGAI, Constant.HUE, SeatType.HARD_SEAT, 5);
		
		Account account = new Account("", Constant.PASSWORD, null);
		
		System.out.println("TC15 - User can book ticket from Timetable");
		
		System.out.println("Pre-condition: an actived account is existing");
		
		String email = registerAndActiveAccount();
		
		System.out.println("1. Navigate to QA Railway Login page");
		
		open(Constant.RAILWAY_URL);
		
		System.out.println("2. Login with a valid account ");
		
		loginPage = homePage.gotoPage(MenuItem.LOGIN, LoginPage.class);
		
		account.setEmail(email);
		
		loginPage.login(account);
		
		System.out.println("3. Click on 'Timetable' tab");
		
		homePage.clickTab(MenuItem.TIMETABLE);
		
		System.out.println("4. Click on book ticket of route 'Quảng Ngãi' to 'Huế'");
		
		timeTablePage.bookTicket(Constant.QUANG_NGAI, Constant.HUE);
		
		System.out.println("V.P: Book ticket form is shown with the corrected \"depart from\" and \"Arrive at\"");
		
		String actualDepartFrom = bookTicketPage.getTxtSelectedOption(LocationType.DEPARTURE, Constant.QUANG_NGAI);
		
		Assert.assertEquals(actualDepartFrom, Constant.QUANG_NGAI, "Departure From is not displayed as expected");
		
		String actualArriveAt = bookTicketPage.getTxtSelectedOption(LocationType.ARRIVE, Constant.HUE);
		
		Assert.assertEquals(actualArriveAt, Constant.HUE, "Arrive At is not displayed as expected");
		
		System.out.println("5. Select Depart date = tomorrow");
		
		System.out.println("6. Select Ticket amount = 5");
		
		System.out.println("7. Click on 'Book ticket' button");
		
		bookTicketPage.bookTicket(ticket);
		
		System.out.println("V.P: Message 'Ticket booked successfully!' displays. Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
		
		String actualSuccessMsg = bookTicketPage.getSuccessMsg();
		
		String expectedSuccessMsgString = "Ticket booked successfully!";
		
		Assert.assertEquals(actualSuccessMsg, expectedSuccessMsgString, "Message is not displayed as expected");
		
		String actualDepartDate = bookTicketPage.getInforTicket(bookTicketPage.getDepartureStation(1));
		
		String expectedDepartDate = bookTicketPage.getDepartureStation(1);
		
		Assert.assertEquals(actualDepartDate, expectedDepartDate, "Depart Date is not displayed as expected");
		
		String actualDepartStation = bookTicketPage.getInforTicket(Constant.QUANG_NGAI);
		
		Assert.assertEquals(actualDepartStation, Constant.QUANG_NGAI, "Departure Station is not displayed as expected");
		
		String actualArriveStation = bookTicketPage.getInforTicket(Constant.HUE);
		
		Assert.assertEquals(actualArriveStation, Constant.HUE, "Arrive Station is not displayed as expected");
		
		String actualSeatType = bookTicketPage.getInforTicket(SeatType.HARD_SEAT);
		
		Assert.assertEquals(actualSeatType, SeatType.HARD_SEAT.getText(), "Seat Type is not displayed as expected");
		
		String actualAmountTicket = bookTicketPage.getInforTicket(String.valueOf(5));
		
		Assert.assertEquals(actualAmountTicket, "5", "Ticket Amount does not match as expected");
	}
}
