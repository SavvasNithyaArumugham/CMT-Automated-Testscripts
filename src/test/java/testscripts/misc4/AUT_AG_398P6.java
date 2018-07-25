package testscripts.misc4;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_398P6 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runTC030P1()
	{
		testParameters.setCurrentTestDescription("ALFDEPLOY-4400-Verify \"Unzip to\" option is not displayed for a user who is having Consumer access for a site on Document Details page for a content created by other user.");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}
	
	@Override
	public void setUp()
	{
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");
		
		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("ApplicationUrl"), Status.DONE);
		
	}
	
	@Override
	public void executeTest()
	{
		
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoSitesPageTest sitePageTest = new AlfrescoSitesPageTest(scriptHelper);
	
		String fileName = dataTable.getData("MyFiles", "FileName");
		String fileName1 = dataTable.getData("MyFiles", "Version");
		String folder2 = dataTable.getData("MyFiles", "CreateFolder");
		
		
		functionalLibrary.loginAsValidUser(signOnPage);
		
		String siteName = sitesPage.getCreatedSiteName();
		sitesPage.siteFinder(siteName);
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.documentdetails(fileName1);
		
		
try {
	boolean isDisplayedBrowseActionName = sitesPage.checkFileOrFolderBrowseOption(fileName,
			"Unzip");

	if (!isDisplayedBrowseActionName) {
		report.updateTestLog(
				"Verify the " + "Unzip" + "Option for any files or folder from Browse Actions",
				"User is not able to see the 'Browse Action' name:" + "<b>" + "Unzip" + "</b>",
				Status.PASS);
	} else {
		report.updateTestLog(
				"Verify the " + "Unzip" + "Option for any files or folder from Browse Actions",
				"User is able to see the 'Browse Actions' name:" + "<b>" + "Unzip" + "</b>", Status.FAIL);
	}
} catch (Exception e) {
	e.printStackTrace();
}
		
		sitesPage.enterIntoDocumentLibrary();
		
		sitesPage.documentdetails(folder2);
		sitesPage.documentdetails(fileName);
		sitePageTest.verifyBrowseActionNameForFileOrFolderInNegativeCase(fileName,"Unzip");

	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}