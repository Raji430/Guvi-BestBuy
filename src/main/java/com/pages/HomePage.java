package com.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utils.Utility;

public class HomePage extends Utility {

	// WebElement to choose the country
	public static WebElement countrySel = new WebDriverWait(driver, Duration.ofSeconds(20))
			.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@lang='en']//a[@class='us-link']")));

	// Choose Country
	public static void countrySelection() throws Exception {
		countrySel = findElementByXpath("//div[@lang='en']//a[@class='us-link']");
		clickOnElementActions(countrySel);
	}

	// Choose SignIn option and navigates to SignIn page for login
	public static void signIn(String email, String password) throws Exception {

		WebElement acccountBtn = driver.findElement(By.id("account-menu-account-button"));
		waitForElement(acccountBtn);
		clickOnElementActions(acccountBtn);

		WebElement signIn = findElementByXpath("//div[@class='account-menu']//a[text()='Sign In']");
		clickOnElementActions(signIn);

		LoginPage.login(email, password);
	}

	// Choose Create Account for SignUp
	public static void signUp() throws Exception {
		WebElement acccountBtn = driver.findElement(By.id("account-menu-account-button"));
		clickOnElementActions(acccountBtn);

		WebElement createAccBtn = findElementByXpath("//div[@class='account-menu']//a[text()='Create Account']");
		clickOnElementActions(createAccBtn);
	}

	// Returns the page titles of all menu
	public static String valAllMenu(String menuLink) throws Exception {
		WebElement allMenuLinks = findElementByXpath("//a[text()='" + menuLink + "']");
		clickOnElementActions(allMenuLinks);
		String actTopDealsTitle = driver.getTitle();

		return actTopDealsTitle;
	}

	// Returns the bottom links text
	public static String bottomLinks(String bottomLink) throws Exception {
		WebElement bottomLinks = findElementByXpath("//a[text()='" + bottomLink + "']");
		String actAccessLink = bottomLinks.getText().toString();

		return actAccessLink;
	}

	// Search Item using search box
	public static void searchItem(String item) throws Exception {
		WebElement searchBoxInput = driver.findElement(By.id("gh-search-input"));
		sendInput(searchBoxInput, item);

		WebElement searchBox = findElementByXpath("//button[@class='header-search-button']");
		clickOnElementActions(searchBox);

		scrollByPixel();

		WebElement addToCart = findElementByXpath("//a[text()='" + item
				+ "']//..//..//parent::div[@class='list-item  lv']" + "//button[text()='Add to Cart']");
		clickOnElementActions(addToCart);

		contShopping();
	
		WebElement cart = findElementByXpath("//a[@title='Cart']");
		waitForElement(cart);
		clickOnElementActions(cart);
	}

	// Continue Shopping
	public static void contShopping() throws Exception {
		WebElement contShop = new WebDriverWait(driver, Duration.ofSeconds(15))
				.ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Continue shopping']")));

		contShop = findElementByXpath("//button[text()='Continue shopping']");

		clickOnElementActions(contShop);
	}

	// Shop By Dept
	public static void shopByDept() throws Exception {
		WebElement menu = findElementByXpath("//button[text()='Menu']");
		clickOnElementActions(menu);

		WebElement dept = findElementByXpath("//button[text()='Cell Phones']");
		clickOnElementActions(dept);

		WebElement brand = findElementByXpath("//button[text()='Samsung Galaxy']");
		clickOnElementActions(brand);

		WebElement item = findElementByXpath("//a[text()='Samsung Galaxy S24']");
		clickOnElementActions(item);

		WebElement model = findElementByXpath("//a[text()='Shop Galaxy S24']");
		clickOnElementActions(model);
		
		scrollByPixel();
		WebElement moveToCart = findElementByXpath("//button[contains(@class,'c-button-lg')]");
		
		waitForElement(moveToCart);
		clickonElement(moveToCart);

		WebElement cart = findElementByXpath("//a[@title='Cart']");
		waitForElement(cart);
		clickOnElementActions(cart);
	}

	// Shop By Brand
	public static void shopByBrand() throws Exception {
		WebElement menu = findElementByXpath("//button[text()='Menu']");
		clickOnElementActions(menu);

		WebElement dept = findElementByXpath("//button[text()='Brands']");
		clickOnElementActions(dept);

		WebElement brand = findElementByXpath("//a[text()='Sony']");
		clickOnElementActions(brand);

		WebElement item = findElementByXpath("//a[text()='Sony cameras']");
		clickOnElementActions(item);

		WebElement model = findElementByXpath("(//button[text()='Add to Cart'])[1]");
		clickOnElementActions(model);

		// Go to Cart
		WebElement goToCart = new WebDriverWait(driver, Duration.ofSeconds(15))
				.ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Go to Cart']")));

		goToCart = findElementByXpath("//a[text()='Go to Cart']");
		goToCart.click();
	}

	// Go To Cart and proceed for checkout and payment
	public static String goToCart(String successMsg) throws Exception {

		// Click on Checkout
		WebElement checkOut = findElementByXpath("//button[text()='Checkout']");
		waitForElement(checkOut);
		checkOut.click();

		// Continue as a Guest
		WebElement guest = new WebDriverWait(driver, Duration.ofSeconds(15))
				.ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Continue as Guest']")));

		guest = findElementByXpath("//button[text()='Continue as Guest']");
		guest.click();

		WebElement email = driver.findElement(By.id("user.emailAddress"));
		email.sendKeys(Utility.getData("EmailId"));

		WebElement mobileNum = driver.findElement(By.id("user.phone"));
		mobileNum.sendKeys(Utility.getData("MobileNumber"));

		WebElement contPayment = findElementByXpath("//span[text()='Continue to Payment Information']");
		contPayment.click();

		WebElement paymentValMsg = findElementByXpath("//span[text()='" + successMsg + "']");
		String actPaymentValMsg = paymentValMsg.getText();

		return actPaymentValMsg;
	}
}
