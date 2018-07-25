package testscripts.sharebox;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
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
public class AUT_AG_053 extends TestCase {
	private FunctionalLibrary functionalLibrary;
	@Test
	public void sharebox_050() {
		testParameters.setCurrentTestDescription("1. Verify Exclude from sharebox option is not displayed for the moved folder file which is having Exclusion applied already."
				+"<br>2. Verify Exclude from sharebox option is displayed for the copied folder file which is having Exclusion applied already.");
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

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		//AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		
		//ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String message = dataTable.getData("Sharebox", "Message");
		String folderdetails = dataTable.getData("MyFiles", "CreateFolder");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String destinationFolder = dataTable.getData("Sites", "TargetFolder");	
		
		String date = new DateUtil().getCurrentDate();
		message = message + "_" + date;
		
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		
		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		
		myFiles.createFolder(filePath);
		
		String moreSettingsOptionNames[] = null;
		if (moreSettingsOptionName.contains(";")) 
		{
		moreSettingsOptionNames = moreSettingsOptionName.split(";");
		}
		
		ArrayList<String> allFolderNamesList = new ArrayList<String>();
		ArrayList<String> folderNamesList = new ArrayList<String>();
		folderNamesList = myFiles.getFolderNames(folderdetails);
		for (String folderName : folderNamesList) {
			allFolderNamesList.add(folderName);
		}
		//Verify Exclude from sharebox option is not displayed for the moved folder file which is having Exclusion applied already.
		sitesPage.openFolder(allFolderNamesList.get(0));
		sitesPage.clickOnMoreSetting(allFolderNamesList.get(1));
		sitesPage.commonMethodForClickOnMoreOptionLink(allFolderNamesList.get(1), moreSettingsOptionNames[0]);
		docLibPg.clickOnOkBtnInPopup();
		sitesPage.clickOnMoreSetting(allFolderNamesList.get(1));
		sitesPage.clickOnMoveToLink(allFolderNamesList.get(1));
		docLibPg.performMoveToOperation(sourceSiteName, destinationFolder);
		docLibPg.navigateToDocumentLibrary();
		sitesPage.openFolder(destinationFolder);
		docLibPg.verifyexcludedfoldertextavailble(allFolderNamesList.get(1));
		
		ArrayList<String> allFileNamesList = new ArrayList<String>();
		ArrayList<String> fileNamesList = new ArrayList<String>();
		fileNamesList = myFiles.getFolderNames(fileName);
		for (String fileNames : fileNamesList) {
			allFileNamesList.add(fileNames);
		}
		
		docLibPg.navigateToDocumentLibrary();
		sitesPage.openFolder(allFolderNamesList.get(0));
		sitesPage.clickOnMoreSetting(allFileNamesList.get(0));
		docLibPg.commonMethodForClickOnMoreSettingsOption(allFileNamesList.get(0), moreSettingsOptionNames[0]);
		docLibPg.clickOnOkBtnInPopup();
		sitesPage.clickOnMoreSetting(allFileNamesList.get(0));
		sitesPage.clickOnMoveToLink(allFileNamesList.get(0));
		docLibPg.performMoveToOperation(sourceSiteName, destinationFolder);
		docLibPg.navigateToDocumentLibrary();
		sitesPage.openFolder(destinationFolder);
		docLibPg.verifyexcludedfiletextavailble(allFileNamesList.get(0));
		
		//Verify Exclude from sharebox option is displayed for the copied folder file which is having Exclusion applied already.
		sitesPage.clickOnMoreSetting(allFolderNamesList.get(1));
		sitesPage.clickOnCopyToLink(allFolderNamesList.get(1));
		docLibPg.performCopyToOperation(sourceSiteName, allFolderNamesList.get(0));
		docLibPg.navigateToDocumentLibrary();
		sitesPage.openFolder(allFolderNamesList.get(0));
		docLibPg.verifyexcludedfoldertextavailble(allFolderNamesList.get(1));
		
		docLibPg.navigateToDocumentLibrary();
		
		sitesPage.openFolder(destinationFolder);
		sitesPage.clickOnMoreSetting(allFileNamesList.get(0));
		sitesPage.clickOnCopyToLink(allFileNamesList.get(0));
		docLibPg.performCopyToOperation(sourceSiteName, allFolderNamesList.get(0));
		docLibPg.navigateToDocumentLibrary();
		sitesPage.openFolder(allFolderNamesList.get(0));
		docLibPg.verifyexcludedfiletextavailble(allFileNamesList.get(0));
		}
	@Override
	public void tearDown() {
		// Nothing to do
	}
}