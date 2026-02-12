package Common;

import java.util.ArrayDeque;
import java.util.Deque;

import org.openqa.selenium.WindowType;

import Constant.Constant;

public class WindowManager {
	
	public static Deque<String> windowStack = new ArrayDeque<>();
	
	public static void saveCurrentWindow() {
		windowStack.push(Constant.WEBDRIVER.getWindowHandle());
	}
	
	public static void switchBack() {
		Constant.WEBDRIVER.switchTo().window(windowStack.pop());
	}
	
	public static void open(String url) {
		Constant.WEBDRIVER.navigate().to(url);
	}
	
	public static void close() {
		Constant.WEBDRIVER.close();
	}
	
	public static void openNewTab(String url) {
		Constant.WEBDRIVER.switchTo().newWindow(WindowType.TAB);
		Constant.WEBDRIVER.get(url);
	}
	
	public static void closeCurrentTabAndBack() {
		close();
		WindowManager.switchBack();
	}
}
