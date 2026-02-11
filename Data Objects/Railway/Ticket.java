package Railway;

import Constant.SeatType;

public class Ticket {
	
	protected Integer departureDate;
	
	protected String departureFrom;
	
	protected String arrivalAt;
	
	protected SeatType seatType;
	
	protected Integer ticketAmount;
	
	public Ticket(Integer departureDate, String departureFrom, String arrivalAt, SeatType seatType, Integer ticketAmount) {
	
		this.departureDate = departureDate;
		
		this.departureFrom = departureFrom;
		
		this.arrivalAt = arrivalAt;
		
		this.seatType = seatType;
		
		this.ticketAmount = ticketAmount;
	}
	
	public void setDepartureDate(Integer departureDate) {
		
		this.departureDate = departureDate;
		
	}
	
	public Integer getDepartureDate() {
	
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
	
	public void setSeatType(SeatType seatType) {
	
		this.seatType = seatType;
	
	}
	
	public SeatType getSeatType() {
	
		return this.seatType;
	
	}
	
	public void setTicketAmount(Integer ticketAmount) {
	
		this.ticketAmount = ticketAmount;
	
	}
	
	public Integer getTicketAmount() {
	
		return this.ticketAmount;
	
	}
}
