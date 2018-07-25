package testscripts.sharebox;

import org.testng.annotations.Test;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
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
public class AUT_AG_047P2 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void sharebox_047P2() {
		testParameters.setCurrentTestDescription("1. Verify the option Exclude from sharebox is not displayed via selected items when multiple files folders got selected from document library." 
						+"<br>2. Verify the options Share folder externally and Exlude from sharbox via More on cancelling the Exclude from sharebox sharing confirmation window." 
						+"<br>3. Verify the option Exclude from sharebox is not displayed for the folders files which are already excluded."
						+"<br>4. Verify the option Share folder externally is not displayed for the folders files which are already excluded." 
						+"<br>5. Verify the message Folder and all its subfolders and content are excluded from sharebox sharing is not displayed below the file folder on performing Remove sharebox exclusion actions via More actions." 
						+"<br>6. Verify the Exclude from sharbox option is not displayed for the shared folder." 
						+"<br>7. Verify the Exclude from sharbox option is displayed once the Sharing is stopped for the shared folder.");
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
		String selectItemMenuOptName = dataTable.getData("Sites", "SelectedItemsMenuOption");		
		String fileName = dataTable.getData("MyFiles", "FileName");
		String notification = dataTable.getData("Sharebox", "Notifications");
		String notifyDetails = dataTable.getData("Sharebox", "Notify_Sharer");
		String properties = dataTable.getData("Sharebox", "ShareboxFieldsData");
		String message = dataTable.getData("Sharebox", "Message");
		String date = new DateUtil().getCurrentDate();
		message = message+"_"+date;
		
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		//AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		//AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesPageTest sitePgTestObj = new AlfrescoSitesPageTest(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		
		//Verify the option "Exclude from sharebox" is not displayed via selected items when multiple files/folders got selected from document library.
		docLibPg.selectAllFilesAndFolders();
		docLibPg.clickSelectedItemsMenu();
		sitePgTestObj.verifySelectedItemsMenuOptionForNegativeCase(selectItemMenuOptName);
		
		String moreSettingsOptionNames[] = null;
		if (moreSettingsOptionName.contains(";")) 
		{
		moreSettingsOptionNames = moreSettingsOptionName.split(";");
		}
		
		//Verify the options Share folder externally and Exlude from sharbox via More on cancelling the Exclude from sharebox sharing confirmation window
		sitesPage.clickOnMoreSetting(folderDetails);
		docLibPg.commonMethodForClickOnMoreSettingsOption(folderDetails, moreSettingsOptionNames[0]);
		docLibPg.verifyConfirmationWindow();
		docLibPg.clickOnOkcancelBtnInPopup();
		sitesPage.clickOnMoreSetting(folderDetails);
		sitePgTestObj.verifyMoreSettingsOptionForFileOrFolderItem(folderDetails, moreSettingsOptionNames[0]);
		sitePgTestObj.verifyMoreSettingsOptionForFileOrFolderItem(folderDetails, moreSettingsOptionNames[1]);
		
		//Verify the option Exclude from sharebox is not displayed for the folders and files which are already excluded.
		sitesPage.clickOnMoreSetting(folderDetails);
		docLibPg.commonMethodForClickOnMoreSettingsOption(folderDetails, moreSettingsOptionNames[0]);
		docLibPg.verifyConfirmationWindow();
		docLibPg.clickOnOkBtnInPopup();
		sitesPage.clickOnMoreSetting(folderDetails);
		sitePgTestObj.verifyMoreSettingsOptionForFileOrFolderItemNegativeCase(folderDetails, moreSettingsOptionNames[0]);
		sitesPage.clickOnMoreSetting(fileName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, moreSettingsOptionNames[0]);
		docLibPg.verifyConfirmationWindow();
		docLibPg.clickOnOkBtnInPopup();
		sitesPage.clickOnMoreSetting(fileName);
		sitePgTestObj.verifyMoreSettingsOptionForFileOrFolderItemNegativeCase(fileName , moreSettingsOptionNames[0]);
		
		//Verify the option Share folder externally is not displayed for the folders which are already excluded.
		sitesPage.clickOnMoreSetting(folderDetails);
		sitePgTestObj.verifyMoreSettingsOptionForFileOrFolderItemNegativeCase(folderDetails, moreSettingsOptionNames[1]);
				
		//Verify the message "Folder and all its subfolders and content are excluded from sharebox sharing" is not displayed below the folder on performing Remove sharebox exclusion actions via More actions.
		sitePgTestObj.verifyMoreSettingsOptionForFileOrFolderItem(folderDetails , moreSettingsOptionNames[2]);
		docLibPg.commonMethodForClickOnMoreSettingsOption(folderDetails, moreSettingsOptionNames[2]);
		docLibPg.clickOnOkBtnInPopup();
		docLibPg.verifyexcludedfoldertextNOTavailbleNegative(folderDetails);
				
		//Verify the message "Document is excluded from ShareBox sharing" is not displayed below the file on performing Remove sharebox exclusion actions via More actions.
		sitesPage.clickOnMoreSetting(fileName);
		sitePgTestObj.verifyMoreSettingsOptionForFileOrFolderItem(fileName , moreSettingsOptionNames[2]);
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, moreSettingsOptionNames[2]);
		docLibPg.clickOnOkBtnInPopup();
		docLibPg.verifyexcludedfiletexNOTtavailbleNegative(fileName);
		
		//Verify the Exclude from sharbox option is not displayed for the shared folder.
		sitesPage.clickOnMoreSetting(folderDetails);
		sitePgTestObj.verifyMoreSettingsOptionForFileOrFolderItem(folderDetails , moreSettingsOptionNames[0]);
		sitePgTestObj.verifyMoreSettingsOptionForFileOrFolderItem(folderDetails , moreSettingsOptionNames[1]);
		docLibPg.commonMethodForClickOnMoreSettingsOption(folderDetails, moreSettingsOptionNames[1]);
		shareboxPg.commonMethodToEnterSharingProperties(properties, message, notification, notifyDetails);
		shareboxPg.clickOnSaveBtnInSharingPopup();
		sitesPage.clickOnMoreSetting(folderDetails);
		sitePgTestObj.verifyMoreSettingsOptionForFileOrFolderItemNegativeCase(folderDetails , moreSettingsOptionNames[0]);
		
		//Verify the Exclude from sharbox option is displayed once the Sharing is stopped for the shared folder.
		sitesPage.clickOnMoreSetting(folderDetails);
		docLibPg.commonMethodForClickOnMoreSettingsOption(folderDetails, moreSettingsOptionNames[3]);
		docLibPg.clickOnOkBtnInPopup();
		sitesPage.clickOnMoreSetting(folderDetails);
		sitePgTestObj.verifyMoreSettingsOptionForFileOrFolderItem(folderDetails , moreSettingsOptionNames[0]);
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}