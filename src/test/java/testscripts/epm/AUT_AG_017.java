package testscripts.epm;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_017 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void runAUT_AG_017() {
		testParameters
				.setCurrentTestDescription("Verify the ISBN and Full Title columns are displayed for the folders with correct values in the 'Database Search - EPM' dash-let");
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
		ArrayList<String> folderNamesList = new ArrayList<String>();
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
	//	homePageObj.navigateToSitesTab();

		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String sourceSiteName = dataTable.getData("Sites", "SiteName");

	sitesPage.siteFinder(sourceSiteName);
		
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

		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(
				scriptHelper);

		docLibPage.deleteAllFilesAndFolders();

		myFiles.createFolder(folderDetails);

		folderNamesList = myFiles.getFolderNames(folderDetails);

		AlfrescoSitesDashboardPage siteDashboard = new AlfrescoSitesDashboardPage(
				scriptHelper);
		AlfrescoSitesDashboardPageTest siteDashboardPgTest = new AlfrescoSitesDashboardPageTest(
				scriptHelper);

		siteDashboard.navigateToSiteDashboard();

		String searchContent = dataTable.getData("EPM",
				"ListTitlesWithFieldValue");
		String SearchType = dataTable.getData("EPM", "SearchContentType");

		for (String folderName : folderNamesList) {
			siteDashboard.selectFoldersInEPMDashlet(folderName);
			siteDashboard.enterSearchCriTeria(searchContent.replace("'",""), SearchType);
			
			siteDashboard.clickOnChooseThisRecord();
			
			String expectedFullTitle = dataTable.getData("EPM",
					"FullTitle");
			String expectedISBN = dataTable.getData("EPM", "ISBNValue");
			siteDashboardPgTest.verifyFolderFullTitleAndISBN(expectedFullTitle,expectedISBN.replace("'", ""));
		}
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}