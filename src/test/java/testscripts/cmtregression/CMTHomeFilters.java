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

public class CMTHomeFilters extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void CMTHome51() {
		testParameters.setCurrentTestDescription("1.Users should have valid CMT admin credentials.\r\n" + 
				"2.Ensure the Home tab option is Displayed\r\n" + 
				"3.Ensure the 2 new filters 'Subject' and 'Search' fields are available.\r\n");

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
				cmtPage.checkIfCountriesInSortedOrder();
				UIHelper.waitFor(driver);
				//Check for Search and Subject filters
				driver.findElement(By.xpath(".//*[@title='India']")).click();
				report.updateTestLog("CMT Home Page", "India is clicked", Status.DONE);
				UIHelper.waitFor(driver);
				cmtPage.checkSubjectAndSeachFilter();
				}catch(Exception e) 
				{
					e.printStackTrace();
				}
	}

	@Override
	public void tearDown() {

	}
}
