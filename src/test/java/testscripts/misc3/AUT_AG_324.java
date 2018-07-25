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
public class AUT_AG_324 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void sharebox_003() {
		testParameters.setCurrentTestDescription(
				"1.Verify user able to see the french translation for Publish option via hover or preview and folder action page."
						+ "<br>2.Verify user able to see the french translation for Publish pop up screen when publishing a file or folder."
						+ "<br>3.Verify user able to see the french translation for Publish pop up screen after publishing a file or folder"
						+ "<br>4.Verify user able to see the french translation for publish successful message."
						+ "<br>5.Verify user able to see the french translation for review changes option and review changes pop up screen."
						+"<br>6.Verify user able to see the french translation for Batch Publish screen when zip files are selected."
						+"<br>7.Verify user able to see the french translation for publish pop up screen when publishing zip file."
						+"<br>8.Verify user able to see the french translation for publish pop up screen after publishing a zip file."
						+"<br>9.Verify user able to see the french translation for Publishing options and Publishing options pop up screen."
						+"<br>10.Verify user able to see the french translation for delete confirmation screen when try to delete a published file or folder."
						+"<br>11.Verify user able to see the french translation for alert pop up message when deleting published file or folder."
						+"<br>12.Verify user able to see the french translation for alert message when try to edit the name field for a published folder or file");
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
			String selectedItemMenuOptVal = dataTable.getData("MyFiles", "BrowseActionName");
			String zipfile = dataTable.getData("MyFiles", "FileName");
			ArrayList<String> history = new ArrayList<String>();

			String title = dataTable.getData("MyFiles", "CreateFileDetails");
			String expMessage = dataTable.getData("MyFiles", "PopUpMsg");
			String expPromptBodyMsg= dataTable.getData("MyFiles", "Sort Options");

			String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");
			String reviewOptionName = dataTable.getData("MyFiles", "RelationshipName");
			String publishOptionsName=dataTable.getData("MyFiles", "StatusReportValue");
			String publishOptionItems[]=dataTable.getData("MyFiles", "FolderDetailsForBreadCumb").split(";");
			String expPromptHeaderValue=dataTable.getData("MyFiles","StatusReportValue");
			String actPromptBodyMsg;
			String popUpOptions = dataTable.getData("MyFiles", "TagName");
			String zipPopUpOptions=dataTable.getData("Document_Details","DocPropertyValues");
			String popUpAfterPublishOption = dataTable.getData("MyFiles", "ContentCategoryItem");
			String zipPopUpAfterPublishOptions=dataTable.getData("Document_Details","DocProperties");
			String deleteDoc=dataTable.getData("MyFiles","Version");
			String deleteFolder=dataTable.getData("MyFiles","ExpectedSitesCount");
			
			String expDeletePromptHeaderValue=dataTable.getData("Document_Details","AspectName");
			String expDeletePromptBodyMsg= dataTable.getData("Document_Details", "ExtractTo");
			String expDeleteMultiFilesPromptBodyMsg=dataTable.getData("Sites", "BrowseActionName");
			String deleteMultipleFilesOptions=dataTable.getData("Sites", "SelectedItemsMenuOption");
			
			String expectedPromptDelteMultiFilesMsg=dataTable.getData("Sites","ExpectedConfirmationMessage");
			String confirmNotdeletItem=dataTable.getData("Sites","ProgramComponentFieldNames");
			String confirmNotEditableName=dataTable.getData("Sites","ProgramComponentSiteFieldValue");
			ArrayList<String> expectedPopUpItems = new ArrayList<String>();
			ArrayList<String> expectedAfterPublishPopUpItems = new ArrayList<String>();
			ArrayList<String> actualPopUpItems = new ArrayList<String>();
			ArrayList<String> actualAfterPublishPopUpItems = new ArrayList<String>();
			
			ArrayList<String> expectedPublishOptionsPopUpItems = new ArrayList<String>();
			ArrayList<String> actualPublishOptionsPopUpItems = new ArrayList<String>();
			
			

