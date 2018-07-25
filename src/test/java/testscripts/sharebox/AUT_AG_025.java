package testscripts.sharebox;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_025 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void sharebox_025() {
		testParameters
				.setCurrentTestDescription("Verify users removed from the 'Sharebox_Users' Groups cannotable to see the 'Share Folder External' option when hovering the mouse on Folder name via More options.");
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
	
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
	

		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		
		String moreSettingsOpt = dataTable.getData("MyFiles",
				"MoreSettingsOption");
		String folderName = dataTable.getData("MyFiles", "Version");
		
		functionalLibrary.loginAsValidUser(signOnPage);
	
		//String site=sitesPage.getCreatedSiteName();
       	sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();


			sitesPage.clickOnMoreSetting(folderName);

			if (!docLibPg.commonMethodToCheckMoreSettingsOption(folderName,
					moreSettingsOpt)) {
				report.updateTestLog("Verify " + moreSettingsOpt
						+ " is available", moreSettingsOpt
						+ " is not available for user not in Sharebox group", Status.PASS);

			} else {

				report.updateTestLog("Verify " + moreSettingsOpt
						+ " is available", moreSettingsOpt
						+ " is  available for user not in Sharebox group", Status.FAIL);
			}
		

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}