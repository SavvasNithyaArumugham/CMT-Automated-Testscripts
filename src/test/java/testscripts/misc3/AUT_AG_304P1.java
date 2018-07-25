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
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_304P1 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void misc3_006() {
		testParameters
				.setCurrentTestDescription("ALFDEPLOY-2065_Verify warning message is not displayed for manager when the name of a folder is edited through Edit Properties with folder name value which doesnt exist in the document library.<br>" + 
						"ALFDEPLOY-2065_Verify warning message is displayed for manager when the name of a folder is edited through All Properties window, with a same folder name value which already exists in the document library.<br>" + 
						"ALFDEPLOY-2065_Verify warning message is not displayed for manager when the name of a folder is edited through All Properties window with folder name value which doesnt exist in the document library.<br>" +
						"ALFDEPLOY-2065_Verify warning message is not displayed for manager when the name of a file is edited through All Properties window with file name value which doesnt exist in the document library.\r\n" + 
						"ALFDEPLOY-2065_Verify warning message is not displayed for manager when the name of a file is edited through All Properties window with file name value which doesnt exist in the document library.\r\n" + 
						"ALFDEPLOY-2065_Verify warning message is displayed for manager when the name of a file is edited through All Properties window, with a same file name value which already exists in the document library.\r\n" + 
						"ALFDEPLOY-2065_Verify warning message is not displayed for manager when the name of a file is edited through Edit Properties with file name value which doesnt exist in the document library.\r\n" + 
						"");
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
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentLibPageTest docLibPgTest =new AlfrescoDocumentLibPageTest(scriptHelper);
		AlfrescoSiteMembersPageTest siteMemPgTest = new AlfrescoSiteMembersPageTest(
				scriptHelper);
		
		//String fileName = dataTable.getData("MyFiles", "FileName");	
		String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
		String siteuserName = dataTable.getData("Sites", "InviteUserName");
		String roleName = dataTable.getData("Sites", "Role");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folder = dataTable.getData("MyFiles", "Version");
		String folder2 = dataTable.getData("MyFiles", "RelationshipName");
		String siteName = dataTable.getData("Sites", "SiteName");
		String file = dataTable.getData("MyFiles", "FileName");
		String fileName = dataTable.getData("MyFiles", "CreateFileDetails");
		String fileName1 = dataTable.getData("MyFiles", "BrowseActionName");
		String renamefile = dataTable.getData("MyFiles", "Sort Options");
		String filePath = dataTable.getData("MyFiles", "FilePath");
	
	
		functionalLibrary.loginAsValidUser(signOnPage);
		homePageObj.navigateToSitesTab();
		sitesPage.createSite(siteName, "Yes");
		String site=sitesPage.getCreatedSiteName();
		//sitesPage.siteFinder(siteName);
		
		sitesPage.performInviteUserToSite(site);
        siteMemPgTest.verifySiteMemebrs(site, siteuserName, roleName);

		sitesPage.enterIntoDocumentLibrary();
		docLibPg.deleteAllFilesAndFolders();
		myFiles.uploadFileInMyFilesPage(filePath, file);
		myFiles.createFolder(folderDetails);
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		
		sitesPage.commonMethodForPerformBrowseOption(folder, moreSettingsOption);
			
		docDetailsPageObj.clickAllProperties();
		
		docDetailsPageObj.editInEditPropertiesInputBox("Name",folder2);
		
		docDetailsPageObj.clickSaveInEditProperties();
		
		docLibPgTest.verifyPopUp("File or folder AutoTest already exists", "OK");
		
		docDetailsPageObj.editInEditPropertiesInputBox("Name","AutoTest2");
		
		docDetailsPageObj.clickSaveInEditProperties();
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		
		if(sitesPage.documentAvailable("AutoTest2")){
			report.updateTestLog("Verify file Renamed",
					"File renamed successfully by Manager"
							+ "<br><b> Renamed File Name : </b>"
							+ "AutoTest2", Status.PASS);
		}else{
			report.updateTestLog("Verify file Renamed",
					"File rename by Manager failed", Status.PASS);
		}
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		
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
		
		
	}
	
	@Override
	public void tearDown() {

	}

}