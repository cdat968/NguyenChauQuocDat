package Constant;

public enum ArrivalCity {
	PHAN_THIET("Phan Thiết"),
	NHA_TRANG("Nha Trang"),
	DA_NANG("Đà Nẵng"),
	HUE("Huế"),
	SAI_GON("Sài Gòn"),
	QUANG_NGAI("Quảng Ngãi");
	
	private final String city;
	
	private ArrivalCity(String city) {
		this.city = city;
	}
	
	public String getCity() {
		return city;
	}
}
