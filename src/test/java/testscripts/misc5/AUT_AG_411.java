package testscripts.misc5;

import org.testng.annotations.Test;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoShareboxPageTest;
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
public class AUT_AG_411 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void sharebox_001() {
		testParameters.setCurrentTestDescription(
				"ALFDEPLOY-3499_Verify user cannot view the entered email address on Email address field on clicking Delete saved email address button.\r\n" + 
				"ALFDEPLOY-3499_Verify user cannot view the suggestion on entering any characters in Email address filed after clicking on Delete saved email address button.\r\n" + 
				"ALFDEPLOY-3499_Verify user is getting an validation error message on clicking Save button on removing the email address via Delete button.");
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

		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		String siteName = dataTable.getData("Sites", "SiteName");
		String mailIds= dataTable.getData("Sharebox", "ShareboxFieldsData");
		sitesPage.siteFinder(siteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPg.deleteAllFilesAndFolders();
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		myFiles.createFolder(folderDetails);
		String folderName = dataTable.getData("MyFiles", "Version");
		
		sitesPage.clickOnMoreSetting(folderName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(folderName, moreSettingsOptionName);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		UIHelper.waitForVisibilityOfEleByXpath(driver, shareboxPg.sharingPopupXpath);
		shareboxPg.enterEmailAddress(mailIds);
		shareboxPg.clickOnCancelBtnInSharingPopup();
		
		// Verify the error validation on delete
		sitesPage.clickOnMoreSetting(folderName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(folderName, moreSettingsOptionName);
		AlfrescoShareboxPageTest shareboxPgTest=new AlfrescoShareboxPageTest(scriptHelper);
		shareboxPgTest.verifyErrorValidationOnDeleteList();
		shareboxPg.clickOkorCancelInPropmpt("OK");
		shareboxPg.clickOnCancelBtnInSharingPopup();
		
		//Verify no suggestion listed without sending value
		sitesPage.clickOnMoreSetting(folderName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(folderName, moreSettingsOptionName);
		shareboxPgTest.verifySuggestionNotListed("");
		shareboxPg.clickOnCancelBtnInSharingPopup();
		sitesPage.enterIntoDocumentLibrary();
		
		//Verify no suggestion listed by sending value
		sitesPage.clickOnMoreSetting(folderName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(folderName, moreSettingsOptionName);
		shareboxPgTest.verifySuggestionNotListed(mailIds.substring(0, 2));
		
		
		
		
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}