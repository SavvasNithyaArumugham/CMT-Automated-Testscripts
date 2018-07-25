package testscripts.misc3;

import java.io.File;
import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_304P5 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void misc3_006() {
		testParameters
				.setCurrentTestDescription("ALFDEPLOY-2065_Verify Reviewer is unable to rename the file created by others through Edit Properties.<br>" + 
						"ALFDEPLOY-2065_Verify Reviewer is unable to rename the folder created by others through Edit Properties.<br>");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog(
				"Invoke Application",
				"Invoke the application under test @ "
						+ properties.getProperty("ApplicationUrl"), Status.DONE);
	}


	@Override
	public void executeTest() {
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoSitesPageTest sitePageTest = new AlfrescoSitesPageTest(scriptHelper);
		
		//String fileName = dataTable.getData("MyFiles", "FileName");	
		String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
		String folder = dataTable.getData("MyFiles", "Version");
		String fileName1 = dataTable.getData("MyFiles", "BrowseActionName");

	
	
	
		functionalLibrary.loginAsValidUser(signOnPage);
		
		String site=sitesPage.getCreatedSiteName();
		sitesPage.siteFinder(site);

		sitesPage.enterIntoDocumentLibrary();
	//	docLibPg.deleteAllFilesAndFolders();
	//	myFiles.uploadFileInMyFilesPage(filePath, file);
	//	myFiles.createFolder(folderDetails)
		
		sitePageTest.verifyBrowseActionNameForFileOrFolderInNegativeCase(folder, moreSettingsOption);
		
		sitePageTest.verifyBrowseActionNameForFileOrFolderInNegativeCase(fileName1, moreSettingsOption);
		
	/*	sitesPage.commonMethodForPerformBrowseOption(folder, moreSettingsOption);
			
		docDetailsPageObj.clickAllProperties();
		
		docDetailsPageObj.editInEditPropertiesInputBox("Name",folder2);
		
		docDetailsPageObj.clickSaveInEditProperties();
		
		docLibPgTest.verifyPopUp("File or folder AutoTest already exists", "OK");
		
		docDetailsPageObj.editInEditPropertiesInputBox("Name",renamefolder);
		
		docDetailsPageObj.clickSaveInEditProperties();
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		
		if(sitesPage.documentAvailable("AutoTest2")){
			report.updateTestLog("Verify file Renamed",
					"File renamed successfully by Manager"
							+ "<br><b> Renamed File Name : </b>"
							+ renamefolder, Status.PASS);
		}else{
			report.updateTestLog("Verify file Renamed",
					"File rename by Manager failed", Status.PASS);
		}*/
		
		/*sitesPage.enterIntoDocumentLibraryWithoutReport();
		
		sitesPage.commonMethodForPerformBrowseOption(fileName1, moreSettingsOption);
		
		docDetailsPageObj.clickAllProperties();
		
		docDetailsPageObj.editInEditPropertiesInputBox("Name",fileName);
		
		docDetailsPageObj.clickSaveInEditProperties();
		
		docLibPgTest.verifyPopUp("File or folder AlfrescoTesting_Word.docx already exists", "OK");
		
		docDetailsPageObj.editInEditPropertiesInputBox("Name",renamefile);
		
		docDetailsPageObj.clickSaveInEditProperties();
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		
		if(sitesPage.documentAvailable(renamefile)){
			report.updateTestLog("Verify file Renamed",
					"File renamed successfully by Manager"
							+ "<br><b> Renamed File Name : </b>"
							+ renamefile, Status.PASS);
		}else{
			report.updateTestLog("Verify file Renamed",
					"File rename by Manager failed", Status.PASS);
		}
		*/
		
	}
	
	@Override
	public void tearDown() {

	}

}