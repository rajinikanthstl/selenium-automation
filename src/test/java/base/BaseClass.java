package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import utilities.DriverManager;
import utilities.Log;
import utilities.Reusables;

public class BaseClass {
	
	protected WebDriver driver;
	private Properties p;
//	private String BrowserName;
	private String url;
	protected Reusables utility;
	
	void getProperties() throws IOException {
		p = new Properties();
		String path = Paths.get(System.getProperty("user.dir"), "src",
	             "test","resources","configure.properties").toString();
		FileInputStream fis = new FileInputStream(path);
		p.load(fis);
//		BrowserName = p.getProperty("browser");
		url = p.getProperty("url");		
	}
		
	
	@BeforeMethod
	@Parameters({"BrowserName"})
	public void launch(@Optional("chrome") String BrowserName) throws IOException
	{		
		getProperties();
		
		Log.info("Launching the browser...");
		if(BrowserName.equalsIgnoreCase("chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			Map<String, Object> prefs = new HashMap<>();
	        prefs.put("credentials_enable_service", false);
	        prefs.put("profile.password_manager_enabled", false);
	        prefs.put("profile.password_manager_leak_detection", false);
	        options.setExperimentalOption("prefs", prefs);
//	        options.addArguments("--headless");
			driver= new ChromeDriver(options);
			Log.info("chromedriver initialized");
		}
		else if(BrowserName.equalsIgnoreCase("edge"))
		{
			EdgeOptions options = new EdgeOptions();
			options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			Map<String, Object> prefs = new HashMap<>();
	        prefs.put("credentials_enable_service", false);
	        prefs.put("profile.password_manager_enabled", false);
	        prefs.put("profile.password_manager_leak_detection", false);
	        options.setExperimentalOption("prefs", prefs);
	        options.addArguments("--headless");
			driver= new EdgeDriver(options);
			Log.info("edgedriver initialized");
		}
		else if(BrowserName.equalsIgnoreCase("firefox"))
		{
			FirefoxOptions options = new FirefoxOptions();
			options.addPreference("dom.webdriver.enabled", false);
			options.addPreference("useAutomationExtension", false);
			options.addPreference("signon.rememberSignons", false);
			options.addPreference("signon.management.page.breach-alerts.enabled", false);
			options.addArguments("-headless");
			driver=new FirefoxDriver(options);
			Log.info("firefoxdriver initialized");
		}
		
		DriverManager.setDriver(driver);
		driver.get(url);
		driver.manage().window().maximize();
		utility = new Reusables(driver);
		Log.info("Browser Launched With Url");
	}
	
	public void closeTab()
	{
		Log.info("closing the browser tab after test");
		driver.close();
	}
	
	@AfterMethod(alwaysRun = true)
	public void closeAllTabs()
	{
		if(driver!=null) {
			Log.info("closing all browser tabs after test");
			driver.quit();			
		}
		DriverManager.removeDriver();
	}
}
