package testscripts.healthcheck;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_041 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runTC53()
	{
		testParameters.setCurrentTestDescription("Verify that user should be able to unfollow the followed asset while clicking on 'X' sign while hovering over the Unfollow option"
				+"<br>2.Verify that user should be able to view 'X' sign while hovering over the Unfollow option"
				+"<br>3.Verify that user should be able to unfollow the followed asset while clicking on 'X' sign while hovering over the Unfollow option");
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
		functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);

		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		
		AlfrescoDocumentLibPage docLibPgObj = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoDocumentLibPageTest docLibPgTest = new AlfrescoDocumentLibPageTest(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);

		String sourceSiteName = dataTable.getData("Sites", "SiteName");

		homePageObj.navigateToSitesTab();

		sitesPage.openSiteFromRecentSites(sourceSiteName);

		sitesPage.enterIntoDocumentLibrary();
		
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");

		ArrayList<String> createdFileNames = myFilesTestObj
				.getCreatedFileNames(fileDetails);

		for (String fileName : createdFileNames) {
			myFiles.deleteUploadedFile(fileName);

			myFiles.createFile(fileDetails);
			
			docDetailsPageTest.verifyCreatedFile(fileName, "");

			docDetailsPageObj.backToFolderOrDocumentPage("");
			
             docLibPgObj.clickOnFollowOption(fileName);
			
			docLibPgTest.verifyXOptionInFollowedFiles(fileName);
			
			docLibPgTest.verifyUnfollowLinkForFile(fileName);
			
			docLibPgObj.clickOnUnfollowOption(fileName);
			
			docLibPgTest.verifyFollowLinkForFile(fileName);
		}
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}