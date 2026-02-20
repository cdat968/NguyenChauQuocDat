package Railway;

import Constant.SeatType;

public class Ticket {
	
	protected String departureDate;
	protected String departureFrom;
	protected String arrivalAt;
	protected String seatType;
	protected Integer ticketAmount;
	
	public Ticket(String departureDate, String departureFrom, String arrivalAt, String seatType, Integer ticketAmount) {
		this.departureDate = departureDate;
		this.departureFrom = departureFrom;
		this.arrivalAt = arrivalAt;
		this.seatType = seatType;
		this.ticketAmount = ticketAmount;
	}
	
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}
	
	public String getDepartureDate() {
		return this.departureDate;
	}
	
	public void setDepartureFrom(String departureFrom) {
		this.departureFrom = departureFrom;
	}
	
	public String getDepartureFrom() {
		return this.departureFrom;
	}
	
	public void setArrivalAt(String arrivalAt) {
		this.arrivalAt = arrivalAt;
	}
	
	public String getArrivalAt() {
		return this.arrivalAt;
	}
	
	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}
	
	public String getSeatType() {
		return this.seatType;
	}
	
	public void setTicketAmount(Integer ticketAmount) {
		this.ticketAmount = ticketAmount;
	}
	
	public Integer getTicketAmount() {
		return this.ticketAmount;
	}
}
