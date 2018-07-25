package testscripts.avsmartlink;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAVSmartLinkPage;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_045P1 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVSMARTLINK_008() {
		testParameters.setCurrentTestDescription(
				
				"Verify Co-ordinator/Collaborator/Contributor is getting Edit smartlink  option displayed for PDF smartlink object which is created by manager and manager is able to edit"
					
				);
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

		try {

			AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
			AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
			AlfrescoAVSmartLinkPage avsmart = new AlfrescoAVSmartLinkPage(
					scriptHelper);
			AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
			AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
					scriptHelper);
			AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
					scriptHelper);
			AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
			AlfrescoSiteMembersPageTest siteMemPgTest = new AlfrescoSiteMembersPageTest(
					scriptHelper);
			AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);

			String filePath = dataTable.getData("MyFiles", "FilePath");
			String fileName = dataTable.getData("MyFiles", "FileName");
			String editSL = dataTable.getData("MyFiles", "MoreSettingsOption");
			String siteName = dataTable.getData("Sites", "SiteName");
			String edittitle = dataTable.getData("Parametrized_Checkpoints",
					"FileName");
			String type = dataTable.getData("Home", "DashletName");
			String title = dataTable.getData("Document_Details", "FilePath");
			String extURLLink = dataTable.getData("Document_Details",
					"FileName");
			String Type = dataTable.getData("MyFiles", "Version");
			String Name = dataTable.getData("MyFiles", "CreateFolder");
			String siteuserName = dataTable.getData("Sites", "InviteUserName");
			String roleName = dataTable.getData("Sites", "Role");

			functionalLibrary.loginAsValidUser(signOnPage);
			UIHelper.waitFor(driver);
			
	         homePageObj.navigateToSitesTab();
			sitesPage.createSite(siteName, "Yes");
			String site=sitesPage.getCreatedSiteName();
           	sitesPage.performInviteUserToSite(site);
            siteMemPgTest.verifySiteMemebrs(site, siteuserName, roleName);


			sitesPage.enterIntoDocumentLibrary();
			UIHelper.waitFor(driver);

			myFiles.createcontenttype(type);
			avsmart.clickSmartLinkType(Type, Name);
			avsmart.entersmarttypedata(Name, title, extURLLink, "Image",
					"Caption", "credit", "", System.getProperty("user.dir")+ filePath + fileName);
	
			avsmart.submitbutton(Name, title);
			sitesPage.clickOnMoreSetting(title);
			docLibPg
					.commonMethodForClickOnMoreSettingsOption(title, editSL);
			avsmart.entersmarttypedata(Name, edittitle, extURLLink, "Images",
					"Captions", "credits", "", System.getProperty("user.dir")+ filePath + fileName);
				
			avsmart.submitbutton(Name, edittitle);

			myFilesTestObj.verifyUploadedFile(edittitle, "");

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("AUT_AG_030 status:",
					"<br>AUT_AG_024 Testcase is Failed", Status.DONE);

		}

	}

	@Override
	public void tearDown() {

	}
}
