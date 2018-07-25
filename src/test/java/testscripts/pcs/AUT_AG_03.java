package testscripts.pcs;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoPCSPage;
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
public class AUT_AG_03 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void pcs_03() {
		testParameters
				.setCurrentTestDescription("1. Verify the UI of standard search panel in 'Database Search - PCS' dash-let of the archive site."
						+ "<br>2. Verify the searching options displayed on selecting a file/folder in 'Database Search - PCS' dash-let of the archive review site.");

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
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoPCSPage pcsObj = new AlfrescoPCSPage(scriptHelper);	
		AlfrescoPCSPageTest pcsTest = new AlfrescoPCSPageTest(
				scriptHelper);
		
	
		String rootFolderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folder1Details = dataTable.getData("MyFiles", "FileName");
		String folder2Details = dataTable.getData("MyFiles", "FilePath");
		String siteassertValue = dataTable.getData("Sites", "SiteName");
		String rootfolder = dataTable.getData("Tasks", "TaskName");
		String folder1 = dataTable.getData("Tasks", "AttachedFileTxtVal");
		String folder2 = dataTable.getData("Tasks", "ReviewerComment");
		
			
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
		myFiles.createFolder(rootFolderDetails);
		//myFiles.createFile(fileDetails);
		sitesPage.siteFinder(siteassertValue);
		pcsObj.checkiteminPCSDashlet(rootfolder);
		
		String gobuttonXpath =pcsObj.buttonXpath.replace("CRAFT", "Go");
		String emtybuttonXpath =pcsObj.buttonXpath.replace("CRAFT", "Empty");
		String exitbuttonXpath =pcsObj.buttonXpath.replace("CRAFT", "Exit");
		
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, pcsTest.pcslogo);
			if (UIHelper.checkForAnElementbyXpath(driver, pcsTest.pcslogo) ||
					UIHelper.checkForAnElementbyXpath(driver, pcsObj.isbnIDXpath) ||
					UIHelper.checkForAnElementbyXpath(driver, gobuttonXpath) ||
					UIHelper.checkForAnElementbyXpath(driver, emtybuttonXpath) ||
					UIHelper.checkForAnElementbyXpath(driver, exitbuttonXpath)) {
				
				UIHelper.highlightElement(driver, pcsTest.pcslogo);
				UIHelper.highlightElement(driver, pcsObj.isbnIDXpath);
				UIHelper.highlightElement(driver, gobuttonXpath);
				UIHelper.highlightElement(driver, emtybuttonXpath);
				UIHelper.highlightElement(driver, exitbuttonXpath);
				
				
				report.updateTestLog("Verify  UI of standard search panel in 'Database Search - PCS' dash-let of the archive site",
						"Verify  UI of standard search panel in 'Database Search - PCS' dash-let of the archive site successfully",
						Status.PASS);
				
			} else {	
				report.updateTestLog("Verify  UI of standard search panel in 'Database Search - PCS' dash-let of the archive site",
						"Verify  UI of standard search panel in 'Database Search - PCS' dash-let of the archive site failed",
						Status.FAIL);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			report.updateTestLog("Verify  UI of standard search panel in 'Database Search - PCS' dash-let of the archive site",
					"Verify  UI of standard search panel in 'Database Search - PCS' dash-let of the archive site failed",
					Status.FAIL);
		}
		
		try{
		pcsObj.searchisbnPCS("0132931281");
		pcsObj.clickbutton("Go");
		String ISBNValue = pcsObj.labelvaluereturned("ISBN-10", "txtIsbn10-pcs");
	
			if (ISBNValue.equals("0132931281")) {
				
				report.updateTestLog("Verify the search results based on the input data are displayed",
						"search results based on the input data are displayed in PCS dashlet",
						Status.PASS);
				
			} else {	
				report.updateTestLog("Verify the search results based on the input data are displayed",
						"search results based on the input data are not displayed in PCS dashlet",
						Status.FAIL);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			report.updateTestLog("Verify the search results based on the input data are displayed",
					"search results based on the input data are not displayed in PCS dashlet",
					Status.FAIL);
		}
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}