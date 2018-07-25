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

public class AUT_AG_031P4 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AV_002() {
		testParameters.setCurrentTestDescription("1.Verify Coordinator is able to create 'MD Pop up' smart link object in document library"
				+"2. Verify the navigation of user to document library upon clicking 'Cancel' button during 'MD Pop up' smart link object creation"
				+"3. Verify user is able to perform document actions such as delete, manage aspects, manage permissions for 'MD Pop up' smart link object");
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

		AlfrescoAVSmartLinkPage avsmart = new AlfrescoAVSmartLinkPage(
				scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);

		String fileName = dataTable.getData("MyFiles", "FileName");

		

		String filePath = dataTable.getData("MyFiles", "FilePath");

		String editSL = dataTable.getData("MyFiles", "MoreSettingsOption");

		

		String extURLLink = dataTable.getData("Document_Details", "FileName");

	

		String data = dataTable.getData("MyFiles", "CreateFileDetails");

		functionalLibrary.loginAsValidUser(signOnPage);

		String siteName = sitesPage.getCreatedSiteName();
		sitesPage.siteFinder(siteName);

		sitesPage.enterIntoDocumentLibrary();

		if (data.contains(";")) {
			String splittedFileDetails[] = data.split(";");
			for (String nxtval : splittedFileDetails) {
				String splittedsmartDetails[] = nxtval.split(",");

				String smartfolder = splittedsmartDetails[0];
				String smartfile2 = splittedsmartDetails[1];
				String editsmart = splittedsmartDetails[2];
				String smarttype = splittedsmartDetails[3];
				
				sitesPage.enterIntoDocumentLibrary();
			
				sitesPage.documentdetails(smartfolder);

				sitesPage.clickOnMoreSetting(smartfile2);
				docLibPg.commonMethodForClickOnMoreSettingsOption(smartfile2,
						editSL);
				avsmart.entersmarttypedata(smarttype, editsmart, extURLLink,
						"Images", "", "", "", System.getProperty("user.dir")
								+ filePath + fileName);
				avsmart.submitbutton(smarttype, editsmart);
				sitesPage.documentAvailable(editsmart);
				//myFilesTestObj.verifyUploadedFile(editsmart, "");
				
				

			}
		}

		

	}
		

	@Override
	public void tearDown() {

	}
}
