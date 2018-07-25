package testscripts.objectmodel;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_009P1 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void objectmodel_009() {
		testParameters
				.setCurrentTestDescription("Verfiy user with roles (Admin, Manager, Collaborator) can add aspects to folder/file - Part 1 - Admin Role");
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
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);
		homePage.navigateToSitesTab();

		String siteNameValue = dataTable.getData("Sites", "SiteName");
		sitesPage.createSite(siteNameValue, "Yes");

		/*AlfrescoSitesPageTest siteTestObj = new AlfrescoSitesPageTest(
				scriptHelper);
		homePage.navigateToSitesTab();
		siteTestObj.verifyCreatedSite();

		homePage.navigateToSitesTab();*/

		sitesPage.inviteUserForSite();

		sitesPage.enterIntoDocumentLibrary();

		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");

		myFiles.createFolder(folderDetails);

		ArrayList<String> folderNamesList = myFiles
				.getFolderNames(folderDetails);

		for (String folderName : folderNamesList) {
			System.out.println("FOLDEER NAME LIST SIZE : "
					+ folderNamesList.size());

			sitesPage.clickOnMoreSetting(folderName);

			sitesPage.clickOnMoreOptionLink(folderName);

			docDetailsPage.addAspectsAndApllyChangesToAFile();

			sitesPage.clickOnEditProperties(folderName);
			docDetailsPage.clickAllProperties();
			
			docDetailsPageTest.verifyAllEditProperties();
		}
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}
