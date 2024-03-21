package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.utils.Utility;

public class SignUp extends Utility
{	
	// Sign Up form details to create account
	public static String createAccount() throws Exception 
	{	
		//SignUp Form WebElements
		WebElement firstName = driver.findElement(By.id("firstName"));
		WebElement lastName = driver.findElement(By.id("lastName"));
		WebElement email = driver.findElement(By.id("email"));
		WebElement password = driver.findElement(By.id("fld-p1"));
		WebElement repassword = driver.findElement(By.id("reenterPassword"));
		WebElement mobile = driver.findElement(By.id("phone"));
		WebElement createAccount = findElementByXpath("//button[text()='Create an Account']");
		
		//SignUp Form Details		
		sendInput(firstName, Utility.getData("FirstName"));
		sendInput(lastName, Utility.getData("LastName"));
		sendInput(email, Utility.getData("EmailId"));
		sendInput(password, Utility.getData("Password"));
		sendInput(repassword, Utility.getData("ConfirmPassword"));
		sendInput(mobile, Utility.getData("MobileNumber"));		
		Thread.sleep(500);		
		scrollToElement(createAccount);		
		createAccount.click();		

		//Return the message to validate after clicking on create account
		WebElement alertMsg = findElementByXpath("//div[contains(@class ,'c-alert-content')]");
		String successMsg = alertMsg.getText().toString();
		return successMsg;
	}
	
	//SignUp Form by without entering inputs
	public static void createAccNoData() throws Exception 
	{	
		WebElement createAccount = findElementByXpath("//button[text()='Create an Account']");
		Thread.sleep(2000);		
		scrollToElement(createAccount);		
		createAccount.click();	
		Thread.sleep(1000);
	}
	
	//Return SignUp form mandatory fields validation message 
	public static String mandatoryFields(String inputField) throws Exception
	{
		WebElement mandtoryValMsg = driver.findElement(By.xpath("//div[contains(@id ,'"+inputField+"')]"));
		String valMandMsg = mandtoryValMsg.getText();
		
		return valMandMsg;
	}
}
