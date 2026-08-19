package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Reusables {
	private WebDriver driver;
	private WebDriverWait wait;
	Actions action;
	JavascriptExecutor js;
	
	public Reusables(WebDriver driver){
		this.driver = driver;
		this.wait = new WebDriverWait(driver,Duration.ofSeconds(30));
	}
	
	public String getProperty(String property) throws IOException {
		Properties p = new Properties();
		String path = Paths.get(System.getProperty("user.dir"), "src",
	             "test","resources","configure.properties").toString();
		FileInputStream fis = new FileInputStream(path);
		p.load(fis);
		return p.getProperty(property);
	}
	
	public void Type(WebElement element,String text) {
		WebElement e = WaitForElement(element);	
		e.clear();
		e.sendKeys(text);
	}
	
	public void Clear(WebElement element) {
		WebElement e = WaitForElement(element);	
		e.clear();
	}
	
	public void Click(WebElement element) {
		WebElement e = WaitForElement(element);	
		e.click();
	}
	
	public boolean IsDisplayed(WebElement element) {
		WebElement e = WaitForElement(element);	
		return e.isDisplayed();
	}
	
	public String getText(WebElement element) {
		WebElement e = wait.until(ExpectedConditions.elementToBeClickable(element));
		return e.getText();
	}
	
	public void selectDropdown(WebElement element,String title)
	{
		WebElement e = WaitForElement(element);
		Select s = new Select(e);
		List<WebElement> options = s.getOptions();
		
		for(WebElement el:options)
		{
			if(getText(el).equals(title))
			{
				Click(el);
				break;
			}
		}	
	}
	
	public void actionClick(WebElement element) {
		WebElement e = WaitForElement(element);
		action = new Actions(driver);
		action.click(e).build().perform();
	}
	
	public void jsClick(WebElement element) {
		WebElement e = WaitForElement(element);
		js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", e);
	}
	
	public WebElement WaitForElement(WebElement element) {
		return wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public WebElement FluentWait(WebElement element) {
		
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(5))
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
		
		return wait.until(ExpectedConditions.visibilityOf(element));
		
	}
	
	public void Screenshot(String methodName) {
		
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
		String timestamp = now.format(formatter);

		String path = Paths.get(System.getProperty("user.dir"), "screenshots", timestamp 
						+ "_" + methodName + ".png").toString();
		
		TakesScreenshot ts = (TakesScreenshot)DriverManager.getDriver();
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(path);
		
		src.renameTo(dest);
	}

}
