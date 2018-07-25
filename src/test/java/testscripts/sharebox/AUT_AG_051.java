package testscripts.sharebox;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoMySitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoRepositoryPage;
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DateUtil;
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
public class AUT_AG_051 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void sharebox_036() {
		testParameters.setCurrentTestDescription(
				"ALFDEPLOY-3638_Verify user can download a root folder contains 2 level subfolder and each folders has some files in it-Part 1:Share folder externally");
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

		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);

		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String properties = dataTable.getData("Sharebox", "ShareboxFieldsData");
		String message = dataTable.getData("Sharebox", "Message");

		String date = new DateUtil().getCurrentDate();
		message = message + "_" + date;
		String notification = dataTable.getData("Sharebox", "Notifications");
		String notifyDetails = dataTable.getData("Sharebox", "Notify_Sharer");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String[] fileName = dataTable.getData("MyFiles", "FileName").split(",");

		String[] folderDetails = dataTable.getData("MyFiles", "CreateFolder").split(";");
		String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");
		String[] folderName = dataTable.getData("MyFiles", "Version").split(",");

		functionalLibrary.loginAsValidUser(signOnPage);

		sitesPage.siteFinder(sourceSiteName);

		sitesPage.enterIntoDocumentLibrary();

		docLibPg.deleteAllFilesAndFolders();

		for (int i = 0; i < folderDetails.length; i++) {
			myFiles.createFolder(folderDetails[i]);

			sitesPage.documentdetails(folderName[i]);

			myFiles.uploadFileInMyFilesPage(filePath, fileName[i]);
		}

		sitesPage.enterIntoDocumentLibraryWithoutReport();

		sitesPage.clickOnMoreSetting(folderName[0]);

		docLibPg.commonMethodForClickOnMoreSettingsOption(folderName[0], moreSettingsOptionName);
		shareboxPg.commonMethodToEnterSharingProperties(properties, message, notification, notifyDetails);
		shareboxPg.clickOnSaveBtnInSharingPopup();

		if (shareboxPg.isfoldershared(folderName[0])) {

			report.updateTestLog("Verify root folder containing subfolder and files are shared externally",
					"Root folder containing subfolder and files are shared externally <b><br>  File/Folder Name: <b>"
							+ folderName[0],
					Status.PASS);

		} else {
			report.updateTestLog("Verify root folder containing subfolder and files are shared externally",
					"Root folder containing subfolder and files are failed to share externally <b><br>  File/Folder Name: <b>"
							+ folderName[0],
					Status.FAIL);

		}

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}