package Railway;

public class TimeTablePage extends GeneralPage {
	
	protected final String linkCheckPriceRoute = "//table[@class='MyTable WideTable']/tbody/tr[td[count(//thead//th[normalize-space()='Depart Station']/preceding-sibling::th)+ 1][normalize-space()='%s'] and td[count(//thead//th[normalize-space()='Arrive Station']/preceding-sibling::th)+ 1][normalize-space()='%s']]//a[normalize-space()='check price']";
	protected final String linkBookTicketRoute = "//table[@class='MyTable WideTable']/tbody/tr[td[count(//thead//th[normalize-space()='Depart Station']/preceding-sibling::th)+ 1][normalize-space()='%s'] and td[count(//thead//th[normalize-space()='Arrive Station']/preceding-sibling::th)+ 1][normalize-space()='%s']]//a[normalize-space()='book ticket']";
	
	protected void checkPrice(Object... params) {
		click(linkCheckPriceRoute, params);
	}
	
	protected void bookTicket(Object... params) {
		click(linkBookTicketRoute, params);
	}
}
