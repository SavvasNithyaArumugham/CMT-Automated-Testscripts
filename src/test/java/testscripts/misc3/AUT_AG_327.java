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
public class AUT_AG_327 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void sharebox_003() {
		testParameters.setCurrentTestDescription(
				"Verify user able to see the french translation for alert message when try to publish a file or folder inside a already published folder.");
		
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
			
			String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");
			String folderName=dataTable.getData("MyFiles","Version");
			
			String expMsg1 =dataTable.getData("MyFiles","AccessToken").replace("Craft", folderName).trim();
			String expMsg2=dataTable.getData("MyFiles", "PopUpMsg").trim();
			String expMsg3=dataTable.getData("MyFiles", "StatusReportValue").replace("Craft", folderName).trim();
			
			String deleteFile = dataTable.getData("MyFiles", "RelationshipName");
			String deleteFolder=dataTable.getData("MyFiles","ExpectedSitesCount");
			

			homePageObj.navigateToSitesTab();
			sitesPage.openSiteFromRecentSites(sourceSiteName);
			sitesPage.enterIntoDocumentLibrary();
			
			try
			{
			if(docLibPg.isFileIsAvailable(folderName)){
				myFiles.openFolder(folderName);
				if(epsPageTest.verifyFilePublishStatus("one-inst-published")||epsPageTest.verifyFilePublishStatus("errored"))
				{
				  deleteFileOrFolder(fileName[0], deleteFile);
				  sitesPage.enterIntoDocumentLibrary();
				  deleteFileOrFolder(folderName, deleteFolder);
				}
				else
				{
					sitesPage.enterIntoDocumentLibrary();
					deleteFileOrFolder(folderName, deleteFolder);
				}
			}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			
			
			myFiles.createFolder(folderDetails);
			myFiles.openFolder(folderName);
            myFiles.uploadFile(filePath, fileName[0]);
			UIHelper.waitFor(driver);
			sitesPage.enterIntoDocumentLibrary();
			sitesPage.clickOnMoreSetting(folderName);
			sitesPage.commonMethodForClickOnMoreOptionLink(folderName,moreSettingsOptionName);
			epsPg.Publish("School Content",folderName,"one");
			
			myFiles.openFolder(folderName);
            myFiles.uploadFile(filePath, fileName[1]);
            sitesPage.clickOnMoreSetting(fileName[1]);
			sitesPage.commonMethodForClickOnMoreOptionLink(fileName[1],moreSettingsOptionName);
			epsPg.PublishWithoutWait("School Content");
			
			String splittedString[]=docLibPg.getTheMessageText().split("\\."); 
			System.out.println(splittedString[0]+ ">>>" +splittedString[1]+ ">>> "+splittedString[2]);
			if(splittedString[0].trim().equals(expMsg1)&&splittedString[1].trim().equals(expMsg2)&&splittedString[2].trim().equals(expMsg3) )
			{
				report.updateTestLog("Verify alert message when try to publish a file inside a already published folder.", "Alert Message displayed successfully in french", Status.PASS);
			}
			else
			{
				report.updateTestLog("Verify alert message when try to publish a file inside a already published folder.", "Alert Message failed to displayed in french", Status.FAIL);
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