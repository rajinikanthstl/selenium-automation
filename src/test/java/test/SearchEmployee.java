package test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.HomePage;
import pages.LoginPage;
import pages.SearchPage;
import utilities.Log;
import utilities.RetryFailed;

public class SearchEmployee extends BaseClass{
	
	@Test(retryAnalyzer=RetryFailed.class)
	void searchEmployee() throws IOException, InterruptedException {
		try {
			
			LoginPage login = new LoginPage(driver);
			HomePage home = new HomePage(driver);
			SearchPage search = new SearchPage(driver);
			
			Log.info("login to the application");
			login.Login();
			Assert.assertTrue(utility.IsDisplayed(home.welcome));
			Log.pass("logged into the application");
			
			Log.info("navigating to the search page");
			search.Click(home.employee);
			search.Click(home.search);
			
			Assert.assertTrue(utility.IsDisplayed(search.search));
			Log.pass("navigated to the search page");
			Log.info("searching for employee record");
			
			search.SearchEmployee("Rajesh");
			
			Assert.assertEquals(utility.getText(search.searchName),"Rajesh");
			Log.pass("employee record found in the search");
			
			home.logOut();
			Log.info("logout from application");
		}catch(Exception e) {
			Log.info("failed test:"+e.getMessage());
		}
	}
}
