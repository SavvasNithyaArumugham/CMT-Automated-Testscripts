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

public class CMTalignmentScreenValidation extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void CMTHome71() {
		testParameters.setCurrentTestDescription("Page should contain Curiculum standard and Product Tab");
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
		String currStdXpath = "//*[@data-ng-bind-template='Curriculum Standard']";
		String productXpath = "//*[@data-ng-bind-template='Product']";
		try {
			UIHelper.waitFor(driver);
			functionalLibrary.loginAsValidUser(signOnPage);
			UIHelper.waitFor(driver);
			managePage.clickIntermediaryAlign();
			UIHelper.waitFor(driver);
			// Check if Page contains Curriculum standard and Product Tab
	if(driver.findElements(By.xpath(currStdXpath)).size() != 0)
	{
	report.updateTestLog("Curiculum standard Tab","Page contains Curiculum standard Tab", Status.PASS);
	}
	else
	{
	report.updateTestLog("Curiculum standard Tab","Page not contains Curiculum standard Tab", Status.FAIL);
	}
	
	if(driver.findElements(By.xpath(productXpath)).size() != 0)
	{
	report.updateTestLog("Product Tab","Page contains Product Tab", Status.PASS);
	}
	else
	{
	report.updateTestLog("Product Tab","Page not contains Product Tab", Status.FAIL);
	}
		
		
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
