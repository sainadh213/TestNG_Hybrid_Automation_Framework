package com.testNG.hybrid.automation.framework.utils;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testNG.hybrid.automation.framework.base.BaseTest;



public class WebDriverUtil {
	WebDriverWait wait;
	Actions actions;

	public void enterText(WebElement element, String text) {
		wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.clear();
		element.sendKeys(text);
	}

	public void clickOnElement(WebElement element) {
		wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();

	}

	public void waitForAnElementLocated(WebElement element, int time) {
		wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(time));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	


	public void mouseOver(WebElement element) {
		actions = new Actions(BaseTest.getDriver());
		actions.moveToElement(element).perform();
	}

	public void ScrollToElement(WebElement element) {
		JavascriptExecutor javaScriptExecutor = (JavascriptExecutor) BaseTest.getDriver();

		javaScriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);

	}

	public void enterTextThroughActionsClass(String text) {
		actions = new Actions(BaseTest.getDriver());
		actions.sendKeys(text).perform();
	}

	public void clearTextThroughActionsClass() {
		actions = new Actions(BaseTest.getDriver());
		actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
		actions.sendKeys(Keys.BACK_SPACE).perform();
	}
	public String getChildWindow(String parentWindow) {
	    for (String win : BaseTest.getDriver().getWindowHandles()) {
	        if (!win.equals(parentWindow)) {
	            return win;   // return first child window
	        }
	    }
	    return null;
	}


}
