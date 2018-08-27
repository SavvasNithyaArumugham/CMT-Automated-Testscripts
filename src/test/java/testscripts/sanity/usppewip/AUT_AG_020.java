package testscripts.sanity.usppewip;

import java.io.File;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoEPSPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Settings;
import com.pearson.framework.Status;

/**
 * @author Naresh Kumar Salla
 */
public class AUT_AG_020 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void EPS_011()
	{
		testParameters.setCurrentTestDescription("Verify the user is able to view the EPS content of the published zip file/Sco Zip file and updated published SCO zip file.");
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
			
		String sourceSiteName = dataTable.getData("Sites", "EPSSiteName");
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg =new AlfrescoDocumentLibPage(scriptHelper);
		String fileName = dataTable.getData("MyFiles", "FileName");
		
		String[] fileNames = fileName.split(",");
		String folderName = dataTable.getData("MyFiles", "Version");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		
		String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");
		String moreSettingsOptionNames[] = null;
		if(moreSettingsOptionName.contains(";"))
		{
			moreSettingsOptionNames = moreSettingsOptionName.split(";");
		}
		
		homePage.navigateToSitesTab();	
		UIHelper.waitFor(driver);
		sitesPage.openSiteFromRecentSites(sourceSiteName);	
		sitesPage.enterIntoDocumentLibrary();
		if(sitesPage.Checkdocument(folderName))
		{
			myFiles.openCreatedFolder(folderName);
			if(docLibPg.isFileIsAvailable(fileNames[0])){
				deleteFileOrFolder(fileNames[0], moreSettingsOptionNames[1]);
			}
			if(docLibPg.isFileIsAvailable(fileNames[1])){
				deleteFileOrFolder(fileNames[1], moreSettingsOptionNames[1]);
			}
			if(docLibPg.isFileIsAvailable("Test.txt")){
				deleteFileOrFolder("Test.txt", moreSettingsOptionNames[1]);
			}
			if(docLibPg.isFileIsAvailable("1111.docx")){
				deleteFileOrFolder("1111.docx", moreSettingsOptionNames[1]);
			}
			if(docLibPg.isFileIsAvailable("AlfrescoTesting_Word.docx")){
				deleteFileOrFolder("AlfrescoTesting_Word.docx", moreSettingsOptionNames[1]);
			}
			if(docLibPg.isFileIsAvailable("&%$#@!^()_+-=.docx")){
				deleteFileOrFolder("&%$#@!^()_+-=.docx", moreSettingsOptionNames[1]);
			}
			if(sitesPage.Checkdocument("FolderWithFile")){
				deletedPublishedFile("FolderWithFile","1111.docx",moreSettingsOptionNames[1],moreSettingsOptionNames[2]);
			}
			if(sitesPage.Checkdocument("PublishedFileFolder1")){
				deletedPublishedFile("PublishedFileFolder1","1111.docx",moreSettingsOptionNames[1],moreSettingsOptionNames[2]);
			}
			if(sitesPage.Checkdocument("PublishedFileFolder2")){
				deletedPublishedFile("PublishedFileFolder2","1111.docx",moreSettingsOptionNames[1],moreSettingsOptionNames[2]);
			}
			sitesPage.enterIntoDocumentLibrary();
			deleteFileOrFolder(folderName, moreSettingsOptionNames[2]);
		}
		myFiles.createFolder(folderDetails);
		myFiles.openCreatedFolder(folderName);
		myFiles.uploadFile(filePath, fileName);
		
		publishAFile(fileNames[0], moreSettingsOptionNames[0]);
		publishAFile(fileNames[1], moreSettingsOptionNames[0]);
		
		myFiles.openAFile(fileNames[1]);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);
		docDetailsPage.uploadNewVersionFileInDocumentDetailsPage();
		
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(folderName);
		publishAFile(fileNames[1], moreSettingsOptionNames[0]);
	}
	
	
	private void deleteFileOrFolder(String fileOrFolderName, String optionToDelete){
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg =new AlfrescoDocumentLibPage(scriptHelper);
		sitesPage.clickOnMoreSetting(fileOrFolderName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileOrFolderName, optionToDelete);
		docLibPg.clickDeletePopup();
	}
	
	private void deletedPublishedFile(String folderName, String fileName, String deleteFileOption, String deleteFolderOption){
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		myFiles.openCreatedFolder(folderName);
		deleteFileOrFolder(fileName, deleteFileOption);
		UIHelper.waitFor(driver);
		driver.navigate().back();
		UIHelper.waitFor(driver);
		deleteFileOrFolder(folderName, deleteFolderOption);
	}
	
	private void publishAFile(String fileName, String moreSettingsOptionName){
		
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg =new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoEPSPage epsPg = new AlfrescoEPSPage(scriptHelper);
		sitesPage.clickOnMoreSetting(fileName);	
	 
		// Added for NALS project
		epsPg.publishbutton(fileName, moreSettingsOptionName);
		
	//docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, moreSettingsOptionName);
		
		UIHelper.waitFor(driver);
		
		docLibPg.clickPublishPopup();
		//UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		UIHelper.pageRefresh(driver);
		
		AlfrescoDocumentLibPageTest docLibPgTest = new AlfrescoDocumentLibPageTest(scriptHelper);
		docLibPgTest.verifyFilePublishedGreenTick(fileName);
		// Added for NALS project
		UIHelper.waitFor(driver);
		UIHelper.pageRefresh(driver);
		sitesPage.clickOnMoreSetting(fileName);	
		epsPg.publishbutton(fileName, moreSettingsOptionName);
		
		//sitesPage.clickOnEditProperties(fileName);
	//	sitesPage.checkPublishedURLfield();
		/*
		String publishedURL = sitesPage.getPublishedURL();
		String accessToken = dataTable.getData("MyFiles", "AccessToken");
		
		String downloadFilePath = Settings.getInstance().getProperty(
				"DefaultDownloadPath");
		new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, fileName);
		
		File zipFile = new File(downloadFilePath + "/" + fileName);
		if (zipFile.exists()) {
			zipFile.delete();
		}
		
		homePage.openURLWithNewTab(publishedURL+accessToken);		
		
		if (zipFile.exists()) {
			report.updateTestLog("Verify Published File Downloaded in download path",
					"File downloaded sucessfully"
							+ "<br><b> File Name : </b>"
							+ fileName
							+ "<br><b> File Download PATH : </b>"
							+ downloadFilePath, Status.PASS);
		}else{
			report.updateTestLog("Verify Published File Downloaded in download path",
					"File not downloaded"
							+ "<br><b> File Name : </b>"
							+ fileName
							+ "<br><b> File Download PATH : </b>"
							+ downloadFilePath, Status.FAIL);
		}
		
		homePage.switchtab(0);
		UIHelper.waitFor(driver);
		*/
	}
	@Override
	public void tearDown() {

	}

}