package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Log;
import utilities.Reusables;

public class SearchPage {
	
	Reusables utility;
	public SearchPage(WebDriver driver){
		PageFactory.initElements(driver,this);
		utility = new Reusables(driver);		
	}
	
	@FindBy(css = "h3")
	public WebElement search;
	
	@FindBy(css = "input#Name")
	public WebElement name;
	
	@FindBy(css = "input#MobileNo")
	public WebElement mobile;
	
	@FindBy(css = "button#btnSearch")
	public WebElement searchBtn;
	
	@FindBy(css = "button#btnClear")
	public WebElement clearBtn;
	
	@FindBy(css = "table#tblEmployee")
	public WebElement table;
	
	@FindBy(xpath = "(//table//tr[1])[2]/td[1]")
	public WebElement searchName;
	
	@FindBy(css = "a[title='Edit']")
	public WebElement edit;
	
	@FindBy(css = "a[title='Delete']")
	public WebElement delete;
	
	@FindBy(xpath = "//button[text()='Yes']")
	public WebElement confirmYes;
	
	@FindBy(xpath = "//button[text()='Cancel']")
	public WebElement confirmCancel;
	
	@FindBy(css = "a[aria-label='First']")
	public WebElement first;
	
	@FindBy(css = "(//a[@aria-label='Last'])[1]")
	public WebElement last;
	
	@FindBy(css = "#toast-container .toast-success")
	public WebElement success;
	
	@FindBy(css = ".toast-close-button")
	public WebElement toastClose;
	
	public void Click(WebElement element) {
		utility.Click(element);
	}
	
	public void SearchEmployee(String ename) {
		Log.info("entering employee name to search");
		utility.Type(name, ename);
		Log.info("clicking on search button");
		utility.Click(searchBtn);
	}
	
	public void DeleteEmployee(String ename) throws InterruptedException {
		Log.info("entering employee name to delete");
		utility.Type(name, ename);
		Log.info("clicking on search button");
		utility.Click(searchBtn);
		Log.info("clicking on delete button");
		utility.FluentWait(delete);
		utility.Click(delete);
		Log.info("clicking on yes");
		utility.Click(confirmYes);
		Log.info("closing the toast message");
		utility.Click(toastClose);
	}
}
