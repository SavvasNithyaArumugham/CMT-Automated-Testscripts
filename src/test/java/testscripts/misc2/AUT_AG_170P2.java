package testscripts.misc2;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_170P2 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC2_065()
	{
		testParameters.setCurrentTestDescription("Verify that user did not receive any Email notification when user perform any operation other than Edit Offline,Edit Inline,Upload New version and Delete Document<br> - Part2:Follow the same file with another site member and perform edit offline");
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
		AlfrescoDocumentLibPage docLibPgObj = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoDocumentLibPageTest docLibPgTest = new AlfrescoDocumentLibPageTest(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);

		String siteName = dataTable.getData("Sites", "SiteName");
		
		homePageObj.navigateToSitesTab();

		sitesPage.openSiteFromRecentSites(siteName);

		sitesPage.enterIntoDocumentLibrary();
		
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		docLibPgObj.deleteAllFilesAndFolders();
		docLibPgObj.selectDocLibViewType("Detailed View");
        myFiles.createFolder(folderDetails);
		
		myFilesTestObj.verifyCreatedFolder(folderDetails);
		
		String fileName = dataTable.getData("MyFiles", "FileName");
		
		myFilesTestObj.verifyUploadedMultipleFiles(fileName);
		
		docLibPgObj.clickOnFollowOption(fileName);
		
		docLibPgTest.verifyUnfollowLinkForFile(fileName);
		
		sitesPage.clickOnMoreSetting(fileName);
		
		String moreSettOptVal = dataTable.getData("MyFiles", "MoreSettingsOption");
		
		AlfrescoDocumentLibPage docLibPg =new AlfrescoDocumentLibPage(scriptHelper);
		
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, moreSettOptVal);
		
		ArrayList<String> folderNamesList = myFiles.getFolderNames(folderDetails);
		
		for(String folderName:folderNamesList)
		{
			docLibPg.performCopyToOperation(siteName, folderName);
		}
		
		myFiles.openUploadedOrCreatedFile(fileName, "");
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}