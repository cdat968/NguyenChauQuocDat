package Constant;

public enum SeatType {
	HARD_SEAT("Hard seat"),
	SOFT_SEAT("Soft seat"),
	SOFT_SEAT_AC("Soft seat with air conditioner"),
	HARD_BED("Hard bed"),
	SOFT_BED("Soft bed"),
	SOFT_BED_AC("Soft bed with air conditioner");
	
	private final String text;
	
	private SeatType(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
}
