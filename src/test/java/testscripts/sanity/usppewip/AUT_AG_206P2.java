package testscripts.sanity.usppewip;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_206P2 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void MISC2_040() {
		testParameters.setCurrentTestDescription(
				"To verify ,for user who is not in site owner group,Sharebox user group, Repository Manager group is not getting below options under More options"
						+ "<br>Share Folder Externally" + "<br>Copy to Repository"

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
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String folder = dataTable.getData("MyFiles", "CreateFolder");
		String moreSettingsOption[] = dataTable.getData("MyFiles", "MoreSettingsOption").split(",");

		homePage.navigateToSitesTab();
		sitesPage.siteFinder(siteNameValue);
		sitesPage.enterIntoDocumentLibrary();

		for (String moreOptions : moreSettingsOption) {
			sitesPage.verifyMoreSettingsOptionForFileOrFolderItemNegativeCase(folder, moreOptions);
		}
	}

	@Override
	public void tearDown() {

	}

}
