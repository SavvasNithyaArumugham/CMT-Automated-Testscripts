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
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_005 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void EPS_004() {
		testParameters
				.setCurrentTestDescription("1) Verify the confirmation pop up window displayed to confirm the zip files to expand and publish."
										+ "</br>2) Verify the user is able to expand and publish a zip file."
										+ "</br>3) Verify the user is able to publish a zip file without expanding Zip folder."
										+ "</br>4) Verify the confirmation pop up window is not displayed to expand and publish for non zip file.");
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
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		
		String sourceSiteName = dataTable.getData("Sites", "EPSSiteName");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String[] fileNames = fileName.split(",");
		String folderName = dataTable.getData("MyFiles", "Version");
		String filePath = dataTable.getData("MyFiles", "FilePath");		
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");

		homePage.navigateToSitesTab();
		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		UIHelper.waitFor(driver);
		
		uploadAFile(fileNames[0], filePath, folderDetails, folderName);
		testcase1();
		sitesPage.enterIntoDocumentLibrary();
		UIHelper.waitFor(driver);
		
		uploadAFile(fileNames[1], filePath, folderDetails, folderName);
		testcase2(fileNames[1], false);
		sitesPage.enterIntoDocumentLibrary();
		UIHelper.waitFor(driver);
		
		uploadAFile(fileNames[0], filePath, folderDetails, folderName);
		testcase2(fileNames[0], true);
		sitesPage.enterIntoDocumentLibrary();
		UIHelper.waitFor(driver);
		
		uploadAFile(fileNames[2], filePath, folderDetails, folderName);
		testcase3();
	}
	
	private void uploadAFile(String fileName, String filePath, String folderDetails, String folderName){
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);		
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String Option = dataTable.getData("MyFiles", "MoreSettingsOption");
		String moreSettingsOptName = dataTable.getData("Sites",
				"SelectedItemsMenuOption");
		if (sitesPage.Checkdocument(folderName)) {
			myFiles.openCreatedFolder(folderName);
		} else {
			myFiles.createFolder(folderDetails);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			myFiles.openCreatedFolder(folderName);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
		}

		if (sitesPage.Checkdocument(fileName)) {
			sitesPage.clickOnMoreSetting(fileName);
			docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, Option);
			docLibPg.clickDeletePopup();
			
			UIHelper.pageRefresh(driver);
		}
		
		myFiles.uploadFile(filePath, fileName);
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		sitesPage.clickOnMoreSetting(fileName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName,
				moreSettingsOptName);
	}
	
	private void testcase1(){
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		if (docLibPg.checkConfirmationPopupForZip()) {
			report.updateTestLog(
					"Check Expand zip file confirmation pop up diplayed",
					"Expand zip file confirmation pop up displayed successfully",
					Status.PASS);
		} else {
			report.updateTestLog(
					"Check Expand zip file confirmation pop up diplayed",
					"Expand zip file confirmation pop up is not displayed",
					Status.FAIL);
		}
		
		docLibPg.clickPublishPopup();
	}
	
	private void testcase2(String fileName, boolean isZipFile){
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String format = dataTable.getData("MyFiles", "CreateFileDetails");
		if(isZipFile){
			docLibPg.selectPublishZipAsItself();
		}
		docLibPg.clickPublishPopup();
		UIHelper.pageRefresh(driver);
		AlfrescoDocumentLibPageTest docLibPgTest = new AlfrescoDocumentLibPageTest(
				scriptHelper);
		
		docLibPgTest.verifyFilePublished(fileName);
		if(isZipFile){
			UIHelper.pageRefresh(driver);
		}
		sitesPage.clickOnEditProperties(fileName);
		if(!isZipFile){
			sitesPage.checkPublishedURLfield();
		}
		sitesPage.checkPublishedURL(format, fileName);
		
		sitesPage.clickOnCancelBtnInEditPropWindow();
	}
	
	private void testcase3(){
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		if (docLibPg.checkConfirmationPopupForZip()) {
			report.updateTestLog(
					"Check Expand zip file confirmation pop up diplayed",
					"Expand zip file confirmation pop up displayed successfully",
					Status.FAIL);
		} else {
			report.updateTestLog(
					"Check Expand zip file confirmation pop up diplayed",
					"Expand zip file confirmation pop up is not displayed",
					Status.PASS);
		}
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}