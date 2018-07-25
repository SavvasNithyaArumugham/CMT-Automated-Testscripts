package testscripts.misc3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoEPSPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoEPSPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DateUtil;
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
public class AUT_AG_325P2 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void sharebox_003() {
		testParameters.setCurrentTestDescription(
				"1.Verify user able to see the french translation for alert pop up message when try to batch publish for multiple folders or files which are all in queue for publication."
				+"<br>2.Verify user able to see the french translation for alert message when try to batch publish for multiple folders in which one of the folder does not contain any contents."
						+"<br>3.Verify user able to see the french translation for alert message when try to publish a folder which does not contain any contents.");
		
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));

		report.updateTestLog("Invoke Application",
				"Invoke the application under test @ " + properties.getProperty("ApplicationUrl"), Status.DONE);

	}

	@Override
	public void executeTest() {
		try {
			AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
			functionalLibrary.loginAsValidUser(signOnPage);

			AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
			AlfrescoEPSPage epsPg = new AlfrescoEPSPage(scriptHelper);
			AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);

			AlfrescoSitesPageTest sitesPgTest = new AlfrescoSitesPageTest(scriptHelper);
			AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
			AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
			AlfrescoDocumentLibPageTest docLibPageTest = new AlfrescoDocumentLibPageTest(scriptHelper);
			AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
			AlfrescoMyFilesPageTest myFilesTest = new AlfrescoMyFilesPageTest(scriptHelper);
			AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);
			AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);
			AlfrescoEPSPageTest epsPageTest = new AlfrescoEPSPageTest(scriptHelper);

			String sourceSiteName = dataTable.getData("Sites", "SiteName");
			String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
			String filePath = dataTable.getData("MyFiles", "FilePath");
			String fileName[] = dataTable.getData("MyFiles", "FileName").split(",");
			String title = dataTable.getData("MyFiles", "CreateFileDetails");
			String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");
			String folderName[]=dataTable.getData("MyFiles","Version").split(",");
			
			String expalreadyBatchPublishedFiles=dataTable.getData("MyFiles","AccessToken");
			
			//String emptyFolder = dataTable.getData("MyFiles", "RelationshipName");
			String expPromptBodyMsg= dataTable.getData("MyFiles", "Sort Options");
			String expnoContentinFolderMsg=dataTable.getData("Sites", "ExpectedConfirmationMessage");
			String expMsgforPublishEmptyFolder=dataTable.getData("MyFiles", "PopUpMsg");
			
			String publishOption=dataTable.getData("MyFiles", "BrowseActionName");
			String deleteFolder=dataTable.getData("MyFiles","ExpectedSitesCount");
			String deleteFile = dataTable.getData("MyFiles", "StatusReportValue");

			homePageObj.navigateToSitesTab();
			sitesPage.openSiteFromRecentSites(sourceSiteName);
			sitesPage.enterIntoDocumentLibrary();
			try
			{
			if(docLibPg.isFileIsAvailable(folderName[0])){
				myFiles.openFolder(folderName[0]);
				if(epsPageTest.verifyFilePublishStatus("one-inst-published")||epsPageTest.verifyFilePublishStatus("errored"))
				{
				  deleteFileOrFolder(fileName[0], deleteFile);
				  sitesPage.enterIntoDocumentLibrary();
				  deleteFileOrFolder(folderName[0], deleteFolder);
				}
				else
				{
					sitesPage.enterIntoDocumentLibrary();
					deleteFileOrFolder(folderName[0], deleteFolder);
				}
			}
			if(docLibPg.isFileIsAvailable(folderName[1])){
				myFiles.openFolder(folderName[1]);
				if(epsPageTest.verifyFilePublishStatus("one-inst-published")||epsPageTest.verifyFilePublishStatus("errored"))
				{
				  deleteFileOrFolder(fileName[1], deleteFile);
				  sitesPage.enterIntoDocumentLibrary();
				  deleteFileOrFolder(folderName[1], deleteFolder);
				}
				else
				{
					sitesPage.enterIntoDocumentLibrary();
					deleteFileOrFolder(folderName[1], deleteFolder);
				}
			}
			
			if(docLibPg.isFileIsAvailable(folderName[2])){
				deleteFileOrFolder(folderName[2], deleteFolder);
			}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}

			
	       myFiles.createFolder(folderDetails);
			
			myFiles.openFolder(folderName[0]);

			myFiles.uploadFile(filePath, fileName[0]);
			UIHelper.waitFor(driver);
			
			sitesPage.enterIntoDocumentLibrary();
			myFiles.openFolder(folderName[1]);
             myFiles.uploadFile(filePath, fileName[1]);
			UIHelper.waitFor(driver);
			sitesPage.enterIntoDocumentLibrary();
			
			
			
			
			
			myFiles.methodToSelectMultipleFiles(title);
			sitesPage.clickOnSelectedItems();
			docLibPg.commonMethodForClickOnSelectedItemsMenuOption(moreSettingsOptionName);
			epsPg.clickBatchPublishButtonWithoutWait();
			
			//myFiles.methodToSelectMultipleFiles(title);
			sitesPage.clickOnSelectedItems();
			docLibPg.commonMethodForClickOnSelectedItemsMenuOption(moreSettingsOptionName);
			try
			{
			String actPromptHeaderValue=docLibPg.getPromptHeaderMessage();
			if(actPromptHeaderValue.equalsIgnoreCase(expPromptBodyMsg))
			{
				epsPg.clickBatchPublishButtonWithoutWait();
				docLibPageTest.verifyLoadingMessage(expalreadyBatchPublishedFiles);
			}
			}
			catch(Exception e)
			{
            docLibPageTest.verifyLoadingMessage(expalreadyBatchPublishedFiles);
            e.printStackTrace();
			}
			
			//when try to batch publish for multiple folders in which one of the folder does not contain any contents
			sitesPage.enterIntoDocumentLibrary();
			myFiles.methodToSelectMultipleFiles(title);
			sitesPage.clickOnMoreSetting(folderName[2]);
			sitesPage.clickOnSelectedItems();
			docLibPg.commonMethodForClickOnSelectedItemsMenuOption(moreSettingsOptionName);
			epsPg.clickBatchPublishButtonWithoutWait();
			docLibPageTest.verifyLoadingMessage(expnoContentinFolderMsg);
			
			//when try to publish a selected folder which does not contain any contents
			sitesPage.enterIntoDocumentLibrary();
			sitesPage.clickOnMoreSetting(folderName[2]);
			sitesPage.commonMethodForClickOnMoreOptionLink(folderName[2],publishOption);
			epsPg.PublishWithoutWait("School Content");
			docLibPageTest.verifyLoadingMessage(expMsgforPublishEmptyFolder);
			
			
			
			}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
	private void deleteFileOrFolder(String fileOrFolderName, String optionToDelete){
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg =new AlfrescoDocumentLibPage(scriptHelper);
		sitesPage.clickOnMoreSetting(fileOrFolderName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileOrFolderName, optionToDelete);
		docLibPg.clickDeletePopup();
	}
	
}