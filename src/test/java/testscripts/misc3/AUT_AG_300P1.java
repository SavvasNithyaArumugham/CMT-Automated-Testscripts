package testscripts.misc3;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_300P1 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void misc3_071()
	{
		testParameters.setCurrentTestDescription("Verify that Reviewer user is able to view the folder details - Part1: Invite/Add internal user to site using 'Manager' role and check invited user directly added to site through 'Site Members'");
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
		
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		homePage.navigateToSitesTab();
		
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		sitesPage.createSite(siteNameValue, "Yes");
		String siteName=sitesPage.getCreatedSiteName();
		
		sitesPage.openSiteFromRecentSites(siteName);
		
		AlfrescoSitesPageTest siteTestObj = new AlfrescoSitesPageTest(scriptHelper);
		siteTestObj.verifyCreatedSite();
		
		sitesPage.inviteUserForSite();
		
		String invitedUserName = dataTable.getData("Sites",
				"InviteUserName");
		String role = dataTable.getData("Sites", "Role");
		
		AlfrescoSiteMembersPageTest siteMembPgTest = new AlfrescoSiteMembersPageTest(scriptHelper);
		siteMembPgTest.verifySiteMemebrs(siteName, invitedUserName, role);
		
		sitesPage.enterIntoDocumentLibrary();
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		
		String fileName = dataTable.getData("MyFiles", "FileName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		
	    myFiles.createFolder(folderDetails);
		sitesPage.enterIntoDocumentLibrary();
		
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);
		myFilesTestObj.verifyUploadedMultipleFiles(fileName);
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}