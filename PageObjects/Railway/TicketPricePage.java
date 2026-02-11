package Railway;

public class TicketPricePage extends GeneralPage {
	
	private final String _txtPriceSeatType = "//tr[th[normalize-space()='Price (VND)']]/td[count(//tr[th[normalize-space()='Seat type']]/td[text()='%s']/preceding-sibling::td) + 1]";
	
	protected String getPriceBySeatType(String seatType) {
		
		return getText(_txtPriceSeatType, seatType);
	
	}
}
