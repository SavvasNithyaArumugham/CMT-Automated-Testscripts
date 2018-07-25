   package testscripts.release184;



import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoShareboxPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_049 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runAUT_AG_049()
	{
		testParameters.setCurrentTestDescription("verify whether user is able to upload/create files when sharing not done completely");
		
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
        AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoSitesPageTest sitesPageTests = new AlfrescoSitesPageTest(scriptHelper);
		AlfrescoDocumentLibPage docLibPg =new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		AlfrescoShareboxPageTest shareboxPgTest = new AlfrescoShareboxPageTest(scriptHelper);
		//Login
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		
		// Navigate to site and upload file
		homePage.navigateToSitesTab();
		
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		
		sitesPage.enterIntoDocumentLibrary();
         
	    docLibPg.deleteAllFilesAndFolders();
		
		//Upload File
	    String folderDetails = dataTable.getData("MyFiles", "CreateFileDetails");
	   String folderName= dataTable.getData("MyFiles", "CreateFolder");
	   
	    myFiles.createFolder(folderDetails);	
		myFilesTestObj.verifyCreatedFolder(folderDetails);
		
		//Click On Sharebox Fucntionality
		
		String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
		
        sitesPage.clickOnMoreSetting(folderName);
		
		sitesPageTests.verifyMoreSettingsOptionForFileOrFolderItem(folderName, moreSettingsOption);
		
		docLibPg.commonMethodForClickOnMoreSettingsOption(folderName, moreSettingsOption);
		
		docLibPg.clickOnOkBtnInPopup();
		
		shareboxPg.clickOnCancelBtnInSharingPopup();
		
		shareboxPgTest.verifyincompleteBannerisDisplayed(folderName);
		
		myFiles.openCreatedFolder(folderName);
		
		///Creating File Inside folder
		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		myFiles.uploadFile(filePath, fileName);
		
		myFilesTestObj.verifyUploadedFile(fileName, "");
		
		
		}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}