package testscripts.healthcheck;


import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_027 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runAUT_AG_047()
	{
		testParameters.setCurrentTestDescription("1. Verify user is able to see 'cancel editing' option under selected items menu dropdown when selected multiple assets that are already locked for editing");
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
		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPageTest sitePageTest = new AlfrescoSitesPageTest(scriptHelper);
		
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
		}
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String selectedItemMenuOptions=dataTable.getData("Sites", "SelectedItemsMenuOption");
		
		String selectedItemMenuOption1="", selectedItemMenuOption2="";
		if(selectedItemMenuOptions.contains(","))
		{
			String splittedOptions[] = selectedItemMenuOptions.split(",");
			if(splittedOptions!=null & splittedOptions.length>1)
			{
				selectedItemMenuOption1=splittedOptions[0];
				selectedItemMenuOption2=splittedOptions[1];
			}
		}
		else
		{
			selectedItemMenuOption1="Edit Offline";
			selectedItemMenuOption2="Cancel Editing";
		}
		
		myFiles.deleteCreatedFolder(folderDetails);
		myFiles.createFolder(folderDetails);
		
		ArrayList<String> folderNamesList = myFiles.getFolderNames(folderDetails);
		
		for (String folderName : folderNamesList) {
			myFiles.openCreatedFolder(folderName);
			
			myFiles.uploadFileInMyFilesPage(filePath, fileName);
			
			String fileDownloadPath = properties.getProperty("DefaultDownloadPath");
			new FileUtil().deleteIfAllFilesExistsInDownloadPath(fileDownloadPath, fileName);
			
			myFiles.methodToSelectMultipleFiles(fileName);
			sitesPage.clickOnSelectedItems();
			sitesPage.selectItemFromSelectedItemsMenuOption(selectedItemMenuOption1);
			docLibPg.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOption1);
			
			new FileUtil().waitUptoAllFilesDownloadComplete(fileDownloadPath, fileName);
			
			myFiles.methodToSelectMultipleFiles(fileName);
			sitesPage.clickOnSelectedItems();
			sitePageTest.verifySelectedItemsMenuOption(selectedItemMenuOption2);
			docLibPg.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOption2);
			
			if(fileName.contains(",")) {
				String splittedFileNames[] = fileName.split(",");
				for (String fileNameVal : splittedFileNames) {
					myFiles.waitForFileUnlock(fileNameVal);
				}
			} else {
				myFiles.waitForFileUnlock(fileName);
			}
			
		}
		
	/*	homePageObj.navigateToMyFilesTab();
		try{
			docLibPg.deleteAllFilesAndFolders();
		}
		catch(Exception e)
		{
		}
		
		myFiles.deleteCreatedFolder(folderDetails);
		myFiles.createFolder(folderDetails);
		
		for (String folderName : folderNamesList) {
			myFiles.openCreatedFolder(folderName);
			
			myFiles.uploadFileInMyFilesPage(filePath, fileName);
			
			String fileDownloadPath = properties.getProperty("DefaultDownloadPath");
			new FileUtil().deleteIfAllFilesExistsInDownloadPath(fileDownloadPath, fileName);
			
			myFiles.methodToSelectMultipleFiles(fileName);
			sitesPage.clickOnSelectedItems();
			sitesPage.selectItemFromSelectedItemsMenuOption(selectedItemMenuOption1);
			docLibPg.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOption1);
			
			new FileUtil().waitUptoAllFilesDownloadComplete(fileDownloadPath, fileName);
			
			myFiles.methodToSelectMultipleFiles(fileName);
			sitesPage.clickOnSelectedItems();
			sitePageTest.verifySelectedItemsMenuOption(selectedItemMenuOption2);
			docLibPg.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOption2);
			
			if(fileName.contains(",")) {
				String splittedFileNames[] = fileName.split(",");
				for (String fileNameVal : splittedFileNames) {
					myFiles.waitForFileUnlock(fileNameVal);
				}
			} else {
				myFiles.waitForFileUnlock(fileName);
			}
		}
		
		homePageObj.navigateToSharedFilesTab();
		
		if(!sitesPage.CheckFolderOrFile(folderNamesList.get(0))){
        	myFiles.createFolder(folderDetails);
        }
		
		for (String folderName : folderNamesList) {
			myFiles.openCreatedFolder(folderName);
			
			myFiles.deleteUploadedFile(fileName);
			myFiles.deleteUploadedFile(fileName);
			
			myFiles.uploadFileInMyFilesPage(filePath, fileName);
			
			String fileDownloadPath = properties.getProperty("DefaultDownloadPath");
			new FileUtil().deleteIfAllFilesExistsInDownloadPath(fileDownloadPath, fileName);
			
			myFiles.methodToSelectMultipleFiles(fileName);
			sitesPage.clickOnSelectedItems();
			sitesPage.selectItemFromSelectedItemsMenuOption(selectedItemMenuOption1);
			docLibPg.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOption1);
			
			new FileUtil().waitUptoAllFilesDownloadComplete(fileDownloadPath, fileName);
			
			myFiles.methodToSelectMultipleFiles(fileName);
			sitesPage.clickOnSelectedItems();
			sitePageTest.verifySelectedItemsMenuOption(selectedItemMenuOption2);
			docLibPg.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOption2);
			
			if(fileName.contains(",")) {
				String splittedFileNames[] = fileName.split(",");
				for (String fileNameVal : splittedFileNames) {
					myFiles.waitForFileUnlock(fileNameVal);
				}
			} else {
				myFiles.waitForFileUnlock(fileName);
			}
		}*/
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}