package Constant;

public enum SeatType {
	HARD_SEAT("HS","Hard seat", 310000),
	SOFT_SEAT("SS","Soft seat", 335000),
	SOFT_SEAT_AC("SSC","Soft seat with air conditioner", 360000),
	HARD_BED("HB","Hard bed", 410000),
	SOFT_BED("SB","Soft bed", 460000),
	SOFT_BED_AC("SBC","Soft bed with air conditioner", 510000);
	
	private final String code;
	private final String text;
	private final Integer expectedPrice;
	
	private SeatType(String code, String text, Integer expectedPrice) {
		this.code = code;
		this.text = text;
		this.expectedPrice = expectedPrice;
	}
	
	public String getText() {
		return text;
	}
	
	public String getCode() {
		return code;
	}
	
	public Integer getExpectedPrice() {
		return expectedPrice;
	}
	
	public static SeatType fromText(String text) {
        for (SeatType type : SeatType.values()) {
            if (type.text.equalsIgnoreCase(text.trim())) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid seat type: " + text);
    }
}
