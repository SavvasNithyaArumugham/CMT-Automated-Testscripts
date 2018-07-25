package testscripts.misc5;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with valid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_406 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void copyToScreenCheckDocLib2() {
		testParameters.setCurrentTestDescription(
				"ALFDEPLOY-3980_Verify user should not see create link option in copy to screen for multiple folders or"
				+ " files by using selected items dropdown");
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
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String siteName = dataTable.getData("Sites", "SiteName");
		String moreSettingOption=dataTable.getData("MyFiles","MoreSettingsOption");
		String buttonName=dataTable.getData("MyFiles","TagName");
		String location=dataTable.getData("MyFiles","Sort Options");
		sitesPage.siteFinder(siteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		myFilesTestObj.verifyUploadedMultipleFiles(fileName);
		docLibPage.selectAllFilesAndFolders();
		sitesPage.clickOnSelectedItems();
		sitesPage.selectItemFromSelectedItemsMenuOption(moreSettingOption);
		UIHelper.waitForLong(driver);
		docLibPage.isRequiredButtonNotAvailable(buttonName,location);
		
		
		}
		
		
	@Override
	public void tearDown() {
		// Nothing to do
	}
}
