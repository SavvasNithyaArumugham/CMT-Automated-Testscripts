package testscripts.healthcheck;

import java.util.ArrayList;

import org.apache.commons.lang3.time.StopWatch;
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
public class AUT_AG_004 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runAUT_AG_011()
	{
		testParameters.setCurrentTestDescription("1. Verify that Classifiable Aspect is not applied for the newly created files and folders by default"
				+ "<br>2. Verify that Classifiable Aspect is not applied for the newly uploaded files"
				+ "<br>3. Verify that Versionable Aspect is applied for the newly created file"
				+ "<br>4. Verify that Versionable Aspect is applied for the newly uploaded file"
				+ "<br>5. Verify that user is able to see only below listed aspects in 'Select Aspect' list for a file/folder :- "
				+"		<br>a) Inline Editable"
				+"		<br>b) Geographic"
				+"		<br>c) Exif"
				+"		<br>d) Audio"
				+"		<br>d) Program"
				+"		<br>d) Program Component"
				+"		<br>d) ISBN"
				+"		<br>d) Taggable"
				+"		<br>d) Classifiable"
				+"		<br>d) Dublin Core"
				+"		<br>d) Versionable"
				+"		<br>d) Product Store Component"
				+"		<br>d) QS Product Store Component"
				+"		<br>d) Content Asset"
				+"<br>6. Verify the default Versionable Aspect should not impact on the time taken for creating files and Folder");
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
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		StopWatch pageLoad = new StopWatch();
		
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String aspectNames = dataTable.getData("Document_Details", "AspectName");
		
		String aspectName1="", aspectName2="";
		if(aspectNames.contains(","))
		{
			String splittedAspectNames[] = aspectNames.split(",");
			if(splittedAspectNames!=null && splittedAspectNames.length>1)
			{
				aspectName1 = splittedAspectNames[0];
				aspectName2 = splittedAspectNames[1];
			}
			else
			{
				aspectName1 = "Classifiable (cm:generalclassifiable)";
				aspectName2 = "Versionable (cm:versionable)";
			}
		}
		else
		{
			aspectName1 = "Classifiable (cm:generalclassifiable)";
			aspectName2 = "Versionable (cm:versionable)";
		}
		
		homePageObj.navigateToSitesTab();
		
		sitesPage.openSiteFromRecentSites(sourceSiteName);

		sitesPage.enterIntoDocumentLibrary();
		
		docLibPg.deleteAllFilesAndFolders();
		
		pageLoad.reset();
		pageLoad.start();
		myFiles.createFolder(folderDetails);
		pageLoad.stop(); 
		long folderCreatedTime_ms = pageLoad.getTime();
		long folderCreatedTime_sec = (folderCreatedTime_ms-6000)/1000;
		report.updateTestLog("Time taken to Create Folder", "<b>Time Taken : </b>" + folderCreatedTime_sec + " sec",
				Status.DONE);
		
        
		//myFilesTestObj.verifyCreatedFolder(folderDetails);
		
		sitesPage.performManageAspectsOnFolder();
		
		AlfrescoSitesPageTest alfrescoSitePgTestObj = new AlfrescoSitesPageTest(scriptHelper);
		alfrescoSitePgTestObj.verifyNotAppliedDefaultManageAspectsOnFolder(aspectName1);
		alfrescoSitePgTestObj.verifyAppliedDefaultManageAspectsOnFolder(aspectName2);
		
		sitesPage.clickOnCancelBtnInManageAspectsPopup();
		
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);
		
		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
		
		ArrayList<String> createdFileNames = myFilesTestObj.getCreatedFileNames(fileDetails);
		
		for(String fileName:createdFileNames)
		{
			docLibPg.deleteAllFilesAndFolders();
			
			myFiles.createFile(fileDetails);
			
			docDetailsPageTest.verifyCreatedFile(fileName, "");
			
			docDetailsPageObj.performManageAspectsDocAction();
		
			alfrescoSitePgTestObj.verifyNotAppliedDefaultManageAspectsOnFolder(aspectName1);
			alfrescoSitePgTestObj.verifyAppliedDefaultManageAspectsOnFile(aspectName2);
			
			sitesPage.clickOnCancelBtnInManageAspectsPopup();
			
			docDetailsPageObj.backToFolderOrDocumentPage("");
		}
		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");

		//myFiles.deleteUploadedFile(fileName);

		myFiles.uploadFileInMyFilesPage(filePath, fileName);

		myFilesTestObj.verifyUploadedFile(fileName, "");

		myFiles.openUploadedOrCreatedFile(fileName, "");

		docDetailsPageTest.verifyUploadedFileIsOpened(fileName, "");

		docDetailsPageObj.performManageAspectsDocAction();

		alfrescoSitePgTestObj.verifyNotAppliedDefaultManageAspectsOnFolder(aspectName1);
		alfrescoSitePgTestObj.verifyAppliedDefaultManageAspectsOnFile(aspectName2);

		sitesPage.clickOnCancelBtnInManageAspectsPopup();
		
		String listOfAspectNames = dataTable.getData("Document_Details", "DocProperties");
		sitesPage.enterIntoDocumentLibrary();
		docLibPg.deleteAllFilesAndFolders();
		
        myFiles.createFolder(folderDetails);
		
		ArrayList<String> folderNamesList = myFiles.getFolderNames(folderDetails);
		
		for(String folderName:folderNamesList)
		{
			sitesPage.clickOnViewDetails(folderName);
			
			docDetailsPageObj.performManageAspectsDocAction();
			
			AlfrescoDocumentDetailsPageTest docDetailsPageTestObj = new AlfrescoDocumentDetailsPageTest(scriptHelper);
			docDetailsPageTestObj.verifyListOfAspectsAvailable(listOfAspectNames);
		}
		
		sitesPage.enterIntoDocumentLibrary();
		docLibPg.deleteAllFilesAndFolders();
		
		
		pageLoad.reset();
		pageLoad.start();
		myFiles.createFile(fileDetails);
		pageLoad.stop(); 
		long fileCreatedTime_ms = pageLoad.getTime();
		long fileCreatedTime_sec = (fileCreatedTime_ms-6000)/1000;
		report.updateTestLog("Time taken to Create File", "<b>Time Taken : </b>" + fileCreatedTime_sec + " sec",
				Status.DONE);

	/*	docDetailsPageObj.performManageAspectsDocAction();

		AlfrescoDocumentDetailsPageTest docDetailsPageTestObj = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		docDetailsPageTestObj.verifyAspectsAvailable();
		
		docDetailsPageObj.clickCancelInManageAspects();
		
		sitesPage.enterIntoDocumentLibrary();*/
		
	/*	pageLoad.reset();
		pageLoad.start();
		myFiles.createFolder(folderDetails);
		pageLoad.stop(); 
		long folderCreatedTime_ms = pageLoad.getTime();
		long folderCreatedTime_sec = (folderCreatedTime_ms-6000)/1000;
		report.updateTestLog("Time taken to Create Folder", "<b>Time Taken : </b>" + folderCreatedTime_sec + " sec",
				Status.DONE);
		*/
	/*	for(String folderName:folderNamesList)
		{
			sitesPage.clickOnMoreSetting(folderName);
			
			sitesPage.clickOnMoreOptionLink(folderName);
			
			docDetailsPageTestObj.verifyAspectsAvailable(aspectName2);
		}*/
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}