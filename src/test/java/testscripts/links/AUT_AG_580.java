package testscripts.links;

import java.util.ArrayList;

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
public class AUT_AG_580 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void LINKS_045() {
		testParameters
				.setCurrentTestDescription("Verify that the  Contract Support  is available under User Dropdown for Admin users");
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

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		String Option = dataTable.getData("Home", "TasksFilterDropdownValue");
	//	homePageObj.UserMenucommonMethod(Option);
		
		/*AlfrescoHomePageTest homePageTestObj = new AlfrescoHomePageTest(
				scriptHelper);
		homePageTestObj.verifyHelpURL();*/
		
		try {
			UIHelper.waitFor(driver);
		
			UIHelper.waitFor(driver);
			String finaltempUserOption = homePageObj.tempUserOpt
					.replace("CRAFT", Option);
			String currentURL = UIHelper.findAnElementbyXpath(driver, finaltempUserOption).getAttribute("href");
			String ExpectedURL = dataTable.getData("Parametrized_Checkpoints",
					"Help URL");
			if (currentURL.equalsIgnoreCase(ExpectedURL)) {
				report.updateTestLog("Verify URL ", "URL = " + currentURL,
						Status.PASS);
			} else {
				report.updateTestLog("Verify URL ", "Verify URL failed",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Help URL ", "Verify URL failed",
					Status.FAIL);
		}

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}