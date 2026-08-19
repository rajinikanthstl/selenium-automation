package test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.HomePage;
import pages.LoginPage;
import utilities.DataDrivenUtil;
import utilities.Log;

public class LoginTest extends BaseClass{
	
	@DataProvider(name="logindata")
	public Object[][] loginData() throws Exception, IOException{
		String path = System.getProperty("user.dir")+"\\src\\test\\resources\\testdata.xlsx";
		String sheet = "Sheet1";
		return DataDrivenUtil.getData(path,sheet);
	}
	
	@Test(dataProvider="logindata")
	void loginTest(String testcase,String username,String password,String validity) throws IOException, InterruptedException {
		
		try {
			
			LoginPage login = new LoginPage(driver);
			HomePage home = new HomePage(driver);
			
			Log.info("loging in with email: "+username+" and passsword: "+password);
			login.Login(username,password);
			
			if(validity.equals("valid")) {
				Assert.assertTrue(utility.IsDisplayed(home.welcome));
				Log.pass("login succesful and verified");
				home.logOut();	
				Log.info("loged out");
				
			}else {
				Assert.assertTrue(utility.IsDisplayed(login.errorToast));
				Log.pass("error message verified");
			}
		}catch(Exception e) {
			Log.fail("test failed!"+e.getLocalizedMessage());
		}
	}
}
