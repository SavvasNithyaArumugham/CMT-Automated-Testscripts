package testscripts.cmtregression;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.pearson.automation.cmt.functionllibs.FunctionalLibrary;
import com.pearson.automation.cmt.pages.CmtHomePage;
import com.pearson.automation.cmt.pages.CmtLoginPage;
import com.pearson.automation.cmt.pages.CmtManagePage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class CMTmanageAuthority extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void CMTHome71() {
		testParameters.setCurrentTestDescription("Ensure Manage Authorities/Curriculum set  button is displayed and enabled" );
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application",
				"Invoke the application under test @ " + properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {
		CmtLoginPage signOnPage = new CmtLoginPage(scriptHelper);
		CmtManagePage managePage = new CmtManagePage(scriptHelper);
		try {
			UIHelper.waitFor(driver);
			functionalLibrary.loginAsValidUser(signOnPage);
			UIHelper.waitFor(driver);
			
			driver.findElement(By.xpath("//a[@data-ng-bind-template='Manage']")).click();
			report.updateTestLog("CMT Manage Tab ", "CMT Manage Tab is clicked", Status.DONE);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
		  Select selectBox = new Select(driver.findElement(By.cssSelector("li:nth-child(6) > select:nth-child(1)")));
		  selectBox.selectByVisibleText("Manage Authority/Curriculum set");
		  UIHelper.waitFor(driver);
		  report.updateTestLog("CMT Manage Tab Curriculums ", "Manage Authority/Curriculum set is selected", Status.PASS); 
		  UIHelper.waitFor(driver);	
		  
		  String countryListXpath ="//*[@id='productBrowseTree']";
		  UIHelper.findAnElementbyXpath(driver,countryListXpath).isDisplayed();
		  report.updateTestLog("List of countries in the Manage Authorities/Curriculum set page" , "User able to view list of countries in the Manage Authorities/Curriculum set page\r\n" , Status.PASS);
		//Click on the + sign   
		  String countryPlusXpath ="//*[@id='https://data.savvas.com/country/IND#this_anchor']/span/span/span";
		  UIHelper.findAnElementbyXpath(driver,countryPlusXpath).click();
		  UIHelper.waitFor(driver);	
		// Modal Dialog to enter;1.Authority Name 2.Authority URL is opened 
		  Actions action = new Actions(driver);
		  String authorityPopUpXpath ="//*[@id='CreateProgramPopup']/div/div/div[1]/span";
		  boolean webelement1 = driver.findElement(By.xpath(authorityPopUpXpath)).isDisplayed();
		  report.updateTestLog("Add Authority for India", "Pop Up to enter 1.Authority Name 2.Authority URL is opened ", Status.PASS);
		 // Enter Authority Name and URL 
		  String authorityNameXpath ="//input[@ng-model='createItemBean.authorityName']";
		  String authorityURLXpath ="//input[@ng-model='createItemBean.authorityUrl']";
	
		  WebElement authorityName = driver.findElement(By.xpath(authorityNameXpath));
		  action.moveToElement(authorityName).click().sendKeys("AutoTest").build().perform();
		  
		  WebElement authorityUrl = driver.findElement(By.xpath(authorityURLXpath));
		  action.moveToElement(authorityUrl).click().sendKeys("http://www.savvas.com").build().perform();
		  
		  String saveXpath ="//*[@id='CreateProgramPopup']/div/div/div[3]/div[2]/input";
		  WebElement save = driver.findElement(By.xpath(saveXpath));
		  action.moveToElement(save).click().build().perform();
		  
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);	
		UIHelper.waitFor(driver);
		
		String expandCountry ="//*[@id='https://data.savvas.com/country/IND#this']/i";
		UIHelper.findAnElementbyXpath(driver, expandCountry).click();
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		
		String authorityXpath ="//span[text()='AutoTest']";
		UIHelper.highlightElement(driver, authorityXpath);
		UIHelper.findAnElementbyXpath(driver, authorityXpath).isDisplayed();
		UIHelper.waitFor(driver);
		
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void tearDown() {

	}
}
