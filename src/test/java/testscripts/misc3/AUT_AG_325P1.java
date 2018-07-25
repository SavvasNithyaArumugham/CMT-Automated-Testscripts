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
public class AUT_AG_325P1 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void sharebox_003() {
		testParameters.setCurrentTestDescription(
				"1.Verify user able to see the french translation for alert pop up message when try to delete a unpublished folder which has published files."
				+"<br>2.Verify user able to see the french translation for alert pop up message when try to publish a folder or file which is in queue for publication."
				+"<br>3.Verify user able to see the french translation for alert message when try to publish a folder which has published file or folder");
		
		
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
			String deleteFolderOption=dataTable.getData("MyFiles","Version");
			String expDelChildFilesMsg = dataTable.getData("MyFiles", "PopUpMsg");
			String expalreadyPublishedFiles=dataTable.getData("MyFiles","AccessToken");
			String deleteFolder=dataTable.getData("MyFiles","ExpectedSitesCount");
			String deleteFile = dataTable.getData("MyFiles", "RelationshipName");
			String expChildpublishedMsg=dataTable.getData("Sites", "ExpectedConfirmationMessage");
			
			
			

			homePageObj.navigateToSitesTab();
			sitesPage.openSiteFromRecentSites(sourceSiteName);
			sitesPage.enterIntoDocumentLibrary();
			
			try
			{
		if(docLibPg.isFileIsAvailable(title)){
				myFiles.openFolder(title);
				if(epsPageTest.verifyFilePublishStatus("one-inst-published"))
				{
				  deleteFileOrFolder(fileName[0], deleteFile);
				  sitesPage.enterIntoDocumentLibrary();
				  deleteFileOrFolder(title, deleteFolder);
				}
				else
				{
					sitesPage.enterIntoDocumentLibrary();
					deleteFileOrFolder(title, deleteFolder);
				}
			}
			if(docLibPg.isFileIsAvailable(fileName[1])){
				deleteFileOrFolder(fileName[1], deleteFile);
			}
			}
			 catch(Exception e)
			{
				 e.printStackTrace();
			}
			

			myFiles.createFolder(folderDetails);
			
			myFiles.openFolder(title);

			myFiles.uploadFile(filePath, fileName[0]);
			UIHelper.waitFor(driver);

		
		    sitesPage.clickOnMoreSetting(fileName[0]);
			sitesPage.commonMethodForClickOnMoreOptionLink(fileName[0], moreSettingsOptionName);
			
			epsPg.Publish("School Content",fileName[0],"one");
			sitesPage.enterIntoDocumentLibraryWithoutReport();
			
			//verify alert message for unpublished folder has published files
			sitesPage.clickOnMoreSetting(title);
			sitesPage.commonMethodForClickOnMoreOptionLinkWithoutWait(title, deleteFolderOption);
			docLibPageTest.verifyLoadingMessage(expDelChildFilesMsg);
			
			//verify alert when try to publish a folder or file which is in queue for publication
			myFiles.uploadFile(filePath, fileName[1]);
			sitesPage.clickOnMoreSetting(fileName[1]);
			sitesPage.commonMethodForClickOnMoreOptionLink(fileName[1], moreSettingsOptionName);
			epsPg.PublishWithoutWait("School Content");
			
			sitesPage.clickOnMoreSetting(fileName[1]);
			sitesPage.commonMethodForClickOnMoreOptionLinkWithoutWait(fileName[1], moreSettingsOptionName);
			docLibPageTest.verifyLoadingMessage(expalreadyPublishedFiles);
			
			//Verify alert message when try to publish a folder which has published file or folder
			sitesPage.enterIntoDocumentLibraryWithoutReport();
			sitesPage.clickOnMoreSetting(title);
			sitesPage.commonMethodForClickOnMoreOptionLink(title, moreSettingsOptionName);
			epsPg.PublishWithoutWait("School Content");
			docLibPageTest.verifyLoadingMessage(expChildpublishedMsg);
			
			
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