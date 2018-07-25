package testscripts.misc3;

import java.util.ArrayList;
import java.util.StringTokenizer;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
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
public class AUT_AG_318P2 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void sharebox_003() {
		testParameters.setCurrentTestDescription(
				"Internal User upload deep level folder inside child folder for verifying folder path displaying in 1hr Aggregated mail");
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
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);

		String sourceSiteName = dataTable.getData("Sites", "SiteName");

		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folder1Details = dataTable.getData("MyFiles", "RelationshipName");
		String zipfolderDetails[] = dataTable.getData("MyFiles", "CreateFileDetails").split(",");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");

		String file2Details = dataTable.getData("MyFiles", "Version");
		String extractTo = dataTable.getData("Document_Details", "ExtractTo");
		String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");

		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();

		ArrayList<String> allFolderNamesList = new ArrayList<String>();

		StringTokenizer token = new StringTokenizer(folderDetails, ";");
		while (token.hasMoreElements()) {
			String folderdetails = token.nextElement().toString();
			ArrayList<String> folderNamesList = new ArrayList<String>();
			folderNamesList = myFiles.getFolderNames(folderdetails);
			for (String folderName : folderNamesList) {
				myFiles.openCreatedFolder(folderName);
				allFolderNamesList.add(folderName);
			}
		}

		myFiles.uploadFile(filePath, fileName);

		UIHelper.waitFor(driver);
		docLibPg.openAFile(fileName);
		docDetailsPage.commonMethodForPerformDocAction(moreSettingsOptionName);

		docDetailsPage.performUnzip(extractTo, fileName);
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		myFiles.openFolder(allFolderNamesList.get(0));
		myFiles.openFolder(allFolderNamesList.get(1));

		for (String zip : zipfolderDetails) {
			myFiles.openFolder(zip);

		}
		myFiles.uploadFile(filePath, file2Details);
		myFiles.createFolder(folder1Details);

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}