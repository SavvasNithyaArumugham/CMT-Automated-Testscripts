package testscripts.pcs;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoPCSPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoPCSPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
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
public class AUT_AG_33 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void pcs_26() {
		testParameters
				.setCurrentTestDescription("1. Verify whether the files and folders created deep inside the folders in the document library of the archive site is displayed in the 'Database Search - PCS' dash-let of the archive review site dashboard"
						+ "<br>2. Verify whether user is able view the breadcrumbs in PCS dashlet when moved deep inside the parent folder");

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
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoPCSPage pcsObj = new AlfrescoPCSPage(scriptHelper);	
		AlfrescoPCSPageTest pcsTest = new AlfrescoPCSPageTest(
				scriptHelper);	
		
	
		String rootFolderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folder1Details = dataTable.getData("MyFiles", "FileName");
		String folder2Details = dataTable.getData("MyFiles", "FilePath");
		String siteassertValue = dataTable.getData("Sites", "SiteName");
		String rootfolder = dataTable.getData("Tasks", "TaskName");
		String folder1 = dataTable.getData("Tasks", "AttachedFileTxtVal");
		String folder2 = dataTable.getData("Tasks", "ReviewerComment");
		
			
		sitesPage.siteFinder(siteassertValue);
		sitesPage.enterIntoDocumentLibrary();
		docLibPge.deleteAllFilesAndFolders();
		myFiles.createFolder(rootFolderDetails);
		sitesPage.documentdetails(rootfolder);
		myFiles.createFolder(folder1Details);
		sitesPage.documentdetails(folder1);
		myFiles.createFolder(folder2Details);
		sitesPage.siteFinder(siteassertValue);
		pcsObj.openFoldersInPCSDashlet(rootfolder,folder1);
		pcsObj.openFoldersInPCSDashlet(folder1, folder2);
		pcsTest.verifyFileFldrDisp(folder2);	
		
		pcsTest.verifyPCSBreadcrumb(rootfolder);
		
		

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}