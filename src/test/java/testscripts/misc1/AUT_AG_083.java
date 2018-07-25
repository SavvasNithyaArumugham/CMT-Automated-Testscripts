package testscripts.misc1;

import java.util.ArrayList;
import java.util.regex.Pattern;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_083 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void MISC1_073() {
		testParameters.setCurrentTestDescription(
				"Verify that user is able to download Images files on performing edit offline operation");
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
		ArrayList<String> fileNamesList = new ArrayList<String>();
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);

		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);

		String siteName = dataTable.getData("Sites", "SiteName");

		homePageObj.navigateToSitesTab();

		sitesPage.openSiteFromRecentSites(siteName);

		sitesPage.enterIntoDocumentLibrary();
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		try {
			docLibPg.deleteAllFilesAndFolders();
			docLibPg.deleteAllFilesAndFolders();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String fileName = dataTable.getData("MyFiles", "FileName");
		String filepath = dataTable.getData("MyFiles", "FilePath");

		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);

		myFiles.uploadFileInMyFilesPage(filepath, fileName);

		fileNamesList = myFiles.getFileNames(fileName);

		for (int i = 0; i < fileNamesList.size(); i++) {
			sitesPage.clickOnMoreSetting(fileNamesList.get(i));

			String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");

			docLibPg.commonMethodForClickOnMoreSettingsOption(fileNamesList.get(i), moreSettingsOption);

			String fileDownloadPath = properties.getProperty("DefaultDownloadPath");
			String finalFileName;
			if (fileNamesList.get(i).contains(".")) {
				String splitVal[] = fileNamesList.get(i).split(Pattern.quote("."));
				String part1 = splitVal[0];
				finalFileName = part1 + "." + splitVal[1];
			} else {
				finalFileName = fileName;
			}

			new FileUtil().deleteIfFileExistsInDownloadPath(fileDownloadPath, finalFileName);

			new FileUtil().waitUptoFileDownloadComplete(fileDownloadPath, finalFileName);

			AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);

			docDetailsPageTest.verifyDownloadedFile(false, finalFileName);

			if (i < fileNamesList.size() - 1)
				sitesPage.enterIntoDocumentLibrary();

		}

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}