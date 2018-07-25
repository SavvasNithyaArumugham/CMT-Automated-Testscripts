package testscripts.mdm;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyTasksPageTest;
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
public class AUT_AG_392 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC5_01()
	{
		testParameters.setCurrentTestDescription("1.ALFDEPLOY-1130_Verify user can upload new version of the normal file(image,video,audio,documents) successfully without any notification when same mimetype and filename is chosen for upload new version<br>"
				+"2. ALFDEPLOY-1130_Verify the name of the file chosen for upload new version is displayed in update file window upon clicking upload new version for normal file.<br>"
				+"3. ALFDEPLOY-1130_Verify notification is displayed upon uploading a file with different name than the original normal file in Alfresco for new version");
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
		

		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		String siteName = dataTable.getData("Sites", "SiteName");
		sitesPage.siteFinder(siteName);
		
		String fileName = dataTable.getData("MyFiles", "FileName");
		String upload = dataTable.getData("MyFiles", "CreateFileDetails");
		String action = dataTable.getData("Document_Details", "Title");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String versionDetails = dataTable.getData("Document_Details", "Version");
		String comments = dataTable.getData("Document_Details", "Comments");
		
		sitesPage.enterIntoDocumentLibrary();
		
		docLibPge.deleteAllFilesAndFolders();
		
	
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		sitesPage.documentdetails(fileName);
		
		docDetailsPageTest.verifyCreatedFile(fileName, "");
		
		sitesPage.enterIntoDocumentLibrary();
		
		sitesPage.clickOnMoreSetting(fileName);
		
		docLibPge.commonMethodForClickOnMoreSettingsOption(fileName, action);
	
		docLibPge.uploadNewVersionInDocLibPage(fileName,
				filePath, versionDetails, comments);
		
	/*	sitesPage.documentdetails(fileName);
	
		AlfrescoMyTasksPageTest taskPageTest = new AlfrescoMyTasksPageTest(scriptHelper);
		//Verify that the latest version listed (top of list) of assets associate with task
		taskPageTest.verifyUploadedFileWithLastVersion(fileName);*/
		
		
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		
		sitesPage.clickOnMoreSetting(fileName);
		
		docLibPge.commonMethodForClickOnMoreSettingsOption(fileName, action);
	
		docLibPge.uploadNewVersionInDocLibPage(upload,
				filePath, versionDetails, comments);
		
		docLibPge.uploadNewnotification("Filename",upload,"new");
		
		docLibPge.uploadNewVersionButton();
		
		sitesPage.clickOnMoreSetting(upload);
		
		docLibPge.commonMethodForClickOnMoreSettingsOption(upload, action);
		
		docLibPge.uploadNewVersionInDocLibPage(fileName,
				filePath, versionDetails, comments);
		
		docLibPge.uploadNewnotification("Filename",upload,"current");
		
		docLibPge.uploadNewnotification("Filename",fileName,"new");
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}