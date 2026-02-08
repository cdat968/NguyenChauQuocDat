package Constant;

import org.openqa.selenium.By;

public enum LocationType {
	DEPARTURE(By.name("DepartStation")),
	ARRIVE(By.name("ArriveStation"));
	
	private final By locator;
	
	LocationType(By locator) {
		this.locator = locator;
	}
	
	public By getLocator() {
		return locator;
	}
}
