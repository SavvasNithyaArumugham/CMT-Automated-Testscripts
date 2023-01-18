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

public class CMTmanageAuthorityCheck extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void CMTHome71() {
		testParameters.setCurrentTestDescription("\"User should be able to view the newly added Authority and Curriculum set in \r\n" + 
				"\r\n" + 
				"1.Upload New curriculum page\r\n" + 
				"2.Create New Curriculum page\r\n" + 
				"for the corresponding countries.\"" );
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
		  selectBox.selectByVisibleText("Upload New Curriculum");
		  selectBox.selectByVisibleText("Create New Curriculum");
		  UIHelper.waitFor(driver);
		  report.updateTestLog("CMT Manage Tab Curriculums ", "Manage Authority/Curriculum set is selected", Status.PASS); 
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
