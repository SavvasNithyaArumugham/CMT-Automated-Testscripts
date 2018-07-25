package testscripts.sharebox;

import java.util.ArrayList;
import java.util.StringTokenizer;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.GmailVerificationPage;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;
import com.pearson.framework.selenium.Browser;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_054 extends TestCase {
	private FunctionalLibrary functionalLibrary;
	@Test
	public void sharebox_051() {
		testParameters.setCurrentTestDescription("1. Verify external user is able to access the external sharebox UI when the shared folder is moved inside the excluded folder."
				+"<br>2. Verify the exclusion is still applied when performing upload new version for the already excluded file."
				+"<br>3. Verify user is able to exclude the files which are uploaded by external sharebox user.");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		testParameters.setBrowser(Browser.Chrome);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application","Invoke the application under test @ "+ properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {
		GmailVerificationPage gmailobj = new GmailVerificationPage(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		
		String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String message = dataTable.getData("Sharebox", "Message");
		String folderdetails = dataTable.getData("MyFiles", "CreateFolder");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String notification = dataTable.getData("Sharebox", "Notifications");
		String notifyDetails = dataTable.getData("Sharebox", "Notify_Sharer");
		String Properties = dataTable.getData("Sharebox", "ShareboxFieldsData");
		String shareboxuserfile = dataTable.getData("MyFiles", "Version");
		String versionChangeTo = dataTable.getData("Document_Details", "Version");
		String comments = dataTable.getData("Document_Details", "Comments");
		
		String date = new DateUtil().getCurrentDate();
		message = message + "_" + date;

		
		String moreSettingsOptionNames[] = null;
		if (moreSettingsOptionName.contains(";")) 
		{
		moreSettingsOptionNames = moreSettingsOptionName.split(";");
		}	
		functionalLibrary.loginAsValidUser(signOnPage);
		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		
		ArrayList<String> allFolderNamesList = new ArrayList<String>();
		StringTokenizer token = new StringTokenizer(folderdetails, ";");
		while (token.hasMoreElements()) {
			String folderDetails = token.nextElement().toString();
			myFiles.createFolder(folderDetails);
			ArrayList<String> folderNamesList = new ArrayList<String>();
			folderNamesList = myFiles.getFolderNames(folderDetails);
			for (String folderName : folderNamesList) {
				allFolderNamesList.add(folderName);
			}
		}
		sitesPage.clickOnMoreSetting(allFolderNamesList.get(0));
		sitesPage.commonMethodForClickOnMoreOptionLink(allFolderNamesList.get(0), moreSettingsOptionNames[0]);
		docLibPg.clickOnOkBtnInPopup();
		sitesPage.clickOnMoreSetting(allFolderNamesList.get(1));
		sitesPage.commonMethodForClickOnMoreOptionLink(allFolderNamesList.get(1), moreSettingsOptionNames[1]);
		shareboxPg.commonMethodToEnterSharingProperties(Properties, message, notification, notifyDetails);
		shareboxPg.clickOnSaveBtnInSharingPopup();
		sitesPage.openFolder(allFolderNamesList.get(1));
		
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		docLibPg.navigateToDocumentLibrary();
		sitesPage.clickOnMoreSetting(allFolderNamesList.get(1));
		sitesPage.clickOnMoveToLink(allFolderNamesList.get(1));
		docLibPg.performMoveToOperation(sourceSiteName, allFolderNamesList.get(0));
		
		driver.get(properties.getProperty("GmailUrl"));
		
		report.updateTestLog("Invoke Application","Invoke the application under test @ "+ properties.getProperty("GmailUrl"), Status.DONE);
		
		gmailobj.enterCredentials();
		
		gmailobj.searchWFmessage(message);
		
		shareboxPg.navigateToShareboxLinkFromMail(allFolderNamesList.get(0));
		shareboxPg.verifyfolderOrfiledisplayedinExShareboxUI(fileName);
		shareboxPg.uploadFileInSharedFolder(filePath, shareboxuserfile);
		
		driver.get(properties.getProperty("ApplicationUrl"));
		
		report.updateTestLog("Invoke Application","Invoke the application under test @ "+ properties.getProperty("ApplicationUrl"), Status.DONE);
		
		if (homePageObj.isDisplayedAllSitesInHomePage()) {
			homePageObj.navigateToSitesTab();
		}
		else 
		{
			functionalLibrary.loginAsValidUser(signOnPage);
			homePageObj.navigateToSitesTab();
		}
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.openFolder(allFolderNamesList.get(0));
		sitesPage.openFolder(allFolderNamesList.get(1));
		sitesPage.clickOnMoreSetting(shareboxuserfile);
		sitesPage.commonMethodForClickOnMoreOptionLink(shareboxuserfile, moreSettingsOptionNames[0]);
		docLibPg.clickOnOkBtnInPopup();
		sitesPage.clickOnMoreSetting(shareboxuserfile);
		sitesPage.commonMethodForClickOnMoreOptionLink(shareboxuserfile, moreSettingsOptionNames[3]);
		sitesPage.clickOnMoreSetting(shareboxuserfile);
		sitesPage.commonMethodForClickOnMoreOptionLink(shareboxuserfile, moreSettingsOptionNames[2]);
		docLibPg.uploadNewVersionFileInDocLibPage(shareboxuserfile, filePath, versionChangeTo, comments);
		docLibPg.checkVersionIncremented(shareboxuserfile, message);
		docLibPg.verifyexcludedfiletextavailble(shareboxuserfile);
	}
	@Override
	public void tearDown() {
		// Nothing to do
	}
}