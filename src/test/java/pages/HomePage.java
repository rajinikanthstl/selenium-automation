package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Reusables;

public class HomePage {
	
	Reusables utility;
	public HomePage(WebDriver driver){
		PageFactory.initElements(driver,this);
		utility = new Reusables(driver);
	}
	
	@FindBy(tagName = "h1")
	public WebElement welcome;
	
	@FindBy(css = "a[href='/Account/SignOut']")
	public WebElement logOut;
	
	@FindBy(linkText = "Employee")
	public WebElement employee;
	
	@FindBy(css = "a[href='/Employee/Create']")
	public WebElement create;
	
	@FindBy(css = "a[href='/Employee/Search']")
	public WebElement search;

	public void logOut() throws InterruptedException {
		utility.Click(logOut);	
	}

}
