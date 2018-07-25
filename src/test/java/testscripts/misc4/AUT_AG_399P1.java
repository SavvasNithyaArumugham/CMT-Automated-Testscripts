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
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_399P1 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runTC030P1()
	{
		testParameters.setCurrentTestDescription("ALFDEPLOY-2922_Verify Download option is visible for user having Manager role at a site and is able to Download file from the Preview page");
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
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(
				scriptHelper);	
		
		String siteName = dataTable.getData("Sites", "SiteName");
		String fileNames = dataTable.getData("MyFiles", "FileName");
		String file1 = dataTable.getData("MyFiles", "CreateFolder");
		String file2 = dataTable.getData("MyFiles", "CreateFileDetails");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
		String downloadopt = dataTable.getData("MyFiles", "BrowseActionName");
		
		functionalLibrary.loginAsValidUser(signOnPage);
		
		homePageObj.navigateToSitesTab();
		sitesPage.createSite(siteName, "Yes");
		sitesPage.performInviteUserToSite(siteName);
		sitesPage.enterIntoDocumentLibrary();
		
		myFiles.uploadFileInMyFilesPage(filePath, fileNames);
		sitesPage.documentdetails(file1);
		
		docDetailsTest.verifyElementPresent(docDetailsTest.downloadbutton,downloadopt);
		
		sitesPage.enterIntoDocumentLibrary();
		
		sitesPage.clickOnMoreSetting(file2);
		docLibPg.commonMethodForClickOnMoreSettingsOption(file2,
					moreSettingsOption);
			
			sitesPage.clickOnAddUserGroupButton("ALF01");
			sitesPage.clickOnUserRole("Reviewer", "ALF01");
			
			sitesPage.clickOnAddUserGroupButton("ALF02");
			sitesPage.clickOnUserRole("Reviewer", "ALF02");
			
			/*sitesPage.clickOnAddUserGroupButton("ALF03");
			sitesPage.clickOnUserRole("Reviewer", "ALF03");
			
			sitesPage.clickOnAddUserGroupButton("ALF02");
			sitesPage.clickOnUserRole("Reviewer", "ALF02");
		
*/			docDetailsPage.removeInheritPermissions();

	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}