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

public class AUT_AG_005P2 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AV_002() {
		testParameters.setCurrentTestDescription(
				"Verify whether collaborator is able to create smart link object(metrodigi link) in document library<br>"
						+ "R2.0.2_ALFDEPLOY-5984_Verify user is able to edit smartlink successfully on entering backslash in Image Reference URL, Image Alt Text, Caption Text, Copyright or Credit Text fields for Metrodigi smartlink");
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
		avsmart.clickSmartLinkType("Metrodigi Link", "metrodigiLink");
		avsmart.entersmarttypedata("metrodigiLink", title, extURLLink, "", "", "", "",
				System.getProperty("user.dir") + filePath + fileName);
		avsmart.subcancelbtn("metrodigiLink", "Submit");

		try {
			Thread.sleep(2000);
		} catch (Exception e) {

		}

		myFilesTestObj.verifyUploadedFile(title, "");

		// Edit smartlink with backslash
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.documentdetails(title);
		docLibPg.commonMethodForClickOnMoreSettingsOption(title, "Edit SmartLink");
		avsmart.editsmarttypedatainduvidual("metrodigiLink", "title", "MetrodigiBackslashUpdated");
		avsmart.editsmarttypedatainduvidual("metrodigiLink", "extURLLink", "https://www.google.com");
		avsmart.editsmarttypedatainduvidual("metrodigiLink", "imgrefpath", "\\t");
		avsmart.editsmarttypedatainduvidual("metrodigiLink", "imgtext", "\\b");
		avsmart.editsmarttypedatainduvidual("metrodigiLink", "caption", "\\f");
		avsmart.editsmarttypedatainduvidual("metrodigiLink", "credit", "\\");
		avsmart.submitbutton("metrodigiLink", "MetrodigiBackslashUpdated");

		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		collectionPg.clickOnMoreSetting("MetrodigiBackslashUpdated");
		collectionPg.commonMethodForClickOnMoreSettingsOption("MetrodigiBackslashUpdated", "Edit SmartLink");
		avsmart.EditSmartlinkFilePathValidation("metrodigiLink", "MetrodigiBackslashUpdated", siteName, fileName);
		avsmart.cancelButtonOnEditSmartlink("metrodigiLink", "MetrodigiBackslashUpdated");

	}

	@Override
	public void tearDown() {

	}
}
