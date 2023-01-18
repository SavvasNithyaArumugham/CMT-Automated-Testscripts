package testscripts.cmtregression;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
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

public class CMTmanageTabValidation extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void CMTHome71() {
		testParameters.setCurrentTestDescription(
				"Ensure the Manage Tab option is Displayed with the sub tabs");
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
		CmtHomePage cmtPage = new CmtHomePage(scriptHelper);				
		try {
			UIHelper.waitFor(driver);
			functionalLibrary.loginAsValidUser(signOnPage);
			UIHelper.waitFor(driver);
			
			driver.findElement(By.xpath("//a[@data-ng-bind-template='Manage']")).click();
			report.updateTestLog("CMT Manage Tab ", "CMT Manage Tab is clicked", Status.DONE);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
							
			boolean drafts = managePage.isObjectFieldAvailable("Drafts");
			boolean waitApprov = managePage.isObjectFieldAvailable("Awaiting approval");
			boolean approv = managePage.isObjectFieldAvailable("Approved");
			boolean reject = managePage.isObjectFieldAvailable("Rejected");
			boolean delete = managePage.isObjectFieldAvailable("Deleted");
			boolean curr = managePage.isObjectOptionAvailable("Curriculums");
			boolean product = managePage.isObjectOptionAvailable("Products");
			boolean inter= managePage.isObjectOptionAvailable("Intermediaries");
			
			if ((drafts = true) && (waitApprov = true) && (approv = true) && (reject = true) && (delete = true) && (curr = true)
				&& (product = true) && (inter = true))	
			{
				report.updateTestLog("Verify 'Manage' tab contains the fields ",
						"Drafts \r\n" + "Awaiting approval \r\n" + "Approved \r\n"+ "Rejected \r\n"
						+ "Deleted \r\n" + "Curriculums\r\n" + "Products\r\n" + "Intermediaries\r\n",Status.PASS);
			} else {
				report.updateTestLog("Verify 'Manage' tab contains the fields",
						" 'Manage' tab fields not available", Status.FAIL);
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
