package testscripts.misc5;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
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
public class AUT_AG_408 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void copyToScreenCheckSharedFiles() {
		testParameters.setCurrentTestDescription(
				"ALFDEPLOY-3980_Verify user should not see create link option in copy to screen for any "
				+ "folders or files in sharedfiles");
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
		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String moreSettingOption=dataTable.getData("MyFiles","MoreSettingsOption");
		String buttonName=dataTable.getData("MyFiles","TagName");
		String location=dataTable.getData("MyFiles","Sort Options");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		ArrayList<String> folderNamesList = myFiles.getFolderNames(folderDetails);
		String folderName = folderNamesList.get(0);
		homePageObj.navigateToSharedFilesTab();
		if(sitesPage.documentAvailable("AutoTest")){
			sitesPage.documentdetails("AutoTest");
			docLibPg.deleteAllFilesAndFolders();
		}else{
			myFiles.createFolder(folderDetails);
		}
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		homePageObj.navigateToSharedFilesTab();
		sitesPage.clickOnMoreSetting(folderName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(folderName ,moreSettingOption);
		UIHelper.waitForLong(driver);
		docLibPg.isRequiredButtonNotAvailable(buttonName,location);
		
		
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}