package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Log;
import utilities.Reusables;

public class CreatePage {
	
	Reusables utility;
	public CreatePage(WebDriver driver){
		PageFactory.initElements(driver,this);
		utility = new Reusables(driver);
	}
	
	@FindBy(css = "h3")
	public WebElement details;
	
	@FindBy(name = "FirstName")
	public WebElement firstName;
	
	@FindBy(name = "LastName")
	public WebElement lastName;
	
	@FindBy(name = "EmailId")
	public WebElement email;
	
	@FindBy(name = "MobileNo")
	public WebElement mobile;
	
	@FindBy(css = "#DOB")
	public WebElement dob;
	
	@FindBy(css = "#rdbMale")
	public WebElement male;
	
	@FindBy(css = "#rdbFemale")
	public WebElement female;
	
	@FindBy(css = "#Address")
	public WebElement address;
	
	@FindBy(css = "#CountryId")
	public WebElement country;
	
	@FindBy(css = "select[name='CityId']")
	public WebElement city;
	
	@FindBy(css = "input[name='chkOtherCity']")
	public WebElement otherCityChk;
	
	@FindBy(css = "input[name='OtherCity']")
	public WebElement otherCity;
	
	@FindBy(xpath = "//input[@type='checkbox'][contains(@id,'chkSkill')]")
	public List<WebElement> skills;
	
	@FindBy(xpath="//input[@type='checkbox'][contains(@id,'chkSkill')]")
	public List<WebElement> skill;
	
	@FindBy(xpath = "//button[normalize-space()='Save']")
	public WebElement save;
	
	@FindBy(xpath="//a[normalize-space()='Cancel']")
	WebElement cancel;
	
	@FindBy(css = "#toast-container .toast-success")
	public WebElement success;
	
	@FindBy(css = ".toast-error")
	public WebElement error;
	
	@FindBy(xpath = "//input[@type='checkbox'][contains(@id,'chkSkill')]/parent::div")
	public List<WebElement> skillDiv;
	
	@FindBy(xpath = "//input[@type='checkbox'][@value='5']")
	public WebElement aws;
	
	@FindBy(xpath = "//input[@type='checkbox'][@value='3']")
	public WebElement devops;
	
	@FindBy(xpath = "//input[@type='checkbox'][@value='2']")
	public WebElement fullstack;
	
	@FindBy(xpath = "//input[@type='checkbox'][@value='4']")
	public WebElement middleware;
	
	@FindBy(xpath = "//input[@type='checkbox'][@value='1']")
	public WebElement qa;
	
	@FindBy(xpath = "//input[@type='checkbox'][@value='6']")
	public WebElement websrvice;
	
	
	public void CreateEmployee(String fName,String lName,String Email,
			String Mobile,String Address,String Country,String City,String Skill) throws InterruptedException {
		
		Log.info("entering first name");
		utility.Type(firstName,fName);
		Log.info("entering last name");
		utility.Type(lastName,lName);
		Log.info("entering email");
		utility.Type(email,Email);
		Log.info("entering mobile number");
		utility.Type(mobile,Mobile);
		Log.info("entering address");
		utility.Type(address,Address);
		Log.info("selecting country from dropdown");
		utility.selectDropdown(country,Country);
		Log.info("selecting city from dropdown");
		utility.selectDropdown(city,City);
		Log.info("checking the skill checkbox");
		switch(Skill) {
			case "AWS" : utility.Click(aws); break;
			case "DevOps" : utility.Click(devops); break;
			case "Full Stack Developer" : utility.Click(fullstack); break;
			case "Middleware" : utility.Click(middleware); break;
			case "QA-Automation" : utility.Click(qa); break;
			case "WebServices" : utility.Click(websrvice);break;
		}
		Log.info("clicking on save button");
		utility.Click(save);
	}
}
