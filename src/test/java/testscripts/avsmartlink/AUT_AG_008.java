package testscripts.avsmartlink;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import java.io.File;
import java.util.ArrayList;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_008 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVSMARTLINK_008() {
		testParameters.setCurrentTestDescription(
				"1.Verify Create Multi Smart Link option is not displayed in More Option on uploading the renamed csv spreadsheet for "
				+ "all 8 templates with any dummy name not prefixed by spreadsheet naming convention<br>"
				+"2. Verify new smart links are created in folders based on the spreadsheet values on selecting 'Create Multi Smart Link' option for the valid sl_image.csv file"		);
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
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
	//	AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
	//	AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoSitesPageTest sitesPageTests = new AlfrescoSitesPageTest(scriptHelper);	

		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String siteName = dataTable.getData("Sites", "SiteName");
	//	String extractTo = dataTable.getData("Document_Details", "ExtractTo");
		String csvName = dataTable.getData("MyFiles", "Version");
		String smartlinks = dataTable.getData("MyFiles", "CreateFileDetails");
		String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");

		File downloadedFile = null;
		ArrayList<String> csvFileRowDataList = null;

		String downloadFilePath = properties.getProperty("DefaultDownloadPath");

		functionalLibrary.loginAsValidUser(signOnPage);

		sitesPage.siteFinder(siteName);

		sitesPage.enterIntoDocumentLibrary();

		docLibPg.deleteAllFilesAndFolders();

		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		sitesPage.clickOnMoreSetting(fileName);

		sitesPageTests.verifyMoreSettingsOptionForFileOrFolderItemNegativeCase(fileName, moreSettingsOption);
	
		myFiles.uploadFileInMyFilesPage(filePath, csvName);
		
		sitesPage.clickOnMoreSetting(csvName);

		sitesPageTests.verifyMoreSettingsOptionForFileOrFolderItemNegativeCase(csvName, moreSettingsOption);

		myFiles.uploadFileInMyFilesPage(filePath, smartlinks);
		
		sitesPage.clickOnMoreSetting(smartlinks);

		sitesPageTests.verifyMoreSettingsOptionForFileOrFolderItemNegativeCase(smartlinks, moreSettingsOption);
	
	}

	@Override
	public void tearDown() {

	}
}
