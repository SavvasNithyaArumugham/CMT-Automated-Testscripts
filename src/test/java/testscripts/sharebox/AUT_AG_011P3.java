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
public class AUT_AG_011P3 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void sharebox_011() {
		testParameters
				.setCurrentTestDescription("Verify the internal user is able to view the upload details on the file in Alfresco, if the file is uploaded by the external user (Eg: External upload by: test@test.com 15 days)");
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
		
		if(docLibPg.isFileIsAvailable(fileName)){
			report.updateTestLog("Verify the External uploaded files in share folder",
					"File verified successfully"
					+ "<br /><b> File Name : </b>" + fileName, Status.PASS);
		}else{
			report.updateTestLog("Verify the External uploaded files in share folder",
					"File not available"
					+ "<br /><b> File Name : </b>" + fileName, Status.FAIL);
		}
		
		if(docLibPg.isExternalUploadTagAvailable("External upload by")){
			report.updateTestLog("Verify the External upload Tag is available",
					"Tag verified successfully", Status.PASS);
		}else{
			report.updateTestLog("Verify the External upload Tag is available",
					"Tag not verified", Status.FAIL);
		}
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}