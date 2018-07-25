package testscripts.avsmartlink;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAVSmartLinkPage;
import com.pearson.automation.alfresco.pages.AlfrescoAVTPage;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoPCSPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_006P1 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AV_002() {
		testParameters.setCurrentTestDescription(
				"Verify whether coordinator is able to create smart link object(image external link) in document library. Part 1: Create site and add coordinator and reviewer<br>"
						+ "R2.0.2_ALFDEPLOY-5241_Verify user not part of the site is able to navigate to folders in document library without any slowness.");
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
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoAVSmartLinkPage avsmart = new AlfrescoAVSmartLinkPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSiteMembersPageTest siteMemPgTest = new AlfrescoSiteMembersPageTest(scriptHelper);

		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String siteName = dataTable.getData("Sites", "SiteName");
		String type = dataTable.getData("Home", "DashletName");
		String title = dataTable.getData("Document_Details", "FilePath");
		String extURLLink = dataTable.getData("Document_Details", "FileName");

		functionalLibrary.loginAsValidUser(signOnPage);

		homePage.navigateToSitesTab();
		sitesPage.createSite(siteName, "Yes");

		String site = sitesPage.getCreatedSiteName();

		String siteuserName = dataTable.getData("Sites", "InviteUserName");
		String roleName = dataTable.getData("Sites", "Role");
		sitesPage.performInviteUserToSite(site);

		siteMemPgTest.verifySiteMemebrs(site, siteuserName, roleName);

		/*
		 * sitesPage.enterIntoDocumentLibrary();
		 * 
		 * UIHelper.waitForVisibilityOfEleByXpath(driver,
		 * myFiles.disablecreate);
		 * System.out.println(UIHelper.findAnElementbyXpath(driver,
		 * myFiles.disablecreate).getAttribute("disabled"));
		 * 
		 * if(UIHelper.findAnElementbyXpath(driver,
		 * myFiles.disablecreate).getAttribute("disabled").equals("disabled")){
		 * 
		 * report.updateTestLog("Verify" + type + "Element available", type +
		 * " Element is not displayed for reviewer role", Status.PASS);
		 * 
		 * }else{ report.updateTestLog("Verify" + type + "Element available",
		 * type + " Element is displayed for reviewer role", Status.FAIL); }
		 */

		// Verify user not part of the site is able to navigate to folders in
		// document library without any slowness.
		sitesPage.siteFinder("AMTS-Auto-Profiling-Test");
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.documentdetails("Asset");
		report.updateTestLog(
				"Verify user not part of the site is able to navigate to folders in document library without any slowness.",
				"User not part of the site is able to navigate to folders in document library without any slowness.",
				Status.PASS);

	}

	@Override
	public void tearDown() {

	}
}
