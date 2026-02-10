package Common;

import java.util.ArrayDeque;
import java.util.Deque;

import Constant.Constant;

public class WindowManager {
	public static Deque<String> windowStack = new ArrayDeque<>();
	
	public static void saveCurrentWindow() {
		windowStack.push(Constant.WEBDRIVER.getWindowHandle());
	}
	
	public static void switchBack() {
		Constant.WEBDRIVER.switchTo().window(windowStack.pop());
	}
}
