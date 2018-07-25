package testscripts.misc1;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Moshin
 */
public class AUT_AG_026 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void MISC1_026() {
		testParameters
				.setCurrentTestDescription("Verify that user is not able to perform download,upload new version and Edit Offline for the Web Resource");
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
			
			String webName = dataTable.getData("WebResource", "FileName");
			String webURL = dataTable.getData("WebResource", "Help URL");
			String type = dataTable.getData("WebResource", "FilePath");
			
			AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
			AlfrescoSitesPageTest sitePageTest = new AlfrescoSitesPageTest(scriptHelper);
			if(sitesPage.Checkdocument(webName)){
				
			}else {
				myFiles.createcontenttype(type);
				myFiles.createWebResource(webName, webURL);
			}
			sitesPage.clickOnMoreSetting(webName);
			sitePageTest.verifyBrowseActionNameForFileOrFolderInNegativeCase(webName, "Edit Offline");
			sitePageTest.verifyBrowseActionNameForFileOrFolderInNegativeCase(webName, "Download");
			sitePageTest.verifyBrowseActionNameForFileOrFolderInNegativeCase(webName, "Upload New Version");
			
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}