package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.WindowManager;
import Constant.Constant;
import Constant.HeaderBookedTicket;
import Constant.LocationType;
import Constant.MenuItem;
import Constant.SeatType;

public class BookTicketTest extends TestBase {
	
	@Test
	public void TC12() {
		
		BookTicketPage bookTicketPage = new BookTicketPage();
		LoginPage loginPage = new LoginPage();
		Integer amountTicket = 1;
		boolean isDateFromDepartDay = true;
		Account account = new Account("", Constant.PASSWORD, null);
		Ticket ticket = new Ticket("", Constant.NHA_TRANG, Constant.HUE, SeatType.SOFT_BED_AC.getText(), amountTicket);
		
		System.out.println("TC12 - User can book 1 ticket at a time");
		System.out.println("Pre-condition: an actived account is existing");
		String email = registerAndActiveAccount();
		
		System.out.println("1. Navigate to QA Railway Login page");
		WindowManager.open(Constant.RAILWAY_URL);
		
		System.out.println("2. Login with a valid account ");
		loginPage = homePage.gotoPage(MenuItem.LOGIN, LoginPage.class);
		account.setEmail(email);
		loginPage.login(account);
		
		System.out.println("3. Click on 'Book ticket' tab");
		bookTicketPage = homePage.gotoPage(MenuItem.BOOKTICKET, BookTicketPage.class);
		
		System.out.println("4. Select the next 2 days from 'Depart date'");
		System.out.println("5. Select Depart from 'Nha Trang' and Arrive at 'Huế'");
		System.out.println("6. Select 'Soft bed with air conditioner' for 'Seat type'");
		System.out.println("7. Select '1' for 'Ticket amount'");
		System.out.println("8. Click on 'Book ticket' button");
		System.out.println("Get DepartDaY:::"+bookTicketPage.getDepartDay());
		String daysFromDepartDay = bookTicketPage.convertDateToString(2, isDateFromDepartDay);
		ticket.setDepartureDate(daysFromDepartDay);
		bookTicketPage.bookTicket(ticket);
		
		System.out.println("V.P: Message 'Ticket booked successfully!' displays.");
		String actualSuccessMsg = bookTicketPage.getSuccessMsg();
		String expectedSuccessMsg = "Ticket booked successfully!";
		Assert.assertEquals(actualSuccessMsg, expectedSuccessMsg, "Message is not displayed as expected");
		
		System.out.println("V.P: Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
		
		String actualDepartureDate = bookTicketPage.getInforTicketByHeader(HeaderBookedTicket.DEPART_DATE.getText());
		String expectedDepartureDate = daysFromDepartDay;
		Assert.assertEquals(actualDepartureDate, expectedDepartureDate, "Depart Date is not displayed as expected");
		
		String actualDepartStation = bookTicketPage.getInforTicketByHeader(HeaderBookedTicket.DEPART_STATION.getText());
		String expectedDepartStation = Constant.NHA_TRANG;
		Assert.assertEquals(actualDepartStation, expectedDepartStation, "Departure Station is not displayed as expected");
		
		String actualArriveStation = bookTicketPage.getInforTicketByHeader(HeaderBookedTicket.ARRIVE_STATION.getText());
		String expectedArriveStation = Constant.HUE;
		Assert.assertEquals(actualArriveStation, expectedArriveStation, "Arrive Station is not displayed as expected");
		
		String actualSeatType = bookTicketPage.getInforTicketByHeader(HeaderBookedTicket.SEAT_TYPE.getText());
		String expectedSeatType = SeatType.SOFT_BED_AC.getText();
		Assert.assertEquals(actualSeatType, expectedSeatType, "Seat Type is not displayed as expected");
		
		String actualAmountTicket = bookTicketPage.getInforTicketByHeader(HeaderBookedTicket.AMOUNT.getText());
		String expectedAmountTicket = String.valueOf(amountTicket);
		Assert.assertEquals(actualAmountTicket, expectedAmountTicket, "Ticket Amount does not match as expected");
	}
	
	@Test
	public void TC13() {
		
		boolean isDateFromDepartDay = true;
		Integer amountTicket = 5;
		LoginPage loginPage = new LoginPage();
		BookTicketPage bookTicketPage = new BookTicketPage();
		Account account = new Account("", Constant.PASSWORD, null);
		Ticket ticket = new Ticket("", Constant.NHA_TRANG, Constant.SAI_GON, SeatType.SOFT_SEAT_AC.getText(), amountTicket);
		
		System.out.println("TC13 - User can book many tickets at a time");
		System.out.println("Pre-condition: an actived account is existing");
		String email = registerAndActiveAccount();
		
		System.out.println("1. Navigate to QA Railway Login page");
		WindowManager.open(Constant.RAILWAY_URL);
		
		System.out.println("2. Login with a valid account ");
		loginPage = homePage.gotoPage(MenuItem.LOGIN, LoginPage.class);
		account.setEmail(email);
		loginPage.login(account);
		
		System.out.println("3. Click on 'Book ticket' tab");
		bookTicketPage = homePage.gotoPage(MenuItem.BOOKTICKET, BookTicketPage.class);
		
		System.out.println("4. Select the next 2 days from 'Depart date'");
		System.out.println("5. Select Depart from 'Nha Trang' and Arrive at 'Huế'");
		System.out.println("6. Select 'Soft bed with air conditioner' for 'Seat type'");
		System.out.println("7. Select '1' for 'Ticket amount'");
		System.out.println("8. Click on 'Book ticket' button");
		String daysFromDepartDay = bookTicketPage.convertDateToString(25, isDateFromDepartDay);
		ticket.setDepartureDate(daysFromDepartDay);
		bookTicketPage.bookTicket(ticket);
		
		System.out.println("V.P: Message 'Ticket booked successfully!' displays. Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
		String actualSuccessMsg = bookTicketPage.getSuccessMsg();
		String expectedSuccessMsg = "Ticket booked successfully!";
		Assert.assertEquals(actualSuccessMsg, expectedSuccessMsg, "Message is not displayed as expected");
		
		String actualDepartDate = bookTicketPage.getInforTicketByHeader(HeaderBookedTicket.DEPART_DATE.getText());
		String expectedDepartDate = daysFromDepartDay;
		Assert.assertEquals(actualDepartDate, expectedDepartDate, "Depart Date is not displayed as expected");
		
		String actualDepartureStation = bookTicketPage.getInforTicketByHeader(HeaderBookedTicket.DEPART_STATION.getText());
		String expectedDepartureStation = Constant.NHA_TRANG;
		Assert.assertEquals(actualDepartureStation, expectedDepartureStation, "Departure Station is not displayed as expected");
		
		String actualArriveStation = bookTicketPage.getInforTicketByHeader(HeaderBookedTicket.ARRIVE_STATION.getText());
		String expectedArriveStation = Constant.SAI_GON; 
		Assert.assertEquals(actualArriveStation, expectedArriveStation, "Arrive Station is not displayed as expected");
		
		String actualSeatType = bookTicketPage.getInforTicketByHeader(HeaderBookedTicket.SEAT_TYPE.getText());
		String expectedSeatType = SeatType.SOFT_SEAT_AC.getText();
		Assert.assertEquals(actualSeatType, expectedSeatType, "Seat Type is not displayed as expected");
		
		String actualAmountTicket = bookTicketPage.getInforTicketByHeader(HeaderBookedTicket.AMOUNT.getText());
		String expectedAmountTicket = String.valueOf(amountTicket);
		Assert.assertEquals(actualAmountTicket, expectedAmountTicket, "Ticket Amount does not match as expected");	
	}
	
	@Test 
	public void TC14() {
		
		LoginPage loginPage = new LoginPage();
		TimeTablePage timeTablePage = new TimeTablePage();
		BookTicketPage bookTicketPage = new BookTicketPage();
		TicketPricePage ticketPricePage = new TicketPricePage();
		Account account = new Account("", Constant.PASSWORD, null);
		
		System.out.println("TC14 - User can check price of ticket from Timetable");
		System.out.println("Pre-condition: an actived account is existing");
		String email = registerAndActiveAccount();
		
		System.out.println("1. Navigate to QA Railway Login page");
		WindowManager.open(Constant.RAILWAY_URL);
		
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
		
		System.out.println("V.P: Ticket table shows 'Ticket price from Đà Nẵng to Sài Gòn'");
		String actualTitle = bookTicketPage.getTextTitleTable();
		String expectedTitle = "Ticket price from Đà Nẵng to Sài Gòn";
		Assert.assertEquals(actualTitle, expectedTitle, "Message is not displayed as expected");
		
		System.out.println("V.P: Price for each seat displays correctly HS = 310000, SS = 335000, SSC = 360000, HB = 410000, SB = 460000, SBC = 510000");
		for (SeatType seatType : SeatType.values()) {
			int actual = ticketPricePage.getPriceBySeatType(seatType);
			int expected = seatType.getExpectedPrice();
			Assert.assertEquals(actual, expected, "Wrong price for "+ seatType.getText());
		}		
	}

	@Test
	public void TC15() {
		
		LoginPage loginPage = new LoginPage();
		BookTicketPage bookTicketPage = new BookTicketPage();
		TimeTablePage timeTablePage = new TimeTablePage();
		boolean isDateFromDepartDay = false;
		int amountTicket = 5;
		
		Ticket ticket = new Ticket("", Constant.QUANG_NGAI, Constant.HUE, SeatType.HARD_SEAT.getText(), amountTicket);
		Account account = new Account("", Constant.PASSWORD, null);
		
		System.out.println("TC15 - User can book ticket from Timetable");
		System.out.println("Pre-condition: an actived account is existing");
		String email = registerAndActiveAccount();
		
		System.out.println("1. Navigate to QA Railway Login page");
		WindowManager.open(Constant.RAILWAY_URL);
		
		System.out.println("2. Login with a valid account ");
		loginPage = homePage.gotoPage(MenuItem.LOGIN, LoginPage.class);
		account.setEmail(email);
		loginPage.login(account);
		
		System.out.println("3. Click on 'Timetable' tab");
		homePage.clickTab(MenuItem.TIMETABLE);
		
		System.out.println("4. Click on book ticket of route 'Quảng Ngãi' to 'Huế'");
		timeTablePage.bookTicket(Constant.QUANG_NGAI, Constant.HUE);
		
		System.out.println("V.P: Book ticket form is shown with the corrected 'depart from' and 'Arrive at'");
		String actualDepartFrom = bookTicketPage.getTxtSelectedOption(LocationType.DEPARTURE, Constant.QUANG_NGAI);
		Assert.assertEquals(actualDepartFrom, Constant.QUANG_NGAI, "Departure From is not displayed as expected");
		String actualArriveAt = bookTicketPage.getTxtSelectedOption(LocationType.ARRIVE, Constant.HUE);
		Assert.assertEquals(actualArriveAt, Constant.HUE, "Arrive At is not displayed as expected");
		
		System.out.println("5. Select Depart date = tomorrow");
		System.out.println("6. Select Ticket amount = 5");
		System.out.println("7. Click on 'Book ticket' button");
		String daysFromDepartDay = bookTicketPage.convertDateToString(1, isDateFromDepartDay);
		ticket.setDepartureDate(daysFromDepartDay);
		bookTicketPage.bookTicket(ticket);
		
		System.out.println("V.P: Message 'Ticket booked successfully!' displays.");
		String actualSuccessMsg = bookTicketPage.getSuccessMsg();
		String expectedSuccessMsgString = "Ticket booked successfully!";
		Assert.assertEquals(actualSuccessMsg, expectedSuccessMsgString, "Message is not displayed as expected");
		
		System.out.println("V.P:  Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
		String actualDepartDate = bookTicketPage.getInforTicketByHeader(HeaderBookedTicket.DEPART_DATE.getText());
		String expectedDepartDate = daysFromDepartDay;
		Assert.assertEquals(actualDepartDate, expectedDepartDate, "Depart Date is not displayed as expected");
		
		String actualDepartStation = bookTicketPage.getInforTicketByHeader(HeaderBookedTicket.DEPART_STATION.getText());
		String expectedDepartStation = Constant.QUANG_NGAI;
		Assert.assertEquals(actualDepartStation, expectedDepartStation, "Departure Station is not displayed as expected");
		
		String actualArriveStation = bookTicketPage.getInforTicketByHeader(HeaderBookedTicket.ARRIVE_STATION.getText());
		String expectedArriveStation = Constant.HUE;
		Assert.assertEquals(actualArriveStation, expectedArriveStation, "Arrive Station is not displayed as expected");
		
		String actualSeatType = bookTicketPage.getInforTicketByHeader(HeaderBookedTicket.SEAT_TYPE.getText());
		String expectedSeatType = SeatType.HARD_SEAT.getText();
		Assert.assertEquals(actualSeatType, expectedSeatType, "Seat Type is not displayed as expected");
		
		String actualAmountTicket = bookTicketPage.getInforTicketByHeader(HeaderBookedTicket.AMOUNT.getText());
		String expectedAmountTicket = String.valueOf(amountTicket);
		Assert.assertEquals(actualAmountTicket, expectedAmountTicket, "Ticket Amount does not match as expected");
	}
}
