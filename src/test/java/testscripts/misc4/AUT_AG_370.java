package testscripts.misc4;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Moshin
 */
public class AUT_AG_370 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void misc4_062() {
		testParameters
				.setCurrentTestDescription("Verify that user is able to Edit property of WebResource");
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

		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoDocumentLibPageTest docLibPageTest = new AlfrescoDocumentLibPageTest(
				scriptHelper);
	
		homePageObj.navigateToMyFilesTab();

		String webName = dataTable.getData("Parametrized_Checkpoints",
				"FileName");
		String webURL = dataTable.getData("Parametrized_Checkpoints",
				"Help URL");
		String type = dataTable.getData("Parametrized_Checkpoints", "FilePath");
		String webURLEdit = dataTable.getData("Parametrized_Checkpoints",
				"fileIcon");

		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		if (sitesPage.Checkdocument(webName)) {
			myFiles.deleteUploadedFile(webName);
		}
		myFiles.createcontenttype(type);
		myFiles.createWebResource(webName, webURL);
		homePageObj.navigateToMyFilesTab();
		sitesPage.clickOnEditProperties(webName);
		myFiles.editWebResource(webName, webURLEdit);
		sitesPage.documentdetails(webName);	
		ArrayList<String> tab = new ArrayList<String>(
				driver.getWindowHandles());
		try {
		driver.switchTo().window(tab.get(1));
		}catch(Exception e) {
			
		}
		if (driver.getCurrentUrl().contains(webURLEdit)) {

			report.updateTestLog("Verify the current URL",
					"Current URL matches with the edited URL"
							+ "<br/><b>URL :</b>" + driver.getCurrentUrl(),
					Status.PASS);

		} else {
			report.updateTestLog("Verify the current URL",
					"Current URL mismatches with the edited URL"
							+ "<br/><b>URL :</b>" + driver.getCurrentUrl(),
					Status.FAIL);
		}

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}

}