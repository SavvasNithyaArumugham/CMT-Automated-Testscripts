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
public class AUT_AG_08 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void pcs_08() {
		testParameters
				.setCurrentTestDescription("1. Verify user is able to view Program Name and ISBN values of a particular file/folder in the PCS dashlet itself once the PCS record is attached successfully"
						+ "<br>2. Verify user is able to sort records based on Program Name,folder name and ISBN values in PCS dashlet after attaching PCS record");

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
		AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoPCSPage pcsObj = new AlfrescoPCSPage(scriptHelper);	
		AlfrescoPCSPageTest pcsTest = new AlfrescoPCSPageTest(
				scriptHelper);
		AlfrescoSitesDashboardPage siteDashboard=new AlfrescoSitesDashboardPage(scriptHelper);

		String folder1details = dataTable.getData("MyFiles", "CreateFileDetails");
		String folderName1 = dataTable.getData("MyFiles", "FileName");
		String siteassertValue = dataTable.getData("Sites", "SiteName");
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
		myFiles.createFolder(folder1details);
		
		siteDashboard.navigateToSiteDashboard();
		pcsTest.verifydashletSort();
		pcsTest.verifydashletSort();

		
		//siteDashboard.navigateToSiteDashboard();
		pcsObj.checkiteminPCSDashlet(folderName);
		pcsObj.searchisbnPCS("0132931281");
		pcsObj.clickbutton("Go");
		UIHelper.waitFor(driver);
		UIHelper.waitForVisibilityOfEleByXpath(driver, pcsObj.chooseRecord);
		UIHelper.click(driver, pcsObj.chooseRecord);
		pcsTest.verifypromptMsg("Ok");
		
		siteDashboard.navigateToSiteDashboard();
		pcsTest.verifyupdatedPCSValues(folderName,"9780132931281");
		
		
		
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}