package Railway;

import Constant.SeatType;

public class TicketPricePage extends GeneralPage {
	
	private final String txtPriceSeatType = "//tr[th[normalize-space()='Price (VND)']]/td[count(//tr[th[normalize-space()='Seat type']]/td[text()='%s']/preceding-sibling::td) + 1]";
	
	protected Integer getPriceBySeatType(SeatType seatType) {
		return Integer.parseInt(getText(txtPriceSeatType, seatType.getCode()));
	}
}
