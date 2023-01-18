package testscripts.cmtregression;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.pearson.automation.cmt.functionllibs.FunctionalLibrary;
import com.pearson.automation.cmt.pages.CmtHomePage;
import com.pearson.automation.cmt.pages.CmtLoginPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class CMTSearchFilter extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void CMTHome71() {
		testParameters.setCurrentTestDescription("To verify the search tab is enabled");

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
				driver.findElement(By.xpath(".//*[@title='India']")).click();
				report.updateTestLog("CMT Home Page", "India is clicked", Status.DONE);
				UIHelper.waitFor(driver);
				cmtPage.searchFilter();
				}catch(Exception e) {
					e.printStackTrace();
				}
	}

	@Override
	public void tearDown() {

	}
}
