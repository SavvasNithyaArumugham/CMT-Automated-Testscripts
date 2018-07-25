package testscripts.avt;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAVTPage;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AVT_TC009 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVT_009() {
		testParameters
				.setCurrentTestDescription("36	Verify user is able to delete the uploaded Thumbnail file in the alfresco via Edit streaming media screen"
						+ "37	Verify user is able to delete the uploaded caption file in the alfresco via Edit streaming media screen"
						+ "38	Verify user is able to delete the uploaded chaptering xml file in the alfresco via Edit streaming media screen"
						+ "" + "");

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

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);
		AlfrescoAVTPage alfrescoAVTPage = new AlfrescoAVTPage(scriptHelper);
		String filePath = dataTable.getData("MyFiles", "FilePath");

		// Login alf cms
		functionalLibrary.loginAsValidUser(signOnPage);

		// From the site Type dropdown select 'Collaboration Site '.
		homePageObj.navigateToSitesTab();
		String siteName = sitesPage.getCreatedSiteName();
		// String siteName = "AutoAVTRandomSite50517074206";
		sitesPage.openSiteFromRecentSites(siteName);

		sitesPage.enterIntoDocumentLibrary();
		ArrayList<String> listOfFilesForMOV = new ArrayList<String>();
		listOfFilesForMOV = alfrescoAVTPage
				.getFilesAndFoldersListFromDocumentLibrary();
		for (String string : listOfFilesForMOV) {

			if (string.contains("MOV") || string.contains("MP4")
					|| string.contains("M4V")) {
				myFiles.openCreatedFolder(string);

				ArrayList<String> listOfFiles = new ArrayList<String>();
				listOfFiles = alfrescoAVTPage
						.getFilesAndFoldersListFromDocumentLibrary();
				// System.out.println(listOfFiles);
				report.updateTestLog(" list of files in " + string,
						"before removing the Thumbnail, chapter xml, captions",
						Status.PASS);
				alfrescoAVTPage.clickOnStreamingMediaOnJsonFile();
				alfrescoAVTPage.deleteThumbnail();
				alfrescoAVTPage.deleteChaptering();
				alfrescoAVTPage.deleteCaption();
				alfrescoAVTPage.clickOnSaveAndUpload();
				alfrescoAVTPage
						.verifyStreamingUploadProgressAndClickOkOnTranscodingInProgressPromptBox();
				listOfFiles = alfrescoAVTPage
						.getFilesAndFoldersListFromDocumentLibrary();
				// System.out.println(listOfFiles);
				report.updateTestLog(" list of files " + string,
						"after removing the Thumbnail, chapter xml, captions",
						Status.PASS);

				sitesPage.enterIntoDocumentLibrary();

			}

		}

	}

	@Override
	public void tearDown() {

	}
}
