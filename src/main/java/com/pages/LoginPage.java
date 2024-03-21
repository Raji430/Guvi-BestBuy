package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.utils.Utility;

public class LoginPage extends Utility
{		
	//Login or SignIn with valid inputs
	public static void login(String email, String passwrd) throws Exception
	{	
		WebElement emailId = driver.findElement(By.id("fld-e"));
		WebElement password = driver.findElement(By.id("fld-p1"));
		WebElement sigIn = findElementByXpath("//button[text()='Sign In']");
		
		sendInput(emailId, email);		
		
		sendInput(password, passwrd);	
		
		Thread.sleep(1000);
		
		clickOnElementActions(sigIn);			
	}
	
	//Validation of Login or SignIn with valid credentials
	public static String loginSuccVal(String loginSucMsg) throws Exception
	{
		String loginSuccessValMsg = null;
		WebElement loginSuccess = driver.findElement(By.xpath("//div[text()='"+loginSucMsg+"']"));
		loginSuccessValMsg = loginSuccess.getText();
		
		return loginSuccessValMsg;		
	}
	
	//Validation of Login or SignIn with invalid credentials
	public static String loginInvalidCredValidation(String loginInvalidCredMsg) throws Exception
	{
		String loginSuccessValMsg = null;
		WebElement loginSuccess = driver.findElement(By.xpath("//div[@class='tb-validation']//p[text()='"+loginInvalidCredMsg+"']"));		
		loginSuccessValMsg = loginSuccess.getText();
		
		return loginSuccessValMsg;		
	}
	
}