			homePageObj.navigateToSitesTab();
			sitesPage.openSiteFromRecentSites(sourceSiteName);
			sitesPage.enterIntoDocumentLibrary();
			try
			{
			if(docLibPg.isFileIsAvailable(fileName[0])){
				deleteFileOrFolder(fileName[0], deleteDoc);
			}
			if(docLibPg.isFileIsAvailable("AlfrescoZipFile.zip")){
				deleteFileOrFolder("AlfrescoZipFile.zip", deleteDoc);
			}
			if(docLibPg.isFileIsAvailable(title)){
				deleteFileOrFolder(title, deleteFolder);
			}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			

			myFiles.createFolder(folderDetails);

			myFiles.uploadFile(filePath, fileName[0]);
			UIHelper.waitFor(driver);

			sitesPage.clickOnMoreSetting(fileName[0]);
			sitesPgTest.verifyMoreSettingsOptionForFileOrFolderItem(fileName[0], moreSettingsOptionName);

			sitesPage.clickOnMoreSetting(title);
			sitesPgTest.verifyMoreSettingsOptionForFileOrFolderItem(title, moreSettingsOptionName);

			myFiles.openAFile(fileName[0]);
			docDetailsPageTest.verifyDocAction(moreSettingsOptionName);
			docDetailsPage.backToFolderOrDocumentPage("");
			sitesPage.clickOnViewDetails(title);
			docDetailsPageTest.verifyDocAction(moreSettingsOptionName);
			docDetailsPage.backToFolderOrDocumentPage("");
			sitesPage.clickOnMoreSetting(fileName[0]);
			sitesPage.commonMethodForClickOnMoreOptionLink(fileName[0], moreSettingsOptionName);
			//before publish pop up for files
			expectedPopUpItems = myFilesTest.getUploadedFileDetails(popUpOptions);

			for (int i = 0; i < expectedPopUpItems.size(); i++) {
				actualPopUpItems = epsPageTest.getPublishPopUpTableItems(expectedPopUpItems.get(i));

			}
			epsPageTest.verifyPublishPopUpTable(actualPopUpItems, expectedPopUpItems);
			epsPg.PublishWithoutWait("School Content");
			docLibPageTest.verifyLoadingMessage(expMessage);
			epsPg.waitUntilPublishContent(fileName[0], "one");

			sitesPage.clickOnMoreSetting(fileName[0]);
			epsPg.publishbutton(fileName[0], moreSettingsOptionName);

			history = epsPg.publishhistory("1");

			if (history != null && history.get(3).equalsIgnoreCase("School Content")) {

				report.updateTestLog("Verify publish  history",
						"Publish history of the file/folder is <br>File/Folder Name" + fileName[0] + history.get(0) + " "
								+ history.get(1) + ", " + history.get(2) + " " + history.get(3) + ", " + history.get(4)
								+ " " + history.get(5) + ", " + history.get(6) + " " + history.get(7),
						Status.PASS);
			} else {
				report.updateTestLog("Verify publish  history", "Failed to retreive publish history", Status.FAIL);
			}
			//after publish pop up for files
			expectedAfterPublishPopUpItems = myFilesTest.getUploadedFileDetails(popUpAfterPublishOption);
			for (int i = 0; i < expectedAfterPublishPopUpItems.size(); i++) {
				actualAfterPublishPopUpItems = epsPageTest
						.getPublishPopUpTableItems(expectedAfterPublishPopUpItems.get(i));
				actualAfterPublishPopUpItems.add(history.get(0));
				actualAfterPublishPopUpItems.add(history.get(6));
			}
			epsPageTest.verifyPublishPopUpTable(actualAfterPublishPopUpItems, expectedAfterPublishPopUpItems);
			UIHelper.pageRefresh(driver);
			//Review changes done for files
			sitesPage.documentdetails(fileName[0]);
			docDetailsPage.uploadNewVersionFileInDocumentDetailsPage();
			sitesPage.enterIntoDocumentLibraryWithoutReport();
			epsPg.orangepublish(fileName[0], "one");
			sitesPage.clickOnMoreSetting(fileName[0]);
			sitesPgTest.verifyMoreSettingsOptionForFileOrFolderItem(fileName[0], reviewOptionName);
			sitesPage.commonMethodForClickOnMoreOptionLink(fileName[0], reviewOptionName);
			epsPg.reviewchanges(reviewOptionName, fileName[0]);
			UIHelper.waitFor(driver);
			sitesPage.enterIntoDocumentLibrary();
			//verify Batch file options
			myFiles.uploadFile(filePath, fileName[1]);
			myFiles.methodToSelectMultipleFiles(zipfile);
			sitesPage.clickOnSelectedItems();
			sitesPgTest.verifySelectedItemsMenuOption(selectedItemMenuOptVal);
			docLibPg.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOptVal);
			String actPromptHeaderValue=docLibPg.getPromptHeaderMessage();
			actPromptBodyMsg=docLibPg.getPromptbodyMessage();
			
