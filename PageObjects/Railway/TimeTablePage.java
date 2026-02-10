package Railway;

import Constant.Constant;

public class TimeTablePage extends GeneralPage {
	//table[@class='MyTable WideTable']/tbody/tr[td[count(//thead//th[normalize-space()='Depart Station']/preceding-sibling::th)+ 1][normalize-space()='Đà Nẵng'] and td[count(//thead//th[normalize-space()='Arrive Station']/preceding-sibling::th)+ 1][normalize-space()='Huế']]//a[normalize-space()='check price']
	//table[@class='MyTable']/tbody/tr[td[count(//thead//th[normalize-space()='Depart Station']/preceding-sibling::th)+ 1][normalize-space()='Sài Gòn'] and td[count(//thead//th[normalize-space()='Arrive Station']/preceding-sibling::th)+ 1][normalize-space()='Phan Thiết']]//input[@value='Cancel']
	protected final String _linkCheckPriceRoute = "//table[@class='MyTable WideTable']/tbody/tr[td[count(//thead//th[normalize-space()='Depart Station']/preceding-sibling::th)+ 1][normalize-space()='%s'] and td[count(//thead//th[normalize-space()='Arrive Station']/preceding-sibling::th)+ 1][normalize-space()='%s']]//a[normalize-space()='check price']";
	protected final String _linkBookTicketRoute = "//table[@class='MyTable WideTable']/tbody/tr[td[count(//thead//th[normalize-space()='Depart Station']/preceding-sibling::th)+ 1][normalize-space()='%s'] and td[count(//thead//th[normalize-space()='Arrive Station']/preceding-sibling::th)+ 1][normalize-space()='%s']]//a[normalize-space()='book ticket']";
	
	protected void checkPrice(Object... params) {
		click(_linkCheckPriceRoute, params);
	}
	
	protected void bookTicket(Object... params) {
		click(_linkBookTicketRoute, params);
	}
}
