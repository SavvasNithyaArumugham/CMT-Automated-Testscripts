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
public class AUT_AG_28 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void pcs_21() {
		testParameters
		.setCurrentTestDescription("1. Verify whether the Search Result count in Alfresco PCS dashlet and PCS site are same - Alfresco Site"
				+ "<br>2. Verify the values corresponds to each field of PCS record in Alfresco PCS Dashlet and PCS Site matches for same search input - Alfresco Site");

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

		try {
			AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
			functionalLibrary.loginAsValidUser(signOnPage);

			AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
			AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
			AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(
					scriptHelper);
			AlfrescoPCSPage pcsObj = new AlfrescoPCSPage(scriptHelper);
			AlfrescoPCSPageTest pcsTest = new AlfrescoPCSPageTest(
					scriptHelper);

			String fileNme = dataTable.getData("MyFiles", "FileName");
			String siteassertValue = dataTable.getData("Sites", "SiteName");
			String rootFolderDetails = dataTable.getData("MyFiles",
					"CreateFolder");
			String inputname = dataTable
					.getData("MyFiles", "CreateFileDetails");
		
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

			AlfrescoSitesDashboardPage siteDashboard = new AlfrescoSitesDashboardPage(
					scriptHelper);
			siteDashboard.navigateToSiteDashboard();
			pcsObj.checkiteminPCSDashlet(fileNme);
			pcsObj.searchisbnPCS("0133405877");
			pcsObj.clickbutton("Go");
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, pcsObj.chooseRecord);
			

			ArrayList<String> inputList = myFiles.getFolderNames(inputname);
			ArrayList<String> labelList = new ArrayList<String>();
			ArrayList<String> dashletList = new ArrayList<String>();
			

			for (String input : inputList) {
				String finallabelValueXpath = pcsObj.labelvalueXpath.replace(
						"CRAFT", input);
				UIHelper.waitForVisibilityOfEleByXpath(driver,
						finallabelValueXpath);
				if (!UIHelper
						.findAnElementbyXpath(driver, finallabelValueXpath)
						.getAttribute("value").isEmpty()) {
					dashletList.add(UIHelper.findAnElementbyXpath(driver,
							finallabelValueXpath).getAttribute("value"));
				}
				
			}
			UIHelper.findAndAddElementsToaList(driver, pcsObj.labellistXpath, labelList);
			String testOutputFilePath = "src/test/resources/AppTestData/TestOutput/PCSDetails.txt";
			new FileUtil().writeListTextToFile(dashletList, testOutputFilePath);
			
			UIHelper.click(driver, pcsObj.chooseRecord);
			pcsTest.verifypromptMsg("Ok");
			UIHelper.waitFor(driver);

		} catch (Exception e) {

			report.updateTestLog(
					"Verify the aspects of an archive file/folder matches with the PCS record",
					"Verify the aspects of an archive file/folder matches with the PCS record failed",
					Status.FAIL);
		}
		
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}