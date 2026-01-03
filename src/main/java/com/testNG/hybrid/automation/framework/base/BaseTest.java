package com.testNG.hybrid.automation.framework.base;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.testNG.hybrid.automation.framework.utils.FileUtil;



public class BaseTest {
	public static WebDriver driver;
	FileUtil fileUtil = new FileUtil();;

	@BeforeClass(alwaysRun = true)
	public void launch() throws Throwable {
		String browser = fileUtil.readDataFromPropertiesFile("browser");
		if (browser.equalsIgnoreCase("chrome")) {

			driver = new ChromeDriver();
		}

		else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String url = fileUtil.readDataFromPropertiesFile("url");
		driver.get(url);

	}

	public static String captureScreenshot(String testName) throws Throwable {
		/*
		 * SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd_HHmmss"); Date dt=new
		 * Date(); String timeStamp = format.format(dt);
		 */

		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "\\Screenshots\\" + testName + "_" + timeStamp + ".png";
		File dest = new File(path);
		FileHandler.copy(src, dest);
		return path;

	}

	@AfterClass(alwaysRun = true)
	public void closeApp() {
		if (driver != null)
			driver.quit();
	}
}
