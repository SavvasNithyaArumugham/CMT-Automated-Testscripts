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
public class AUT_AG_048 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void sharebox_048() {
		testParameters.setCurrentTestDescription("1. Verify user is not able to perform Exclude from sharebox for the shared folder which is linked inisde the unshared folder." 
						+"<br>2. Verify user is not able to perform Exclude from sharebox for the shared folder which is linked inisde the shared folder.");
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
		String destinationFolder = dataTable.getData("Sites", "TargetFolder");	
		String notification = dataTable.getData("Sharebox", "Notifications");
		String notifyDetails = dataTable.getData("Sharebox", "Notify_Sharer");
		String properties = dataTable.getData("Sharebox", "ShareboxFieldsData");
		String message = dataTable.getData("Sharebox", "Message");
		String propertyName = dataTable.getData("Sites", "FilePropertyName");
		String date = new DateUtil().getCurrentDate();
		message = message+"_"+date;
		
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesPageTest sitePgTestObj = new AlfrescoSitesPageTest(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		
		String moreSettingsOptionNames[] = null;
		if (moreSettingsOptionName.contains(";")) 
		{
		moreSettingsOptionNames = moreSettingsOptionName.split(";");
		}
		//Verify user is not able to perform Exclude from sharebox for the shared folder which is linked inisde the unshared folder.
		myFiles.createFolder(folderDetails);
		ArrayList<String> folderNamesList = myFiles.getFolderNames(folderDetails);
		for (String folderName : folderNamesList) 
		{
		sitesPage.clickOnMoreSetting(folderName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(folderName, moreSettingsOptionNames[1]);
		shareboxPg.commonMethodToEnterSharingProperties(properties, message, notification, notifyDetails);
		shareboxPg.clickOnSaveBtnInSharingPopup();
		sitesPage.clickOnMoreSetting(folderName);
		sitesPage.clickOnMoreLinkOptions(folderName, propertyName);
		docDetailsPageObj.selectFolderInLinkPopUp(sourceSiteName, destinationFolder);
		docDetailsPageObj.clickLinkBtnInLinkPopUp();
		sitesPage.openFolder(destinationFolder);
		sitesPage.clickOnMoreSetting(folderName);
		sitePgTestObj.verifyMoreSettingsOptionForFileOrFolderItemNegativeCase(folderDetails , moreSettingsOptionNames[0]);
	
		//Verify user is not able to perform Exclude from sharebox for the shared folder which is linked inisde the shared folder.
		docLibPg.navigateToDocumentLibrary();
		sitesPage.clickOnMoreSetting(destinationFolder);
		docLibPg.commonMethodForClickOnMoreSettingsOption(destinationFolder, moreSettingsOptionNames[1]);
		shareboxPg.commonMethodToEnterSharingProperties(properties, message, notification, notifyDetails);
		shareboxPg.clickOnSaveBtnInSharingPopup();
		sitesPage.openFolder(destinationFolder);
		sitesPage.clickOnMoreSetting(folderName);
		sitePgTestObj.verifyMoreSettingsOptionForFileOrFolderItemNegativeCase(folderName , moreSettingsOptionNames[0]);
		docLibPg.navigateToDocumentLibrary();
		sitesPage.clickOnMoreSetting(folderName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(folderName, moreSettingsOptionNames[3]);
		docLibPg.clickOnOkBtnInPopup();	
		sitesPage.clickOnMoreSetting(destinationFolder);
		docLibPg.commonMethodForClickOnMoreSettingsOption(destinationFolder, moreSettingsOptionNames[3]);
		docLibPg.clickOnOkBtnInPopup();	
		}
	}
	@Override
	public void tearDown() {
		// Nothing to do
	}
}