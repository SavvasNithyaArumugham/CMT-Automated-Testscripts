package com.pearson.automation.alfresco.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.RobotUtil;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.Status;

/**
 * SignOnPage class
 * 
 * @author Cognizant
 */
public class AlfrescoLoginPage extends ReusableLibrary {
	private String homeTabLinkXpath = ".//*[@id='HEADER_HOME_text']/a";
	private String userfield = ".//*[@id='user-name-txt']";
	private String loaderXpath = ".//*[@id='loader']";
	private String sitesMenuLinkXpath = "//*[@class='listingTable']//a[contains(text(),'Sites')]";
//	private String pearsonlogo = "//img[@alt='Logo image']";
	private String pearsonlogo = "//div[@id='HEADER_LOGO']";
	/**
	 * Constructor to initialize the page
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public AlfrescoLoginPage(ScriptHelper scriptHelper) {
		super(scriptHelper);

		// if(!driver.getTitle().contains("Sign In")) {
		// throw new
		// IllegalStateException("Sign-on page expected, but not displayed!");
		// }
	}

	//Method for Login
	public void login() {
		
		String userName = dataTable.getData("General_Data", "Username");
		String password = dataTable.getData("General_Data", "Password");

		if(properties.getProperty("ApplicationUrl").contains("pearsoncms")){
		performLogin(userName, password);
		UIHelper.waitForVisibilityOfEleByXpath(driver, pearsonlogo);
		}else if(properties.getProperty("ApplicationUrl").contains("alfrescoppe")){
		try {
		performAWSLogin(userName, password);
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		UIHelper.waitForVisibilityOfEleByXpath(driver, pearsonlogo);
		}
		else{
		performLoginAWS(userName, password);
		UIHelper.waitForVisibilityOfEleByXpath(driver, pearsonlogo);
		}
			
		/*String userName = dataTable.getData("General_Data", "Username");
		String password = dataTable.getData("General_Data", "Password");
			
		if(properties.getProperty("ApplicationUrl").contains("pearsoncms")){
			performLogin(userName, password);
			UIHelper.waitForVisibilityOfEleByXpath(driver, pearsonlogo);
		}
		else if (properties.getProperty("ApplicationUrl").contains("alfrescoppe")){
			performLogin(userName, password);
=======
		if(properties.getProperty("ApplicationUrl").contains("pearsoncms")){
			performLogin(userName, password);
			UIHelper.waitForVisibilityOfEleByXpath(driver, pearsonlogo);
		}else if(properties.getProperty("ApplicationUrl").contains("alfrescoppe")){
			try {
				performAWSLogin(userName, password);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			UIHelper.waitForVisibilityOfEleByXpath(driver, pearsonlogo);
		}
		else{
			performLoginAWS(userName, password);
>>>>>>> 2ef7837583cbe1598b1f6535fdf2758d25bdc2eb
			UIHelper.waitForVisibilityOfEleByXpath(driver, pearsonlogo);
		}
		
		else{
			performLoginAWS(userName, password);
			UIHelper.waitForVisibilityOfEleByXpath(driver, pearsonlogo);
		}*/
	
	}
	
	/*
	 * //Added for NALS public void performAWSLogin(String userName, String
	 * password) { try { UIHelper.waitForVisibilityOfEleByXpath(driver,
	 * ".//input[@name='username']"); UIHelper.highlightElement(driver,
	 * ".//input[@name='username']");
	 * driver.findElement(By.name("username")).sendKeys(userName);
	 * 
	 * UIHelper.waitForVisibilityOfEleByXpath(driver, ".//input[@name='password']");
	 * driver.findElement(By.name("password")).sendKeys(password);
	 * UIHelper.click(driver, "//*[contains(@id, 'submit-button')]");
	 * 
	 * UIHelper.waitFor(driver); report.updateTestLog("Login",
	 * "Enter login credentials: " + "Username = " + userName, Status.DONE);
	 * }catch(Exception e) { report.updateTestLog("Login",
	 * "Enter login credentials: " + "Username = " + userName, Status.FAIL); } }
	 */

	//Method for login with valid user credentils
	public AlfrescoHomePage loginAsValidUserForPearsonMail() {
		loginForPearson();
		return new AlfrescoHomePage(scriptHelper);
	}

	//Method for login with second user
	public void loginDifferentUser() {
		String userName = dataTable.getData("General_Data", "UsernameTwo");
		String password = dataTable.getData("General_Data", "PasswordTwo");
		if(properties.getProperty("ApplicationUrl").contains("pearsoncms")){
			performLogin(userName, password);
		}else{
			performLoginAWS(userName, password);
		}
	}

	//Method for login - who are not assigned to task
	public void loginUnassignedUser() {
		String userName = dataTable.getData("General_Data", "UsernameThree");
		String password = dataTable.getData("General_Data", "PasswordThree");
		performLogin(userName, password);
	}

	//Perform Login
	public void performLogin(String userName, String password) {
		UIHelper.waitForVisibilityOfEleByXpath(driver, userfield);
		
		driver.findElement(By.id("user-name-txt")).sendKeys(userName);
		driver.findElement(By.id("pwd-txt")).clear();
		driver.findElement(By.id("pwd-txt")).sendKeys(password);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch blocks
			e.printStackTrace();
		}	
		driver.findElement(By.id("signin-button")).click(); 
		UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, "signin-button");
		UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loaderXpath);
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		report.updateTestLog("Login", "Enter login credentials: "
				+ "Username = " + userName, Status.DONE);
		
	}

	//Method for pearson mail login
	private void loginForPearson() {
		String userName = dataTable.getData("Gmail", "Username");
		String password = properties.getProperty("GMailpassword");
		performLoginForPearson(userName, password);
	}

	//Perfor Pearson Mail Login
	private void performLoginForPearson(String userName, String password) {
		try {
			driver.findElement(By.id("user-name-txt")).sendKeys(userName);
			driver.findElement(By.id("pwd-txt")).sendKeys(password);
			driver.findElement(By.id("signin-button")).click();
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			report.updateTestLog("Login into Pearson mail",
					"Enter login credentials: " + "<br /><b>Username : </b>"
							+ userName, Status.DONE);
		} catch (Exception e) {
			report.updateTestLog("Login into Pearson mail",
					"Login to Gmail failed", Status.FAIL);
		}
	}

	// To login into Webdav application
	public void loginAsValidUserForWebdav() {
		
		String userName = dataTable.getData("General_Data", "Username");
		String password = dataTable.getData("General_Data", "Password");
		RobotUtil loginRobot = new RobotUtil();
		try {
			loginRobot.webdavLogin(userName, password);
			report.updateTestLog("Login", "Enter login credentials: "
					+ "Username = " + userName, Status.DONE);
			UIHelper.waitForVisibilityOfEleByXpath(driver, sitesMenuLinkXpath);
			
			report.updateTestLog("Verify Login", "Login succeeded for valid user", Status.PASS);

		} catch (Exception e) {
			report.updateTestLog("Verify Login", "Login Failed for valid user", Status.FAIL);
		}
	}

	//Close browser
	public AlfrescoLoginPage logout() {
		driver.close();
		// driver.findElement(By.linkText("SIGN-OFF")).click();
		// report.updateTestLog("Logout", "Click the sign-off link",
		// Status.DONE);
		return new AlfrescoLoginPage(scriptHelper);
	}

	public AlfrescoHomePage loginAsValidUser() {
		login();		
		System.out.println("5");
		return new AlfrescoHomePage(scriptHelper);
	}

	public AlfrescoHomePage loginAsValidDifferentUser() {
		loginDifferentUser();
		return new AlfrescoHomePage(scriptHelper);
	}

	public AlfrescoHomePage loginAsValidUnassignedtUser() {
		loginUnassignedUser();
		return new AlfrescoHomePage(scriptHelper);
	}

	public AlfrescoLoginPage loginAsInvalidUser() {
		login();
		return new AlfrescoLoginPage(scriptHelper);
	}

	public boolean verifyNetworkConnection() {
		UIHelper.waitForPageToLoad(driver);

		if (driver.findElement(By.id("UsernameTextBox")).isDisplayed()) {
			return true;
		} else {
			return false;
		}

	}
	
	
	//Perform Login
	public void performLoginAWS(String userName, String password) {
	
		/*
		driver.findElement(By.id("UsernameTextBox")).sendKeys(userName);
		driver.findElement(By.id("PasswordTextBox")).clear();
		driver.findElement(By.id("PasswordTextBox")).sendKeys(password);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch blocks
			e.printStackTrace();
		}
		driver.findElement(By.id("SubmitButton")).click();
		UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loaderXpath);
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitForPageToLoad(driver);
	
		UIHelper.waitFor(driver);*/
		
		
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitForVisibilityOfEleByXpath(driver, ".//input[@name='username']");
		UIHelper.highlightElement(driver, "html/body");
		UIHelper.highlightElement(driver, ".//input[@name='username']");
		driver.findElement(By.name("username")).click();
		
		driver.findElement(By.name("username")).sendKeys(userName);
		UIHelper.waitForVisibilityOfEleByXpath(driver, ".//input[@name='password']");
		driver.findElement(By.name("password")).click();
		
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.xpath(".//button")).click();
		
		UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loaderXpath);
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitForPageToLoad(driver);
	
		UIHelper.waitFor(driver);
		UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, loaderXpath);
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitForPageToLoad(driver);
		report.updateTestLog("Login", "Enter login credentials: "
				+ "Username = " + userName, Status.DONE);
		
	}
	
	//Perform Login
		public void performAWSLogin(String userName, String password) {
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver, ".//input[@name='username']");
				UIHelper.highlightElement(driver, ".//input[@name='username']");	
				driver.findElement(By.name("username")).sendKeys(userName);

				UIHelper.waitForVisibilityOfEleByXpath(driver, ".//input[@name='password']");	
				driver.findElement(By.name("password")).sendKeys(password);	
				UIHelper.click(driver, "//*[contains(@id, 'submit-button')]");
				UIHelper.waitFor(driver);
				
				/*UIHelper.waitForVisibilityOfEleByXpath(driver, ".//input[@name='password']");	
				driver.findElement(By.name("password")).sendKeys(password);	
				UIHelper.click(driver, "//*[contains(@id, 'submit-button')]");
				UIHelper.waitFor(driver);*/
				report.updateTestLog("Login", "Enter login credentials: "
				+ "Username = " + userName, Status.DONE);
			}catch(Exception e) {
				e.printStackTrace();
				report.updateTestLog("Login", "Enter login credentials: "
						+ "Username = " + userName, Status.FAIL);
			}
		}
}