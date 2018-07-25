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
public class AUT_AG_041P4 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void sharebox_036() {
		testParameters
				.setCurrentTestDescription("[1]ALFDEPLOY-5278_Verify the presence of Sharebox filter in the side bar of document library when user is not a member of Sharebox_Users group");
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
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		
		String filter = dataTable.getData("MyFiles", "BrowseActionName");
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String message = dataTable.getData("Sharebox", "Message");
		String date = new DateUtil().getCurrentDate();
		message = message+"_"+date;	
		String folderName = dataTable.getData("MyFiles", "Version");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");
		String notification = dataTable.getData("Sharebox", "Notifications");
		String notifyDetails = dataTable.getData("Sharebox", "Notify_Sharer");
		String nonSharedfolder = dataTable.getData("MyFiles", "CreateFileDetails");
		String properties = dataTable.getData("Sharebox", "ShareboxFieldsData");
		
		ArrayList<String> filterList = new ArrayList<String>();
		
		functionalLibrary.loginAsValidUser(signOnPage);	
		
		sitesPage.siteFinder(sourceSiteName);
		
		sitesPage.enterIntoDocumentLibrary();
		
		tasksPage.filter(filter);
		
		sitesPage.documentdetails(folderName);
	
		if(sitesPage.documentAvailable(fileName)) {
			
			report.updateTestLog("Verify whether you can access files uploaded in ShareBox Filter ",
					"Can access files uploaded  in external Sharebox in ShareBox Filter. <b><br>  File/Folder Name: <b>"+fileName, Status.PASS);
			
		}else {
			report.updateTestLog("Verify whether you can access files uploaded in external Sharebox in ShareBox Filter ",
					"Cannot access files uploaded  in external Sharebox in ShareBox Filter. <b><br>  File/Folder Name: <b>"+fileName, Status.FAIL);
			
		}
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		
		
		
		
		
		
		
		
		
		
		homePage.navigateToMyFilesTab();
		
		UIHelper.findAndAddElementsToaList(driver, tasksPage.filterList, filterList);
		
		if(filterList.contains(filter)) {
			
			report.updateTestLog("Verify ShareBox Filter is displayed in My Files page",
					"ShareBox Filter is not displayed in My Files page as expected. ", Status.PASS);
			
		}else {
			report.updateTestLog("Verify ShareBox Filter is displayed in My Files page",
					"ShareBox Filter is  displayed in My Files page which is not expected. ", Status.FAIL);
		}
		
		filterList.clear();
		
		homePage.navigateToSharedFilesTab();
		
		UIHelper.findAndAddElementsToaList(driver, tasksPage.filterList, filterList);
		
		if(filterList.contains(filter)) {
			
			report.updateTestLog("Verify ShareBox Filter is displayed in Shared files page",
					"ShareBox Filter is not displayed in Shared files page as expected. ", Status.PASS);
			
		}else {
			report.updateTestLog("Verify ShareBox Filter is displayed in Shared files page",
					"ShareBox Filter is  displayed in Shared files page which is not expected. ", Status.FAIL);
		}

		filterList.clear();
		
		homePage.navigateToRepositoryTab();
		
		UIHelper.findAndAddElementsToaList(driver, tasksPage.filterList, filterList);
		
		if(filterList.contains(filter)) {
			
			report.updateTestLog("Verify ShareBox Filter is displayed in Repository  page",
					"ShareBox Filter is not displayed in Repository page as expected. ", Status.PASS);
			
		}else {
			report.updateTestLog("Verify ShareBox Filter is displayed in Repository page",
					"ShareBox Filter is  displayed in Repository page which is not expected. ", Status.FAIL);
		}

		filterList.clear();
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}