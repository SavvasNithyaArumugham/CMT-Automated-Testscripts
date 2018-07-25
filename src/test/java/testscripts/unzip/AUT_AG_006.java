package testscripts.unzip;

import java.util.ArrayList;
import java.util.regex.Pattern;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_006 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void unzip_06()
	{
		testParameters.setCurrentTestDescription("Verify user is able to perform 'Copy to, Move to, Delete Folder, Start workflow, Link to' operations on a extracted Zipped content");
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
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);
		AlfrescoTasksPage taskPage = new AlfrescoTasksPage(scriptHelper);
		
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		
		homePageObj.navigateToSitesTab();
		
		sitesPage.openSiteFromRecentSites(sourceSiteName);

		sitesPage.enterIntoDocumentLibrary();
		
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		try{
			docLibPg.deleteAllFilesAndFolders();
			docLibPg.deleteAllFilesAndFolders();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String zipFileName = dataTable.getData("MyFiles", "FileName");
		
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
        myFiles.createFolder(folderDetails);
		
		String splitVal[] = zipFileName.split(Pattern.quote("."));
		String zippedFolderName = splitVal[0];
		
		myFiles.uploadFileInMyFilesPage(filePath, zipFileName);
		myFilesTestObj.verifyUploadedFile(zipFileName, "");		
		myFiles.openUploadedOrCreatedFile(zipFileName, "");

		docDetailsPageTest.verifyUploadedFileIsOpened(zipFileName, "");

		String extractTo = dataTable.getData("Document_Details", "ExtractTo");
		docDetailsPageObj.performUnzipDocAction(extractTo);
		docDetailsPageObj.backToFolderOrDocumentPage("");
		sitesPage.enterIntoDocumentLibrary();
		myFilesTestObj.verifyUnzippedFolder(zippedFolderName);
		
		sitesPage.clickOnMoreSetting(zippedFolderName);		
		String moreSettOptVal = dataTable.getData("MyFiles", "MoreSettingsOption");	
		String[] moreOptions = moreSettOptVal.split(",");
		docLibPg.commonMethodForClickOnMoreSettingsOption(zippedFolderName, moreOptions[0]);
		
		ArrayList<String> folderNamesList = myFiles.getFolderNames(folderDetails);
		docLibPg.performCopyToOperation(sourceSiteName, folderNamesList.get(0));
		
		UIHelper.waitFor(driver);
		sitesPage.clickOnMoreSetting(zippedFolderName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(zippedFolderName, moreOptions[2]);
		docLibPg.performLinkToOperation(sourceSiteName, folderNamesList.get(2));
		
		UIHelper.waitFor(driver);
		String taskType = dataTable.getData("Workflow", "Tasktype");
		String message = dataTable.getData("Workflow", "Message");
		//String dueDate = dataTable.getData("Workflow", "DueDate");
		String dueDate = new DateUtil().getCurrentDate();
		String priority = dataTable.getData("Workflow", "Priority");

		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		
		sitesPage.clickOnMoreSetting(zippedFolderName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(zippedFolderName, moreOptions[3]);
		
		taskPage.submitWorkFlow(taskType, message, dueDate, priority);
		taskPage.selectApprover();
		taskPage.clickStartWorkflowBtn();
		
		UIHelper.waitFor(driver);
		myFiles.deleteCreatedFolder(zippedFolderName);
		if(!docLibPg.isFileIsAvailable(zippedFolderName)){
			report.updateTestLog("Verify the UnZipped folder deleted or not",
					"UnZipped folder deleted Successfully"
					+"</br> <b>Folder Name : </b>" +zippedFolderName , Status.PASS);
		}else{
			report.updateTestLog("Verify the UnZipped folder deleted or not",
					"UnZipped folder not able to delete"
					+"</br> <b>Folder Name : </b>" +zippedFolderName , Status.FAIL);
		}
		
		myFiles.openUploadedOrCreatedFile(zipFileName, "");
		docDetailsPageObj.performUnzipDocAction(extractTo);
		docDetailsPageObj.backToFolderOrDocumentPage("");
		sitesPage.enterIntoDocumentLibrary();
		
		UIHelper.waitFor(driver);
		sitesPage.clickOnMoreSetting(zippedFolderName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(zippedFolderName, moreOptions[1]);
		docLibPg.performMoveToOperation(sourceSiteName, folderNamesList.get(1));
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}