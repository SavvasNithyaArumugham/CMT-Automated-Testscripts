package testscripts.pcs;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoPCSPageTest;
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
public class AUT_AG_01 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void pcs_01() {
		testParameters
				.setCurrentTestDescription("1. Verify the UI of PCS dashlet in Archive Site Dashboard"
						+ "<br>2. Verify whether the files and folders created in the document library of the archive site is displayed in the 'Database Search - PCS' dash-let of the archive review site dashboard"
						+ "<br>3. Verify the user is able to create a archive site by selecting ''Archive Review - PCS' from 'Type' drop down."
						+ "<br>4. Verify the new option 'Archive Review - PCS' is added to the 'Type' drop down in 'Create site' window.");

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

		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
	//	homePage.navigateToSitesTab();

	//	sitesPage.clickOnCreateSite();

		AlfrescoSitesPageTest sitesPageTest = new AlfrescoSitesPageTest(
				scriptHelper);
	//	sitesPageTest.verifyCreateSitePageIsDisplayed();

	//	sitesPageTest.verifyCreateSiteTypeValues();

		String siteassertValue = dataTable.getData("Sites", "SiteName");
		sitesPage.siteFinder(siteassertValue);

		AlfrescoSitesPageTest siteTestObj = new AlfrescoSitesPageTest(
				scriptHelper);
		homePage.navigateToHomePage();
		siteTestObj.verifySiteInSiteFinderResults(siteassertValue);

		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoPCSPageTest pcsTest = new AlfrescoPCSPageTest(scriptHelper);

		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
		String fleNme = dataTable.getData("MyFiles", "FileName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folderName = dataTable.getData("Sites", "FileName");

		sitesPage.siteFinder(siteassertValue);
		AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(
				scriptHelper);
		AlfrescoSitesDashboardPage sitesDashboardPage = new AlfrescoSitesDashboardPage(scriptHelper);
		
		
		String dashletNme = dataTable.getData("Home", "DashletName");
		String dashlet = dataTable.getData("Home", "DashBoardName");
		if(!sitesDashboardPage.checkDashletInSiteDashboard(dashlet))
		{
			sitesDashboardPage.customizeSiteDashboard();
			sitesDashboardPageTest.verifyAddDashletInCustomDashBrd();
		}
		
		
		sitesPage.enterIntoDocumentLibrary();
		docLibPge.deleteAllFilesAndFolders();
		myFiles.createFolder(folderDetails);
		myFiles.createFile(fileDetails);
		sitesPage.siteFinder(siteassertValue);
		pcsTest.verifyFileFldrDisp(folderName);

		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			String foldercheckName = pcsTest.filechkXPath.replace("CRAFT",
					fleNme);
			UIHelper.waitForVisibilityOfEleByXpath(driver, pcsTest.dashletname);
			if (UIHelper.checkForAnElementbyXpath(driver, pcsTest.dashletname)
					|| UIHelper.checkForAnElementbyXpath(driver,
							pcsTest.foldername)
					|| UIHelper.checkForAnElementbyXpath(driver,
							pcsTest.programname)
					|| UIHelper.checkForAnElementbyXpath(driver,
							pcsTest.isbnname)
					|| UIHelper.checkForAnElementbyXpath(driver,
							pcsTest.fileXPath)
					|| UIHelper.checkForAnElementbyXpath(driver,
							foldercheckName)) {

				UIHelper.highlightElement(driver, pcsTest.dashletname);
				UIHelper.highlightElement(driver, pcsTest.foldername);
				UIHelper.highlightElement(driver, pcsTest.programname);
				UIHelper.highlightElement(driver, pcsTest.isbnname);
				UIHelper.highlightElement(driver, pcsTest.fileXPath);
				UIHelper.highlightElement(driver, foldercheckName);

				report.updateTestLog(
						"Verify the UI of PCS dashlet",
						"Dashlet UI verified. <br><b> Dashlet Name : <\b> "
								+ UIHelper.findAnElementbyXpath(driver,
										pcsTest.dashletname).getText(),
						Status.PASS);

			} else {
				report.updateTestLog("Verify the UI of PCS dashlet",
						"Verify the UI of PCS dashlet failed", Status.FAIL);
			}
		} catch (Exception e) {

			e.printStackTrace();
			report.updateTestLog("Verify the UI of PCS dashlet",
					"Verify the UI of PCS dashlet failed", Status.FAIL);
		}

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}