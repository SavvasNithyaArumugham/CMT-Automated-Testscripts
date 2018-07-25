	package testscripts.pcs;

import java.util.ArrayList;
import java.util.Collections;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoPCSPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoPCSPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_07 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void pcs_07() {
		testParameters
				.setCurrentTestDescription("1. Verify the aspects of an archive file/folder matches with the PCS record when attaching a PCS record to the file/folder."
						+ "<br>2. Verify the metadata aspects added via PCS dashlet is reflected in Manage Aspect Window"
						+ "<br>3. Verify the metadata aspect values added via PCS dashlet are reflected in 'All Properties' in Edit Properties page"
						+ "<br>4. Verify the existing metadata of an archive folder overridden by the updated values when attaching the same metadata to the folder.");

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
	
		String rootFolderDetails = dataTable.getData("MyFiles",
				"CreateFolder");
		String fileNme = dataTable.getData("MyFiles", "FileName");
		String siteassertValue = dataTable.getData("Sites", "SiteName");
		
		
			
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
		//myFiles.uploadFile(filePath, fileNme);
		myFiles.createFolder(rootFolderDetails);

	AlfrescoSitesDashboardPage siteDashboard=new AlfrescoSitesDashboardPage(scriptHelper);
		siteDashboard.navigateToSiteDashboard();
		pcsObj.checkiteminPCSDashlet(fileNme);
		pcsObj.searchisbnPCS("0132931281");
		pcsObj.clickbutton("Go");
		UIHelper.waitFor(driver);
		UIHelper.waitForVisibilityOfEleByXpath(driver, pcsObj.chooseRecord);
		UIHelper.click(driver, pcsObj.chooseRecord);
		pcsTest.verifypromptMsg("Ok");
		
		
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnViewDetails(fileNme);
		
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		docDetailsPage.performManageAspectsDocAction();
		
		AlfrescoDocumentDetailsPageTest docDetailsPageTestObj = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		docDetailsPageTestObj.verifyListOfAspectsAvailable();
		
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.commonMethodForPerformBrowseOption(fileNme, "Edit Properties");
		
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);	
		docDetailsPageObj.clickAllProperties();
		
		String fieldDataForAllProperties = dataTable.getData("MyFiles", "FilePath");
		AlfrescoCollectionsPageTest collectionPgTest = new AlfrescoCollectionsPageTest(scriptHelper);
		collectionPgTest.verifyFieldValuesInEditPropPage(fieldDataForAllProperties);
		
		
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}