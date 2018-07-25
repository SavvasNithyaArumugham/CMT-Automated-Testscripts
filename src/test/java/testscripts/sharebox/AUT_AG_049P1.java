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
public class AUT_AG_049P1 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void sharebox_049P1() {
		testParameters.setCurrentTestDescription("1. Verify external user is not able to see the exluded folder in External sharebox UI." 
						+"<br>2. Verify external user is not able to see the exluded file in External sharebox UI."
						+"<br>3. Verify external user is not able to see all the files folders which is availble under the Exclueded folder via External sharebox UI."
						+"<br>4. Verify external user able to view and access all the folder files via external sharebox UI after the exclusion is removed for folders files"
						+"<br>5. Verify adding new files and folder inside the Excluded folder and verify external user not able to view the newly added files and folders via external sharebox UI."
						+"<br>6. Verify user is able to share the subfolder which is present inside the excluded folder.");
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
		report.updateTestLog("Invoke Application",
				"Invoke the application under test @ " + properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {
				
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");	
		String notification = dataTable.getData("Sharebox", "Notifications");
		String notifyDetails = dataTable.getData("Sharebox", "Notify_Sharer");
		String properties = dataTable.getData("Sharebox", "ShareboxFieldsData");
		String message = dataTable.getData("Sharebox", "Message");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String date = new DateUtil().getCurrentDate();
		message = message+"_"+date;
		
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		
		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		
		try {
			docLibPg.deleteAllFilesAndFolders();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String moreSettingsOptionNames[] = null;
		if (moreSettingsOptionName.contains(";")) 
		{
		moreSettingsOptionNames = moreSettingsOptionName.split(";");
		}
		//Verify external user is not able to see the exluded folder/file/subfolders under excluded folder in External sharebox UI.
		
		ArrayList<String> allFolderNamesList = new ArrayList<String>();
		StringTokenizer token = new StringTokenizer(folderDetails, ";");
		while (token.hasMoreElements()) {
			String folderdetails = token.nextElement().toString();
			myFiles.createFolder(folderdetails);
			ArrayList<String> folderNamesList = new ArrayList<String>();
			folderNamesList = myFiles.getFolderNames(folderdetails);
			for (String folderName : folderNamesList) {
				myFiles.openCreatedFolder(folderName);
				allFolderNamesList.add(folderName);
			}
		}
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnMoreSetting(allFolderNamesList.get(0));
		docLibPg.commonMethodForClickOnMoreSettingsOption(allFolderNamesList.get(0), moreSettingsOptionNames[1]);
		shareboxPg.commonMethodToEnterSharingProperties(properties, message, notification, notifyDetails);
		shareboxPg.clickOnSaveBtnInSharingPopup();
		sitesPage.openFolder(allFolderNamesList.get(0));
		sitesPage.clickOnMoreSetting(allFolderNamesList.get(1));
		sitesPage.commonMethodForClickOnMoreOptionLink(allFolderNamesList.get(1), moreSettingsOptionNames[0]);
		docLibPg.clickOnOkBtnInPopup();
		
		//Verify user is able to share the subfolder which is present inside the excluded folder.
		sitesPage.openFolder(allFolderNamesList.get(1));
		sitesPage.clickOnMoreSetting(allFolderNamesList.get(2));
		sitesPage.commonMethodForClickOnMoreOptionLink(allFolderNamesList.get(2), moreSettingsOptionNames[1]);
		shareboxPg.commonMethodToEnterSharingProperties(properties, message, notification, notifyDetails);
		shareboxPg.clickOnSaveBtnInSharingPopup();
		docLibPg.navigateToDocumentLibrary();
		sitesPage.openFolder(allFolderNamesList.get(0));
		
		ArrayList<String> allFileNamesList = new ArrayList<String>();
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		ArrayList<String> fileNamesList = new ArrayList<String>();
		fileNamesList = myFiles.getFolderNames(fileName);
		for (String fileNames : fileNamesList) {
			allFileNamesList.add(fileNames);
		}
		sitesPage.clickOnMoreSetting(allFileNamesList.get(0));
		docLibPg.commonMethodForClickOnMoreSettingsOption(allFileNamesList.get(0), moreSettingsOptionNames[0]);
		docLibPg.clickOnOkBtnInPopup();
	}
	
	@Override
	public void tearDown() {
		// Nothing to do
	}
}