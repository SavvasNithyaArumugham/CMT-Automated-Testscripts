package testscripts.misc4;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Moshin
 */
public class AUT_AG_363 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void runAUT_AG_671() {
		testParameters
				.setCurrentTestDescription("Verify that user is able to Edit property of WebResource");
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
			AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
			homePageObj.navigateToMyFilesTab();
			
			String webName = dataTable.getData("Gmail", "Username");
			String webURL = dataTable.getData("Gmail", "MailBodyText");
			String type = dataTable.getData("Parametrized_Checkpoints", "FilePath");
			
			AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(scriptHelper);
			docLibPge.deleteAllFilesAndFolders();
			
			myFiles.createcontenttype(type);
			myFiles.createWebResource(webName, webURL);
			
			AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
			String browseOption = dataTable.getData("MyFiles", "BrowseActionName");
			sitesPage.commonMethodForPerformBrowseOption(
					webName, browseOption);
			
			String eWebName = dataTable.getData("Parametrized_Checkpoints", "FileName");
			String eWebURL = dataTable.getData("Parametrized_Checkpoints", "Help URL");
			myFiles.editWebResource(eWebName, eWebURL);
			
			sitesPage.documentdetails(eWebName);
			
			AlfrescoHomePageTest homePageTestObj = new AlfrescoHomePageTest(
					scriptHelper);
			homePageTestObj.verifyHelpURL();
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}