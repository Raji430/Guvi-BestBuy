package com.test;

import static org.testng.Assert.assertEquals;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.pages.HomePage;
import com.pages.LoginPage;
import com.pages.SignUp;
import com.utils.Utility;

public class BestBuy extends Utility
{
	@BeforeMethod
	public void launchSite() throws Exception
	{
		Utility.browserLaunch(Utility.getData("Browser"));
		Utility.loadSite(Utility.getData("URL")); 
		HomePage.countrySelection();
	}
	
	@BeforeTest
	public void brokenUrl_TC01()
	{	
		int respCode = Utility.brokenURL(Utility.getData("URL"), Utility.getData("RespCode"));		
		
        if(respCode >= 400)
        {
          System.out.println(Utility.getData("URL")+" is a broken link");
        }
        else
        {
          System.out.println(Utility.getData("URL")+" is a valid link");
        }        
    } 
	
	@Test(priority=1)
	public void login_InvalidEmail_TC01() throws Exception
	{
		String actLoginInvalidMsg = null;
		String email = Utility.getData("InvalidEmail");
		String password = Utility.getData("Password");		
		String expLoginInvalidMsg = "Please enter a valid email address.";
		
		HomePage.signIn(email, password);
		actLoginInvalidMsg = LoginPage.loginInvalidCredValidation(expLoginInvalidMsg);
		assertEquals(actLoginInvalidMsg, expLoginInvalidMsg);
	}
	
	@Test(priority=2)
	public void signUp_MandatoryCheck_TC02() throws Exception
	{
		HomePage.signUp();
		SignUp.createAccNoData();
		
		Map<String, String> allMandFieldList = new HashMap<String, String>();
		allMandFieldList.put("Please enter your first name.", "firstName");
		allMandFieldList.put("Please enter your last name.", "lastName");
		allMandFieldList.put("Please enter a valid email address.", "email");
		allMandFieldList.put("Please enter a strong password.", "fld-p1");
		allMandFieldList.put("Please reenter your password.", "reenterPassword");
		allMandFieldList.put("Please enter a valid mobile phone number.", "phone");		
		
		for (Map.Entry<String,String> entry : allMandFieldList.entrySet())  
		{
			String actValMandMsg = SignUp.mandatoryFields(entry.getValue()).toString();
			assertEquals(actValMandMsg, entry.getKey());			
		} 	
	} 
	@Test(priority=3)
	public void login_MandPassword_TC03() throws Exception
	{
		String actLoginInvalidMsg = null;
		String email = Utility.getData("EmailId");
		String password = "";		
		String expLoginInvalidMsg = "Please enter your password.";
		
		HomePage.signIn(email, password);
		actLoginInvalidMsg = LoginPage.loginInvalidCredValidation(expLoginInvalidMsg);
		assertEquals(actLoginInvalidMsg, expLoginInvalidMsg);
	} 
	
	@Test(priority=4)
	public void signUp_TC04() throws Exception
	{	
		String actAlertValMsg = null;
		String expAlertMsg = "An account with this email already exists."
				+ "\n" + "Please sign in or use a different email address.";
		
		String expAlertMsg1 = "Sorry, there was a problem creating your account."
				+ "\n" + "Please review your info and try again.";
		
		HomePage.signUp();		
		actAlertValMsg = SignUp.createAccount();				
		
		if(actAlertValMsg.equals(expAlertMsg))
		{
			assertEquals(actAlertValMsg, expAlertMsg);
		}
		else
		{
			assertEquals(actAlertValMsg, expAlertMsg1);
		}
    } 
	
	@Test(priority=5)
	public void signIn_TC05() throws Exception
	{	
		String actLoginSucMsg = null;
		String email = Utility.getData("EmailId");
		String password = Utility.getData("Password");
		String expLoginSucMsg = "Sorry, something went wrong. Please try again.";
		
		HomePage.signIn(email, password);
		actLoginSucMsg = LoginPage.loginSuccVal(expLoginSucMsg);
		assertEquals(actLoginSucMsg, expLoginSucMsg);
    } 

	@Test(priority=6)
	public void allMenuValidation_TC06() throws Exception
	{		
		Map<String, String> mapOfList = new HashMap<String, String>();
		mapOfList.put("Top Deals and Featured Offers on Electronics - Best Buy", "Top Deals");
		mapOfList.put("Deal of the Day: Electronics Deals - Best Buy", "Deal of the Day");
		mapOfList.put("Yes, Best Buy Sells That – Best Buy", "Yes, Best Buy Sells That");
		mapOfList.put("My Best Buy Memberships", "My Best Buy Memberships");
		mapOfList.put("Best Buy Credit Card: Rewards & Financing", "Credit Cards");
		mapOfList.put("Gifts Cards and E-Gift Cards - Best Buy", "Gift Cards");
		mapOfList.put("Gift Ideas 2024: Best Gifts to Give This Year - Best Buy", "Gift Ideas");
		
		for (Map.Entry<String,String> entry : mapOfList.entrySet())  
		{
			String actMenuLink = HomePage.valAllMenu(entry.getValue()).toString();
			assertEquals(actMenuLink, entry.getKey());			
		} 	
    } 	
	
	@Test(priority=7)
	public void bottomLinksValidation_TC07() throws Exception
	{		
		List<String> bottomLinks = new ArrayList<>();
		bottomLinks.add("Accessibility");
		bottomLinks.add("Terms & Conditions");
		bottomLinks.add("Privacy");
		bottomLinks.add("Interest-Based Ads");
		
		for(String expBottomLink: bottomLinks)
		{				
			String actBottomLink = HomePage.bottomLinks(expBottomLink).toString();
			assertEquals(actBottomLink, expBottomLink);
		}
    } 	
	
	@Test(priority=8)
	public void search_AddItem_Cart_TC08() throws Exception
	{
		String expPaymentValMsg = "Request failed because of network connection";
		HomePage.searchItem("Apple - Pre-Owned iPhone 13 5G 128GB (Unlocked) - Blue");
		String actPaymentValMsg = HomePage.goToCart(expPaymentValMsg);
		assertEquals(actPaymentValMsg, expPaymentValMsg);
	}
		
	@Test(priority=9)
	public void shopDept_AddItem_Cart_TC09() throws Exception
	{
		String expPaymentValMsg = "Request failed because of network connection";
		HomePage.shopByDept();
		String actPaymentValMsg = HomePage.goToCart(expPaymentValMsg);
		assertEquals(actPaymentValMsg, expPaymentValMsg);
	}
	
	@Test(priority=10)
	public void shopBrand_AddItem_Cart_TC10() throws Exception
	{
		String expPaymentValMsg = "Request failed because of network connection";
		HomePage.shopByBrand();
		String actPaymentValMsg = HomePage.goToCart(expPaymentValMsg);
		assertEquals(actPaymentValMsg, expPaymentValMsg);
	}
	
	@AfterMethod
	public void closeSite()
	{
		Utility.closeCurrentWindow(); 
	}
}