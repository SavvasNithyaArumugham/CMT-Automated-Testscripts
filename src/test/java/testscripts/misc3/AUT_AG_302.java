package testscripts.misc3;

import java.io.File;
import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_302 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void misc3_006() {
		testParameters
				.setCurrentTestDescription("ALFDEPLOY-2732_Validate Collections user able to download a folder as zip  which contains multiple  different types of files.<br>" + 
						"ALFDEPLOY-2732_Validate Collections user able to download a folder as zip  which contains different types of  files through  mouse Hover option.<br>" + 
						"ALFDEPLOY-2732_Validate Collections user able to download pdf files as zip file.<br>" + 
						"ALFDEPLOY-2732_Validate Collections user able to download different types files as zip file.<br>" + 
						"ALFDEPLOY-2732_Validate Collections user able to download a folder as zip  which contains multiple pdf files.<br>" + 
						"ALFDEPLOY-2732_Validate Collections user able to download a folder as zip  which contains multiple pdf files through  mouse Hover option.<br>" );
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
		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoSiteMembersPageTest siteMemPgTest = new AlfrescoSiteMembersPageTest(
				scriptHelper);
		
		
		String siteName = dataTable.getData("Sites", "SiteName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String fileName2 = dataTable.getData("MyFiles", "CreateFileDetails");
		String fileName3 = dataTable.getData("MyFiles", "Sort Options");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folder = dataTable.getData("MyFiles", "Version");
		String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
		String zipfile = dataTable.getData("MyFiles", "RelationshipName");
		String zipfolder = dataTable.getData("MyFiles", "BrowseActionName");
		String downloadFilePath = properties.getProperty("DefaultDownloadPath");
		String siteuserName = dataTable.getData("Sites", "InviteUserName");
		String roleName = dataTable.getData("Sites", "Role");
		File downloadedFile = null;
		ArrayList<String> FileList = new ArrayList<String>();
	
		ArrayList<String> folderNamesList = new ArrayList<String>();
		folderNamesList = myFiles.getFileNames(fileName);
		
		
		
		
		functionalLibrary.loginAsValidUser(signOnPage);
		homePageObj.navigateToSitesTab();
		sitesPage.createSite(siteName, "Yes");
		String site=sitesPage.getCreatedSiteName();
	///	sitesPage.siteFinder(site);
		
      	sitesPage.performInviteUserToSite(site);
        siteMemPgTest.verifySiteMemebrs(site, siteuserName, roleName);
        
        
        
		sitesPage.enterIntoDocumentLibrary();
	
		myFiles.createFolder(folderDetails);
		sitesPage.documentdetails(folder);
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		new FileUtil().deleteIfFileExistsInDownloadPath(downloadFilePath, zipfile);
	
		docLibPg.selectAllFilesAndFolders();
		sitesPage.clickOnSelectedItems();
		docLibPg.commonMethodForClickOnSelectedItemsMenuOption(moreSettingsOption);

		new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, zipfile);
		downloadedFile = new File(downloadFilePath + "/" + zipfile);
		if (downloadedFile.exists() && !zipfile.equalsIgnoreCase("File Not Found")) {
			report.updateTestLog("Verify download file", "File: " + zipfile + " downloaded sucessfully", Status.PASS);
		
		}else {
			report.updateTestLog("Verify download file", "File: " + zipfile + " download not  sucessfully", Status.FAIL);
		}
	

		myFiles.uploadFileInMyFilesPage(filePath, fileName2);
	
		folderNamesList =myFiles.getFileNames(fileName3);
	
		new FileUtil().deleteIfFileExistsInDownloadPath(downloadFilePath, zipfile);
		
		docLibPg.selectAllFilesAndFolders();
		sitesPage.clickOnSelectedItems();
		docLibPg.commonMethodForClickOnSelectedItemsMenuOption(moreSettingsOption);

		new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, zipfile);
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		UIHelper.waitFor(driver);
		downloadedFile = new File(downloadFilePath + "/" + zipfile);
		if (downloadedFile.exists() && !zipfile.equalsIgnoreCase("File Not Found")) {
			report.updateTestLog("Verify download file", "File: " + zipfile + " downloaded sucessfully", Status.PASS);
			
			FileList = new FileUtil().readZip(downloadFilePath + zipfile);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
		/*	if(FileList.equals(folderNamesList)) {
				report.updateTestLog("Verify Downloaded files are available", "File: " + FileList + " downloaded sucessfully", Status.PASS);
			}else {
				report.updateTestLog("Verify Downloaded files are available", "File: " + FileList + " download not sucessfully", Status.FAIL);
			}*/
			
			if(FileList.contains("AlfrescoTesting_PDF.pdf") && FileList.contains("AlfrescoPDFFile.pdf") &&
					FileList.contains("AlfrescoTesting_Word.docx") && FileList.contains("AlfrescoTesting_PowerPoint.pptx") 
					&& FileList.contains("AlfrescoTesting_Excel.xlsx") && FileList.contains("AlfrescoTesting_JPEG.jpeg")
					&& FileList.contains("AlfrescoTesting_BMP.bmp")) {
				report.updateTestLog("Verify Downlaoded files are available", "File: " + FileList + " downloaded sucessfully", Status.PASS);
			}else {
				report.updateTestLog("Verify Downlaoded files are available", "File: " + FileList + " download not sucessfully", Status.FAIL);
			}
		}
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		new FileUtil().deleteIfFileExistsInDownloadPath(downloadFilePath, zipfolder);
		
		sitesPage.commonMethodForPerformBrowseOption(folder, moreSettingsOption);

		new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, zipfolder);
		downloadedFile = new File(downloadFilePath + "/" + zipfolder);
		if (downloadedFile.exists() && !zipfolder.equalsIgnoreCase("File Not Found")) {
			report.updateTestLog("Verify download file", "File: " + zipfolder + " downloaded sucessfully", Status.PASS);
		}else {
			report.updateTestLog("Verify download file", "File: " + zipfolder + " download not  sucessfully", Status.FAIL);
		}
	}
	
	@Override
	public void tearDown() {

	}

}