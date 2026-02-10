package Constant;

public enum OpenType{
	NAVIGATE_URL("NAVIGATE_URL"),
	CURRENT_WINDOW("CURRENT_WINDOW"),
	NEW_TAB("NEW_TAB"),
	NEW_WINDOW("NEW_WINDOW");
	
	private final String url;
	
	private OpenType(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
}
