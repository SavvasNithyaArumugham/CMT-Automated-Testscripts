package testscripts.session;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Naresh Kumar Salla
 */
public class AUT_AG_ClickOnMenuHeaderAfterXMins extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void runAUT_AG_CNF() {
		testParameters
				.setCurrentTestDescription("Click on application menu header within 3 Hours");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog(
				"Invoke Application",
				"Invoke the application under test @ "
						+ properties.getProperty("ApplicationUrl"), Status.DONE);

	}

	@Override
	public void executeTest() {
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);

		report.updateTestLog("Verify the current time",
				"Current Time: "+new DateUtil().getCurrentDateAndTime(),
				Status.DONE);
		
		String labelName = dataTable.getData("Labels", "LabelName");
		
		String minutes = dataTable.getData("Labels", "Minutes");
		
		int minuteInNumeric = Integer.parseInt(minutes);
		
		try{
			Thread.sleep(1000*minuteInNumeric*60);
		}
		catch(Exception e)
		{
		}
		
		report.updateTestLog("Wait upto "+minutes+" minutes without perform any action in application",
				"User waited "+minutes+" minutes",
				Status.DONE);
		
		homePage.clickOnApplicationMenuHeader(labelName, minutes, "Positive");
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}