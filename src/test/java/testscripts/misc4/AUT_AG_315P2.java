package testscripts.misc4;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyActivitiesPage;
import com.pearson.automation.alfresco.pages.AlfrescoRepositoryPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_315P2 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void misc4_015() {
		testParameters
				.setCurrentTestDescription("Verify that Reviewer is not be able to view original version of the content.<br>"+
						"Verify that Reviewer is not be able to download working copy of the file from alfresco.<br>"+" Part2: Check Preview");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);

	
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String fileName = dataTable.getData("MyFiles", "FileName");
		sitesPage.siteFinder(siteNameValue);
		sitesPage.enterIntoDocumentLibrary();
		
		
		AlfrescoSitesPageTest sitePageTest = new AlfrescoSitesPageTest(scriptHelper);
		sitePageTest.verifyBrowseActionNameForFileOrFolderInNegativeCase(fileName, "Download");
		
		
		
		sitesPage.documentdetails(fileName);
	
		UIHelper.waitForPageToLoad(driver);
		if (!UIHelper.checkForAnElementbyXpath(driver, sitesPage.revertXpath)) {
			report.updateTestLog(
					"Check Reviewer is not be able to view original version of the content",
					"Reviewer is not be able to view original version of the content", Status.PASS);
		} else {
			report.updateTestLog(
					"Check Reviewer is not be able to view original version of the content",
					"Reviewer is be able to view original version of the content", Status.FAIL);
		}
	}

	@Override
	public void tearDown() {

	}
}
