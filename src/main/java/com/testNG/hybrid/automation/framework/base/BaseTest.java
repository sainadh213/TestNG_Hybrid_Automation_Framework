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
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	FileUtil fileUtil = new FileUtil();

	public static WebDriver getDriver() {
		return driver.get();
	}

	@BeforeClass(alwaysRun = true)
	public void launch() throws Throwable {
		String browser = fileUtil.readDataFromPropertiesFile("browser");
		if (browser.equalsIgnoreCase("chrome")) {

			driver.set(new ChromeDriver());
		}

		else if (browser.equalsIgnoreCase("firefox")) {
			driver.set(new FirefoxDriver());
		}

		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String url = fileUtil.readDataFromPropertiesFile("url");
		getDriver().get(url);

	}

	protected static String getTimeStamp() {
		return new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(new Date());
	}

	public static String captureScreenshot(String testName) throws Throwable {
		/*
		 * SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd_HHmmss"); Date dt=new
		 * Date(); String timeStamp = format.format(dt);
		 */

		WebDriver currentDriver = getDriver();
		if (currentDriver == null) {
			return null;
		}

		TakesScreenshot ts = (TakesScreenshot) currentDriver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "\\Screenshots\\" + testName + "_" + getTimeStamp() + ".png";
		File dest = new File(path);
		FileHandler.copy(src, dest);
		return path;

	}

	@AfterClass(alwaysRun = true)
	public void closeApp() {
		if (getDriver() != null)
			getDriver().quit();
		driver.remove();

	}
}
