package testscripts.eps;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * @author Naresh Kumar Salla
 */
public class AUT_AG_006 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void EPS_005()
	{
		testParameters.setCurrentTestDescription("1) Verify user is not able to perform Batch publish if any one of the selected folder has no files in it"
											+ "</br>2) Verify user is getting an error message when user tries to “Delete” non published folder which contains published file content"
											+ "</br>3) Verify user is not able to perfom delete When user tries to “Delete” multiple folders contains published files using “multi-select delete” option");
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
		String fileName = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String folderName = dataTable.getData("MyFiles", "Version");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		
		homePage.navigateToSitesTab();	
		sitesPage.openSiteFromRecentSites(sourceSiteName);	
		sitesPage.enterIntoDocumentLibrary();
		
		String multipleFolderDetails1 = "FolderName:FolderWithFile,Title:Test,Description:TestDemo;FolderName:EmptyFolder,Title:Test,Description:TestDemo";
		uploadFile(fileName, filePath, folderName, folderDetails, multipleFolderDetails1, true);
		testcase1(folderName);
		sitesPage.enterIntoDocumentLibrary();
		
		String singleFolderDetails = "FolderName:FolderWithFile,Title:Test,Description:TestDemo";
		uploadFile(fileName, filePath, folderName, folderDetails, singleFolderDetails, true);
		testcase2(fileName, folderName);
		sitesPage.enterIntoDocumentLibrary();
		
		String multipleFolderDetails2 = "FolderName:PublishedFileFolder1,Title:Test,Description:TestDemo;"
				+ "FolderName:PublishedFileFolder2,Title:Test,Description:TestDemo;"
				+ "FolderName:FolderWithFile1,Title:Test,Description:TestDemo;"
				+ "FolderName:FolderWithFile2,Title:Test,Description:TestDemo";
		uploadFile(fileName, filePath, folderName, folderDetails, multipleFolderDetails2, true);
		testcase3();
	}
	
	private void uploadFile(String fileName, String filePath, String folderName, String folderDetails,
			String multiOrSingleFolderDetails, boolean isSingleFileUpload){
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg =new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		
		String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");
		String moreSettingsOptionNames[] = null;
		if(moreSettingsOptionName.contains(";"))
		{
			moreSettingsOptionNames = moreSettingsOptionName.split(";");
		}
		
		if(sitesPage.Checkdocument(folderName)){
			myFiles.openCreatedFolder(folderName);
			if(docLibPg.isFileIsAvailable(fileName)){
				deleteFileOrFolder(fileName, moreSettingsOptionNames[0]);
			}
			if(docLibPg.isFileIsAvailable("AlfrescoZipFile.zip")){
				deleteFileOrFolder("AlfrescoZipFile.zip", moreSettingsOptionNames[0]);
			}
			if(docLibPg.isFileIsAvailable("SCOZipFile.zip")){
				deleteFileOrFolder("SCOZipFile.zip", moreSettingsOptionNames[0]);
			}
			if(docLibPg.isFileIsAvailable("Test.txt")){
				deleteFileOrFolder("Test.txt", moreSettingsOptionNames[0]);
			}
			if(docLibPg.isFileIsAvailable("AlfrescoTesting_Word.docx")){
				deleteFileOrFolder("AlfrescoTesting_Word.docx", moreSettingsOptionNames[0]);
			}
			if(docLibPg.isFileIsAvailable("&%$#@!^()_+-=.docx")){
				deleteFileOrFolder("&%$#@!^()_+-=.docx", moreSettingsOptionNames[0]);
			}
			if(sitesPage.Checkdocument("FolderWithFile")){
				deletedPublishedFile("FolderWithFile","1111.docx",moreSettingsOptionNames[0],moreSettingsOptionNames[1]);
			}
			if(sitesPage.Checkdocument("PublishedFileFolder1")){
				deletedPublishedFile("PublishedFileFolder1","1111.docx",moreSettingsOptionNames[0],moreSettingsOptionNames[1]);
			}
			if(sitesPage.Checkdocument("PublishedFileFolder2")){
				deletedPublishedFile("PublishedFileFolder2","1111.docx",moreSettingsOptionNames[0],moreSettingsOptionNames[1]);
			}
			sitesPage.enterIntoDocumentLibrary();
			deleteFileOrFolder(folderName, moreSettingsOptionNames[1]);
		}
		
		myFiles.createFolder(folderDetails);
		myFiles.openCreatedFolder(folderName);
		
		myFiles.createFolder(multiOrSingleFolderDetails);
		
		if(isSingleFileUpload){
			myFiles.openCreatedFolder("FolderWithFile");
			myFiles.uploadFile(filePath, fileName);
		}else{
			createFolderAndUploadAFile(fileName, moreSettingsOptionNames[2]);
		}
	}
	
	private void openFolderAndUploadAFile(String folderToOpen){
		String fileName = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		
		myFiles.openCreatedFolder(folderToOpen);
		myFiles.uploadFile(filePath, fileName);
	}
	
	private void publishAFile(String fileName, String moreSettingsOptionName) {
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg =new AlfrescoDocumentLibPage(scriptHelper);
		sitesPage.clickOnMoreSetting(fileName);		
		
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, moreSettingsOptionName);		
		//docLibPg.clickPublishPopup();
	}
	
	private void createFolderAndUploadAFile(String fileName, String moreOPtionSettings){
		openFolderAndUploadAFile("PublishedFileFolder1");
		publishAFile(fileName, moreOPtionSettings);		
		driver.navigate().back();
		UIHelper.waitFor(driver);
		
		openFolderAndUploadAFile("PublishedFileFolder2");
		publishAFile(fileName, moreOPtionSettings);		
		driver.navigate().back();
		UIHelper.waitFor(driver);
		
		openFolderAndUploadAFile("FolderWithFile1");	
		driver.navigate().back();
		UIHelper.waitFor(driver);
		
		openFolderAndUploadAFile("FolderWithFile2");	
		driver.navigate().back();
		UIHelper.waitFor(driver);
	}
	
	private void testcase1(String folderName){
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg =new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(folderName);
		UIHelper.pageRefresh(driver);
		docLibPg.selectAllFilesAndFolders();
		
		sitesPage.clickOnSelectedItems();
		String selectedItemMenuOptVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
		docLibPg.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOptVal.split(",")[0]);
		docLibPg.clickBatchPublishPopup();
		
		AlfrescoDocumentLibPageTest docLibPgTest = new AlfrescoDocumentLibPageTest(scriptHelper);
		
		String popUpMsg = dataTable.getData("MyFiles", "PopUpMsg");
		if (docLibPgTest.isPopUpMsgDisplayed(popUpMsg.split(";")[0])) {
			report.updateTestLog("Verify PopUp Message",
					"PopUp Message displayed successfully"
							+ "<br><b> Verified Pop Message : </b>"
							+ popUpMsg, Status.PASS);
		}else{
			report.updateTestLog("Verify PopUp Message",
					"PopUp Message not displayed"
							+ "<br><b> Verified Pop Message : </b>"
							+ popUpMsg, Status.FAIL);
		}
	}
	
	private void testcase2(String fileName, String folderName){
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg =new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		
		String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");
		String moreSettingsOptionNames[] = null;
		if(moreSettingsOptionName.contains(";"))
		{
			moreSettingsOptionNames = moreSettingsOptionName.split(";");
		}
		
		sitesPage.clickOnMoreSetting(fileName);		
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, moreSettingsOptionNames[2]);		
		//docLibPg.clickPublishPopup();
		
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(folderName);
		sitesPage.clickOnMoreSetting("FolderWithFile");
		docLibPg.commonMethodForClickOnMoreSettingsOption("FolderWithFile", moreSettingsOptionNames[1]);
		
		AlfrescoDocumentLibPageTest docLibPgTest = new AlfrescoDocumentLibPageTest(scriptHelper);
		
		String popUpMsg = dataTable.getData("MyFiles", "PopUpMsg");
		if (docLibPgTest.isPopUpMsgDisplayed(popUpMsg.split(";")[1])) {
			report.updateTestLog("Verify PopUp Message",
					"PopUp Message displayed successfully"
							+ "<br><b> Verified Pop Message : </b>"
							+ popUpMsg, Status.PASS);
		}else{
			report.updateTestLog("Verify PopUp Message",
					"PopUp Message not displayed"
							+ "<br><b> Verified Pop Message : </b>"
							+ popUpMsg, Status.FAIL);
		}
	}
	
	private void testcase3(){
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg =new AlfrescoDocumentLibPage(scriptHelper);
		
		docLibPg.selectAllFilesAndFolders();
		sitesPage.clickOnSelectedItems();
		String selectedItemMenuOptVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
		docLibPg.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOptVal.split(",")[1]);
		docLibPg.clickDeletePopup();
		
		AlfrescoDocumentLibPageTest docLibPgTest = new AlfrescoDocumentLibPageTest(scriptHelper);
		
		String popUpMsg = dataTable.getData("MyFiles", "PopUpMsg");
		if (docLibPgTest.isPopUpMsgDisplayed(popUpMsg.split(";")[2])) {
			report.updateTestLog("Verify PopUp Message",
					"PopUp Message displayed successfully"
							+ "<br><b> Verified Pop Message : </b>"
							+ popUpMsg, Status.PASS);
		}else{
			report.updateTestLog("Verify PopUp Message",
					"PopUp Message not displayed"
							+ "<br><b> Verified Pop Message : </b>"
							+ popUpMsg, Status.FAIL);
		}
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
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
}