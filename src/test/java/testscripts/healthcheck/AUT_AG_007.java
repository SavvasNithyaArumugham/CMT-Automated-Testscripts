package testscripts.healthcheck;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoEPMToolPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_007 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void runAUT_AG_001() {
		testParameters.setCurrentTestDescription(
				"1. Verify the new option 'Archive Review- EPM' added to the 'Type' drop down in 'Create site' window"
						+ "<br>2. Verify the user is able to create a archive site by selecting 'Archive Review- EPM' from 'Type' drop down"
						+ "<br>3. Verify only the folders created in the document library displayed in the 'Database Search - EPM' dash-let of the archive review site"
						+ "<br>4. To Check if upon creating an 'Archive Review - EPM' site in Alfresco, the EPM dashlet loads successfully and pulls up the folder view of the Document Library"
						+ "<br>5. To Check if the user is able to load the EPM dashlet in multiple sites"
						+ "<br>6. To check if the EPM Dashlet loads without glitches when the user refreshes site dashboard page");
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
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoSitesPageTest sitesPageTest = new AlfrescoSitesPageTest(scriptHelper);
		AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesDashboardPage siteDashboard = new AlfrescoSitesDashboardPage(scriptHelper);
		AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(scriptHelper);
		AlfrescoEPMToolPage epmToolsPg = new AlfrescoEPMToolPage(scriptHelper);

		homePage.navigateToSitesTab();

		// sitesPage.clickOnUserDashboardCreateSite();

		sitesPage.clickOnCreateSite();

		sitesPageTest.verifyCreateSitePageIsDisplayed();
		sitesPageTest.verifyCreateSiteTypeValues();

		//sitesPage.clickOnCancelBtnInCreateSiteDailog();

		String siteDetails = dataTable.getData("Sites", "CreateMultipleSites");
		sitesPage.createMultipleSitesWithTimeStamp(siteDetails);
		sitesPageTest.verifyCreatedSite();

		if (epmToolsPg.verifyEPMDashletInSiteDashBoard()) {
			report.updateTestLog("Verify EPM Dashlet is added by default", "EPM dashlet added by default", Status.PASS);
		} else {
			report.updateTestLog("Verify EPM Dashlet is added by default", "EPM dashlet is not available", Status.FAIL);
		}

		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
		String fleNme = dataTable.getData("MyFiles", "FileName");
		String siteName = dataTable.getData("Sites", "SiteName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folderName = dataTable.getData("Sites", "FileName");

		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteName);

		sitesPage.enterIntoDocumentLibrary();
		docLibPge.deleteAllFilesAndFolders();
		myFiles.createFolder(folderDetails);
		myFiles.createFile(fileDetails);

		/*
		 * homePage.navigateToSitesTab();
		 * sitesPage.openSiteFromRecentSites(siteName);
		 */
		siteDashboard.navigateToSiteDashboard();
		if (epmToolsPg.verifyEPMDashletInSiteDashBoard()) {
			report.updateTestLog("Verify EPM Dashlet is added by default", "EPM dashlet added by default", Status.PASS);
		} else {
			report.updateTestLog("Verify EPM Dashlet is added by default", "EPM dashlet is not available", Status.FAIL);
		}
		sitesDashboardPageTest.verEPMFileFldrDisp(folderName, fleNme);

		sitesPage.createMultipleSitesWithTimeStamp(siteDetails);
		sitesPageTest.verifyCreatedSite();
		if (epmToolsPg.verifyEPMDashletInSiteDashBoard()) {
			report.updateTestLog("Verify EPM Dashlet is added by default", "EPM dashlet added by default", Status.PASS);
		} else {
			report.updateTestLog("Verify EPM Dashlet is added by default", "EPM dashlet is not available", Status.FAIL);
		}

		sitesPage.createMultipleSitesWithTimeStamp(siteDetails);
		sitesPageTest.verifyCreatedSite();
		UIHelper.pageRefresh(driver);
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		if (epmToolsPg.verifyEPMDashletInSiteDashBoard()) {
			report.updateTestLog("Verify EPM Dashlet is added by default", "EPM dashlet added by default", Status.PASS);
		} else {
			report.updateTestLog("Verify EPM Dashlet is added by default", "EPM dashlet is not available", Status.FAIL);
		}

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}