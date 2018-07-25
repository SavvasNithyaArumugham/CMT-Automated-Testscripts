package testscripts.objectmodel;

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
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_013P1 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void objectmodel_013()
	{
		testParameters.setCurrentTestDescription("1. Verify that user is able to add Archive aspect to any uploaded asset in the Document Library"
				+ "<br>2. Verify that user is able to  add Archive aspect to any created asset in the Document Library"
				+ "<br>3. Verify the attributes available for files or folders after applying Archive Aspect in Document Library");
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
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoSitesPageTest sitePgTestObj = new AlfrescoSitesPageTest(scriptHelper);
		
		homePageObj.navigateToSitesTab();
		
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		
		sitesPage.enterIntoDocumentLibrary();
		
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
		
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		myFilesTestObj.verifyUploadedMultipleFiles(fileName);
		
		myFiles.openUploadedOrCreatedFile(fileName,"");
		
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);		
		docDetailsPageObj.performManageAspectsDocAction();
		
		AlfrescoDocumentDetailsPageTest docDetailsPageTestObj = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		docDetailsPageTestObj.verifyAspectsAvailable();
		
		docDetailsPageObj.performManageAspectsDocAction();
		docDetailsPageObj.addAspectsAndApllyChangesToAFile();
		docDetailsPageObj.performManageAspectsDocAction();
		docDetailsPageTestObj.verifyAppliedAspects();
		
		sitesPage.clickOnCancelBtnInManageAspectsPopup();
		
		sitesPage.enterIntoDocumentLibrary();
		
		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");

		ArrayList<String> createdFileNames = myFilesTestObj
				.getCreatedFileNames(fileDetails);
		
		for (String fileNameVal : createdFileNames) {

			myFiles.createFile(fileDetails);
			docDetailsPageTestObj.verifyCreatedFile(fileNameVal, "");
		
			docDetailsPageObj.performManageAspectsDocAction();
			docDetailsPageTestObj.verifyAspectsAvailable();
			docDetailsPageObj.addAspectsAndApllyChangesToAFile();
		
			docDetailsPageObj.performManageAspectsDocAction();
			docDetailsPageTestObj.verifyAppliedAspects();
			
			sitesPage.clickOnCancelBtnInManageAspectsPopup();
		}
		
		sitesPage.enterIntoDocumentLibrary();
		
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		
		myFiles.createFolder(folderDetails);
			
		myFilesTestObj.verifyCreatedFolder(folderDetails);
		
		ArrayList<String> folderNamesList = myFiles.getFolderNames(folderDetails);
		
		for(String folderName:folderNamesList)
		{
			sitesPage.clickOnMoreSetting(folderName);
			
			String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
			
			sitePgTestObj.verifyMoreSettingsOptionForFileOrFolderItem(folderName, moreSettingsOption);
			
			docLibPg.commonMethodForClickOnMoreSettingsOption(folderName, moreSettingsOption);
			
			docDetailsPageTestObj.verifyAspectsAvailable();
			
			docDetailsPageObj.addAspectsAndApllyChangesToAFile();
			
			sitesPage.clickOnMoreSetting(folderName);
			docLibPg.commonMethodForClickOnMoreSettingsOption(folderName, moreSettingsOption);
			docDetailsPageTestObj.verifyAppliedAspects();
			
			docDetailsPageObj.clickCancelBtnInAspectsPopup();
			
			sitesPage.clickOnEditProperties(folderName);
			docDetailsPageObj.clickAllProperties();
			
			docDetailsPageObj.enterDataAndSaveIntoEditProperties();
			
			sitesPage.clickOnViewDetails(folderName);
			
			String aspectName = dataTable.getData("Document_Details","AspectName");
			String aspectAttributesWithValues = dataTable.getData("Document_Details","DocProperties");
			docDetailsPageTestObj.verifyAttributesInPropertySec(aspectAttributesWithValues, aspectName);
		}
	
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}