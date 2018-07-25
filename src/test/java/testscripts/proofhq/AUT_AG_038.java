package testscripts.proofhq;


import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
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
public class AUT_AG_038 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void proofhq_22()
	{
		testParameters.setCurrentTestDescription("Verifying the alfresco 'Add relationship' feature for the proof created file");
		
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
		AlfrescoSitesPageTest sitesPageTests = new AlfrescoSitesPageTest(scriptHelper);	
		AlfrescoDocumentLibPage docLibPg =new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		
		String addRelationSite = dataTable.getData("Sites", "SiteToSelect");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folderName = dataTable.getData("MyFiles", "Version");
	/*	ArrayList<String> folderNamesList = myFiles.getFolderNames(folderDetails);
		String folderName ="";
		if(folderNamesList!=null)
		{
			folderName = folderNamesList.get(0);
		}
		
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(addRelationSite);
		sitesPage.enterIntoDocumentLibrary();
		docLibPg.deleteAllFilesAndFolders();
		
		myFiles.createFolder(folderDetails);
		myFilesTestObj.verifyCreatedFolder(folderDetails);
		
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		
		homePage.navigateToSitesTab();*/
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		
		docLibPg.deleteAllFilesAndFolders();

		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		
		myFiles.createFolder(folderDetails);
		//sitesPage.documentdetails(folderName);
		
		myFiles.uploadFile(filePath, fileName);
		
		
		String moreSettingsOptions = dataTable.getData("MyFiles", "MoreSettingsOption");
		
		String moreSettingsOption1="",moreSettingsOption2="";
		if(moreSettingsOptions.contains(","))
		{
			String splittedMoreSettingOptions[] = moreSettingsOptions.split(",");
			if(splittedMoreSettingOptions!=null && splittedMoreSettingOptions.length>1)
			{
				moreSettingsOption1=splittedMoreSettingOptions[0];
				moreSettingsOption2=splittedMoreSettingOptions[1];
			}
			
		}
		
	/*	sitesPage.clickOnMoreSetting(fileName);
	//	sitesPageTests.verifyMoreSettingsOptionForFileOrFolderItem(fileName, moreSettingsOption1);
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, moreSettingsOption1);
		
		String recepients=  dataTable.getData("MyFiles", "Recepients");
		String policy=  dataTable.getData("MyFiles", "ProofHQPolicy");
		sitesPage.addProofHQ(recepients, policy, fileName);*/
		
		sitesPage.clickOnMoreSetting(fileName);
	//	sitesPageTests.verifyMoreSettingsOptionForFileOrFolderItem(fileName, moreSettingsOption2);
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, moreSettingsOption2);
		
		String relationshipName = dataTable.getData("MyFiles", "RelationshipName");
		sitesPage.addRelationshiptositefolder(relationshipName, folderName, addRelationSite);
		
		myFiles.openAFile(fileName);
		
		if(docDetailsPage.isRelationshipAddedForSite()){
			report.updateTestLog("Verify Relationship Added between proof created File and Site folder",
					"Relationship Added successfully"
							+ "</br><b>File Name:</b> " + fileName
							+ "</br><b>Site Name:</b> " + addRelationSite
							+ "</br><b>Site Folder Name:</b> " + folderName,
					Status.PASS);
			
			docDetailsPage.clickOnAddedRelationshipLink(folderName, fileName);
		}else{
			report.updateTestLog("Verify Relationship Added between proof created File and Site",
					"Relationship not Added"
							+ "</br><b>File Name:</b> " + fileName
							+ "</br><b>Site Name:</b> " + addRelationSite
							+ "</br><b>Site Folder Name:</b> " + folderName,
					Status.FAIL);
		}
		
	/*	homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPg.deleteAllFilesAndFolders();
		
		myFiles.uploadFile(filePath, fileName);
		myFilesTestObj.verifyUploadedFile(fileName, "");
		
		sitesPage.clickOnMoreSetting(fileName);
		sitesPageTests.verifyMoreSettingsOptionForFileOrFolderItem(fileName, moreSettingsOption2);
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, moreSettingsOption2);
		
		sitesPage.addRelationshipBtwAssetAndSiteFolder(relationshipName, folderName, addRelationSite);
		
		sitesPage.clickOnMoreSetting(fileName);
		sitesPageTests.verifyMoreSettingsOptionForFileOrFolderItem(fileName, moreSettingsOption1);
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, moreSettingsOption1);
		
		sitesPage.addProofHQ(recepients, policy, fileName);
		
		myFiles.openAFile(fileName);
		
		if(docDetailsPage.isRelationshipAddedForSite()){
			report.updateTestLog("Verify Relationship Added between proof created File and Site folder",
					"Relationship Added successfully"
							+ "</br><b>File Name:</b> " + fileName
							+ "</br><b>Site Name:</b> " + addRelationSite
							+ "</br><b>Site Folder Name:</b> " + folderName,
					Status.PASS);
			
			docDetailsPage.clickOnAddedRelationshipLink(folderName, fileName);
		}else{
			report.updateTestLog("Verify Relationship Added between proof created File and Site",
					"Relationship not Added"
							+ "</br><b>File Name:</b> " + fileName
							+ "</br><b>Site Name:</b> " + addRelationSite
							+ "</br><b>Site Folder Name:</b> " + folderName,
					Status.FAIL);
		}*/
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}