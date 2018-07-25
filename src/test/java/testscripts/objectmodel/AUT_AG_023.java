package testscripts.objectmodel;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoPCSPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoPCSPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author lokesh
 *
 */
public class AUT_AG_023 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void objectmodel_004()
	{
		testParameters.setCurrentTestDescription("[1]ALFDEPLOY-5853_Verify Smart Folder aspect is displayed in the Manage Aspect pop up window<br>"+
	"[1]ALFDEPLOY-5853_Verify user is able to add Smart Folder aspect in the Manage Aspect pop up window.<br>"+
				"[1]ALFDEPLOY-5853_Verify Smart Folder Template property is added in the Properties window once Smart Folder aspect is selected in the Manage Aspect pop up window.<br>"+
	"[1]ALFDEPLOY-5853_Verify a hierarchy ISBN subfolder C1 and C2 sub folders are displayed inside the selected folder for which Smart Folder Template with Sample-Smart-Folder-Template.json property is selected.<br>"+
				"[1]ALFDEPLOY-5853_Verify all files tagged as C1 is visible inside C1 folder if the parent folder is a Virtual Smart folder<br>"+
	"[1]ALFDEPLOY-5853_Verify all files tagged as C2 is visible inside C2 folder if the parent folder is a Virtual Smart folder<br>"+
				"[1]ALFDEPLOY-5853_Verify user is able to add aspects other than Smart Folder aspect for a folder without any issues.");
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
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		
		
		String fileName = dataTable.getData("MyFiles", "FileName");
		String fileNames[] = dataTable.getData("MyFiles", "FileName").split(",");
		String folders[] = dataTable.getData("MyFiles", "Version").split(",");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		//String aspectprop = dataTable.getData("Document_Details", "AspectName");
		String aspectprop = dataTable.getData("Document_Details", "Comments");
		String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String value = dataTable.getData("Document_Details", "Title");
	
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPge.deleteAllFilesAndFolders();
		
	    myFiles.createFolder(folderDetails);
	    
	    sitesPage.documentdetails(folders[0]);
		myFiles.uploadFile(filePath, fileName);
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		 sitesPage.clickOnMoreSetting(folders[0]);
		 docLibPge.commonMethodForClickOnMoreSettingsOption(folders[0], moreSettingsOption);
		 
	
	        
	       /* 
	        * 
	        *  sitesPage.documentdetails(fileNme);
		 docDetailsPageObj.performManageAspectsDocAction();
	        
	        docDetailsPageTestObj.verifyAspectsAvailable();*/
			docDetailsPageObj.addAspectsAndApllyChangesToAFile();
			
			
			sitesPage.commonMethodForPerformBrowseOption(folders[0], "Edit Properties");
			
		/*	sitesPage.enterIntoDocumentLibrary();
			sitesPage.clickOnEditProperties(fileNme);*/
			docDetailsPageObj.clickAllProperties();
			
			docLibPge.editpropertyDpdown(aspectprop,value);
			collectionPg.clickOnSaveBtn();
			sitesPage.enterIntoDocumentLibraryWithoutReport();
			sitesPage.documentdetails(folders[0]);
			docLibPge.addTagContent(fileNames[0], "c1");
			sitesPage.enterIntoDocumentLibraryWithoutReport();
			sitesPage.documentdetails(folders[0]);
			docLibPge.addTagContent(fileNames[1], "c2");
			
		
			sitesPage.documentdetails(folders[1]);
			sitesPage.documentdetails(folders[2]);
			if(sitesPage.documentAvailable(fileNames[0])){
				 report.updateTestLog("Verify all files tagged as C1 is visible inside C1 folder", 
						 "File tagged with C1 are in C1 smart folder. File Name " +fileNames[1] , Status.PASS);
			}else {
				 report.updateTestLog("Verify all files tagged as C1 is visible inside C1 folder", 
						 "File tagged with C1 are not available in C1 smart folder.Test cases failed" , Status.PASS);
			}
			
			sitesPage.enterIntoDocumentLibraryWithoutReport();
			sitesPage.documentdetails(folders[0]);
			sitesPage.documentdetails(folders[1]);
			sitesPage.documentdetails(folders[3]);
			
			if(sitesPage.documentAvailable(fileNames[1])){
				 report.updateTestLog("Verify all files tagged as C2 is visible inside C2 folder", 
						 "File tagged with C2 are in C2 smart folder. File Name " +fileNames[1] , Status.PASS);
			}else {
				report.updateTestLog("Verify all files tagged as C2 is visible inside C2 folder", 
						 "File tagged with C2 are not available in C2 smart folder.Test cases failed ", Status.FAIL);
			}
			
			
	
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}
