package testscripts.collections9;

import org.testng.annotations.Test;

import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_1706_TC003 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_049() {
		testParameters
				.setCurrentTestDescription("1.Confirm \"Collections Job Queue\" dashlet appears on user's homepage"+
		"Check Import and Generate Realize Csv job Status in \"Collections Job Queue\" dashlet ");
		
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
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		String	csvFileName[] = dataTable.getData("MyFiles", "Sort Options").split(",");
		String  jobType[]= dataTable.getData("MyFiles", "StatusReportValue").split(",");
		String userName = dataTable.getData("MyFiles", "AccessToken");
		String reqSiteName = dataTable.getData("MyFiles", "PopUpMsg");
		String serverName = dataTable.getData("MyFiles", "FolderDetailsForBreadCumb");
		String progress[]  = dataTable.getData("MyFiles", "SelectDropdownValue").split(",");
		//  login
		functionalLibrary.loginAsValidUser(signOnPage);
				
		// goto site > document lib
		homePageObj.navigateToSitesTab();
				sitesPage.siteFinder("jobqueueuichecksite15");
				sitesPage.enterIntoDocumentLibrary();
																		
				// Generate Realize Csv
				sitesPage.enterIntoDocumentLibrary();
				myFiles.openCreatedFolder(folderNames[0]);
				myFiles.openCreatedFolder(folderNames[1]);
				collectionPg.clickOnEditCollectionButton();
				UIHelper.waitForPageToLoad(driver);			
				
				collectionPg.clickOnMoreSetting("AutolinkTest SO1");
				UIHelper.waitFor(driver);	
				collectionPg.commonMethodForClickOnMoreSettingsOption("AutolinkTest SO1", "Duplicate all");
							
				homePageObj.navigateToHomePage();
				UIHelper.waitFor(driver);
				collectionPg.isDuplicateAllStatusDisplayed(csvFileName[0],userName,reqSiteName,jobType[0],serverName,progress[0]);
				UIHelper.pageRefresh(driver);
				collectionPg.isDuplicateAllStatusDisplayed(csvFileName[0],userName,reqSiteName,jobType[0],serverName,progress[0]);
				UIHelper.waitFor(driver);
				//UIHelper.waitFor(driver);
						
	}
	
	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
