package testscripts.misc4;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_395P1 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runTC030P1()
	{
		testParameters.setCurrentTestDescription("1. ALFDEPLOY-3745_Verify Manager or Coordinator or Collaborator or Contributor can delete the own folder containing own content which is locked by some other user-via More or Document actions.<br> - Part1: Login with User A and Open Site and create a file");
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
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSiteMembersPageTest siteMemPgTest = new AlfrescoSiteMembersPageTest(
				scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(scriptHelper);
		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String folder = dataTable.getData("MyFiles", "Version");
		String siteName = dataTable.getData("Sites", "SiteName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folder3 = dataTable.getData("MyFiles", "CreateChildFolder");
		String action = dataTable.getData("MyFiles", "MoreSettingsOption");
		String siteuserName = dataTable.getData("Sites", "InviteUserName");
		String roleName = dataTable.getData("Sites", "Role");
		
		
		functionalLibrary.loginAsValidUser(signOnPage);
		
		
		homePage.navigateToSitesTab();
		sitesPage.createSite(siteName, "Yes");
		
		String site=sitesPage.getCreatedSiteName();
		
	
		sitesPage.performInviteUserToSite(site);

		siteMemPgTest.verifySiteMemebrs(site, siteuserName, roleName);
		
		sitesPage.enterIntoDocumentLibrary();
	
		myFiles.createFolder(folderDetails);
		
		sitesPage.documentdetails(folder);
		
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		myFilesTestObj.verifyUploadedMultipleFiles(fileName);
		
		
		sitesPage.enterIntoDocumentLibrary();
	
		
		myFiles.createFolder(folderDetails);
		
		sitesPage.documentdetails(folder3);
		
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		myFilesTestObj.verifyUploadedMultipleFiles(fileName);
		
		sitesPage.clickOnMoreSetting(fileName);

		docLibPge.commonMethodForClickOnMoreSettingsOption(fileName, action);
		
		if(docLibPge.isOfflineLockMsgDisplayed()){
			report.updateTestLog(
					"Verify the user is able to lock a file",
					"File Locked successfully."
							+ "<br><b> Locked File Name </b> :"
							+ fileName, Status.PASS);
		}else{
			report.updateTestLog(
					"Verify the user is able to lock a file",
					"File not Locked."
							+ "<br><b> File Name </b> :"
							+ fileName, Status.FAIL);
		}
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}