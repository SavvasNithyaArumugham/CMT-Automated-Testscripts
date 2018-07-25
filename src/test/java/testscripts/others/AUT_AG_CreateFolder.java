package testscripts.others;

import java.util.ArrayList;

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
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Naresh Kumar Salla
 */
public class AUT_AG_CreateFolder extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void runAUT_AG_CNF() {
		testParameters
				.setCurrentTestDescription("Create folder level upto 500 levels in alfresco");
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
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(
				scriptHelper);

		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		
		homePage.navigateToSitesTab();
		
		sitesPage.openSiteFromRecentSites(siteNameValue);
		sitesPage.enterIntoDocumentLibrary();

		String folderName = "", folderTitle = "", folderDescription = "";
		
		if (folderDetails.contains(",")) {
			String splittedFolderValues[] = folderDetails.split(",");

			folderName = splittedFolderValues[0].replace("FolderName:", "");
			folderTitle = splittedFolderValues[1].replace("Title:", "");
			folderDescription = splittedFolderValues[2].replace("Description:",
					"");
			for (int index = 1; index <= 500; index++) {
				myFiles.commonMethodForCreateFolder(folderName+index,
						folderTitle + index, folderDescription + index);
			}
		}
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}