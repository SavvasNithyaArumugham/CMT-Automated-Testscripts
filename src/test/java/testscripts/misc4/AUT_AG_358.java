package testscripts.misc4;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * @author Moshin
 */
public class AUT_AG_358 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void misc4_052() {
		testParameters
				.setCurrentTestDescription("Verify that user is able to view  'Web Resource' under create  option in Document Library,My Files and Shared Files.");
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
		String type = dataTable.getData("Parametrized_Checkpoints", "FilePath");
		try {
			AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
			functionalLibrary.loginAsValidUser(signOnPage);
			
			AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
			AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
			homePageObj.navigateToMyFilesTab();
			
			UIHelper.waitForVisibilityOfEleByXpath(driver, myFiles.createLinkXpath);
			UIHelper.click(driver,myFiles.createLinkXpath);
			AlfrescoDocumentDetailsPageTest docDetailsTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);
			docDetailsTest.verifyElementPresent(docDetailsTest.commoncreatexpath, type);
			
			AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
			String siteassertValue = dataTable.getData("Sites", "SiteName");
			sitesPage.openSiteFromRecentSites(siteassertValue);
			sitesPage.enterIntoDocumentLibrary();
			UIHelper.waitForVisibilityOfEleByXpath(driver, myFiles.createLinkXpath);
			UIHelper.click(driver,myFiles.createLinkXpath);
			docDetailsTest.verifyElementPresent(docDetailsTest.commoncreatexpath, type);
			
			homePageObj.navigateToSharedFilesTab();
			UIHelper.waitForVisibilityOfEleByXpath(driver, myFiles.createLinkXpath);
			UIHelper.click(driver,myFiles.createLinkXpath);
			docDetailsTest.verifyElementPresent(docDetailsTest.commoncreatexpath, type);
		} catch (Exception e) {
			
			report.updateTestLog("Verify " + type + "Element available",
					"Verify " + type + "Element is not displayed in the page",
					Status.FAIL);
		}

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}