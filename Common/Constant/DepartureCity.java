package Constant;

import java.util.List;

public enum DepartureCity {
	SAI_GON(List.of(ArrivalCity.PHAN_THIET, ArrivalCity.NHA_TRANG, ArrivalCity.DA_NANG, ArrivalCity.HUE, ArrivalCity.QUANG_NGAI)),
	PHAN_THIET(List.of(ArrivalCity.SAI_GON, ArrivalCity.NHA_TRANG, ArrivalCity.DA_NANG)),
	NHA_TRANG(List.of(ArrivalCity.SAI_GON, ArrivalCity.DA_NANG, ArrivalCity.HUE, ArrivalCity.PHAN_THIET)),
	DA_NANG(List.of(ArrivalCity.SAI_GON, ArrivalCity.NHA_TRANG, ArrivalCity.HUE, ArrivalCity.QUANG_NGAI)),
	HUE(List.of(ArrivalCity.SAI_GON, ArrivalCity.NHA_TRANG, ArrivalCity.DA_NANG, ArrivalCity.QUANG_NGAI)),
	QUANG_NGAI(List.of(ArrivalCity.SAI_GON, ArrivalCity.NHA_TRANG, ArrivalCity.DA_NANG, ArrivalCity.HUE));
	
	private final List<ArrivalCity> validArrivals;
	
	private DepartureCity(List<ArrivalCity> validArrivals) {
		this.validArrivals = validArrivals;
	}
	
	public boolean isValidArrivals(ArrivalCity arrival) {
		return validArrivals.contains(arrival);
	}
}
