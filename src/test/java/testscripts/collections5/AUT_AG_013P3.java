package testscripts.collections5;

import org.testng.annotations.Test;

import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_013P3 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_52() {
		testParameters
		.setCurrentTestDescription("1. ALFDEPLOY-3521_Verify that user can upload content to data import folder where user have Collaborator permission for that folder but not a member of the collection site<br>"
				+"2. ALFDEPLOY-3521_Verify that user can download content from data import folder where user have manager permission for that folder but not a member of the collection site<br>"
				+"3. ALFDEPLOY-3521_Verify that user can download content from data import folder where user have Collaborator permission for that folder but not a member of the collection site<br>"
				+"4. ALFDEPLOY-3521_Verify that user cannot download others content from data import folder where user have Reviewer permission for that folder but not a member of the collection site<br>"
				+"Part 3 :  Manager can upload content to folder");
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

	String folderName = dataTable.getData("MyFiles", "Version");
	String fileName = dataTable.getData("MyFiles", "FileName");
	
	// login
	functionalLibrary.loginAsValidUser(signOnPage);

	// goto site > document lib
	String siteName = sitesPage.getCreatedSiteName();
	sitesPage.siteFinder(siteName);
	sitesPage.enterIntoDocumentLibrary();
	sitesPage.documentdetailsColl("Data Import");
	sitesPage.documentdetailsColl(folderName);
	AlfrescoSitesPageTest sitePageTest = new AlfrescoSitesPageTest(scriptHelper);
	sitePageTest.verifyBrowseActionNameForFileOrFolderInNegativeCase("Download", fileName);
		
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}