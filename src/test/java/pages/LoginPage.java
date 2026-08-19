package pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Log;
import utilities.Reusables;

public class LoginPage {
	
	Reusables utility;
	public LoginPage(WebDriver driver){
		PageFactory.initElements(driver,this);
		utility = new Reusables(driver);
	}
	
	@FindBy(css = "#UserName")
	public WebElement username;
	
	@FindBy(css = "#Password")
	public WebElement password;
	
	@FindBy(css = "#btnLogin")
	public WebElement loginBtn;
	
	@FindBy(css = ".toast-error")
	public WebElement errorToast;
	
	public void Login(String name,String pwd) {
		Log.info("entering username");
		utility.Type(username, name);
		Log.info("entering password");
		utility.Type(password, pwd);
		Log.info("clicking login button");
		utility.Click(loginBtn);
	}
	
	public void Login() throws IOException {
		Log.info("entering username");
		utility.Type(username, utility.getProperty("username"));
		Log.info("entering password");
		utility.Type(password, utility.getProperty("password"));
		Log.info("clicking login button");
		utility.Click(loginBtn);
	}
}