			if(actPromptHeaderValue.equalsIgnoreCase(expPromptHeaderValue)&& actPromptBodyMsg.contains(expPromptBodyMsg)) {
				report.updateTestLog("Verify prompt header message in french", "Prompt header message verified in french successfully <br><b>Expected :</b>"+expPromptHeaderValue+"<br><b>Actual :</b>"+actPromptHeaderValue, Status.PASS);
			}else {
				report.updateTestLog("Verify prompt header message in french", "Prompt header message verification in french failed <br><b>Expected :</b>"+expPromptHeaderValue+"<br><b>Actual :</b>"+actPromptHeaderValue, Status.FAIL);
			}
			epsPageTest.verifyFilesListedOnBatchPublish(zipfile);
			epsPageTest.verifyPromptFooterButton(expectedPopUpItems.get(0));
			UIHelper.pageRefresh(driver);
			
			//before publish pop up for zipfiles
			
			sitesPage.clickOnMoreSetting(fileName[1]);
			sitesPage.commonMethodForClickOnMoreOptionLink(fileName[1], moreSettingsOptionName);
			expectedPopUpItems.clear();
			actualPopUpItems.clear();
			expectedPopUpItems = myFilesTest.getUploadedFileDetails(zipPopUpOptions);

			for (int i = 0; i < expectedPopUpItems.size(); i++) {
				actualPopUpItems = epsPageTest.getPublishPopUpTableItems(expectedPopUpItems.get(i));

			}
			epsPageTest.verifyPublishPopUpTable(actualPopUpItems, expectedPopUpItems);
			
			//after publish pop up for zipfiles
			epsPg.Publish("School Content",fileName[1], "one");

			sitesPage.clickOnMoreSetting(fileName[1]);
			epsPg.publishbutton(fileName[1], moreSettingsOptionName);
			history.clear();
			history = epsPg.publishhistory("1");

			if (history != null && history.get(3).equalsIgnoreCase("School Content")) {

				report.updateTestLog("Verify publish  history",
						"Publish history of the file/folder is <br>File/Folder Name" + fileName[0] + history.get(0) + " "
								+ history.get(1) + ", " + history.get(2) + " " + history.get(3) + ", " + history.get(4)
								+ " " + history.get(5) + ", " + history.get(6) + " " + history.get(7)+" " + history.get(8)+" " + history.get(9),
						Status.PASS);
			} else {
				report.updateTestLog("Verify publish  history", "Failed to retreive publish history", Status.FAIL);
			}
			expectedAfterPublishPopUpItems.clear();
			actualAfterPublishPopUpItems.clear();
			expectedAfterPublishPopUpItems = myFilesTest.getUploadedFileDetails(zipPopUpAfterPublishOptions);
			for (int i = 0; i < expectedAfterPublishPopUpItems.size(); i++) {
				actualAfterPublishPopUpItems = epsPageTest
						.getPublishPopUpTableItems(expectedAfterPublishPopUpItems.get(i));
				actualAfterPublishPopUpItems.add(history.get(0));
				actualAfterPublishPopUpItems.add(history.get(6));
				actualAfterPublishPopUpItems.add(history.get(8));
			}
			epsPageTest.verifyPublishPopUpTable(actualAfterPublishPopUpItems, expectedAfterPublishPopUpItems);
			epsPg.clickCancelOnPublishScreen();
			
