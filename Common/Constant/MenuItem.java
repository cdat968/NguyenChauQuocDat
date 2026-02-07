package Constant;

public enum MenuItem {
	HOME("Home"),
	FAQ("FAQ"),
	CONTACT("Contact"),
	LOGOUT("Log out"),
	REGISTER("Register"),
	LOGIN("Login");
	
	private final String displayText;
	
	MenuItem(String displayText) {
		this.displayText = displayText;
	}
	
	public String getText() {
		return displayText;
	}
	
	
}
