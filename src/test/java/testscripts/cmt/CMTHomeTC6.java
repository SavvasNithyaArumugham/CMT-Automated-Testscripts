package testscripts.cmt;

import org.testng.annotations.Test;

import com.pearson.automation.cmt.functionllibs.FunctionalLibrary;
import com.pearson.automation.cmt.pages.CmtHomePage;
import com.pearson.automation.cmt.pages.CmtLoginPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class CMTHomeTC6 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void CMTHome61() {
		testParameters.setCurrentTestDescription("Ensure that welcome message: “Welcome to the CMT Your\r\n" + 
				"curriculum management, alignment, and reporting tool\" is displayed on the right side of the page. and under the welcome message \r\n" + 
				"“Select a country to view all the curricula available.” message is displayed.\r\n" + 
				"\r\n" + 
				"Following field is displayed on Left side:\r\n" + 
				"1. List of countries in alphabetic order.");

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
				CmtHomePage cmtPage = new CmtHomePage(scriptHelper);
				try {
				functionalLibrary.loginAsValidUser(signOnPage);
				UIHelper.waitFor(driver);
				cmtPage.checkWelcomeMessage();
				UIHelper.waitFor(driver);
				cmtPage.checkIfCountriesInSortedOrder();
				}catch(Exception e) {
					e.printStackTrace();
				}
	}

	@Override
	public void tearDown() {

	}
}
