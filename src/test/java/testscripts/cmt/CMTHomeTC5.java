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

public class CMTHomeTC5 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void CMTHome51() {
		testParameters.setCurrentTestDescription("\"Ensure that as an admin following tabs are displayed on UI with their positons.\r\n" + 
				"1.Home \r\n" + 
				"2.Manage \r\n" + 
				"3..Intermediary Align \r\n" + 
				"4.Correlation \r\n" + 
				"\r\n" + 
				"\"");

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
				cmtPage.checkMenu();
				UIHelper.waitFor(driver);
				}catch(Exception e) {
					e.printStackTrace();
				}
	}

	@Override
	public void tearDown() {

	}
}
