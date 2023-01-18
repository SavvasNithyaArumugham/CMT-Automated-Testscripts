package com.pearson.automation.cmt.pages;

import org.jboss.aerogear.security.otp.Totp;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.Status;

/**
 * SignOnPage class
 * 
 * @author Cognizant
 */
public class CmtLoginPage extends ReusableLibrary {
	private String userfield = ".//*[@id='userNameInputId']";
	private String userNameInputXpath = ".//*[@id='username']";
	private String passwordInputXpath = ".//*[@id='password']";
	private String loginButtonXpath = ".//*[@class='button-bar']//button[contains(.,'Continue')]";
	private String firstWinHandle;
	private String secondWinHandle;

	/**
	 * Constructor to initialize the page
	 * 
	 * @param scriptHelper The {@link ScriptHelper} object passed from the
	 *                     {@link DriverScript}
	 */
	public CmtLoginPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	// Method for Login
	public void login() {
		String userName = dataTable.getData("General_Data", "Username");
		String password = dataTable.getData("General_Data", "Password");
		performLogin(userName, password);
		
	}

	// Perform Login
	public void performLogin(String userName, String password) {

		UIHelper.waitForVisibilityOfEleByXpath(driver, userNameInputXpath);
		UIHelper.highlightElement(driver,userNameInputXpath);
		driver.findElement(By.id("username")).sendKeys(userName);
		driver.findElement(By.id("password")).clear();
		UIHelper.highlightElement(driver,passwordInputXpath);
		driver.findElement(By.id("password")).sendKeys(password);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch blocks
			e.printStackTrace();
		}
	
		UIHelper.highlightElement(driver,loginButtonXpath);
		driver.findElement(By.xpath(loginButtonXpath)).click(); 
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
	/*	
	// Auth0 authentication-Code to get the one time code(six digit number)
		String otpKeyStr ="EMYTOTRTGBRHE3LDGQ6DM4ZZIBTGQMTS";
		Totp totp = new Totp(otpKeyStr);
		String twoFactorCode = totp.now();
		System.out.println("Two Factor code :"+twoFactorCode);
		
		String CodeXpath ="//*[@id='code']";
		WebElement webelement = driver.findElement(By.xpath(CodeXpath));
		webelement.sendKeys(twoFactorCode);
		String continewButtonXpath = ".//*[@class='button-bar']//button[contains(.,'Continue')]";
		driver.findElement(By.xpath(continewButtonXpath)).click(); */
		
		UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loginButtonXpath);
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		report.updateTestLog("Login", "Enter login credentials: "
				+ "Username = " + userName, Status.DONE);
		
			
		
		/*
		
		WebElement ele = driver.findElement(By.xpath("//*[@id=\"loginButton\"]"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", ele);
		UIHelper.waitFor(driver);
		WebElement UsernameTextbox = driver.findElement(By.xpath("//*[@id=\"userNameInputId\"]"));		
		UsernameTextbox.sendKeys(userName);
		UIHelper.waitFor(driver);
		
		UIHelper.waitFor(driver);
		WebElement PasswdTextbox = driver.findElement(By.xpath("//*[@id=\"password\"]"));		
		PasswdTextbox.clear();
		PasswdTextbox.sendKeys(password);
		
		UIHelper.waitFor(driver);
		WebElement login = driver.findElement(By.xpath("//*[@id=\"buttonOk\"]"));
		login.click();		
		UIHelper.waitFor(driver);
		report.updateTestLog("Login", "Enter login credentials: " + "Username = " + userName, Status.DONE);*/
		
	}

	// Close browser
	public CmtLoginPage logout() {
		driver.close();
		// driver.findElement(By.linkText("SIGN-OFF")).click();
		// report.updateTestLog("Logout", "Click the sign-off link",
		// Status.DONE);
		return new CmtLoginPage(scriptHelper);
	}

	public CmtHomePage loginAsValidUser() {
		login();
		return new CmtHomePage(scriptHelper);
	}

	public boolean verifyNetworkConnection() {
		UIHelper.waitForPageToLoad(driver);

		if (driver.findElement(By.id("UsernameTextBox")).isDisplayed()) {
			return true;
		} else {
			return false;
		}

	}
}