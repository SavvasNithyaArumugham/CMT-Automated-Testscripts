package testscripts.marklogic;

import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAdminToolsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMarklogicPageTest;
import com.pearson.automation.utils.CC_API_Helper;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Lokesh
 */
public class AUT_AG_001 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void MARKLOGIC_001() {
		testParameters.setCurrentTestDescription("VVerify if 'Add ResourceContainer Aspect' is visible under More option for the user who is member of 'Resource Container Manager' group and able to add the aspect to empty folder");
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
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoAdminToolsPage adminPg = new AlfrescoAdminToolsPage(
				scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(
				scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);

		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folderName = dataTable.getData("MyFiles", "Version");
		String aspect = dataTable.getData("MyFiles", "RelationshipName");
		
		String moreSetOpt = dataTable.getData("MyFiles", "MoreSettingsOption");
		String[] moreSetOptions = moreSetOpt.split(",");
		String siteNameValue = dataTable.getData("Sites", "SiteName");
	
		sitesPage.siteFinder(siteNameValue);
		sitesPage.enterIntoDocumentLibrary();
		
		docLibPage.deleteAllFilesAndFolders();
		myFiles.createFolder(folderDetails);

		sitesPage.clickOnMoreSetting(folderName);
		if (sitesPage.checkMoreSettingsOption(folderName, moreSetOptions[0])) {
			report.updateTestLog("Verify '" + moreSetOptions[0]
					+ "' aspect for empty folder under 'More' option",
					"Verified Successfully", Status.PASS);
			/*sitesPage.commonMethodForClickOnMoreOptionLink(folderName,
					moreSetOptions[0]);*/
			
			docLibPg.commonMethodForClickOnMoreSettingsOption(folderName, moreSetOptions[0]);

			sitesPage.enterIntoDocumentLibrary();
			sitesPage.clickOnViewDetails(folderName);
			
			String markLogicUrl = myFiles.getMarkLogicUrl();
			
			homePage.navigateToAdminTab();
			adminPg.navigateToNodeBrowserTab();
			adminPg.searchNodeBrowserResults(markLogicUrl);
			adminPg.navigateToNodeBrowserDetailsPg(folderName);
			AlfrescoMarklogicPageTest marklogic = new AlfrescoMarklogicPageTest(
					scriptHelper);
			
			marklogic.verifyResourceaspect(aspect);
		}

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}