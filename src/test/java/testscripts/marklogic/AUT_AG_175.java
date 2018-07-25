package testscripts.marklogic;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Naresh Kumar Salla
 */
public class AUT_AG_175 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void runAUT_AG_175() {
		testParameters
				.setCurrentTestDescription("Verify the user who is not a member of 'Metadata Synchronization Controller' group is not able to view the Add to or Remove from Watched content items for Assets folder");
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
		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();
		
		String folderName = dataTable.getData("MyFiles", "Version");
		String moreSetOpt = dataTable.getData("MyFiles", "MoreSettingsOption");
		String[] moreSetOptions = moreSetOpt.split(",");
		String siteNameValue = dataTable.getData("Sites", "SiteName");

		sitesPage.openSiteFromRecentSites(siteNameValue);
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnMoreSetting(folderName);

		if (sitesPage.checkMoreSettingsOption(folderName, moreSetOptions[0])) {
			report.updateTestLog("Verify '" + moreSetOptions[0]
					+ "' aspect for empty folder under 'More' option",
					moreSetOptions[1] + " option is displayed", Status.FAIL);

		} else {
			report.updateTestLog("Verify '" + moreSetOptions[0]
					+ "' aspect for empty folder under 'More' option",
					moreSetOptions[0] + " option is not displayed", Status.PASS);
		}
		UIHelper.pageRefresh(driver);
		sitesPage.clickOnMoreSetting(folderName);
		if (sitesPage.checkMoreSettingsOption(folderName, moreSetOptions[1])) {
			report.updateTestLog("Verify '" + moreSetOptions[1]
					+ "' aspect for empty folder under 'More' option",
					moreSetOptions[1] + " option is displayed", Status.FAIL);

		} else {
			report.updateTestLog("Verify '" + moreSetOptions[1]
					+ "' aspect for empty folder under 'More' option",
					moreSetOptions[1] + " option is not displayed", Status.PASS);
		}

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}