			//Verify "PublishOption" for folder and verify PublishOption popUpScreen
  			sitesPage.clickOnMoreSetting(title);
			sitesPgTest.verifyMoreSettingsOptionForFileOrFolderItem(title, publishOptionsName);
			sitesPage.commonMethodForClickOnMoreOptionLink(title, publishOptionsName);
			
			
			for (String OptionItem  : publishOptionItems) {
				actualPublishOptionsPopUpItems = epsPageTest
						.getPublishPopUpTableItems(OptionItem);
					
			}
			expectedPublishOptionsPopUpItems.addAll(actualPublishOptionsPopUpItems);
			epsPageTest.verifyPublishPopUpTable(actualPublishOptionsPopUpItems, expectedPublishOptionsPopUpItems);
			epsPg.clickCancelOnPublishScreen();
			
			//Verify Delete PopUp Message
			sitesPage.clickOnMoreSetting(fileName[0]);
			sitesPage.commonMethodForClickOnMoreOptionLink(fileName[0], deleteDoc);
			
			
			String actDeletePromptHeaderValue=docLibPg.getPromptHeaderMessage();
			actPromptBodyMsg=docLibPg.getPromptbodyMessage();
			if(actDeletePromptHeaderValue.equalsIgnoreCase(expDeletePromptHeaderValue)&& actPromptBodyMsg.contains(expDeletePromptBodyMsg)) {
				report.updateTestLog("Verify Delete Publish Popup prompt header message in french", "Delete Publish Popup Prompt header message verified in french successfully <br><b>Expected :</b>"+expDeletePromptHeaderValue+"<br><b>Actual :</b>"+actDeletePromptHeaderValue, Status.PASS);
			}else {
				report.updateTestLog("Verify Delete Publish Popup prompt  header message in french", "Delete Publish Popup Prompt header message verification in french failed <br><b>Expected :</b>"+expDeletePromptHeaderValue+"<br><b>Actual :</b>"+actDeletePromptHeaderValue, Status.FAIL);
			}
			epsPg.clickCancelOnPublishScreen();
			sitesPage.enterIntoDocumentLibrary();
			myFiles.methodToSelectMultipleFiles(zipfile);
			sitesPage.clickOnSelectedItems();
			docLibPg.commonMethodForClickOnSelectedItemsMenuOption(deleteMultipleFilesOptions);
			String actDelteMultipleFilesPromptHeaderValue=docLibPg.getPromptHeaderMessage();
			actPromptBodyMsg=docLibPg.getPromptbodyMessage();
			
			if(actDelteMultipleFilesPromptHeaderValue.equalsIgnoreCase(expectedPromptDelteMultiFilesMsg)&& actPromptBodyMsg.contains(expDeleteMultiFilesPromptBodyMsg) ) {
				report.updateTestLog("Verify prompt header message in french", "Prompt header message verified in french successfully <br><b>Expected :</b>"+expectedPromptDelteMultiFilesMsg+"<br><b>Actual :</b>"+actDelteMultipleFilesPromptHeaderValue, Status.PASS);
			}else {
				report.updateTestLog("Verify prompt header message in french", "Prompt header message verification in french failed <br><b>Expected :</b>"+expectedPromptDelteMultiFilesMsg+"<br><b>Actual :</b>"+actDelteMultipleFilesPromptHeaderValue, Status.FAIL);
			}
			epsPageTest.verifyFilesListedOnBatchPublish(zipfile);
			epsPg.clickDeleteOnPublishScreen();
			docLibPageTest.verifyLoadingMessage(confirmNotdeletItem);
			sitesPage.enterIntoDocumentLibrary();
			
			//Verify Published files cannot be Edited
			sitesPage.clickOnEditProperties(fileName[0]);
			if(confirmNotEditableName.equals(epsPageTest.verifyPublishedFileEditable()))
					{
				  report.updateTestLog("Verify Published File Name cannot be Edited", "Name Field is non Editable", Status.PASS);
					}
			else
			{
				report.updateTestLog("Verify Published File Name cannot be Edited", "Name Field is Editable", Status.FAIL);
			}
			
				
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