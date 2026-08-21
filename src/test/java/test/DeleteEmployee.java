package test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.HomePage;
import pages.LoginPage;
import pages.SearchPage;
import utilities.Log;

public class DeleteEmployee extends BaseClass{
	
	@Test(description="delete an employee")
	void deleteEmployee() throws IOException, InterruptedException {
		try {
			
			LoginPage login = new LoginPage(driver);
			HomePage home = new HomePage(driver);
			SearchPage delete = new SearchPage(driver);
			
			Log.info("loging into the application");
			login.Login();
			Assert.assertTrue(utility.IsDisplayed(home.welcome));
			Log.pass("logged into the application");
			
			Log.info("clicking on employee->search for navigating to search page");
			utility.Click(home.employee);
			utility.Click(home.search);
			
			Assert.assertTrue(utility.IsDisplayed(delete.search));
			Log.pass("navigated to search page");
			Log.info("deleting the employee");
			
			delete.DeleteEmployee("Rajesh");
			
			Assert.assertTrue(utility.IsDisplayed(delete.success));
			Log.pass("deleted employee successfully");
			
			Log.info("loging out of application");
			home.logOut();
			
		}catch(Exception e) {
			Log.fail("test failed:"+e.getMessage());
		}
	}

}
