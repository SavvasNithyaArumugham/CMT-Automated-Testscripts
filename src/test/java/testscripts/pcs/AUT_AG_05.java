package testscripts.pcs;

import java.util.ArrayList;

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
public class AUT_AG_05 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void pcs_05() {
		testParameters
		.setCurrentTestDescription("1. Verify the user is able to view zip files in 'Database Search - PCS' dash-let of the archive review site."
				+ "<br>2. Verify the warning message displayed when user search different business unit PEARID/ISBN which is currently not supported for Alfresco PCS Dashlet."
			+ "<br>3. Verify the warning message window displayed when trying to search with empty fields in search tab."
			+ "<br>4. Verify the warning message window displayed when trying to search with incorrect ISBN data in the search fields."
			+ "<br>5. Verify the warning message window displayed when trying to search with incorrect PEAR ID data in the search fields."
			+ "<br>6. Verify the Search results tab is disabled until first search for an archive folder is not done."
			+ "<br>7. Verify the existing metadata of an archive folder overridden by the updated values when attaching the same metadata to the folder."
			+"<br>8. Verify the user is able to attach the aspects to the selected zip file by clicking on [CHOOSE THIS RECORD] button from search results page in 'Database Search - PCS' dash-let.");

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
		AlfrescoPCSPageTest pcsTest = new AlfrescoPCSPageTest(scriptHelper);
		AlfrescoSitesDashboardPage siteDashboard=new AlfrescoSitesDashboardPage(scriptHelper);
		AlfrescoPCSPage pcsObj = new AlfrescoPCSPage(scriptHelper);	
	
		String fileNme = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String siteassertValue = dataTable.getData("Sites", "SiteName");
		String rootFolderDetails = dataTable.getData("MyFiles",
				"CreateFolder");

			
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
		myFiles.uploadFile(filePath, fileNme);
	
		siteDashboard.navigateToSiteDashboard();
		pcsTest.verifyFileFldrDisp(fileNme);
		
		siteDashboard.navigateToSiteDashboard();
		pcsObj.checkiteminPCSDashlet(fileNme);
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, pcsObj.searchlisttab);

			if (UIHelper.findAnElementbyXpath(driver, pcsObj.searchlisttab)
					.getAttribute("aria-disabled").equals("true")) {
				UIHelper.highlightElement(driver, pcsObj.searchlisttab);
				report.updateTestLog("Verify Search results tab is disabled",
						"Search results tab is disabled as expected ",
						Status.PASS);
			} else {
				report.updateTestLog("Verify Search results tab is disabled",
						"Search results tab is not disabled.", Status.FAIL);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			report.updateTestLog("Verify Search results tab is disabled",
					"Search results tab is not disabled.", Status.FAIL);
		}
		
		pcsObj.searchisbnPCS("0134030680");
		pcsObj.clickbutton("Go");
		UIHelper.waitFor(driver);
		UIHelper.waitForVisibilityOfEleByXpath(driver, pcsObj.chooseRecord);
		UIHelper.click(driver, pcsObj.chooseRecord);
		pcsTest.verifypromptMsg("Ok");
		
		siteDashboard.navigateToSiteDashboard();
		pcsObj.checkiteminPCSDashlet(fileNme);
		pcsObj.searchisbnPCS("0132931281");
		pcsObj.clickbutton("Go");
		UIHelper.waitFor(driver);
		UIHelper.waitForVisibilityOfEleByXpath(driver, pcsObj.chooseRecord);
		UIHelper.click(driver, pcsObj.chooseRecord);
	//	pcsTest.verifypromptMsg("Proceed");
		pcsTest.verifypromptMsg("Ok");
		
		siteDashboard.navigateToSiteDashboard();
		pcsTest.verifyupdatedPCSValues(fileNme,"9780132931281");
		
		siteDashboard.navigateToSiteDashboard();
		pcsObj.checkiteminPCSDashlet(fileNme);
		pcsObj.clickbutton("Go");
		pcsTest.verifypromptMsg("Ok");
		
		siteDashboard.navigateToSiteDashboard();
		pcsObj.checkiteminPCSDashlet(fileNme);

		pcsObj.searchisbnPCS("013403068");
		pcsObj.clickbutton("Go");
		pcsTest.verifypromptMsg("Ok");
		
		siteDashboard.navigateToSiteDashboard();
		pcsObj.checkiteminPCSDashlet(fileNme);
		pcsObj.searchisbnPCS("00010001000554353");
		pcsObj.clickbutton("Go");
		pcsTest.verifypromptMsg("Ok");
		
		

		
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}