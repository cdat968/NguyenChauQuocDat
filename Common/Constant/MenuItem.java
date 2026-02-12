package Constant;

public enum MenuItem {
	
	HOME("Home"),
	FAQ("FAQ"),
	CONTACT("Contact"),
	LOGOUT("Log out"),
	REGISTER("Register"),
	LOGIN("Login"),
	BOOKTICKET("Book ticket"),
	TIMETABLE("Timetable"),
	TICKETPRICE("Ticket price"),
	MYTICKET("My ticket");
	
	private final String displayText;
	
	MenuItem(String displayText) {
		this.displayText = displayText;
	}
	
	public String getText() {
		return displayText;
	}
	
}
