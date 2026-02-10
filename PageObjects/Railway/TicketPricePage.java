package Railway;

import org.openqa.selenium.By;

public class TicketPricePage extends GeneralPage {
	private final String _btnCheckPrice = "//li[text()='%s']/ancestor::tr//a[@class='BoxLink']";
	
	private final By _tableHeaderTicketPrice = By.xpath("//table[@class='MyTable MedTable']//tr[@class='TableSmallHeader']//th");
	private final String _txtPriceSeatType = "//tr[th[normalize-space()='Price (VND)']]/td[count(//tr[th[normalize-space()='Seat type']]/td[text()='%s']/preceding-sibling::td) + 1]";
	
	protected String getPriceBySeatType(String seatType) {
		return getText(_txtPriceSeatType, seatType);
	}
}
