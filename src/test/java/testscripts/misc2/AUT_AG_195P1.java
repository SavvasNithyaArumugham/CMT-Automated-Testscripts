package testscripts.misc2;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
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
public class AUT_AG_195P1 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC2_088()
	{
		testParameters.setCurrentTestDescription("When a document is being followed, then the follower will receive an indication by email that a new version has been uploaded<br> - Part1: Open Site and follow the uploaded file");
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
		AlfrescoSiteMembersPage siteMemPg = new AlfrescoSiteMembersPage(scriptHelper);

		String siteName = dataTable.getData("Sites", "SiteName");

		homePageObj.navigateToSitesTab();

		sitesPage.openSiteFromRecentSites(siteName);
		
		sitesPage.performInviteUserToSite(siteName);

		sitesPage.enterIntoDocumentLibrary();
		
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		try{
			docLibPg.deleteAllFilesAndFolders();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		myFilesTestObj.verifyUploadedMultipleFiles(fileName);
		docLibPgObj.selectDocLibViewType("Detailed View");
		docLibPgObj.clickOnFollowOption(fileName);
		
		docLibPgTest.verifyUnfollowLinkForFile(fileName);
		
/*		String userName = dataTable.getData("Sites", "InviteUserName");
		String roleName = dataTable.getData("Sites", "Role");
		
		boolean isDisplayedSiteMember = siteMemPg.isDisplayedRequiredUserInSiteMembersList(userName, roleName);
		
		if(!isDisplayedSiteMember)
		{
			sitesPage.performInviteUserToSite(siteName);
		}*/
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}