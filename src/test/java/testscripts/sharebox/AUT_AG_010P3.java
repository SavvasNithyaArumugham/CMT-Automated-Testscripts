package testscripts.sharebox;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Naresh Kuamr Salla
 *
 */
public class AUT_AG_010P3 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void sharebox_010() {
		testParameters
				.setCurrentTestDescription("Verify the external user is able to upload multiple files into the Shared Folder");
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
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);

		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String folderName = dataTable.getData("MyFiles", "CreateFolder");
		String fileName = dataTable.getData("MyFiles", "FileName");
		
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.openFolder(folderName);
		
		String[] fileNames = fileName.split(",");
		if(docLibPg.isFileIsAvailable(fileNames[0])){
			report.updateTestLog("Verify the External uploaded files in share folder",
					"File verified successfully"
					+ "<br /><b> File Name : </b>" + fileNames[0], Status.PASS);
		}else{
			report.updateTestLog("Verify the External uploaded files in share folder",
					"File not available"
					+ "<br /><b> File Name : </b>" + fileNames[0], Status.FAIL);
		}
		
		if(docLibPg.isFileIsAvailable(fileNames[1])){
			report.updateTestLog("Verify the External uploaded files in share folder",
					"File verified successfully"
					+ "<br /><b> File Name : </b>" + fileNames[1], Status.PASS);
		}else{
			report.updateTestLog("Verify the External uploaded files in share folder",
					"File not available"
					+ "<br /><b> File Name : </b>" + fileNames[1], Status.FAIL);
		}
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}