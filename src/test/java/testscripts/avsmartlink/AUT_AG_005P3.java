package testscripts.avsmartlink;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAVSmartLinkPage;
import com.pearson.automation.alfresco.pages.AlfrescoAVTPage;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoPCSPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_005P3 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AV_002() {
		testParameters.setCurrentTestDescription(
				"Verify whether Contributor is able to create smart link object(audio external link) in document library<br>"
						+ "R2.0.2_ALFDEPLOY-5984_Verify user is able to create smartlink successfully on entering backslash in Image Alt Text, Caption Text, Copyright or Credit Text fields for Audio External link.");
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
		AlfrescoAVSmartLinkPage avsmart = new AlfrescoAVSmartLinkPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSiteMembersPageTest siteMemPgTest = new AlfrescoSiteMembersPageTest(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);

		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");

		String type = dataTable.getData("Home", "DashletName");
		String title = dataTable.getData("Document_Details", "FilePath");
		String extURLLink = dataTable.getData("Document_Details", "FileName");

		functionalLibrary.loginAsValidUser(signOnPage);

		String siteName = sitesPage.getCreatedSiteName();
		sitesPage.siteFinder(siteName);

		sitesPage.enterIntoDocumentLibrary();

		myFiles.createcontenttype(type);
		avsmart.clickSmartLinkType("Audio External Link", "audioExternalLink");
		avsmart.entersmarttypedata("audioExternalLink", title, extURLLink, "", "", "", "",
				System.getProperty("user.dir") + filePath + fileName);
		avsmart.subcancelbtn("audioExternalLink", "Submit");

		try {
			Thread.sleep(2000);
		} catch (Exception e) {

		}

		myFilesTestObj.verifyUploadedFile(title, "");

		// backslash
		sitesPage.enterIntoDocumentLibrary();
		myFiles.createcontenttype(type);
		avsmart.clickSmartLinkType("Audio External Link", "audioExternalLink");
		avsmart.entersmarttypedata("audioExternalLink", "AudioSmartLinkBackslash", extURLLink, "\\b", "\\f", "\\", "",
				System.getProperty("user.dir") + filePath + fileName);
		avsmart.subcancelbtn("audioExternalLink", "Submit");

		myFilesTestObj.verifyUploadedFile("AudioSmartLinkBackslash", "");

		collectionPg.clickOnMoreSetting("AudioSmartLinkBackslash");
		collectionPg.commonMethodForClickOnMoreSettingsOption("AudioSmartLinkBackslash", "Edit SmartLink");
		avsmart.EditSmartlinkFilePathValidation("audioExternalLink", "AudioSmartLinkBackslash", siteName, fileName);
		avsmart.cancelButtonOnEditSmartlink("audioExternalLink", "AudioSmartLinkBackslash");

	}

	@Override
	public void tearDown() {

	}
}