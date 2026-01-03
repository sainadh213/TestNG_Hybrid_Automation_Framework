package com.testNG.hybrid.automation.framework.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	protected WebDriver driver;
	protected WebDriverWait wait;
	
	public BasePage(WebDriver driver)
	{
		this.driver=driver;
		wait=new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	 protected WebElement waitForVisible(By locator) {
	        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	    }

	    protected WebElement waitForClickable(By locator) {
	        return wait.until(ExpectedConditions.elementToBeClickable(locator));
	    }

	    protected void click(By locator) {
	        waitForClickable(locator).click();
	    }

	    protected void type(By locator, String text) {
	        WebElement ele = waitForVisible(locator);
	        ele.clear();
	        ele.sendKeys(text);
	    }

	    protected String getText(By locator) {
	        return waitForVisible(locator).getText();
	    }
	    protected boolean isDisplayed(By locator) {
	        try {
	            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
	        } catch (Exception e) {
	            return false;
	        }
	    }
	    protected By findELementByDynamicXpath(String xpathTemplate, Object... args) {
	        String finalXpath = String.format(xpathTemplate, args);
	        return By.xpath(finalXpath);
	    }

	    protected By findElementDynamically(String locatorType, String template, Object... args) {
	        String value = String.format(template, args);

	        switch (locatorType.toLowerCase()) {
	            case "id":
	                return By.id(value);

	            case "name":
	                return By.name(value);

	            case "classname":
	                return By.className(value);

	            case "css":
	            case "cssselector":
	                return By.cssSelector(value);

	            case "xpath":
	                return By.xpath(value);

	            case "tagname":
	                return By.tagName(value);

	            case "linktext":
	                return By.linkText(value);

	            case "partiallinktext":
	                return By.partialLinkText(value);
	            default:
	                throw new IllegalArgumentException("Invalid locator type: " + locatorType);
	        }
	        //return null; // since you don't want default
	    }

	    //=====================================================================================
	    /* ========== 1. Dynamic XPath helpers ========== */

	  /*  // Build a By from a template like "//div[text()='%s']"
	    protected By byXpath(String xpathTemplate, Object... args) {
	        String finalXpath = String.format(xpathTemplate, args);
	        return By.xpath(finalXpath);
	    }

	    // Single element with dynamic xpath
	    protected WebElement find(String xpathTemplate, Object... args) {
	        return wait.until(
	                ExpectedConditions.visibilityOfElementLocated(byXpath(xpathTemplate, args))
	        );
	    }

	    // Multiple elements with dynamic xpath
	    protected List<WebElement> findAll(String xpathTemplate, Object... args) {
	        return wait.until(
	                ExpectedConditions.visibilityOfAllElementsLocatedBy(byXpath(xpathTemplate, args))
	        );
	    }*/
}
