package com.testNG.hybrid.automation.framework.testscripts;

import java.io.File;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;

public class StaleElement {
	@Test
	public void login() throws Throwable
	{
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		/*WebElement loginBtn = driver.findElement(By.linkText("Login"));
		driver.navigate().refresh();
		loginBtn.click();*/
		List<WebElement> options = driver.findElements(By.xpath("//a[@class='dropdown-toggle' and text()='Desktops' or text()='Laptops & Notebooks' or text()='Components']"));
	for(WebElement option:options)
	{
		System.out.println(option.getText());
		File src = option.getScreenshotAs(OutputType.FILE);
		File file=new File("C:\\Users\\SaiNadh\\eclipse-workspace\\automation-project\\Screenshots\\"+option.getText()+".png");
		FileHandler.copy(src, file);
	}
	/*TakesScreenshot ts=(TakesScreenshot)driver;
	
	File src = ts.getScreenshotAs(OutputType.FILE);
	File dest=new File("C:\\Users\\SaiNadh\\eclipse-workspace\\automation-project\\Screenshots\\test.png");
	FileHandler.copy(src, dest);*/
	
	}
	
}