package testscripts.avt;

import org.testng.annotations.Test;
import com.pearson.automation.utils.TestCase;
import java.util.ArrayList;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAVTPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AVT_TC021 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVT_001() {
		testParameters
				.setCurrentTestDescription("1.Verify the fields of Upload File tab in Create Streaming media screen."
						+ "<br>Streaming media screen_Upload file tab fields verification");

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

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFilePageObj = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoAVTPage alfrescoAVTPage = new AlfrescoAVTPage(scriptHelper);

		String fileOptions = dataTable.getData("MyFiles", "BrowseActionName");
		String uploadlabel = dataTable.getData("MyFiles", "Sort Options");
		String singleFileFields = dataTable.getData("MyFiles", "RelationshipName");

		// Login alf cms
		functionalLibrary.loginAsValidUser(signOnPage);

		// From the site Type dropdown select 'Collaboration Site '.
		homePageObj.navigateToSitesTab();

		String siteName = sitesPage.getCreatedSiteName();
		sitesPage.openSiteFromRecentSites(siteName);

		// Navigate to document library
		sitesPage.enterIntoDocumentLibrary();

		// click on streaming media
		alfrescoAVTPage.clickOnStreamingMedia();

		// verify files option type
		ArrayList<String> fileOptionsType = new ArrayList<String>();
		fileOptionsType = myFilePageObj.getFileNames(fileOptions);
		alfrescoAVTPage.verifyFileOptionAvailable(fileOptionsType);

		// verify upload files tab's fields
		ArrayList<String> uploadTabField = new ArrayList<String>();
		uploadTabField = myFilePageObj.getFileNames(uploadlabel);
		alfrescoAVTPage.verifyUploadedTabFields(uploadTabField);

		// verify Single upload tab
		ArrayList<String> singleFileTabField = new ArrayList<String>();
		singleFileTabField = myFilePageObj.getFileNames(singleFileFields);
		alfrescoAVTPage.verifySingleFileTabFields(singleFileTabField);
		alfrescoAVTPage.verifyBatchFileUpload();

	}

	@Override
	public void tearDown() {

	}
}
