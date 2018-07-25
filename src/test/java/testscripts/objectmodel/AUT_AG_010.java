package testscripts.objectmodel;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_010 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void objectmodel_010() {
		testParameters
				.setCurrentTestDescription("Verfiy user is able to add below custom aspects to the following scenaroies"
						+ "</br>linked file / "
						+ "</br>portuguese characters(ãáàâçéêíõóôúü) in it's name / "
						+ "</br>Special characters(~!@#$^&-+.,) in it's name / "
						+ "</br>perform download on a file"
						+ "</br>perform edit offline on a file");
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
		functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTestObj = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);

		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folderName = dataTable.getData("MyFiles", "Version");
		String aspectNames = dataTable.getData("Document_Details", "AspectName");
		String fileName = dataTable.getData("Document_Details", "FileName");
		String[] fileNames = fileName.split(",");

		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteNameValue);
		sitesPage.enterIntoDocumentLibrary();
			docLibPage.deleteAllFilesAndFolders();
			
			myFiles.createFolder(folderDetails);
			myFiles.createFile(fileDetails);
		
		
		
		//sitesPage.documentdetails(fileNames[0]);
		//myFiles.openAFile(fileNames[0]);
			
			
		sitesPage.enterIntoDocumentLibrary();
		
			
		linkAFile(fileNames[0], siteNameValue);
		sitesPage.enterIntoDocumentLibrary();
		
		myFiles.openCreatedFolder(folderName);
		myFiles.openAFile(fileNames[0]);
		doAspectFunctionality(fileNames[0], aspectNames, true);
		sitesPage.enterIntoDocumentLibrary();
		
		myFiles.openEditedFile(fileNames[0]);
		docDetailsPageObj.performEditPropertiesDocAction();
		docDetailsPageTestObj.verifyAllEditProperties();
		sitesPage.enterIntoDocumentLibrary();
		
		myFiles.openAFile(fileNames[1]);
		doAspectFunctionality(fileNames[1], aspectNames, true);
		sitesPage.enterIntoDocumentLibrary();
		
		myFiles.openEditedFile(fileNames[1]);
		docDetailsPageObj.performEditPropertiesDocAction();
		docDetailsPageTestObj.verifyAllEditProperties();
		sitesPage.enterIntoDocumentLibrary();
		
		myFiles.openAFile(fileNames[2]);
		doAspectFunctionality(fileNames[2], aspectNames, true);
		sitesPage.enterIntoDocumentLibrary();
		
		myFiles.openEditedFile(fileNames[2]);
		docDetailsPageObj.performEditPropertiesDocAction();
		docDetailsPageTestObj.verifyAllEditProperties();
		sitesPage.enterIntoDocumentLibrary();
		
		myFiles.openAFile(fileNames[3]);
		doAspectFunctionality(fileNames[3], aspectNames, true);
		sitesPage.enterIntoDocumentLibrary();
		
		myFiles.openEditedFile(fileNames[3]);
		docDetailsPageObj.performEditPropertiesDocAction();
		docDetailsPageTestObj.verifyAllEditProperties();
		sitesPage.enterIntoDocumentLibrary();
		
		performDownload(fileNames[3]);
		sitesPage.enterIntoDocumentLibrary();
		
		sitesPage.documentdetails(fileNames[4]);
		doAspectFunctionality(fileNames[4], aspectNames, true);
		sitesPage.enterIntoDocumentLibrary();
		
		myFiles.openEditedFile(fileNames[4]);
		docDetailsPageObj.performEditPropertiesDocAction();
		docDetailsPageTestObj.verifyAllEditProperties();
		sitesPage.enterIntoDocumentLibrary();
		
		performEditOffLine(fileNames[4]);
	
	}
	
	private void performEditOffLine(String fileName){
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		
		docLibPage.openAFile(fileName);
		docLibPage.verifyEditOffline();

		if (docLibPage.isFileLocked()) {
			AlfrescoDocumentLibPageTest docLibPageTest = new AlfrescoDocumentLibPageTest(scriptHelper);
			docLibPageTest.verifyDocumentPreviewed(fileName);
		}
		docLibPage.navigateToDocumentLibrary();
		docLibPage.deleteFileInDocumentLibrary();
	}
	
	private void performDownload(String fileName){
		AlfrescoDocumentDetailsPageTest docDetailsTestPage = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		
		docLibPage.openAFile(fileName);
		driver.navigate().refresh();
		
		docDetailsPageObj.deleteFileInDownloadedPath(false);
		docDetailsPageObj.downloadFileInDocumentDetailsPage();
		
		docDetailsTestPage.verifyDownloadedFile(false, null);
	}
	
	private void linkAFile(String fileName, String sourceSiteName){
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		sitesPage.clickOnMoreSetting(fileName);
		docLibPage.commonMethodForClickOnMoreSettingsOption(fileName,"Link To");
		
		
		docDetailsPageObj.selectFolderInLinkPopUp(sourceSiteName);
		docDetailsPageObj.clickLinkBtnInLinkPopUp();
		
		/*AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		docDetailsPageObj.clickonLinkToInPreviewPage(fileName);
		docDetailsPageObj.selectFolderInLinkPopUp(sourceSiteName);
		docDetailsPageObj.clickLinkBtnInLinkPopUp();*/
	}
	
	private void doAspectFunctionality(String fileOrFolder, String aspectName, boolean isFile){
		
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTestObj = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		//AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		
		if(isFile){
			//myFiles.openAFile(fileOrFolder);
			docDetailsPageObj.performManageAspectsDocAction();
		}else{
			sitesPage.clickOnMoreSetting(fileOrFolder);
			sitesPage.clickOnMoreOptionLink(fileOrFolder);
		}
		docDetailsPageObj.addAspectsAndApllyChangesToAFile(aspectName);
		if(isFile){
			docDetailsPageObj.performManageAspectsDocAction();
		}else{
			sitesPage.clickOnMoreSetting(fileOrFolder);
			sitesPage.clickOnMoreOptionLink(fileOrFolder);
		}
		
		String[] aspectNameArray = aspectName.split(",");
		for (String aspect : aspectNameArray) {
			docDetailsPageTestObj.verifyAppliedAspects(aspect);
		}
		
		docDetailsPageObj.clickCancelBtnInAspectsPopup();
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}
