package Constant;

public enum HeaderBookedTicket {
	
	DEPART_STATION("Depart Station"),
	ARRIVE_STATION("Arrive Station"),
	SEAT_TYPE("Seat Type"),
	DEPART_DATE("Depart Date"),
	BOOK_DATE("Book Date"),
	EXPIRED_DATE("Expired Date"),
	AMOUNT("Amount"),
	TOTAL_PRICE("Total Price");
	
	private final String displayText;
	
	HeaderBookedTicket(String displayText) {
		this.displayText = displayText;
	}
	
	public String getText() {
		return displayText;
	}
}
