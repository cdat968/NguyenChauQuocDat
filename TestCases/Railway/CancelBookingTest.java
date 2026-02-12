package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Constant.Constant;
import Constant.MenuItem;
import Constant.SeatType;

public class CancelBookingTest extends TestBase {
	
	@Test
	public void TC16() {
		
		BookTicketPage bookTicketPage = new BookTicketPage();
		MyTicketPage myTicketPage = new MyTicketPage();
		int amountTicket = 6;
		Ticket ticket = new Ticket("", Constant.DA_NANG, Constant.HUE, SeatType.HARD_SEAT, amountTicket);
		LoginPage loginPage = new LoginPage();
		
		System.out.println("TC16 - User can cancel a ticket");
		
		System.out.println("Pre-condition: an actived account is existing");
		String email = registerAndActiveAccount();
		
		System.out.println("1. Navigate to QA Railway Login page");
		open(Constant.RAILWAY_URL);
		
		System.out.println("2. Login with a valid account ");
		loginPage = homePage.gotoPage(MenuItem.LOGIN, LoginPage.class);
		loginPage.login(new Account(email, Constant.PASSWORD, null));
		
		System.out.println("3. Book a ticket");
		bookTicketPage = homePage.gotoPage(MenuItem.BOOKTICKET, BookTicketPage.class);
		String daysFromDepartDay = bookTicketPage.convertDateToString(6, bookTicketPage.getDepartDay());
		ticket.setDepartureDate(daysFromDepartDay);
		bookTicketPage.bookTicket(ticket);
		
		System.out.println("4. Click on 'My ticket' tab");
		myTicketPage = homePage.gotoPage(MenuItem.MYTICKET, MyTicketPage.class);
		
		System.out.println("5. Click on 'Cancel' button of ticket which user want to cancel.");
		System.out.println("6. Click on 'OK' button on Confirmation message 'Are you sure?' ");
		String deleteTicketId = myTicketPage.cancelBooking(Constant.DA_NANG, Constant.HUE);
		
		System.out.println("ticketId: " + deleteTicketId);
		System.out.println("V.P: The canceled ticket is disappeared.");
		Assert.assertFalse(myTicketPage.isDeletedTicket(deleteTicketId), "Ticket is not canceled as expected");
	}
	
}
