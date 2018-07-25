   package testscripts.release184;



import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
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
public class AUT_AG_050 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runAUT_AG_048()
	{
		testParameters.setCurrentTestDescription("verify whether user is able to create folder from View Details page for incomplete sharing folder");
		
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
		AlfrescoDocumentDetailsPageTest docDetailsPageTest= new AlfrescoDocumentDetailsPageTest(scriptHelper);
		
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
	   
	    String splittedFolderDetails[] = folderDetails.split(";");
	    
	    String folderName= dataTable.getData("MyFiles", "CreateFolder");
	    String splittedFolderName[] = folderName.split(",");
	    
	    
		myFiles.createFolder(splittedFolderDetails[0]);	
		myFilesTestObj.verifyCreatedFolder(splittedFolderDetails[0]);
		
		//Click On Sharebox Fucntionality
		
		String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
		String splittedMoreSettingsOption[] = moreSettingsOption.split(",");
		
        sitesPage.clickOnMoreSetting(splittedFolderName[0]);
		
		sitesPageTests.verifyMoreSettingsOptionForFileOrFolderItem(splittedFolderName[0], moreSettingsOption);
		
		docLibPg.commonMethodForClickOnMoreSettingsOption(splittedFolderName[0], splittedMoreSettingsOption[0]);
		
		docLibPg.clickOnOkBtnInPopup();
		
		shareboxPg.clickOnCancelBtnInSharingPopup();
		
		shareboxPgTest.verifyincompleteBannerisDisplayed(splittedFolderName[0]);
		
		//Select view Details
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnViewDetails(splittedFolderName[0]);
		
		docDetailsPageTest.verifyShareBoxIncompleteBannerisDisplayed(splittedFolderName[0]);	
		
		sitesPage.enterIntoDocumentLibrary();
		
		///Creating Folder Inside folder
		
		myFiles.openCreatedFolder(splittedFolderName[0]);
		
		myFiles.createFolder(splittedFolderDetails[1]);
		myFilesTestObj.verifyCreatedFolder(splittedFolderName[1]);
		
		}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}