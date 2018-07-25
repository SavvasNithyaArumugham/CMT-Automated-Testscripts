package testscripts.proofhq;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
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
public class AUT_AG_004P2 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void proofhq_04() {
		testParameters.setCurrentTestDescription(
				"Reviewer role should not be able to create a proof via alfresco.<br> Part 2: Verify ProofHQ option in Document Library<br>"
						+ "[1]ALFDEPLOY-3008_Verify reviewer is able to create a proof without getting welcome mail to access the proof.<br>"
						+ "ALFDEPLOY-2969_Verify the Create proof in ProofHQ option is available for ZIP file specific to user having reviewer role for the site in document library");

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

		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoSitesPageTest sitesPageTests = new AlfrescoSitesPageTest(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);

		homePage.navigateToSitesTab();

		String siteName = sitesPage.getCreatedSiteName();

		sitesPage.siteFinder(siteName);

		sitesPage.enterIntoDocumentLibrary();

		String[] fileName = dataTable.getData("MyFiles", "FileName").split(",");
		String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");

		for (String file : fileName) {
			sitesPage.clickOnMoreSetting(file);
			sitesPageTests.verifyMoreSettingsOptionForFileOrFolderItemNegativeCase(file, moreSettingsOption);
		}

		/*
		 * docLibPg.commonMethodForClickOnMoreSettingsOption(fileName,
		 * moreSettingsOption);
		 * 
		 * String recepients= dataTable.getData("MyFiles", "Recepients"); String
		 * policy= dataTable.getData("MyFiles", "ProofHQPolicy"); String
		 * roleName = dataTable.getData("Sites", "Role");
		 * sitesPage.addProofHQNegative(recepients, policy, roleName);
		 */

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}