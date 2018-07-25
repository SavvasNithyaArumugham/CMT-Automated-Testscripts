package testscripts.links;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAdminToolsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoAdminToolsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class TC18 extends TestCase{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void LINKS_012(){
		testParameters.setCurrentTestDescription("1). Verify the admin/Non admin users is able to add rendition relationship between files"
				+ "2). Verify that the user is able to Add Relationship from the Relationship Widget by Clicking on the Plus Icon."
				+ "3). Verify that the user is able to delete Relationship from the Relationship Widget by Clicking on the Cross Mark."
				+ "4). Verify that the location of the target files on the 'Relationship' Widget present on the Preview Page");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}
	
	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");
		
		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPageTest sitePgTestObj = new AlfrescoSitesPageTest(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPage=new AlfrescoDocumentDetailsPage(scriptHelper);


		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String relationshipName = dataTable.getData("MyFiles", "RelationshipName");
		String MoreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
		String propertyName = dataTable.getData("Sites", "FilePropertyName");
		String relationshipName1 = dataTable.getData("Admin","Bi-Directional_Label");
		
		ArrayList<String> uploadedFileDetails = myFilesTestObj.getUploadedFileDetails(fileName);
		ArrayList<String> folderNamesList = myFiles.getFolderNames(folderDetails);
		ArrayList<String> filesNamesList = new ArrayList<String>();

		functionalLibrary.loginAsValidUser(signOnPage);		
		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteNameValue);
		sitesPage.enterIntoDocumentLibrary();
		
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		docLibPg.deleteAllFilesAndFolders();	
    /*    myFiles.createFolder(folderDetails);        
		myFilesTestObj.verifyCreatedFolder(folderDetails);*/
		
		
//		for(String folderName:folderNamesList)
	//	{
		//	myFiles.openCreatedFolder(folderName);		
			myFiles.uploadFileInMyFilesPage(filePath, fileName);			
			myFilesTestObj.verifyUploadedMultipleFiles(fileName);
			sitesPage.documentdetails(uploadedFileDetails.get(0));
			
			if(uploadedFileDetails!=null && uploadedFileDetails.size()>1)
			{
				/*sitesPage.clickOnMoreSetting(uploadedFileDetails.get(0));				
				sitePgTestObj.verifyMoreSettingsOptionForFileOrFolder(uploadedFileDetails.get(0));	*/			
				/*sitesPage.clickOnMoreOptionLink(uploadedFileDetails.get(0));		
				sitesPage.commonMethodForClickOnMoreOptionLink(uploadedFileDetails.get(0),MoreSettingsOption);	*/
				
				UIHelper.waitForVisibilityOfEleByXpath(driver, "//*[@id='addNewRelationship']/a");
				UIHelper.highlightElement(driver, "//*[@id='addNewRelationship']/a");
				UIHelper.click(driver, "//*[@id='addNewRelationship']/a");
				
				sitesPage.addRelationship(relationshipName, uploadedFileDetails.get(1));						
				myFiles.openUploadedOrCreatedFile(uploadedFileDetails.get(0),"");				
				//docDetailsPageTest.verifyUploadedFileIsOpened(uploadedFileDetails.get(0),"");				
				docDetailsPageTest.verifyAddedRelationshipData(uploadedFileDetails.get(1));
				
				docDetailsPage.deleteRelationshipData( uploadedFileDetails.get(1));
			}
	//	}
		
		
//	+ "2). Verify that the user is able to Add Relationship from the Relationship Widget by Clicking on the Plus Icon."

		/*sitesPage.enterIntoDocumentLibrary();	
		filesNamesList = myFiles.getFileNames(fileName);		
		myFiles.openUploadedOrCreatedFile(filesNamesList.get(0), "");
		docDetailsPage.commonMethodForPerformDocAction(propertyName);
		sitesPage.addRelationshipFromWidget(relationshipName1, filesNamesList.get(1));
		docDetailsPageTest.verifyAddedRelationshipData(filesNamesList.get(1));*/
		
//		(covered in 2)  "4). Verify that the location of the target files on the 'Relationship' Widget present on the Preview Page");
//		
//		sitesPage.enterIntoDocumentLibrary();			
//		filesNamesList = myFiles.getFileNames(fileName);		
//		myFiles.openUploadedOrCreatedFile(filesNamesList.get(0), "");
//		docDetailsPage.commonMethodForPerformDocAction(propertyName);
//		sitesPage.addRelationshipFromWidget(relationshipName, filesNamesList.get(1));
//		docDetailsPageTest.verifyAddedRelationshipData(filesNamesList.get(1));
//		
//		+ "3). Verify that the user is able to delete Relationship from the Relationship Widget by Clicking on the Cross Mark."
		
	/*	sitesPage.enterIntoDocumentLibrary();			
		myFiles.openUploadedOrCreatedFile(filesNamesList.get(0), "");
		docDetailsPageTest.verifyUploadedFileIsOpened(filesNamesList.get(0), "");
		docDetailsPageTest.verifyAddedRelationshipData(filesNamesList.get(1));		
		docDetailsPage.deleteRelationshipData( filesNamesList.get(1));*/
		
	}

	@Override
	public void tearDown() {
		
	}
}
