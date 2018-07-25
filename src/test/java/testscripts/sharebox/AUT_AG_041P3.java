package testscripts.sharebox;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DateUtil;
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
public class AUT_AG_041P3 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void sharebox_036() {
		testParameters
				.setCurrentTestDescription("[1]ALFDEPLOY-5278_Verify the presence of Share Box filter in the sidebar of my files and check the shared folders listed in share box filter<br>"+
		"[1]ALFDEPLOY-5278_Verify the presence of Share Box filter in the sidebar of shared files and check the shared folders listed in share box filter<br>"+
						"[1]ALFDEPLOY-5278_Verify the Share Box folder is hidden in the sidebar of Repository browser<br>"+
		"[1]ALFDEPLOY-5278_Verify the files or folders uploaded via external sharebox is reflected in Share Box filter present in the sidebar of document library");
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
		AlfrescoTasksPage tasksPage = new AlfrescoTasksPage(scriptHelper);
		
		String filter = dataTable.getData("MyFiles", "BrowseActionName");
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		
		ArrayList<String> filterList = new ArrayList<String>();
		
		functionalLibrary.loginAsValidUser(signOnPage);	
		
		sitesPage.siteFinder(sourceSiteName);
		
		sitesPage.enterIntoDocumentLibrary();
		
		UIHelper.findAndAddElementsToaList(driver, tasksPage.filterList, filterList);
		
		if(filterList.contains(filter)) {
			
			report.updateTestLog("Verify ShareBox Filter is displayed in My Files page",
					"ShareBox Filter is not displayed in My Files page as expected. ", Status.PASS);
			
		}else {
			report.updateTestLog("Verify ShareBox Filter is displayed in My Files page",
					"ShareBox Filter is  displayed in My Files page which is not expected. ", Status.FAIL);
		}
		
		filterList.clear();

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}