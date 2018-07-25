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
public class AUT_AG_26 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void pcs() {
		testParameters
				.setCurrentTestDescription("1. Verify the search results based on the input data are displayed by clicking on [Go] button in 'Database Search - PCS' dash-let of the archive review site."
						+ "<br>2. Verify all the values entered in the fields are cleared by clicking on [EMPTY] button in 'Database Search - PCS' dash-let of the archive review site."
						+ "<br>3. Verify the user is able to return back to the archive folder list from search panel by clicking on [EXIT] button in 'Database Search - PCS' dash-let of the archive review site."
						+ "<br>4. Verify the UI of search results panel in 'Database Search - PCS' dash-let of the archive review site."
						+ "<br>5. Verify the user is able to return back to the archive folder list from search results by clicking on [EXIT] button in 'Database Search - PCS' dash-let of the archive review site.");

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
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoPCSPage pcsObj = new AlfrescoPCSPage(scriptHelper);
		AlfrescoPCSPageTest pcsTest = new AlfrescoPCSPageTest(scriptHelper);

		String rootFolderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String siteassertValue = dataTable.getData("Sites", "SiteName");
		String rootfolder = dataTable.getData("Tasks", "TaskName");
		String folderDetails = dataTable
				.getData("MyFiles", "CreateFileDetails");
		try {
			AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
			functionalLibrary.loginAsValidUser(signOnPage);

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
			// myFiles.createFile(fileDetails);
			sitesPage.siteFinder(siteassertValue);
			pcsObj.checkiteminPCSDashlet(rootfolder);
			pcsObj.searchisbnPCS("0133405877");
			pcsObj.clickbutton("Empty");

			pcsObj.checkiteminPCSDashlet(rootfolder);
			pcsObj.searchisbnPCS("0133405877");
			pcsObj.clickbutton("Exit");
			// pcsObj.checkiteminPCSDashlet(rootfolder);
			pcsTest.verifyFileFldrDisp(rootfolder);

		} catch (Exception e) {

			e.printStackTrace();

		}

		try {
			pcsObj.checkiteminPCSDashlet(rootfolder);
			pcsObj.searchisbnPCS("0133405877");
			pcsObj.clickbutton("Go");

			ArrayList<String> folderNamesList = myFiles
					.getFolderNames(folderDetails);
			System.out.println(folderNamesList);

			if (UIHelper.checkForAnElementbyXpath(driver, pcsObj.chooseRecord)
					|| UIHelper.checkForAnElementbyXpath(driver,
							pcsObj.searchExit)
					|| UIHelper.checkForAnElementbyXpath(driver,
							pcsObj.searchimgslot)) {
				// ArrayList<String> folderNamesList =
				// myFiles.getFolderNames(folderDetails);
				for (String label : folderNamesList) {
					String finallabel = pcsObj.labelXpath.replace("CRAFT",
							label);
					if (UIHelper.checkForAnElementbyXpath(driver, finallabel)) {
						UIHelper.highlightElement(driver, finallabel);

					} else {
						report.updateTestLog(
								"Verify the UI of search results panel in 'Database Search - PCS' dash-let",
								"UI of search results panel in 'Database Search - PCS' dash-let not as expected",
								Status.FAIL);
						break;
					}

				}
				UIHelper.highlightElement(driver, pcsObj.chooseRecord);
				UIHelper.highlightElement(driver, pcsObj.searchExit);
				UIHelper.highlightElement(driver, pcsObj.searchimgslot);

				report.updateTestLog(
						"Verify the UI of search results panel in 'Database Search - PCS' dash-let",
						"UI of search results panel in 'Database Search - PCS' dash-let verified",
						Status.PASS);

			} else {
				report.updateTestLog(
						"Verify the UI of search results panel in 'Database Search - PCS' dash-let",
						"UI of search results panel in 'Database Search - PCS' dash-let not as expected",
						Status.FAIL);
			}
		} catch (Exception e) {

			e.printStackTrace();
			report.updateTestLog(
					"Verify the UI of search results panel in 'Database Search - PCS' dash-let",
					"UI of search results panel in 'Database Search - PCS' dash-let not as expected",
					Status.FAIL);
		}

		UIHelper.waitFor(driver);
		UIHelper.waitForVisibilityOfEleByXpath(driver, pcsObj.chooseRecord);
		UIHelper.click(driver, pcsObj.chooseRecord);
		pcsTest.verifypromptMsg("Ok");
		

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}