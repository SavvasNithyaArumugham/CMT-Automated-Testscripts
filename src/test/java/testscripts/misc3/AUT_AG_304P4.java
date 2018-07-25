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

public class AUT_AG_304P4 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void misc3_006() {
		testParameters
				.setCurrentTestDescription("ALFDEPLOY-2065_Verify Contributor is able to rename the folder through Edit Properties window with folder name value which doesnt exist in the document library.\r\n" + 
						"ALFDEPLOY-2065_Verify Contributor is able to rename the folder through All Properties window with folder name value which doesnt exist in the document library.\r\n" + 
						"ALFDEPLOY-2065_Verify Contributor gets warning message when the folder name is renamed through All Properties window with a same folder name value which already exists in the document library.\r\n" + 
						"ALFDEPLOY-2065_Verify Contributor is able to rename the file through Edit Properties window with file name value which doesnt exist in the document library.\r\n" + 
						"ALFDEPLOY-2065_Verify Contributor is able to rename the file through All Properties window with file name value which doesnt exist in the document library.\r\n" + 
						"ALFDEPLOY-2065_Verify Contributor gets warning message when the file name is renamed through All Properties window with a same file name value which already exists in the document library.\r\n" + 
						"ALFDEPLOY-2065_Verify Contributor is unable to rename the file created by others through Edit Properties.\r\n" );
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
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentLibPageTest docLibPgTest =new AlfrescoDocumentLibPageTest(scriptHelper);
		AlfrescoSiteMembersPageTest siteMemPgTest = new AlfrescoSiteMembersPageTest(
				scriptHelper);
		
		
		
		String file = dataTable.getData("MyFiles", "FileName");	
		String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
		String folder = dataTable.getData("MyFiles", "Version");
		String fileName1 = dataTable.getData("MyFiles", "BrowseActionName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folder1 = dataTable.getData("MyFiles", "RelationshipName");
		String fileName = dataTable.getData("MyFiles", "CreateFileDetails");
		String folder2 = dataTable.getData("MyFiles", "PopUpMsg");
		String renamefile = dataTable.getData("MyFiles", "Sort Options");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String renamefolder = dataTable.getData("MyFiles", "AccessToken");
	
		functionalLibrary.loginAsValidUser(signOnPage);
		
		String site=sitesPage.getCreatedSiteName();
		sitesPage.siteFinder(site);

		sitesPage.enterIntoDocumentLibrary();
		myFiles.uploadFileInMyFilesPage(filePath, file);
		myFiles.createFolder(folderDetails);
		
		sitePageTest.verifyBrowseActionNameForFileOrFolderInNegativeCase(folder, moreSettingsOption);
		
		sitePageTest.verifyBrowseActionNameForFileOrFolderInNegativeCase(fileName1, moreSettingsOption);
		
		sitesPage.commonMethodForPerformBrowseOption(folder1, moreSettingsOption);
		
		docDetailsPageObj.clickAllProperties();
		
		docDetailsPageObj.editInEditPropertiesInputBox("Name",folder2);
		
		docDetailsPageObj.clickSaveInEditProperties();
		
		docLibPgTest.verifyPopUp("File or folder AutoTest already exists", "OK");
		
		docDetailsPageObj.editInEditPropertiesInputBox("Name",renamefolder);
		
		docDetailsPageObj.clickSaveInEditProperties();
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		
		if(sitesPage.documentAvailable(renamefolder)){
			report.updateTestLog("Verify file Renamed",
					"File renamed successfully by Manager"
							+ "<br><b> Renamed File Name : </b>"
							+ renamefolder, Status.PASS);
		}else{
			report.updateTestLog("Verify file Renamed",
					"File rename by Manager failed", Status.PASS);
		}
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		
		sitesPage.commonMethodForPerformBrowseOption(file, moreSettingsOption);
		
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
		
	}
	
	@Override
	public void tearDown() {

	}

}