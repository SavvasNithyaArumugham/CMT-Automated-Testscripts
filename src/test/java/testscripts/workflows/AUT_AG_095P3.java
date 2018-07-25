package testscripts.workflows;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_095P3 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void workflows_64() {
		testParameters
				.setCurrentTestDescription("1.Verify reviewer can able to view the lower level folder and file inside."
						+ "<br>2.verify the user should able to download the file.");
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

		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPg = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoMyTasksPage myTaskPage = new AlfrescoMyTasksPage(scriptHelper);
		String fileName = dataTable.getData("MyFiles", "FileName");
		String folderTitle = dataTable.getData("MyFiles", "Version");
		String Message = dataTable.getData("Workflow", "Message");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = sdf.format(date);
		String Msg = Message + "_" + strDate;

		homePage.navigateToMyTasks();
		myTaskPage.selectHighPriorityTaskMenu();
		myTaskPage.selectTaskFromActiveTasksList(Msg);
		myTaskPage.clickOnWFFolderLink(folderTitle);
		docDetailsPg.clickOnBreadCrumbLink(folderTitle);

		sitesPage.commonMethodForPerformBrowseOption(fileName, "Download");
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		AlfrescoDocumentDetailsPageTest docDetailsTestPage = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		docDetailsTestPage.verifyDownloadedFile(false, null);

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}