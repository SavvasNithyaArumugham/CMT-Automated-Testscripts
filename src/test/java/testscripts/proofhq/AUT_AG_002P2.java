package testscripts.proofhq;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMySitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoProofHQPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_002P2 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void proofhq_02() {
		testParameters.setCurrentTestDescription(
				"Coordinator role should be able to create a proof via alfresco.<br> Part 2: Create ProofHQ in Document Library<br>"
						+ "ALFDEPLOY-2969_Verify the Create proof in ProofHQ option is available for HTML file in document preview<br>"
						+ "ALFDEPLOY-2969_Verify the Create proof in ProofHQ option is available for XML file in document preview<br>"
						+ "ALFDEPLOY-2969_Verify the Create proof in ProofHQ option is available for XML file in document library<br>"
						+ "ALFDEPLOY-2969_Verify the Create proof in ProofHQ option is available for ZIP file in document library");

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
		AlfrescoMySitesPage mySites = new AlfrescoMySitesPage(scriptHelper);
		String actualHome = dataTable.getData("Home", "Status");
		String submenu = dataTable.getData("Home", "DashletName");
		String email = dataTable.getData("Home", "ColumnNoofAddDashlet");
		String token = dataTable.getData("Home", "ColumnNoofremvDashlet");

		homePageObj.UserMenucommonMethod(actualHome);

		mySites.navigatetoconfigMenu(submenu);

		AlfrescoProofHQPage proofHQObj = new AlfrescoProofHQPage(scriptHelper);

		proofHQObj.configProofHQ(email, token);

		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoSitesPageTest sitesPageTests = new AlfrescoSitesPageTest(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPg = new AlfrescoDocumentDetailsPage(scriptHelper);

		String siteName = sitesPage.getCreatedSiteName();
		sitesPage.siteFinder(siteName);
		sitesPage.enterIntoDocumentLibrary();

		String[] fileName = dataTable.getData("MyFiles", "FileName").split(",");
		String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");

		sitesPage.clickOnMoreSetting(fileName[0]);

		sitesPageTests.verifyMoreSettingsOptionForFileOrFolderItem(fileName[0], moreSettingsOption);

		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName[0], moreSettingsOption);

		String recepients = dataTable.getData("MyFiles", "Recepients");
		String policy = dataTable.getData("MyFiles", "ProofHQPolicy");
		sitesPage.addProofHQ(recepients, policy, fileName[0]);

		sitesPage.documentdetails(fileName[1]);
		if (!docDetailsPg.checkDocumentActionName(moreSettingsOption)) {
			report.updateTestLog(
					"Verify the Create proof in ProofHQ option is available for HTML file in document preview",
					"Create proof in ProofHQ option is not available for HTML file in document preview", Status.PASS);
		} else {
			report.updateTestLog(
					"Verify the Create proof in ProofHQ option is available for HTML file in document preview",
					"Create proof in ProofHQ option is available for HTML file in document preview", Status.FAIL);
		}
		
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.documentdetails(fileName[2]);
		if (docDetailsPg.checkDocumentActionName(moreSettingsOption)) {
			report.updateTestLog(
					"Verify the Create proof in ProofHQ option is available for XML file in document preview",
					"Create proof in ProofHQ option is available for XML file in document preview", Status.PASS);
		} else {
			report.updateTestLog(
					"Verify the Create proof in ProofHQ option is available for XML file in document preview",
					"Create proof in ProofHQ option is not available for XML file in document preview", Status.FAIL);
		}

		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnMoreSetting(fileName[2]);
		sitesPageTests.verifyMoreSettingsOptionForFileOrFolderItem(fileName[2], moreSettingsOption);

		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName[2], moreSettingsOption);
		sitesPage.addProofHQ(recepients, policy, fileName[2]);

		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnMoreSetting(fileName[3]);
		sitesPageTests.verifyMoreSettingsOptionForFileOrFolderItemNegativeCase(fileName[3], moreSettingsOption);

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}