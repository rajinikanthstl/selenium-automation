package test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.CreatePage;
import pages.HomePage;
import pages.LoginPage;
import pages.SearchPage;
import utilities.Log;

public class CreateEmployee extends BaseClass{
	
	@Test
	void createEmployee() throws IOException, InterruptedException {
		try {
			
			LoginPage login = new LoginPage(driver);
			HomePage home = new HomePage(driver);
			CreatePage create = new CreatePage(driver);
			SearchPage search = new SearchPage(driver);
			
			Log.info("login to the appllication");
			login.Login();
			Assert.assertTrue(utility.IsDisplayed(home.welcome));
			Log.pass("logged into the application");
			
			Log.info("clicking on employee->create for navigating to the create page");
			utility.Click(home.employee);
			utility.Click(home.create);
			
			Assert.assertTrue(utility.IsDisplayed(home.employee));
			Log.pass("navigated to create page");
			Log.info("filling the employee form and clicking on save");
			
			create.CreateEmployee("Rajesh", "Chatla","rajesh@gmail.com", 
					"8873876362", "Hyderabad","India","Hyderabad","QA-Automation");		
			
			Assert.assertTrue(utility.IsDisplayed(create.success));
			Log.pass("created a new employee record");
			Assert.assertTrue(utility.IsDisplayed(search.search));
			Log.pass("navigated to search page");
			
		}catch(Exception e) {
			Log.fail("test failed:"+e.getMessage());
		}
		
	}

}
