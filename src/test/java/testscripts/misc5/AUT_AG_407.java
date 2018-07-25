package testscripts.misc5;

import org.testng.annotations.Test;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
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
public class AUT_AG_407 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void copyToScreenCheckMyFiles() {
		testParameters.setCurrentTestDescription(
				"ALFDEPLOY-3980_Verify user should not see create link option in copy to screen for any folders or"
				+ " files in myfiles");
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
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String folderName = dataTable.getData("MyFiles", "CreateFolder");
		String openfolderName = dataTable.getData("MyFiles", "CreateFileDetails");
		String moreSettingOption=dataTable.getData("MyFiles","MoreSettingsOption");
		String buttonName=dataTable.getData("MyFiles","TagName");
		String location=dataTable.getData("MyFiles","Sort Options");
		homePageObj.navigateToMyFilesTab();
		docLibPage.deleteAllFilesAndFolders();
		myFiles.createFolder(folderName);
		myFiles.openCreatedFolder(openfolderName);
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		myFilesTestObj.verifyUploadedFile(fileName, "");
		homePageObj.navigateToMyFilesTab();
		docLibPage.selectAllFilesAndFolders();
		docLibPage.commonMethodForClickOnMoreSettingsOption(openfolderName ,moreSettingOption );
		UIHelper.waitForLong(driver);
		docLibPage.isRequiredButtonNotAvailable(buttonName,location);
		
		
		
		}
		
		
	@Override
	public void tearDown() {
		// Nothing to do
	}
}
