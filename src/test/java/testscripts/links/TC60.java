package testscripts.links;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoRepositoryPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class TC60 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void LINKS_060() {
		testParameters
				.setCurrentTestDescription("Verify that user don't get pop up when user opens any linked file from linked location");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog(
				"Invoke Application",
				"Invoke the application under test @ "
						+ properties.getProperty("ApplicationUrl"), Status.DONE);

	}

	@Override
	public void executeTest() {
		ArrayList<String> folderNamesList = new ArrayList<String>();
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		homePageObj.navigateToSitesTab();

		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String sourceSiteName = dataTable.getData("Sites", "SiteName");

		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();

		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");

		myFiles.deleteCreatedFolder(folderDetails);

		myFiles.createFolder(folderDetails);

		myFiles.deleteUploadedFile(fileName);

		myFiles.uploadFileInMyFilesPage(filePath, fileName);

		myFiles.openUploadedOrCreatedFile(fileName, "");

		docDetailsPageObj.clickonLinkToInPreviewPage(fileName);

		docDetailsPageObj.selectFolderInLinkPopUp(sourceSiteName);
		docDetailsPageObj.clickLinkBtnInLinkPopUp();

		sitesPage.enterIntoDocumentLibrary();

		folderNamesList = myFiles.getFolderNames(folderDetails);

		for (String folderName : folderNamesList) {
			myFiles.openFolder(folderName);
		}

		
		AlfrescoSearchPage searchPageObj = new AlfrescoSearchPage(scriptHelper);
	

		myFiles.openUploadedOrCreatedFile(fileName, "");
		
		if (searchPageObj
				.getTheMessageText("This Document is located to site"
						+ sourceSiteName
						+ " We will redirect you to this site shortly") == "This Document is located to site"
				+ sourceSiteName + " We will redirect you to this site shortly") {

			report.updateTestLog("Verfiy warning message displayed",
					"Warning message displayed", Status.FAIL);

		} else {
			report.updateTestLog("Verfiy warning message displayed",
					"Warning message is not displayed", Status.PASS);
		}

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}