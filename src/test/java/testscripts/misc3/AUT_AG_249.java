package testscripts.misc3;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_249 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void misc3() {
		testParameters
				.setCurrentTestDescription("Verify that user cancel checkout will revert to recent version");
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
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		
		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		
		sitesPage.openSiteFromRecentSites(siteNameValue);
		
		sitesPage.enterIntoDocumentLibrary();
		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		//if(!docLibPage.checkFileIsAvailable(fileName)){
			myFiles.deleteUploadedFile(fileName);
			
			myFiles.uploadFileInMyFilesPage(filePath, fileName);
			
			AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
			myFilesTestObj.verifyUploadedFile(fileName,"");
		//}
		docLibPage.openAFile(fileName);
		
		if(!(docLibPage.isFileLocked())){
			docLibPage.lockFile(fileName);
		}
		
		String docActionVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
		
		AlfrescoDocumentDetailsPage docDetailsPg = new AlfrescoDocumentDetailsPage(scriptHelper);
		docDetailsPg.clickCancelEditing();
		/*String uploadedFilesName = docDetailsPg.getDocumentHeaderName();
		System.out.println(""+uploadedFilesName);*/
		
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		
		String versionNo = dataTable.getData("MyFiles", "Version");
		String verNo;
		verNo=docDetailsPg.retFileVersionNo();
		if(verNo.contains(versionNo))
		{
			report.updateTestLog(
					"Verify file version restored to last version",
					"Correct version number is displayed for file after cancel Editing ",
					Status.PASS);
			
		}
		
		else
		{
			report.updateTestLog("Verify file version restored to last version",
					"Correct version number is NOT displayed for file after cancel Editing ",
					Status.FAIL);
			
		}
		
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.openAFile(fileName);
		
		if(!(docLibPage.isFileLocked())){
			docLibPage.lockFile(fileName);
		}
		
		
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}
