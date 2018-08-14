package testscripts.misc4;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyActivitiesPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_315P1 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void misc4_015() {
		testParameters
				.setCurrentTestDescription("Verify that Reviewer is not be able to view original version of the content.<br>"+
		"Verify that Reviewer is not be able to download working copy of the file from alfresco.<br>"
		+"Part 1: Create site and invite a member as reviewer");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);

		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(
				scriptHelper);
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		sitesPage.siteFinder(siteNameValue);
		sitesPage.siteMembersPage();
		sitesPage.inviteUserForSite();
		sitesPage.documentlib();
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		docLibPg.deleteAllFilesAndFolders();
		
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		myFilesTestObj.verifyUploadedFile(fileName,"");		
		
		docLibPage.openAFile(fileName);
		
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);
		docDetailsPage.uploadNewVersionFileInDocumentDetailsPage();
		docDetailsPage.uploadNewVersionFileInDocumentDetailsPage();

	}

	@Override
	public void tearDown() {

	}
}