package testscripts.misc3;

import java.util.ArrayList;

import org.openqa.selenium.server.browserlaunchers.Sleeper;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Moshin
 */
public class AUT_AG_217 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void misc3() {
		testParameters
				.setCurrentTestDescription("Verify on hitting the url www.pearsoncms.com, user is not directed to uswip.pearson.com instance");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("PearsonUrl"));
		report.updateTestLog(
				"Invoke Application",
				"Invoke the application under test @ "
						+ properties.getProperty("PearsonUrl"), Status.DONE);

	}

	@Override
	public void executeTest() {
		try{
		//UIHelper.waitForPageToLoad(driver);
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		/*AlfrescoHomePageTest homePageTestObj = new AlfrescoHomePageTest(
				scriptHelper);
		homePageTestObj.verifyHelpURL();*/
		
		/*ArrayList<String> tab = new ArrayList<String>(
				driver.getWindowHandles());
		driver.switchTo().window(tab.get(1));*/
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		UIHelper.pageRefresh(driver);
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		String currentURL = driver.getCurrentUrl();
		String ExpectedURL = dataTable.getData("Parametrized_Checkpoints",
				"Help URL");
		System.out.println(currentURL);
		System.out.println(ExpectedURL);
		if (currentURL.contains(ExpectedURL)) {
			report.updateTestLog("Verify URL ", "URL = " + currentURL,
					Status.PASS);
		} else {
			report.updateTestLog("Verify URL ", "Verify URL failed",
					Status.FAIL);
		}
	} catch (Exception e) {
		report.updateTestLog("Verify URL ", "Verify URL failed",
				Status.FAIL);
	}
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}